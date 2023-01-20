package com.abi.flappybird.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.abi.flappybird.Constants;

import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;

    private Bird bird;
    private int pipeWidth;
    private int pipeHeight;
    private Random random;


    private Pipe[] topPipes = new Pipe[Constants.NUM_PIPES];
    private Pipe[] bottomPipes = new Pipe[Constants.NUM_PIPES];

    private Pipe pipe1;
    private Pipe pipe2;

    public GameView(Context context) {
        super(context);

        isPlaying = true;
        bird = new Bird(getResources());
        pipeWidth = 150;
        pipeHeight = 400;


        random = new Random();
        pipe1 = new Pipe(Constants.DISPLAY_WIDTH + pipeWidth, 0, pipeWidth, random.nextInt(Constants.DISPLAY_HEIGHT - Constants.PIPE_GAP_HEIGHT));
        pipe2 = new Pipe(Constants.DISPLAY_WIDTH + pipeWidth, pipe1.height + Constants.PIPE_GAP_HEIGHT, pipeWidth, Constants.DISPLAY_HEIGHT - (pipe1.height + Constants.PIPE_GAP_HEIGHT));

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
        pipe1.onUpdate();
        pipe2.onUpdate();

        if (pipe1.x + pipeWidth < 0) {
            pipe1 = new Pipe(Constants.DISPLAY_WIDTH, 0, pipeWidth, random.nextInt(Constants.DISPLAY_HEIGHT - Constants.PIPE_GAP_HEIGHT));
        }

        if (pipe2.x + pipeWidth < 0) {
            pipe2 = new Pipe(Constants.DISPLAY_WIDTH, pipe1.height + Constants.PIPE_GAP_HEIGHT, pipeWidth, Constants.DISPLAY_HEIGHT - (pipe1.height + Constants.PIPE_GAP_HEIGHT));
        }
    }

    private void draw() {
        if (!getHolder().getSurface().isValid()) {
            return;
        }

        Canvas canvas = getHolder().lockCanvas();
        canvas.drawColor(Color.BLACK);

        bird.onDraw(canvas);
        pipe1.onDraw(canvas);
        pipe2.onDraw(canvas);

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