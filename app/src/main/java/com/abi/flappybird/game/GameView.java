package com.abi.flappybird.game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import com.abi.flappybird.R;


public class GameView extends View {

    Handler handler;
    Runnable runnable;
    final int UPDATE_MILLIS = 30;
    Bitmap background;
    Display display;
    Point point;

    int displayWidth, displayHeight;
    Rect displayRect;

    Bitmap bird;

    int velocity = 0, gravity = 3;
    int birdX, birdY;

    public GameView(Context context) {
        super(context);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };


        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        displayWidth = point.x;
        displayHeight = point.y;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        displayRect = new Rect(0, 0, displayWidth, displayHeight);

        bird = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
        birdX = (displayWidth - bird.getWidth()) / 2;
        birdY = (displayHeight - bird.getHeight()) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (birdY < displayHeight - bird.getHeight() || velocity < 0) {
            velocity += gravity;
            birdY += velocity;
        }

        canvas.drawBitmap(background, null, displayRect, null);
        canvas.drawBitmap(bird, birdX, birdY, null);

        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            velocity = -30;
        }

        return super.onTouchEvent(event);
    }
}
