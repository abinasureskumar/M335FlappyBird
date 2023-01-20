package com.abi.flappybird.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.abi.flappybird.Constants;

public class Pipe extends GameObject {

    public Pipe(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void onUpdate() {
        x -= Constants.GAME_SPEED;
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
}
