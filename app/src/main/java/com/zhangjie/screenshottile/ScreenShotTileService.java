package com.zhangjie.screenshottile;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.service.quicksettings.TileService;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zhangjie on 2017/2/4.
 */
@TargetApi(24)
public class ScreenShotTileService extends Service {

    private static final String TAG ="Zhangjie";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void onTileAdded() {
        Log.i(TAG, "onTileAdded: ");
    }

    public void onClick() {
        Log.i(TAG, "onClick: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String action=intent.getAction();
        Log.i(TAG, "onBind: "+action);
        return null;
    }
}
