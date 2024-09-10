package apw.risingos.settings.clone.Activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import apw.risingos.settings.clone.Utils.SwitchState;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.transition.MaterialArcMotion;
import apw.risingos.settings.clone.R;
import apw.risingos.settings.clone.databinding.ActivityThemesBinding;

public class ThemesActivity extends AppCompatActivity {
    public ActivityThemesBinding binding;
    public SwitchState dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThemesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(
                v -> {
                    onBackPressed();
                });
        dbHelper = new SwitchState(this);
        boolean switchState = dbHelper.getSwitchState();
        binding.hello.setChecked(switchState);
        binding.hello.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        dbHelper.setSwitchState(isChecked);
                        if (isChecked) {
                            Toast.makeText(
                                            getApplicationContext(),
                                            "Please clear the application from recents to see changes",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
        int currentNightMode =
                getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                binding.light.setTextColor(
                        ContextCompat.getColor(
                                getApplicationContext(), android.R.color.system_accent1_600));
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                binding.dark.setTextColor(
                        ContextCompat.getColor(
                                getApplicationContext(), android.R.color.system_accent1_600));

                break;
        }
    }
}
