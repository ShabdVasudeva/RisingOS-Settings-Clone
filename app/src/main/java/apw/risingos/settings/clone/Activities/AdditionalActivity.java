package apw.risingos.settings.clone.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import apw.risingos.settings.clone.R;
import apw.risingos.settings.clone.databinding.ActivityAdditionalBinding;

public class AdditionalActivity extends AppCompatActivity {
    private ActivityAdditionalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdditionalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(
                v -> {
                    onBackPressed();
                });
        binding.date.setOnClickListener(
                v -> {
                    try {
                        startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
                    } catch (Exception i) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "an error occured",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        binding.kayboard.setOnClickListener(
                v -> {
                    try {
                        startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
                    } catch (Exception i) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "an error occured",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        binding.language.setOnClickListener(
                v -> {
                    try {
                        startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
                    } catch (Exception i) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "an error occured",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        binding.captions.setOnClickListener(
                v -> {
                    try {
                        startActivity(new Intent(Settings.ACTION_CAPTIONING_SETTINGS));
                    } catch (Exception i) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "an error occured",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        binding.backup.setOnClickListener(
                v -> {
                    try {
                        Intent ig = new Intent();
                        ig.setClassName(
                                "com.google.android.apps.subscriptions.red",
                                "com.google.android.apps.subscriptions.red.main.MainActivity");
                        startActivity(ig);
                    } catch (Exception i) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "please install or enable google one application",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }
}
