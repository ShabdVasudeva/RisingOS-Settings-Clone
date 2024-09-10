package apw.risingos.settings.clone.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import apw.risingos.settings.clone.Activities.PermissionActivity;
import apw.risingos.settings.clone.R;

public class FragmentApplications extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
        setPreferencesFromResource(R.xml.app_prefs, arg1);
        Preference pref1 = findPreference("all_apps");
        Preference pref2 = findPreference("default_apps");
        Preference pref3 = findPreference("quick_switch");
        Preference pref4 = findPreference("assistant");
        Preference pref5 = findPreference("perm_manage");
        pref1.setIconSpaceReserved(false);
        pref2.setIconSpaceReserved(false);
        pref3.setIconSpaceReserved(false);
        pref4.setIconSpaceReserved(false);
        pref5.setIconSpaceReserved(false);
        pref1.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            startActivity(new Intent(Settings.ACTION_APPLICATION_SETTINGS));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "An error occured",
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
                            startActivity(new Intent(Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "An error occured",
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
                            startActivity(new Intent(Settings.ACTION_HOME_SETTINGS));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "An error occured",
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
                            startActivity(new Intent(Settings.ACTION_VOICE_INPUT_SETTINGS));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "An error occured",
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
                            startActivity(
                                    new Intent(
                                            getActivity().getApplicationContext(),
                                            PermissionActivity.class));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "An error occured",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                        return true;
                    }
                });
    }
}
