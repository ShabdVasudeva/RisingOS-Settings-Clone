package apw.risingos.settings.clone.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

public class BatteryReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context ctxt, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
        int batteryPct = level * 100 / (int) scale;
    }
}