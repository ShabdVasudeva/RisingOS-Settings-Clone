package apw.risingos.settings.clone.Models;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.ParcelFileDescriptor;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.io.IOException;

public class StylesModel extends ViewModel {
    private MutableLiveData<Drawable> lockScreenWallpaper = new MutableLiveData<>();
    private MutableLiveData<Drawable> homeScreenWallpaper = new MutableLiveData<>();
    
    public LiveData<Drawable> getLockScreenWallpaper() {
        return lockScreenWallpaper;
    }

    public LiveData<Drawable> getHomeScreenWallpaper() {
        return homeScreenWallpaper;
    }

    public void retrieveLockScreenWallpaper(Context ctxt) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            WallpaperManager manager = WallpaperManager.getInstance(ctxt);
            try {
                ParcelFileDescriptor descriptor = manager.getWallpaperFile(WallpaperManager.FLAG_LOCK);
                if (descriptor != null) {
                    Bitmap bitmap = BitmapFactory.decodeFileDescriptor(descriptor.getFileDescriptor());
                    Drawable wallpaper = new BitmapDrawable(ctxt.getResources(), bitmap);
                    lockScreenWallpaper.setValue(wallpaper);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void retrieveHomeScreenWallpaper(Context ctxt) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(ctxt);
        Drawable wallpaperDrawable = wallpaperManager.getDrawable();
        homeScreenWallpaper.setValue(wallpaperDrawable);
    }
}