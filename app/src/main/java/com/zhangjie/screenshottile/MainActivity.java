package com.zhangjie.screenshottile;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import cyanogenmod.app.CMStatusBarManager;
import cyanogenmod.app.CustomTile;

public class MainActivity extends AppCompatActivity {

    public static final int CUSTOM_TILE_ID = 1;
    public static final String ACTION_TOGGLE_STATE =
            "com.zhangjie.screenshottile.ACTION_TOGGLE_STATE";
    public static final String STATE = "state";
    private static final int CM_TILE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createStatusBarTiles(MainActivity.this, false);
                Intent intent = new Intent();
                intent.setAction(TileReceiver.ACTION_SCREEN_SHOT);
                sendBroadcast(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createStatusBarTiles(Context context, boolean nowStatus) {
        Intent intent = new Intent();
        intent.setAction(TileReceiver.ACTION_SCREEN_SHOT);

        CustomTile customTile = new CustomTile.Builder(context)
                .shouldCollapsePanel(false)
                .setLabel(nowStatus ? R.string.notification_action_turn_off : R.string.app_name)
                .setIcon(nowStatus ? R.drawable.screenshot : R.drawable.screenshot)
                .setOnClickIntent(PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT))
                .build();

        CMStatusBarManager.getInstance(context).publishTile(CM_TILE_CODE, customTile);
    }
}
