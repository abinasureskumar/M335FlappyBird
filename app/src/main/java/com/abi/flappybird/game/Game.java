package com.abi.flappybird.game;

import android.content.Context;
import android.graphics.Canvas;

import com.abi.flappybird.game.objects.Background;
import com.abi.flappybird.game.objects.Bird;
import com.abi.flappybird.game.objects.PipePool;
import com.abi.flappybird.utils.BitmapBank;

public class Game {

    private final Background background;
    private final Bird bird;
    private final PipePool pipePool;
    private boolean isGameOver;


    public Game(Context context) {
        BitmapBank bitmapBank = new BitmapBank(context.getResources());
        background = new Background(bitmapBank);
        bird = new Bird(bitmapBank);
        pipePool = new PipePool(bitmapBank);
        isGameOver = false;
    }

    public void onUpdate(Canvas canvas) {
        background.onUpdate(canvas);
        pipePool.onUpdate(canvas);
        bird.onUpdate(canvas);

        if (pipePool.intersects(bird.getRect())) {
            isGameOver = true;
            // Does not work:
            // MainActivity.getInstance().changeFragment(new GameOverFragment());
        }
    }

    public void onTap() {
        bird.jump();
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
