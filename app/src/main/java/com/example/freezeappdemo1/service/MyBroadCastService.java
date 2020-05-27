package com.example.freezeappdemo1.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.freezeappdemo1.receiver.MyReceiver;

public class MyBroadCastService extends Service {
    MyReceiver myReceiver;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);

//        intentFilter.addDataScheme("package");
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, intentFilter);
    }
}
