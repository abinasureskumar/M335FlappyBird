package com.abi.flappybird.game;

import android.graphics.Canvas;

public abstract class GameObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public void onUpdate() {}

    public void onDraw(Canvas canvas) {}
}
