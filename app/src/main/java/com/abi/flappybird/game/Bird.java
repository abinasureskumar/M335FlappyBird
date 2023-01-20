package com.abi.flappybird.game;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import com.abi.flappybird.R;

public class Bird {

    private int x;
    private int y;
    private final Bitmap image;

    public Bird(Point displaySize, Activity activity) {
        image = BitmapFactory.decodeResource(activity.getResources(), R.drawable.bird);
        x = (displaySize.x - image.getWidth()) / 2;
        y = (displaySize.y - image.getHeight()) / 2;
    }

    public void draw(Canvas canvas)  {
        canvas.drawBitmap(image, x, y, null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }
}
