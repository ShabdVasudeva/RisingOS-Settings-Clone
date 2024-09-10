package apw.risingos.settings.clone.Activities;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.format.Time;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceFragmentCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Visibility;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import apw.risingos.settings.clone.Activities.StatusActivity;
import apw.risingos.settings.clone.Activities.ThemesActivity;
import apw.risingos.settings.clone.R;
import java.util.prefs.PreferenceChangeListener;
import apw.risingos.settings.clone.databinding.ActivityWallpaperBinding;
import apw.risingos.settings.clone.Models.StylesModel;

public class WallpaperActivity extends AppCompatActivity {

    private ActivityWallpaperBinding binding;
    public StylesModel util;
    public BottomSheetBehavior bottomSheetBehavior;
    public static final int REQUEST_MEDIA_PERMISSION = 1;
    public static final int REQUEST_WRITE_PERMISSION = 1;
    private static final int PICK_IMAGE_REQUEST = 1;
    private boolean isBottomSheetOpen = false;
    private int colorToString(String color) {
        return Color.parseColor(color);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWallpaperBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(
                v -> {
                    onBackPressed();
                });
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hhmm", Locale.getDefault());
        binding.lockTime.setText(sdf.format(now));
        binding.lockDate.setText(
                cal.get(Calendar.DATE)
                        + ", "
                        + cal.get(Calendar.MONTH)
                        + ", "
                        + cal.get(Calendar.YEAR));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
                                this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_MEDIA_PERMISSION);
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_PERMISSION);
        } else {
            getWallpapers();
        }
        binding.textbut.setOnClickListener(
                v -> {
                    openImagePicker();
                });

        bottomSheetBehavior = BottomSheetBehavior.from(binding.frame);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior = BottomSheetBehavior.from(binding.frame);
        binding.textButton.setOnClickListener(
                v -> {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    isBottomSheetOpen = true;
                });
        binding.close.setOnClickListener(
                v -> {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                });
        binding.theme.setOnClickListener(
                v -> {
                    try {
                        startActivity(new Intent(this, ThemesActivity.class));
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "An error occured",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        binding.sethome.setOnClickListener(
                v -> {
                    Intent ig = new Intent();
                    ig.setClassName(
                            "app.lawnchair", "app.lawnchair.ui.preferences.PreferenceActivity");
                    try {
                        startActivity(ig);
                    } catch (Exception io) {
                        Toast.makeText(
                                getApplicationContext(),
                                "please install our lawnchair fork to perform this task",
                                Toast.LENGTH_LONG);
                    }
                });
        binding.palettte.setOnClickListener(
                v -> {
                    Intent intent = new Intent();
                    intent.setComponent(
                            new ComponentName(
                                    "dev.kdrag0n.dyntheme",
                                    "dev.kdrag0n.dyntheme.ui.main.MainActivity"));
                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(
                                        this,
                                        "Please install Repainter app first",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        binding.dream.setOnClickListener(
                v -> {
                    try {
                        startActivity(new Intent(Settings.ACTION_DREAM_SETTINGS));
                    } catch (Exception ios) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "your device don't support this setting",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        binding.wall1.setOnClickListener(
                v -> {
                    final WallpaperManager wallpaperManager =
                            WallpaperManager.getInstance(getApplicationContext());
                    try {
                        wallpaperManager.setResource(R.drawable.wall1);
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "an error occured",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
        binding.wall2.setOnClickListener(
                v -> {
                    final WallpaperManager wallpaperManager =
                            WallpaperManager.getInstance(getApplicationContext());
                    try {
                        wallpaperManager.setResource(R.drawable.wall2);
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "an error occured",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
        binding.wall3.setOnClickListener(
                v -> {
                    final WallpaperManager wallpaperManager =
                            WallpaperManager.getInstance(getApplicationContext());
                    try {
                        wallpaperManager.setResource(R.drawable.wall3);
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "an error occured",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
        binding.wall4.setOnClickListener(
                v -> {
                    final WallpaperManager wallpaperManager =
                            WallpaperManager.getInstance(getApplicationContext());
                    try {
                        wallpaperManager.setResource(R.drawable.wall4);
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "an error occured",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
        binding.wall5.setOnClickListener(
                v -> {
                    final WallpaperManager wallpaperManager =
                            WallpaperManager.getInstance(getApplicationContext());
                    try {
                        wallpaperManager.setResource(R.drawable.wall5);
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "an error occured",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
        binding.wall6.setOnClickListener(
                v -> {
                    final WallpaperManager wallpaperManager =
                            WallpaperManager.getInstance(getApplicationContext());
                    try {
                        wallpaperManager.setResource(R.drawable.wall6);
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "an error occured",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
        binding.wall7.setOnClickListener(
                v -> {
                    final WallpaperManager wallpaperManager =
                            WallpaperManager.getInstance(getApplicationContext());
                    try {
                        wallpaperManager.setResource(R.drawable.wall7);
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "an error occured",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
        binding.setWall.setOnClickListener(
                v -> {
                    try {
                        String str1 = binding.edit.getText().toString();
                        String str2 = binding.edit2.getText().toString();
                        int a = colorToString(str1);
                        int b = colorToString(str2);
                        setWallpaperWithGradientColors(a, b);
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "An Error Occured",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
        binding.livewall.setOnClickListener(v ->{
            try {
            	Intent intent = new Intent(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
                startActivity(intent);
            } catch(Exception err) {
            	Toast.makeText(
                                        getApplicationContext(),
                                        "An Error Occured",
                                        Toast.LENGTH_LONG)
                                .show();
            }
        });
        binding.status.setOnClickListener(v ->{
            startActivity(new Intent(this,StatusActivity.class));
        });
    }

    private Bitmap resizeBitmap(Bitmap bitmap, int targetWidth, int targetHeight) {
        return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true);
    }

    private void setWallpaperWithGradientColors(int startColor, int endColor) {
        GradientDrawable gradientDrawable =
                new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM, new int[] {startColor, endColor});
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        gradientDrawable.setBounds(0, 0, 100, 100);
        gradientDrawable.draw(new Canvas(bitmap));
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        Bitmap resizedBitmap = resizeBitmap(bitmap, screenWidth, screenHeight);
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Set wallpaper as")
                .setNegativeButton(
                        "Lockscreen",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    wallpaperManager.setBitmap(
                                            resizedBitmap, null, true, WallpaperManager.FLAG_LOCK);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .setPositiveButton(
                        "Homescreen",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    wallpaperManager.setBitmap(
                                            resizedBitmap,
                                            null,
                                            true,
                                            WallpaperManager.FLAG_SYSTEM);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .setNeutralButton(
                        "Both",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    wallpaperManager.setBitmap(resizedBitmap);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .show();
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_MEDIA_PERMISSION && requestCode == REQUEST_WRITE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getWallpapers();
            } else {
                Toast.makeText(
                                getApplicationContext(),
                                "Please allow necessary permissions to display wallpapers",
                                Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    public void getWallpapers() {
        util = new ViewModelProvider(this).get(StylesModel.class);
        util.getLockScreenWallpaper()
                .observe(
                        this,
                        lockScreenWallpaper -> {
                            binding.lock.setBackgroundDrawable(lockScreenWallpaper);
                        });

        util.getHomeScreenWallpaper()
                .observe(
                        this,
                        homeScreenWallpaper -> {
                            binding.home.setBackgroundDrawable(homeScreenWallpaper);
                        });
        util.retrieveLockScreenWallpaper(this);
        util.retrieveHomeScreenWallpaper(this);
    }

    @Override
    public void onBackPressed() {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        } else {
            super.onBackPressed();
        }
    }

    private void openImagePicker() {
        Intent pickImageIntent =
                new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImageIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
            builder.setTitle("Set wallpaper as")
                    .setNegativeButton(
                            "Lockscreen",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setWallpaperLock(selectedImageUri);
                                }
                            })
                    .setPositiveButton(
                            "Homescreen",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setWallpaper(selectedImageUri);
                                }
                            })
                    .setNeutralButton(
                            "Both",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setWallpaperBoth(selectedImageUri);
                                }
                            })
                    .show();
        }
    }

    private void setWallpaper(Uri imageUri) {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            wallpaperManager.setStream(
                    getContentResolver().openInputStream(imageUri),
                    null,
                    false,
                    WallpaperManager.FLAG_SYSTEM);
            Toast.makeText(this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
        }
    }

    private void setWallpaperLock(Uri imageUri) {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            wallpaperManager.setStream(
                    getContentResolver().openInputStream(imageUri),
                    null,
                    false,
                    WallpaperManager.FLAG_LOCK);
            Toast.makeText(this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
        }
    }

    private void setWallpaperBoth(Uri imageUri) {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            wallpaperManager.setStream(
                    getContentResolver().openInputStream(imageUri),
                    null,
                    false,
                    WallpaperManager.FLAG_LOCK);
            wallpaperManager.setStream(
                    getContentResolver().openInputStream(imageUri),
                    null,
                    false,
                    WallpaperManager.FLAG_SYSTEM);
            Toast.makeText(this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
        }
    }

    private void success() {
        Toast.makeText(this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}