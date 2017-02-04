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
public class TileReceiver extends BroadcastReceiver{
    public static final String ACTION_UPDATE_STATUS = "com.zhangjie.screenshottile.ACTION_UPDATE_STATUS";
    private static final String TAG ="zhangjie";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: ");
        if (MainActivity.ACTION_TOGGLE_STATE.equals(intent.getAction())) {
            Intent newIntent = new Intent();
            newIntent.setAction(MainActivity.ACTION_TOGGLE_STATE);
            String label = "CustomTile " + 0;

            int state = getCurrentState(intent);
            Log.i(TAG, "onReceive: "+state);

            switch (state) {
                case 0:
                    newIntent.putExtra(MainActivity.STATE, 1);
                    label = "CustomTile " + 1;
                    break;
                case 1:
                    newIntent.putExtra(MainActivity.STATE, 0);
                    label = "CustomTile " + 0;
                    break;
            }

            PendingIntent pendingIntent =
                    PendingIntent.getBroadcast(context, 0,
                            newIntent , PendingIntent.FLAG_UPDATE_CURRENT);

            CustomTile customTile = new CustomTile.Builder(context)
                    .setOnClickIntent(pendingIntent)
                    .shouldCollapsePanel(false)
                    .setContentDescription("Generic content description")
                    .setLabel(label)
                    .setIcon(R.drawable.screenshot)
                    .build();

            CMStatusBarManager.getInstance(context)
                    .publishTile(MainActivity.CUSTOM_TILE_ID, customTile);
        }
    }

    private int getCurrentState(Intent intent) {
        return intent.getIntExtra(MainActivity.STATE, 0);
    }
}
