package apw.risingos.settings.clone.fragments;

import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;
import java.util.stream.Stream;
import apw.risingos.settings.clone.R;

public class SettingsPreference extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
        setPreferencesFromResource(R.xml.preferences, arg1);
        SwitchPreferenceCompat timing = findPreference("time_display");
        timing.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference arg0, Object arg1) {
                        Boolean isChecked = (Boolean) arg1;
                        if (isChecked) {
                            Settings.System.putString(
                                    getActivity().getContentResolver(),
                                    Settings.System.TIME_12_24,
                                    "24");
                        } else {
                            Settings.System.putString(
                                    getActivity().getContentResolver(),
                                    Settings.System.TIME_12_24,
                                    "12");
                        }
                        return true;
                    }
                });
        timing.setIconSpaceReserved(false);
        SwitchPreferenceCompat switchPref2 = findPreference("clock_seconds");
        switchPref2.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference arg0, Object arg1) {
                        Boolean isChecked = (Boolean) arg1;
                        if (isChecked) {
                            try {
                                Settings.Secure.putInt(
                                        getActivity().getContentResolver(), "clock_seconds", 1);
                            } catch (Exception err) {
                                Toast.makeText(
                                                getActivity().getApplicationContext(),
                                                "An error occured",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        } else {
                            try {
                                Settings.Secure.putInt(
                                        getActivity().getContentResolver(), "clock_seconds", 0);
                            } catch (Exception err) {
                                Toast.makeText(
                                                getActivity().getApplicationContext(),
                                                "An error occured",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                        return true;
                    }
                });
        switchPref2.setIconSpaceReserved(false);
        SwitchPreferenceCompat switchPref3 = findPreference("center_clock");
        switchPref3.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference arg0, Object arg1) {
                        Boolean isChecked = (Boolean) arg1;
                        if (isChecked) {
                            try {
                                Settings.Secure.putInt(
                                        getActivity().getContentResolver(),
                                        "android.settings.extra.battery_saver_mode_enabled",
                                        0);
                            } catch (Exception err) {
                                Toast.makeText(
                                                getActivity().getApplicationContext(),
                                                "An error occured",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        } else {
                            try {
                                Settings.Secure.putInt(
                                        getActivity().getContentResolver(),
                                        "android.settings.extra.battery_saver_mode_enabled",
                                        1);
                            } catch (Exception err) {
                                Toast.makeText(
                                                getActivity().getApplicationContext(),
                                                "An error occured",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                        return true;
                    }
                });
        switchPref3.setIconSpaceReserved(false);
        SwitchPreferenceCompat switchPref4 = findPreference("full_charge");
        switchPref4.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference arg0, Object arg1) {
                        Boolean isChecked = (Boolean) arg1;
                        if (isChecked) {
                            try {
                                Settings.Secure.putString(
                                        getActivity().getContentResolver(),
                                        "icon_blacklist",
                                        "battery");
                            } catch (Exception err) {
                                Toast.makeText(
                                                getActivity().getApplicationContext(),
                                                "Error occured",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        } else {
                            try {
                                Settings.Secure.putString(
                                        getActivity().getContentResolver(), "icon_blacklist", "0");
                            } catch (Exception err) {
                                Toast.makeText(
                                                getActivity().getApplicationContext(),
                                                "Error occured",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                        return true;
                    }
                });
        switchPref4.setIconSpaceReserved(false);
        SwitchPreferenceCompat switchPref5 = findPreference("not_icons");
        switchPref5.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference arg0, Object arg1) {
                        Boolean isChecked = (Boolean) arg1;
                        if (isChecked) {
                            try {
                                Settings.Secure.putInt(
                                        getActivity().getContentResolver(),
                                        "notification_badging",
                                        0);
                            } catch (Exception err) {
                                Toast.makeText(
                                                getActivity().getApplicationContext(),
                                                "Error occured",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        } else {
                            try {
                                Settings.Secure.putInt(
                                        getActivity().getContentResolver(),
                                        "notification_badging",
                                        1);
                            } catch (Exception err) {
                                Toast.makeText(
                                                getActivity().getApplicationContext(),
                                                "Error occured",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                        return true;
                    }
                });
        switchPref5.setIconSpaceReserved(false);
        PreferenceCategory cat1 = findPreference("cat1");
        PreferenceCategory cat2 = findPreference("cat2");
        PreferenceCategory cat3 = findPreference("cat3");
        cat1.setIconSpaceReserved(false);
        cat2.setIconSpaceReserved(false);
        cat3.setIconSpaceReserved(false);
    }
}