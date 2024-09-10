package apw.risingos.settings.clone.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import apw.risingos.settings.clone.databinding.ActivityAppsBinding;
import apw.risingos.settings.clone.fragments.FragmentApplications;
import apw.risingos.settings.clone.R;

public class AppsActivity extends AppCompatActivity {
    private ActivityAppsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(v ->{
            onBackPressed();
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_container,new FragmentApplications()).commit();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
    
}
