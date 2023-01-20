package com.abi.flappybird.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.abi.flappybird.Constants;
import com.abi.flappybird.R;

public class Bird extends GameObject {

    private Bitmap image;
    private int velocity;

    public Bird(Resources resources) {
        image = BitmapFactory.decodeResource(resources, R.drawable.bird);
        width = image.getWidth();
        height = image.getHeight();
        x = (Constants.DISPLAY_WIDTH - width) / 2;
        y = (Constants.DISPLAY_HEIGHT - height) / 2;
    }

    @Override
    public void onUpdate() {
        if (y < Constants.DISPLAY_HEIGHT - 300 || velocity < 0) {
            velocity += Constants.GRAVITY;
            y += velocity;
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void jump() {
        velocity = -Constants.BIRD_JUMP_VELOCITY;
    }
}
