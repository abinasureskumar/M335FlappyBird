package com.abi.flappybird.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.abi.flappybird.MainActivity;
import com.abi.flappybird.fragments.GameFragment;
import com.abi.flappybird.fragments.GameOverFragment;

public class GameThread extends Thread {

    private final SurfaceHolder surfaceHolder;
    private final Game game;
    private boolean isRunning;
    private boolean isPaused;

    public GameThread(Game game, SurfaceHolder holder) {
        this.game = game;
        this.surfaceHolder = holder;
        this.isRunning = true;
        this.isPaused = false;
    }

    public void close() {
        try {
            isRunning = false;
            join();
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }
    }

    @Override
    public void run() {

        final long targetFrames = 60;
        final long framePeriod = 1000 / targetFrames;
        long lastUpdate = System.currentTimeMillis();

        while (isRunning) {
            if (isPaused) {
                continue;
            }

            if (game.isGameOver()) {
                isRunning = false;
                MainActivity.getInstance().changeFragment(new GameOverFragment());
                break;
            }

            long currentTime = System.currentTimeMillis();

            if (currentTime - lastUpdate >= framePeriod) {

                Canvas canvas = surfaceHolder.lockCanvas(null);

                if (canvas != null) {
                    game.onUpdate(canvas);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }

                lastUpdate = currentTime;
            }
        }

        close();
    }

    public void togglePause() {
        isPaused = !isPaused;
    }

    public boolean isRunning() {
        return isRunning;
    }
}

