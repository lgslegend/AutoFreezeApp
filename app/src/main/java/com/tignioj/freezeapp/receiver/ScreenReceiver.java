package com.tignioj.freezeapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tignioj.freezeapp.config.MyConfig;
import com.tignioj.freezeapp.utils.DeviceMethod;

public class ScreenReceiver extends BroadcastReceiver {
    public static boolean isLockScreen;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(MyConfig.MY_TAG, intent.getAction() + ":" + isLockScreen);
        if (intent.getAction() == null) {
            return;
        }

        switch (intent.getAction()) {
            case Intent.ACTION_SCREEN_ON:
            case Intent.ACTION_USER_PRESENT:
                if (isLockScreen) {
                    Log.d(MyConfig.BROADCAST_RECEIVER, "屏幕开启了");
                    DeviceMethod.getInstance(context).lockNow();
                }
                break;
        }
    }
}
