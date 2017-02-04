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

    public static final int REQUEST_CODE = 0;
    public static final int CUSTOM_TILE_ID = 1;
    public static final int CUSTOM_TILE_LIST_ID = 2;
    public static final int CUSTOM_TILE_GRID_ID = 3;
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                createStatusBarTiles(MainActivity.this,false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createStatusBarTiles(Context context, boolean nowStatus) {
        Intent intent = new Intent();
        intent.setAction(TileReceiver.ACTION_UPDATE_STATUS);
        //intent.putExtra(C.EXTRA_ACTION, nowStatus ? C.ACTION_STOP : C.ACTION_START);

        CustomTile customTile = new CustomTile.Builder(context)
                .shouldCollapsePanel(false)
                .setLabel(nowStatus ? R.string.notification_action_turn_off : R.string.app_name)
                .setIcon(nowStatus ? R.drawable.screenshot : R.drawable.screenshot)
                .setOnClickIntent(PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT))
                .build();

        CMStatusBarManager.getInstance(context).publishTile(CM_TILE_CODE, customTile);
    }
}
