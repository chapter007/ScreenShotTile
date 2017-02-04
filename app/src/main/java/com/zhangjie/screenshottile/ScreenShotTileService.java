package com.zhangjie.screenshottile;

import android.annotation.TargetApi;
import android.service.quicksettings.TileService;
import android.util.Log;

/**
 * Created by zhangjie on 2017/2/4.
 */
@TargetApi(24)
public class ScreenShotTileService extends TileService {

    private static final String TAG ="Zhangjie";

    @Override
    public void onTileAdded() {
        Log.i(TAG, "onTileAdded: ");
        super.onTileAdded();
    }

    @Override
    public void onClick() {
        Log.i(TAG, "onClick: ");
        super.onClick();
    }
}
