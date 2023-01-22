package com.abi.flappybird.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.abi.flappybird.Constants;
import com.abi.flappybird.R;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;

    private final Background background;
    private final Bird bird;
    private final PipePool pipePool;

    public GameView(Context context) {
        super(context);

        isPlaying = true;
        background = new Background((getResources());
        bird = new Bird(getResources());
        pipePool = new PipePool(getResources());

        start();
    }

    public void start() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        isPlaying = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        final long targetFrames = 60;
        final long framePeriod = 1000 / targetFrames;
        long lastUpdate = System.currentTimeMillis();

        while (isPlaying) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastUpdate >= framePeriod) {
                update();
                lastUpdate = currentTime;
            }
        }

        stop();
    }

    private void update() {
        if (!getHolder().getSurface().isValid()) {
            return;
        }

        Canvas canvas = getHolder().lockCanvas();

        background.onUpdate(canvas);
        pipePool.onUpdate(canvas);
        bird.onUpdate(canvas);

        if (pipePool.intersects(bird.getRect())) {
            System.out.println("TEST");
        }

        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            bird.jump();
        }

        return super.onTouchEvent(event);
    }
}