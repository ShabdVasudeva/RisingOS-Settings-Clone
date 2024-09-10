package apw.risingos.settings.clone.Activities;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import apw.risingos.settings.clone.Utils.UserDatabase;
import apw.risingos.settings.clone.databinding.ActivityPersonalisationBinding;
import apw.risingos.settings.clone.fragments.FragmentCustomisation;
import apw.risingos.settings.clone.R;

public class PersonalisationActivity extends AppCompatActivity {
    private ActivityPersonalisationBinding binding;
    public UserDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalisationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(
                v -> {
                    onBackPressed();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                });
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new FragmentCustomisation())
                .commit();
        db = new UserDatabase(getApplicationContext());
        binding.setName.setOnClickListener(
                v -> {
                    db.setUserName(binding.edit.getText().toString());
                    Toast.makeText(
                                    this,
                                    "Changes has been applied pls restart App to see changes.",
                                    Toast.LENGTH_LONG)
                            .show();
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
