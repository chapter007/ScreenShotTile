package com.zhangjie.screenshottile;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cyanogenmod.app.CMStatusBarManager;
import cyanogenmod.app.CustomTile;

/**
 * Created by zhangjie on 2017/2/4.
 */
public class TileReceiver extends BroadcastReceiver {
    public static final String ACTION_UPDATE_STATUS = "com.zhangjie.screenshottile.ACTION_UPDATE_STATUS";
    private static final String TAG = "zhangjie";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: " );
        Intent newIntent = new Intent(context,ScreenShotTileService.class);
        newIntent.setAction("ScreenShotButton");
        //String label = "CustomTile " + 0;
        //newIntent.putExtra(MainActivity.STATE, 1);
        context.startService(newIntent);

        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, 0,
                        newIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        CustomTile customTile = new CustomTile.Builder(context)
                .setOnClickIntent(pendingIntent)
                .shouldCollapsePanel(false)
                .setContentDescription("Generic content description")
                .setIcon(R.drawable.screenshotc)
                .setLabel("ScreenShot")
                .build();

        CMStatusBarManager.getInstance(context)
                .publishTile(MainActivity.CUSTOM_TILE_ID, customTile);


}

}
