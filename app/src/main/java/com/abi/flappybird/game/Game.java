package com.abi.flappybird.game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;

import com.abi.flappybird.MainActivity;
import com.abi.flappybird.game.objects.Background;
import com.abi.flappybird.game.objects.Bird;
import com.abi.flappybird.game.objects.PipePool;
import com.abi.flappybird.utils.BitmapBank;

public class Game {

    private final Background background;
    private final Bird bird;
    private final PipePool pipePool;


    public Game(Context context) {
        BitmapBank bitmapBank = new BitmapBank(context.getResources());
        background = new Background(bitmapBank);
        bird = new Bird(bitmapBank);
        pipePool= new PipePool(bitmapBank);
    }

    public void onUpdate(Canvas canvas) {
        background.onUpdate(canvas);
        pipePool.onUpdate(canvas);
        bird.onUpdate(canvas);

        if (pipePool.intersects(bird.getRect())) {

        }
    }

    public void onTap() {
        bird.jump();
    }
}
