package com.abi.flappybird;

import android.content.res.Resources;

public class Constants {

    public static final int DISPLAY_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static final int DISPLAY_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static final int GRAVITY = 3;
    public static final int GAME_SPEED = 5;
    public static final int NUM_PIPES = 4;
    public static final int PIPE_GAP_HEIGHT = 700;
    public static final int BIRD_JUMP_VELOCITY = 30;
}
