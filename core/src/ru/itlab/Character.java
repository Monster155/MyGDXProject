package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import ru.itlab.Enums.WalkState;

import static ru.itlab.Enums.WalkState.*;
import static ru.itlab.Enums.WalkState.STAND;

public class Character {

    int lives;
    Vector2 position = new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);;
    TextureRegion region;
    TextureAtlas atlas;
    AssetManager assetManager;
    long walkStartTime;
    Assets assets;
    WalkState walkState, lastWalkState = WalkState.DOWN;

    public Character(){
        assetManager = new AssetManager();
        assetManager.load(Constants.C_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();
        atlas = assetManager.get(Constants.C_ATLAS);
        assets = new Assets(atlas);
    }

    public void update(float delta){
        if(walkState == STAND)walkStartTime = TimeUtils.nanoTime();
        move(delta);
    }

    public void move(float delta){
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        boolean left = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.D);
        boolean up = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.S);
        if (left && down && !up && !right) {
            //moving left and down
            walkState = LEFTandDOWN;
            if(!(position.x-delta * Constants.C_SPEED / Math.sqrt(2) < 0))
                position.x -= delta * Constants.C_SPEED / Math.sqrt(2);
            if(!(position.y-delta * Constants.C_SPEED / Math.sqrt(2) < 0))
                position.y -= delta * Constants.C_SPEED / Math.sqrt(2);
        } else if (down && !left && !up && !right) {
            // moving down
            walkState = DOWN;
            if(!(position.y-delta * Constants.C_SPEED < 0))
                position.y -= delta * Constants.C_SPEED;
        } else if (right && down && !up && !left) {
            // moving right and down
            walkState = RIGHTandDOWN;
            if(!(position.x+Constants.C_SIZE.x+delta * Constants.C_SPEED / Math.sqrt(2) > w))
                position.x += delta * Constants.C_SPEED / Math.sqrt(2);
            if(!(position.y-delta * Constants.C_SPEED / Math.sqrt(2) < 0))
                position.y -= delta * Constants.C_SPEED / Math.sqrt(2);
        } else if (left && !right && !up && !down) {
            //moving left
            walkState = LEFT;
            if(!(position.x-delta * Constants.C_SPEED < 0))
                position.x -= delta * Constants.C_SPEED;
        } else if (right && !left && !up && !down) {
            // moving right
            walkState = RIGHT;
            if(!(position.x+Constants.C_SIZE.x+delta * Constants.C_SPEED > w))
                position.x += delta * Constants.C_SPEED;
        } else if (left && up && !right && !down) {
            // moving left and up
            walkState = LEFTandUP;
            if (!(position.x - delta * Constants.C_SPEED / Math.sqrt(2) < 0))
                position.x -= delta * Constants.C_SPEED / Math.sqrt(2);
            if(!(position.y + Constants.C_SIZE.y + delta * Constants.C_SPEED / Math.sqrt(2) > h))
                position.y += delta * Constants.C_SPEED / Math.sqrt(2);
        } else if (up && !left && !right && !down) {
            // moving up
            walkState = UP;
            if(!(position.y+Constants.C_SIZE.y+delta * Constants.C_SPEED > h))
                position.y += delta * Constants.C_SPEED;
        } else if (right && up && !left && !down) {
            //moving right and up
            walkState = RIGHTandUP;
            if(!(position.x+Constants.C_SIZE.x+delta * Constants.C_SPEED / Math.sqrt(2) > w))
                position.x += delta * Constants.C_SPEED / Math.sqrt(2);
            if(!(position.y+Constants.C_SIZE.y+delta * Constants.C_SPEED / Math.sqrt(2) > h))
                position.y += delta * Constants.C_SPEED / Math.sqrt(2);
        } else {
            // standing
            walkState = STAND;
        }
    }

    public void anim() {
        float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
        if(walkState != STAND)lastWalkState = walkState;
        switch (walkState) {
            case LEFT:
                region = (TextureRegion) assets.walkingLeftAnimation.getKeyFrame(walkTimeSeconds);
                break;
            case RIGHT:
                region = (TextureRegion) assets.walkingRightAnimation.getKeyFrame(walkTimeSeconds);
                break;
            case RIGHTandDOWN:
                region = (TextureRegion) assets.walkingRightAnimation.getKeyFrame(walkTimeSeconds);
                break;
            case RIGHTandUP:
                region = (TextureRegion) assets.walkingRightAnimation.getKeyFrame(walkTimeSeconds);
                break;
            case LEFTandUP:
                region = (TextureRegion) assets.walkingRightAnimation.getKeyFrame(walkTimeSeconds);
                break;
            case LEFTandDOWN:
                region = (TextureRegion) assets.walkingRightAnimation.getKeyFrame(walkTimeSeconds);
                break;
            case UP:
                region = (TextureRegion) assets.walkingUpAnimation.getKeyFrame(walkTimeSeconds);
                break;
            case DOWN:
                region = (TextureRegion) assets.walkingDownAnimation.getKeyFrame(walkTimeSeconds);
                break;
            case STAND:
                switch (lastWalkState) {
                    case RIGHT:
                        region = assets.right;
                        break;
                    case RIGHTandDOWN:
                        region = assets.right;
                        break;
                    case RIGHTandUP:
                        region = assets.right;
                        break;
                    case LEFT:
                        region = assets.left;
                        break;
                    case LEFTandDOWN:
                        region = assets.left;
                        break;
                    case LEFTandUP:
                        region = assets.left;
                        break;
                    case UP:
                        region = assets.up;
                        break;
                    case DOWN:
                        region = assets.down;
                        break;
                }
                break;
        }
    }

    public void render(SpriteBatch batch) {
        anim();
        batch.draw(
                region.getTexture(),
                position.x,
                position.y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }

    public void damaged(int damage) {
        lives -= damage;
        if (lives < 1) dead();
    }

    public void heal(int heal) {
        lives += heal;
        if (lives > Constants.C_MAX_LIVES) lives = Constants.C_MAX_LIVES;
    }

    public void dead() {
        lives = 0;
    }

    public Vector2 getPosition(){
        return position;
    }

    public WalkState getWalkState(){
        return lastWalkState;
    }
}
