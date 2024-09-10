package apw.risingos.settings.clone.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import apw.risingos.settings.clone.Activities.AboutActivity;
import apw.risingos.settings.clone.Activities.AdditionalActivity;
import apw.risingos.settings.clone.Activities.AppsActivity;
import apw.risingos.settings.clone.Activities.BatteryActivity;
import apw.risingos.settings.clone.Activities.MainActivity;
import apw.risingos.settings.clone.Activities.PersonalisationActivity;
import apw.risingos.settings.clone.Activities.ThemesActivity;
import apw.risingos.settings.clone.Activities.WallpaperActivity;
import apw.risingos.settings.clone.Utils.AboutUtils;
import apw.risingos.settings.clone.Utils.TransitionDatabase;
import apw.risingos.settings.clone.Utils.UserDatabase;
import apw.risingos.settings.clone.databinding.ActivityMainBinding;
import apw.risingos.settings.clone.R;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public boolean state;
    public TransitionDatabase openHelper;
    public UserDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        openHelper = new TransitionDatabase(this);
        state = openHelper.getSwitchState();
        this.registerReceiver(this.mBroad, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = this.registerReceiver(null, ifilter);
        Intent batteryTechnology = this.registerReceiver(null, ifilter);
        int status =
                batteryStatus != null
                        ? batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                        : -1;
        boolean isCharging =
                status == BatteryManager.BATTERY_STATUS_CHARGING
                        || status == BatteryManager.BATTERY_STATUS_FULL;
        if (isCharging) {
            binding.textper.setText("charging");
        } else {
            binding.textper.setText("discharging");
        }
        db = new UserDatabase(this);
        String name = db.getUserName();
        try {
            if (name == null) {
                binding.welcome.setText("Hey, " + "User" + " Welcome Back.");
            } else {
                binding.welcome.setText("Hey, " + name + " Welcome Back.");
            }
        } catch (Exception err) {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
        }
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long bytesAvailable;
        bytesAvailable = stat.getBlockSizeLong() * stat.getAvailableBlocksLong();
        long megAvailable = bytesAvailable / (1024 * 1024 * 1024);
        int value = (int) megAvailable;
        binding.progressbar2.setProgress(value);
        onClick(binding.search, new Intent(Settings.ACTION_APP_SEARCH_SETTINGS));
        onClick(binding.search2, new Intent(Settings.ACTION_APP_SEARCH_SETTINGS));
        onClick(binding.network, new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS));
        onClick(binding.Bluetooth, new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
        onClick(binding.home, new Intent(Settings.ACTION_HOME_SETTINGS));
        onClick(binding.display, new Intent(Settings.ACTION_DISPLAY_SETTINGS));
        onClick(binding.sound, new Intent(Settings.ACTION_SOUND_SETTINGS));
        onClick(binding.notify, new Intent(Settings.ACTION_ALL_APPS_NOTIFICATION_SETTINGS));
        onClick(binding.storage, new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS));
        onClick(binding.security, new Intent(Settings.ACTION_SECURITY_SETTINGS));
        onClick(binding.privacy, new Intent(Settings.ACTION_PRIVACY_SETTINGS));
        onClick(binding.location, new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        onClick(binding.account, new Intent(Settings.ACTION_SYNC_SETTINGS));
        onClick(binding.battery, new Intent(this, BatteryActivity.class));
        onClick(binding.tunesystem, new Intent(this, WallpaperActivity.class));
        onClick(binding.personalizations, new Intent(this, PersonalisationActivity.class));
        onClick(binding.apps, new Intent(this, AppsActivity.class));
        onClick(binding.addition, new Intent(this, AdditionalActivity.class));
        onClick(binding.about, new Intent(this, AboutActivity.class));
        onClick(binding.aboutphn, new Intent(this, AboutActivity.class));
        binding.wellbeing.setOnClickListener(
                v -> {
                    Intent wellb = new Intent();
                    wellb.setClassName(
                            "com.google.android.apps.wellbeing",
                            "com.google.android.apps.wellbeing.settings.TopLevelSettingsActivity");
                    startActivity(wellb);
                });
        binding.apw.setText(Build.MANUFACTURER + " " + AboutUtils.getModelName());
    }

    private BroadcastReceiver mBroad =
            new BroadcastReceiver() {
                @Override
                public void onReceive(Context arg0, Intent arg1) {
                    int level = arg1.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                    int scale = arg1.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                    int percentage = level * 100 / (int) scale;
                    try {
                        binding.progressbar.setProgress(percentage);
                        binding.pre.setText(String.valueOf(percentage) + " " + "Percent");
                    } catch (Exception err) {
                        Log.e("An error occured", "An error occured");
                    }
                }
            };

    public void launchIntent(Intent intent, boolean state) {
        try {
            if (state) {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            } else {
                startActivity(intent);
            }
        } catch (Exception err) {
            Toast.makeText(getApplicationContext(), "An error occured", Toast.LENGTH_LONG).show();
        }
    }

    public void onClick(LinearLayout input, Intent intent) {
        input.setOnClickListener(
                v -> {
                    launchIntent(intent, state);
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
