package apw.risingos.settings.clone.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Px;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import apw.risingos.settings.clone.databinding.ActivityBatteryBinding;
import apw.risingos.settings.clone.fragments.BatteryFragment;
import apw.risingos.settings.clone.R;

public class BatteryActivity extends AppCompatActivity {
    private ActivityBatteryBinding binding;
    private BroadcastReceiver mBatInfoReceiver =
            new BroadcastReceiver() {
                @Override
                public void onReceive(Context ctxt, Intent intent) {
                    try {
                        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                        int batteryPct = level * 100 / (int) scale;
                        String technology =
                                intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
                        int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
                        int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
                        binding.percentage.setText(String.valueOf(batteryPct));
                        binding.progressbar.setProgressCompat(batteryPct, true);
                        /* binding.tech.setText(technology);
                        binding.temp.setText(temperature / 10.0 + "°c");
                        if (health == BatteryManager.BATTERY_HEALTH_COLD) {
                            binding.hlth.setText("Cold");
                        } else if (health == BatteryManager.BATTERY_HEALTH_GOOD) {
                            binding.hlth.setText("Good");
                        } else if (health == BatteryManager.BATTERY_HEALTH_DEAD) {
                            binding.hlth.setText("Dead");
                        } else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
                            binding.hlth.setText("Over Voltage");
                        } else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
                            binding.hlth.setText("Over Heating");
                        } else {
                            binding.hlth.setText("Unknown");
                        } */
                    } catch (Exception ig) {
                        Toast.makeText(
                                getApplicationContext(), "an error occured", Toast.LENGTH_SHORT);
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            binding = ActivityBatteryBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            setSupportActionBar(binding.toolbar);
            showBatteryEstimation(getApplicationContext());
            binding.toolbar.setNavigationOnClickListener(
                    v -> {
                        onBackPressed();
                    });
            this.registerReceiver(
                    this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
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
                binding.advice.setText("Charging rapidly • please wait untill full charge");
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.settings_container,new BatteryFragment()).commit();
        } catch (Exception err) {

        }
    }

    public void showBatteryEstimation(Context context) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, filter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        int batteryPercent = (int) ((level / (float) scale) * 100);
        int remainingTimeHours = batteryPercent;
        String message = "Estimated remaining time: " + remainingTimeHours + " hours";
        binding.advice.setText(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}