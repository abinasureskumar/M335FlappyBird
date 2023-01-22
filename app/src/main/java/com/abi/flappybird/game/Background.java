package com.abi.flappybird.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.abi.flappybird.Constants;
import com.abi.flappybird.R;

public class Background extends GameObject {

    private final Bitmap bitmap;

    public Background(Resources resources) {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.background);
    }

    @Override
    public void onUpdate(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, Constants.DISPLAY_RECT, null);
    }
}
