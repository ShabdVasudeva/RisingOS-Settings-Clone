package apw.risingos.settings.clone.Utils;

import android.app.Application;
import android.content.ContentResolver;
import androidx.appcompat.app.AppCompatDelegate;
import apw.risingos.settings.clone.Utils.SwitchState;
import com.google.android.material.color.DynamicColors;

public class AppName extends Application {
    public SwitchState dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
        dbHelper = new SwitchState(this);
        if (dbHelper.getSwitchState()) {
            ContentResolver contentResolver = getContentResolver();
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            ContentResolver contentResolver = getContentResolver();
        }
    }
}
