package apw.risingos.settings.clone.Activities;

import android.os.Bundle;
import android.preference.SwitchPreference;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import apw.risingos.settings.clone.R;
import apw.risingos.settings.clone.databinding.ActivityStatusBinding;
import apw.risingos.settings.clone.fragments.SettingsPreference;

public class StatusActivity extends AppCompatActivity {
    public ActivityStatusBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(v ->{
            onBackPressed();
        });
        	getSupportFragmentManager().beginTransaction().replace(R.id.settings_container,new SettingsPreference()).commit();
    }
}