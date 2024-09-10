package apw.risingos.settings.clone.fragments;

import android.Manifest;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;
import java.util.List;
import apw.risingos.settings.clone.R;

public class FragmentSaver extends PreferenceFragmentCompat {
    private static int previousBrightness;
    private final ActivityResultLauncher<String> activityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.RequestPermission(),
                    new ActivityResultCallback<Boolean>() {
                        @Override
                        public void onActivityResult(Boolean o) {
                            if (o) {
                                Toast.makeText(
                                                getActivity().getApplicationContext(),
                                                "Post notification permission granted",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            } else {
                                Toast.makeText(
                                                getActivity().getApplicationContext(),
                                                "Post notification permission not granted",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                    });

    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
        setPreferencesFromResource(R.xml.battery_saver, arg1);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getActivity().getApplicationContext(), "test")
                        .setSmallIcon(R.drawable.about)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText("You've just turned on the battery saver.")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager =
                getActivity().getSystemService(NotificationManager.class);
        try {
            previousBrightness =
                    Settings.System.getInt(
                            getActivity().getApplicationContext().getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception err) {
            Toast.makeText(
                            getActivity().getApplicationContext(),
                            "An error occured",
                            Toast.LENGTH_LONG)
                    .show();
        }
        SwitchPreferenceCompat pref1 = findPreference("battery_saver");
        pref1.setIconSpaceReserved(false);
        pref1.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference arg0, Object arg1) {
                        Boolean isChecked = (Boolean) arg1;
                        if (isChecked) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                                    && ActivityCompat.checkSelfPermission(
                                                    getActivity().getApplicationContext(),
                                                    Manifest.permission.POST_NOTIFICATIONS)
                                            != PackageManager.PERMISSION_GRANTED) {
                                activityResultLauncher.launch(
                                        Manifest.permission.POST_NOTIFICATIONS);
                            } else {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    CharSequence name = getString(R.string.app_name);
                                    String description = "Example Notification";
                                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                                    NotificationChannel channel =
                                            new NotificationChannel("test", name, importance);
                                    channel.setDescription(description);
                                    notificationManager.createNotificationChannel(channel);

                                    notificationManager.notify(10, builder.build());
                                }
                            }
                            try {
                                Settings.System.putInt(
                                        getActivity().getContentResolver(),
                                        Settings.System.SCREEN_BRIGHTNESS,
                                        1);
                                ActivityManager activityManager =
                                        (ActivityManager)
                                                getActivity()
                                                        .getApplicationContext()
                                                        .getSystemService(Context.ACTIVITY_SERVICE);
                                List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfo =
                                        activityManager.getRunningAppProcesses();
                                for (ActivityManager.RunningAppProcessInfo processInfo :
                                        runningAppProcessInfo) {
                                    if (!processInfo.processName.equals(
                                            getActivity()
                                                    .getApplicationContext()
                                                    .getPackageName())) {
                                        activityManager.killBackgroundProcesses(
                                                processInfo.processName);
                                    }
                                }
                            } catch (Exception err) {
                                Toast.makeText(
                                                getActivity().getApplicationContext(),
                                                "Please give modify system settings permission first.",
                                                Toast.LENGTH_LONG)
                                        .show();
                                isChecked = false;
                            }
                        } else {
                            try {
                                Settings.System.putInt(
                                        getActivity().getApplicationContext().getContentResolver(),
                                        Settings.System.SCREEN_BRIGHTNESS,
                                        previousBrightness);
                            } catch (Exception err) {
                                Toast.makeText(
                                                getActivity().getApplicationContext(),
                                                "Please give modify system settings permission first.",
                                                Toast.LENGTH_LONG)
                                        .show();
                                isChecked = false;
                            }
                        }
                        return true;
                    }
                });
    }
}
