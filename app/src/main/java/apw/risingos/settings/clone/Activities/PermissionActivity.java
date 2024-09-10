package apw.risingos.settings.clone.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import apw.risingos.settings.clone.databinding.ActivityPermissionBinding;
import apw.risingos.settings.clone.R;
import apw.risingos.settings.clone.fragments.FragmentPermission;

public class PermissionActivity extends AppCompatActivity {
    private ActivityPermissionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermissionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(
                v -> {
                    onBackPressed();
                });
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new FragmentPermission())
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
