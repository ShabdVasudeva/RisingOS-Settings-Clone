package apw.risingos.settings.clone.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import apw.risingos.settings.clone.R;

public class FragmentPermission extends PreferenceFragmentCompat {
    private static final String modify = Settings.ACTION_MANAGE_WRITE_SETTINGS;
    private static final String usage = Settings.ACTION_USAGE_ACCESS_SETTINGS;
    private static final String files = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION;
    private static final String unknown = Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES;
    private static final String display = Settings.ACTION_MANAGE_OVERLAY_PERMISSION;
    private static final String notification = Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS;

    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
        setPreferencesFromResource(R.xml.permissions, arg1);
        Preference pref1 = findPreference("modify_system");
        Preference pref2 = findPreference("usage_access");
        Preference pref3 = findPreference("files_access");
        Preference pref4 = findPreference("unknown_sources");
        Preference pref5 = findPreference("display_other");
        Preference pref6 = findPreference("device_notification");
        pref1.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            startActivity(new Intent(modify));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "an error occured",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                        return true;
                    }
                });
        pref2.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            startActivity(new Intent(usage));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "an error occured",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                        return true;
                    }
                });
        pref3.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            startActivity(new Intent(files));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "an error occured",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                        return true;
                    }
                });
        pref4.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            startActivity(new Intent(unknown));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "an error occured",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                        return true;
                    }
                });
        pref5.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            startActivity(new Intent(display));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "an error occured",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                        return true;
                    }
                });
        pref6.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            startActivity(new Intent(notification));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "an error occured",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                        return true;
                    }
                });
    }
}
