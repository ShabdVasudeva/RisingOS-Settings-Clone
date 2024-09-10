package apw.risingos.settings.clone.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import java.lang.annotation.Target;
import apw.risingos.settings.clone.Activities.BatterySaver;
import apw.risingos.settings.clone.R;
import apw.risingos.settings.clone.Utils.BatteryReciever;

public class BatteryFragment extends PreferenceFragmentCompat {
    public Preference pct, temp, tech, hlth, voltage;

    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
        setPreferencesFromResource(R.xml.battery_preferences, arg1);
        Preference pref1 = findPreference("battery_usage");
        Preference pref2 = findPreference("battery_saver");
        pref1.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        startActivity(new Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS));
                        return true;
                    }
                });
        pref1.setIconSpaceReserved(false);
        pref2.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        startActivity(new Intent(getActivity().getApplicationContext(),BatterySaver.class));
                        return true;
                    }
                });
        pref2.setIconSpaceReserved(false);
        PreferenceCategory cat = findPreference("category1");
        cat.setIconSpaceReserved(false);
    }

    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        pct = findPreference("battery_percent");
        temp = findPreference("battery_temp");
        tech = findPreference("battery_technology");
        hlth = findPreference("battery_health");
        voltage = findPreference("battery_voltage");
        temp.setIconSpaceReserved(false);
        temp.setEnabled(false);
        pct.setEnabled(false);
        pct.setIconSpaceReserved(false);
        tech.setEnabled(false);
        tech.setIconSpaceReserved(false);
        hlth.setEnabled(false);
        hlth.setIconSpaceReserved(false);
        voltage.setEnabled(false);
        voltage.setIconSpaceReserved(false);
        BatteryReciever batteryReciever = new BatteryReciever();
        getActivity()
                .registerReceiver(batteryReciever, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent levelBat = getActivity().registerReceiver(null, ifilter);
        Intent scaleBat = getActivity().registerReceiver(null, ifilter);
        Intent tempBat = getActivity().registerReceiver(null, ifilter);
        Intent techBat = getActivity().registerReceiver(null, ifilter);
        Intent hlthBat = getActivity().registerReceiver(null, ifilter);
        String technology =
                techBat != null
                        ? techBat.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY)
                        : null;
        int health = hlth != null ? hlthBat.getIntExtra(BatteryManager.EXTRA_HEALTH, -1) : -1;
        int temperature =
                tempBat != null ? tempBat.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) : -1;
        int level = levelBat != null ? levelBat.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
        int scale = scaleBat != null ? scaleBat.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;
        int batteryPct = level * 100 / (int) scale;
        BatteryManager batteryManager = (BatteryManager) getActivity().getApplicationContext().getSystemService(Context.BATTERY_SERVICE);
        int counter = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER) / 1000;
        int capacity = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        voltage.setSummary(String.valueOf(counter) + " mAh");
        pct.setSummary(String.valueOf(batteryPct) + "%" + " " + "available");
        temp.setSummary(String.valueOf(temperature / 10.0) + "° c");
        tech.setSummary(technology);
        if (health == BatteryManager.BATTERY_HEALTH_COLD) {
            hlth.setSummary("cold");
        } else if (health == BatteryManager.BATTERY_HEALTH_GOOD) {
            hlth.setSummary("Good");
        } else if (health == BatteryManager.BATTERY_HEALTH_DEAD) {
            hlth.setSummary("Dead");
        } else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
            hlth.setSummary("Over Voltage");
        } else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
            hlth.setSummary("Over Heating");
        } else {
            hlth.setSummary("Unknown");
        }
    }
}