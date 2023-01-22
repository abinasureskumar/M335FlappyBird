package com.abi.flappybird.game;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private final Game game;
    private final GameThread thread;

    public GameView(Context context, AttributeSet attributes) {
        super(context, attributes);

        getHolder().addCallback(this);
        setFocusable(true);

        game = new Game(context);
        thread = new GameThread(game, getHolder());
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        if (thread.isRunning()) {
            thread.close();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            game.onTap();
        }

        return super.onTouchEvent(event);
    }

    public void togglePause() {
        thread.togglePause();
    }
}
