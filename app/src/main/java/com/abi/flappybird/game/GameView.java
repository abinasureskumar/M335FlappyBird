package com.abi.flappybird.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceView;

import static com.abi.flappybird.Constants.*;

import java.util.Random;


public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;

    private final Bird bird;
    private final Random random;
    private final Pipe[] topPipes = new Pipe[NUM_PIPES];
    private final Pipe[] bottomPipes = new Pipe[NUM_PIPES];

    public GameView(Context context) {
        super(context);

        isPlaying = true;
        bird = new Bird(getResources());
        random = new Random();

        for (int i = 0; i < NUM_PIPES; i++) {
            int x = DISPLAY_WIDTH + PIPE_WIDTH + (i + 1) * PIPE_MIN_DISTANCE;
            topPipes[i] = new Pipe(x, 0, PIPE_WIDTH, random.nextInt(DISPLAY_HEIGHT - PIPE_GAP_HEIGHT));
            bottomPipes[i] = new Pipe(x, topPipes[i].height + PIPE_GAP_HEIGHT, PIPE_WIDTH, DISPLAY_HEIGHT - (topPipes[i].height + PIPE_GAP_HEIGHT));

        }

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

            draw();
        }

        stop();
    }

    private void update() {
        bird.onUpdate();

        for (int i = 0; i < NUM_PIPES; i++) {
            topPipes[i].onUpdate();
            bottomPipes[i].onUpdate();

            if (topPipes[i].x + PIPE_WIDTH < 0) {
                int x = topPipes[(i + NUM_PIPES - 1) % NUM_PIPES].x + PIPE_MIN_DISTANCE + PIPE_WIDTH;
                topPipes[i] = new Pipe(x, 0, PIPE_WIDTH, random.nextInt(DISPLAY_HEIGHT - PIPE_GAP_HEIGHT));
                bottomPipes[i] = new Pipe(x, topPipes[i].height + PIPE_GAP_HEIGHT, PIPE_WIDTH, DISPLAY_HEIGHT - (topPipes[i].height + PIPE_GAP_HEIGHT));
            }
        }
    }

    private void draw() {
        if (!getHolder().getSurface().isValid()) {
            return;
        }

        Canvas canvas = getHolder().lockCanvas();
        canvas.drawColor(Color.BLACK);

        bird.onDraw(canvas);
        for (int i = 0; i < NUM_PIPES; i++) {
            topPipes[i].onDraw(canvas);
            bottomPipes[i].onDraw(canvas);
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