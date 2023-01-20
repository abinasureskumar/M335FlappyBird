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
    Bird bird;

    int velocity = 0, gravity = 3;

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
        bird = new Bird(point, (Activity)getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (bird.getY() < displayHeight - bird.getHeight() || velocity < 0) {
            velocity += gravity;
            bird.setY(bird.getY() + velocity);
        }

        canvas.drawBitmap(background, null, displayRect, null);
        bird.draw(canvas);

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
