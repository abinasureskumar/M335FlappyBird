package com.abi.flappybird.utils;

import android.content.res.Resources;
import android.graphics.Rect;

public class Constants {

    public static final int DISPLAY_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static final int DISPLAY_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static final Rect DISPLAY_RECT = new Rect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
    public static final int GRAVITY = 3;
    public static final int GAME_SPEED = 5;
    public static final int NUM_PIPES = 4;
    public static final int PIPE_MIN_DISTANCE = 600;
    public static final int PIPE_GAP_HEIGHT = 700;
    public static final int PIPE_WIDTH = 200;
    public static final int BIRD_JUMP_VELOCITY = 30;
}
