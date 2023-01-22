package com.abi.flappybird.game.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.abi.flappybird.game.GameObject;
import com.abi.flappybird.utils.BitmapBank;
import com.abi.flappybird.utils.Constants;

public class Background extends GameObject {

    private final Bitmap image;

    public Background(BitmapBank bitmapBank) {
        image = bitmapBank.getBackground();
    }

    @Override
    public void onUpdate(Canvas canvas) {
        canvas.drawBitmap(image, null, Constants.DISPLAY_RECT, null);
    }
}
