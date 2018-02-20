package ru.itlab;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

public class Assets {
    public final AtlasRegion left;
    public final AtlasRegion moveLeft;
    public final AtlasRegion right;
    public final AtlasRegion moveRight;
    public final AtlasRegion up;
    public final AtlasRegion moveUp;
    public final AtlasRegion down;
    public final AtlasRegion moveDown;

    public final Animation walkingLeftAnimation;
    public final Animation walkingRightAnimation;
    public final Animation walkingUpAnimation;
    public final Animation walkingDownAnimation;

    public Assets(TextureAtlas atlas) {
        left = atlas.findRegion(Constants.T_LEFT);
        moveLeft = atlas.findRegion(Constants.T_LEFT_M);
        right = atlas.findRegion(Constants.T_RIGHT);
        moveRight = atlas.findRegion(Constants.T_RIGHT_M);
        up = atlas.findRegion(Constants.T_BACK);
        moveUp = atlas.findRegion(Constants.T_BACK_M);
        down = atlas.findRegion(Constants.T_FRONT);
        moveDown = atlas.findRegion(Constants.T_FRONT_M);

        Array<AtlasRegion> walkingLeftFrames = new Array<AtlasRegion>();
        walkingLeftFrames.add(moveLeft);
        walkingLeftFrames.add(left);
        walkingLeftAnimation = new Animation(Constants.WALK_LOOP_DURATION, walkingLeftFrames, PlayMode.LOOP_PINGPONG);

        Array<AtlasRegion> walkingRightFrames = new Array<AtlasRegion>();
        walkingRightFrames.add(moveRight);
        walkingRightFrames.add(right);
        walkingRightAnimation = new Animation(Constants.WALK_LOOP_DURATION, walkingRightFrames, PlayMode.LOOP_PINGPONG);

        Array<AtlasRegion> walkingUpFrames = new Array<AtlasRegion>();
        walkingUpFrames.add(moveUp);
        walkingUpFrames.add(up);
        walkingUpAnimation = new Animation(Constants.WALK_LOOP_DURATION, walkingUpFrames, PlayMode.LOOP_PINGPONG);

        Array<AtlasRegion> walkingDownFrames = new Array<AtlasRegion>();
        walkingDownFrames.add(moveDown);
        walkingDownFrames.add(down);
        walkingDownAnimation = new Animation(Constants.WALK_LOOP_DURATION, walkingDownFrames, PlayMode.LOOP_PINGPONG);
    }
}
