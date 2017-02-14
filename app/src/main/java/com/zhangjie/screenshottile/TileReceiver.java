package com.zhangjie.screenshottile;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.service.quicksettings.TileService;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;

import cyanogenmod.app.CMStatusBarManager;
import cyanogenmod.app.CustomTile;

/**
 * Created by zhangjie on 2017/2/4.
 */
public class TileReceiver extends BroadcastReceiver {
    public static final String ACTION_SCREEN_SHOT = "com.zhangjie.screenshottile.ACTION_SCREEN_SHOT";
    private static final String TAG = "zhangjie";

    @Override
    public void onReceive(Context context, Intent intent) {
        int flags=intent.getIntExtra("flags",0);
        String action=intent.getAction();
        Intent newIntent = new Intent();
        newIntent.setAction(TileReceiver.ACTION_SCREEN_SHOT);
        newIntent.putExtra("flags",1);
        if (action.equals(TileReceiver.ACTION_SCREEN_SHOT)&&flags==1){
            adbScreenShot();
        }else if(flags==0){
            Log.i(TAG, "onReceive: add tile");
            Toast.makeText(context,"已填加磁贴",Toast.LENGTH_SHORT).show();
        }if (flags==0){
            Toast.makeText(context,"已填加磁贴",Toast.LENGTH_SHORT).show();
        }

        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, 0,
                        newIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        CustomTile customTile = new CustomTile.Builder(context)
                .setOnClickIntent(pendingIntent)
                .shouldCollapsePanel(true)
                .setContentDescription("点击即可截屏")
                .setIcon(R.drawable.screenshot)
                .setLabel("ScreenShot")
                .build();

        CMStatusBarManager.getInstance(context)
                .publishTile(MainActivity.CUSTOM_TILE_ID, customTile);

    }

    private void adbScreenShot() {
        OutputStream os = null;
        String cmd = "input keyevent 120\n";
        try {
            if (os == null) {
                os = Runtime.getRuntime().exec("su").getOutputStream();
            }
            os.write(cmd.getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
