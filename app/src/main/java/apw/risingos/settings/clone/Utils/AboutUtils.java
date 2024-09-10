package apw.risingos.settings.clone.Utils;

import android.util.DisplayMetrics;

public class AboutUtils {
    private static final String modelName = android.os.Build.MODEL.toLowerCase();
    private static final String androidVersion = android.os.Build.VERSION.RELEASE;
    private static final String securityPatchLevel = android.os.Build.VERSION.SECURITY_PATCH;
    private static final String basebandVersion = android.os.Build.getRadioVersion();
    private static final String kernelVersion = System.getProperty("os.version");
    private static final String hardwareVersion = android.os.Build.HARDWARE;
    public DisplayMetrics displayMetrics = new DisplayMetrics();
    public static String getModelName(){
        return modelName;
    }
    public static String getAndroidVersion(){
        return androidVersion;
    }
    public static String getSecurityPatchLevel(){
        return securityPatchLevel;
    }
    public static String getBasebandVersion(){
        return basebandVersion;
    }
    public static String getKernelVersion(){
        return kernelVersion;
    }
    public static String getHardwareVersion(){
        return hardwareVersion;
    }
    public DisplayMetrics getDisplayMetrics(){
        return displayMetrics;
    }
}