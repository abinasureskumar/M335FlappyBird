package com.abi.flappybird.game.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.abi.flappybird.game.GameObject;
import com.abi.flappybird.utils.BitmapBank;
import com.abi.flappybird.utils.Constants;

public class Bird extends GameObject {

    private final Bitmap image;
    private final Rect rect;
    private int velocity;

    public Bird(BitmapBank bitmapBank) {
        image = bitmapBank.getBird();
        int width = image.getWidth();
        int height = image.getHeight();
        int x = (Constants.DISPLAY_WIDTH - width) / 2;
        int y = (Constants.DISPLAY_HEIGHT - height) / 2;

        rect = new Rect(0, 0, width, height);
        rect.offsetTo(x, y);
    }

    @Override
    public void onUpdate(Canvas canvas) {
        if (rect.top < Constants.DISPLAY_HEIGHT - image.getHeight() || velocity < 0) {
            velocity += Constants.GRAVITY;
            rect.offsetTo(rect.left, rect.top + velocity);
        }

        canvas.drawBitmap(image, null, rect, null);
    }

    public void jump() {
        velocity = -Constants.BIRD_JUMP_VELOCITY;
    }

    public Rect getRect() {
        return rect;
    }
}
