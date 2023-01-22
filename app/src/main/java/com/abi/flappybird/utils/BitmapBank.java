package com.abi.flappybird.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.abi.flappybird.R;

public class BitmapBank {

    private final Bitmap background;
    private final Bitmap bird;
    private final Bitmap pipe;

    public BitmapBank(Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        bird = BitmapFactory.decodeResource(res, R.drawable.bird);
        pipe = BitmapFactory.decodeResource(res, R.drawable.pipe);
    }

    public Bitmap getBackground() {
        return background;
    }

    public Bitmap getBird() {
        return bird;
    }

    public Bitmap getPipe() {
        return pipe;
    }
}