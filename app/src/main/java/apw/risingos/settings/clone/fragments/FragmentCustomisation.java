package apw.risingos.settings.clone.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;
import apw.risingos.settings.clone.Activities.MainActivity;
import apw.risingos.settings.clone.Activities.WallpaperActivity;
import apw.risingos.settings.clone.R;
import apw.risingos.settings.clone.Utils.TransitionDatabase;

public class FragmentCustomisation extends PreferenceFragmentCompat {
    public TransitionDatabase openHelper;

    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
        setPreferencesFromResource(R.xml.customisation, arg1);
        SwitchPreferenceCompat switch1 = findPreference("animate_up");
        openHelper = new TransitionDatabase(getActivity().getApplicationContext());
        switch1.setChecked(openHelper.getSwitchState());
        switch1.setIconSpaceReserved(false);
        switch1.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference arg0, Object arg1) {
                        Boolean isChecked = (Boolean) arg1;
                        if (isChecked) {
                            openHelper.setSwitchState(true);
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "Changes has been applied succesfully please restart the application to make changes.",
                                            Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            openHelper.setSwitchState(false);
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "Changes has been applied successfully please restart the application to make changes.",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                        return openHelper.getSwitchState();
                    }
                });
        Preference pref1 = findPreference("open_walls");
        pref1.setIconSpaceReserved(false);
        pref1.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            startActivity(
                                    new Intent(
                                            getActivity().getApplicationContext(),
                                            WallpaperActivity.class));

                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "An error occured",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                        return false;
                    }
                });
        Preference pref2 = findPreference("about_us");
        pref2.setIconSpaceReserved(false);
        pref2.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            startActivity(
                                    new Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("https://androidportworld.netlify.app/")));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "An error occured please retry later",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                        return false;
                    }
                });
    }
}
