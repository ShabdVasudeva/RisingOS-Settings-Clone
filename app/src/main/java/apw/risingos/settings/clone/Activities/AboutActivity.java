package apw.risingos.settings.clone.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import apw.risingos.settings.clone.R;
import apw.risingos.settings.clone.Utils.AboutUtils;
import apw.risingos.settings.clone.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity {
    private ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(
                v -> {
                    onBackPressed();
                });
        binding.deviceName.setText(AboutUtils.getModelName().toUpperCase());
        binding.kernelName.setText(AboutUtils.getKernelVersion());
        binding.securityPatch.setText(AboutUtils.getSecurityPatchLevel());
        binding.deviceNametwo.setText(AboutUtils.getModelName());
        binding.androVersion.setText(AboutUtils.getAndroidVersion());
        binding.baseVersion.setText(AboutUtils.getBasebandVersion());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        binding.screenSize.setText(width + "x" + height);
        binding.process.setText(AboutUtils.getHardwareVersion());
        binding.more.setOnClickListener(
                v -> {
                    try {
                        startActivity(new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS));
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "an error occured",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        binding.help.setOnClickListener(
                v -> {
                    try {
                        startActivity(
                                new Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://t.me/AndroidPortWorld")));
                    } catch (Exception err) {
                        Toast.makeText(
                                        this,
                                        "An error occured please try again later",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
