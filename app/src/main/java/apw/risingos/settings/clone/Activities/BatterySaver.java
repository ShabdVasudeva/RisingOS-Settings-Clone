package apw.risingos.settings.clone.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import apw.risingos.settings.clone.databinding.BatterySaverBinding;
import apw.risingos.settings.clone.R;
import apw.risingos.settings.clone.fragments.FragmentSaver;

public class BatterySaver extends AppCompatActivity {
    private BatterySaverBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BatterySaverBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(v ->{
            onBackPressed();
        });
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_container,new FragmentSaver()).commit();
    }
}