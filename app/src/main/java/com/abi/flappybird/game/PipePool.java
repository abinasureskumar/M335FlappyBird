package com.abi.flappybird.game;

import static com.abi.flappybird.Constants.*;

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
    public void onUpdate(Canvas canvas) {
        for (int i = 0; i < NUM_PIPES; i++) {
            topPipes[i].offset(-GAME_SPEED, 0);
            bottomPipes[i].offset(-GAME_SPEED, 0);

            updatePipePair(i);
            drawPipePair(i, canvas);
        }
    }

    public boolean intersects(Rect rect) {
        for (int i = 0; i < NUM_PIPES; i++) {
            if (topPipes[i].intersect(rect) || bottomPipes[i].intersect(rect)) {
                return true;
            }
        }

        return false;
    }

    private void updatePipePair(int index) {
        topPipes[index].offset(-GAME_SPEED, 0);
        bottomPipes[index].offset(-GAME_SPEED, 0);

        if (topPipes[index].right + PIPE_WIDTH < 0) {
            int x = topPipes[(index + NUM_PIPES - 1) % NUM_PIPES].right + PIPE_MIN_DISTANCE;
            int y = random.nextInt(DISPLAY_HEIGHT - PIPE_GAP_HEIGHT);
            topPipes[index].set(x, 0, x + PIPE_WIDTH, y);
            bottomPipes[index].set(x, y + PIPE_GAP_HEIGHT, x + PIPE_WIDTH, DISPLAY_HEIGHT);
        }
    }

    private void drawPipePair(int index, Canvas canvas) {
        canvas.drawBitmap(bitmap, null, topPipes[index], null);
        canvas.drawBitmap(bitmap, null, bottomPipes[index], null);
    }
}
