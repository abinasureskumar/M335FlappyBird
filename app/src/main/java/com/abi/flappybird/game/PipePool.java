package com.abi.flappybird.game;

import static com.abi.flappybird.Constants.*;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.abi.flappybird.R;

import java.util.Random;

public class PipePool extends GameObject {

    private final Rect[] topPipes;
    private final Rect[] bottomPipes;
    private final Random random;

    private final Bitmap bitmap;

    public PipePool(Resources resources) {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.pipe);
        topPipes = new Rect[NUM_PIPES];
        bottomPipes = new Rect[NUM_PIPES];
        random = new Random();

        for (int i = 0; i < NUM_PIPES; i++) {
            int x = DISPLAY_WIDTH + PIPE_WIDTH + i * PIPE_MIN_DISTANCE;
            int y = random.nextInt(DISPLAY_HEIGHT - PIPE_GAP_HEIGHT);
            topPipes[i] = new android.graphics.Rect(x, 0, x + PIPE_WIDTH, y);
            bottomPipes[i] = new android.graphics.Rect(x, y + PIPE_GAP_HEIGHT, x + PIPE_WIDTH, DISPLAY_HEIGHT);

        }
    }

    @Override
    public void onUpdate() {
        for (int i = 0; i < NUM_PIPES; i++) {
            topPipes[i].offset(-GAME_SPEED, 0);
            bottomPipes[i].offset(-GAME_SPEED, 0);

            if (topPipes[i].right + PIPE_WIDTH < 0) {
                int x = topPipes[(i + NUM_PIPES - 1) % NUM_PIPES].right + PIPE_MIN_DISTANCE;
                int y = random.nextInt(DISPLAY_HEIGHT - PIPE_GAP_HEIGHT);
                topPipes[i].set(x, 0, x + PIPE_WIDTH, y);
                bottomPipes[i].set(x, y + PIPE_GAP_HEIGHT, x + PIPE_WIDTH, DISPLAY_HEIGHT);
            }
        }
    }

    @Override
    @SuppressLint("DrawAllocation")
    public void onDraw(Canvas canvas) {
        bitmap.setDensity(Bitmap.DENSITY_NONE);
        for (int i = 0; i < NUM_PIPES; i++) {
            Bitmap b1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), topPipes[i].height());
            Bitmap b2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bottomPipes[i].height());

            canvas.drawBitmap(b1,null,topPipes[i], null);
            canvas.drawBitmap(b2, null, bottomPipes[i], null);
        }
    }
}
