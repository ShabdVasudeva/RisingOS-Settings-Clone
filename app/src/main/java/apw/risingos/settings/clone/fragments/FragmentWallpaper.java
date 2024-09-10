package apw.risingos.settings.clone.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;
import apw.risingos.settings.clone.R;

public class FragmentWallpaper extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
        setPreferencesFromResource(R.xml.wallpaper_plus, arg1);
        SwitchPreferenceCompat pref1 = findPreference("wallpaper_plus");
        pref1.setIconSpaceReserved(false);
        pref1.setDefaultValue(false);
        pref1.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference arg0, Object arg1) {
                        boolean isChecked = (boolean) arg1;
                        SharedPreferences.Editor editor =
                                PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit();
                        editor.putBoolean("switch_state", isChecked);
                        editor.apply();
                        return true;
                    }
                });
    }

}