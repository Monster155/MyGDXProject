package ru.itlab;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Constants {
    public static final Vector2 C_SIZE = new Vector2(31, 35);
    public static final Vector2 E_SIZE = new Vector2(31, 35);
    public static final Vector2 B_SIZE = new Vector2(3, 3);
    public static final Texture BACKGROUND = new Texture("background.png");
    public static final Texture BULLET = new Texture("background.png");
    public static final float C_SPEED = 100f;
    public static final int C_MAX_LIVES = 100;
    public static final float WALK_LOOP_DURATION = 0.25f;
    public static final String T_FRONT = "Front";
    public static final String T_FRONT_M = "Front_Move";
    public static final String T_BACK = "Back";
    public static final String T_BACK_M = "Back_Move";
    public static final String T_LEFT = "Left";
    public static final String T_LEFT_M = "Left_Move";
    public static final String T_RIGHT = "Right";
    public static final String T_RIGHT_M = "Right_Move";
    public static final String C_ATLAS = "atlas/superBoy.atlas";
    public static final float BULLET_SPEED = 200f;
    public static final int BULLET_DAMAGE = 1;
    public static final float ENEMY_SPEED = 50f;
    public static final int ENEMY_HEALTH = 5;
    public static final float SHOOT_RATE = 12; // bullets for 1 minute
}
