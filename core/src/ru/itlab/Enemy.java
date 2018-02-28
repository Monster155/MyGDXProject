package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import ru.itlab.Enums.WalkState;
import sun.rmi.runtime.Log;

import static ru.itlab.Enums.WalkState.STAND;


public class Enemy {

    final long startTime;
    public Vector2 position;
    public int health;
    private WalkState walkState;
    Assets assets;
    AssetManager assetManager;
    Vector2 eS = Constants.E_SIZE;
    Vector2 cS = Constants.C_SIZE;
    TextureRegion region;
    TextureAtlas atlas;
    int lastPosition = 2;
    long walkStartTime;

    public Enemy(int x, int y) {
        walkState = WalkState.STAND;
        position = new Vector2(x, y);
        startTime = TimeUtils.nanoTime();
        health = Constants.ENEMY_HEALTH;

        assetManager = new AssetManager();
        assetManager.load(Constants.C_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();
        atlas = assetManager.get(Constants.C_ATLAS);
        assets = new Assets(atlas);
    }

    public void update(float delta, Vector2 cpos) {
        if(walkState == STAND)walkStartTime = TimeUtils.nanoTime();
        if(position.x+eS.x/2 < cpos.x){ // Enemy in left from Character
            if(position.y+eS.y/2 < cpos.y){ // Enemy under Character
                position.x += delta * Constants.ENEMY_SPEED / Math.sqrt(2);
                position.y += delta * Constants.ENEMY_SPEED / Math.sqrt(2);
            } else if(position.y > cpos.y+cS.y/2){ // Enemy higher than Character
                position.x += delta * Constants.ENEMY_SPEED / Math.sqrt(2);
                position.y -= delta * Constants.ENEMY_SPEED / Math.sqrt(2);
            } else { // Enemy Y pos = Character Y pos
                position.x += delta * Constants.ENEMY_SPEED;
            }
        } else if(position.x > cpos.x+cS.x/2){ // Enemy in right from Character
            if(position.y+eS.y/2 < cpos.y){ // Enemy under Character
                position.x -= delta * Constants.ENEMY_SPEED / Math.sqrt(2);
                position.y += delta * Constants.ENEMY_SPEED / Math.sqrt(2);
            } else if(position.y > cpos.y+cS.y/2){ // Enemy higher than Character
                position.x -= delta * Constants.ENEMY_SPEED / Math.sqrt(2);
                position.y -= delta * Constants.ENEMY_SPEED / Math.sqrt(2);
            } else { // Enemy Y pos = Character Y pos
                position.x -= delta * Constants.ENEMY_SPEED;
            }
        } else { // Enemy X pos = Character X pos
            if(position.y+eS.y/2 < cpos.y){ // Enemy under Character
                position.y += delta * Constants.ENEMY_SPEED;
            } else if(position.y > cpos.y+cS.y/2){ // Enemy higher than Character
                position.y -= delta * Constants.ENEMY_SPEED;
            } else { // Enemy Y pos = Character Y pos
                Gdx.app.debug("Enemy", "I caught Character");
            }
        }
    }

    public void render(SpriteBatch batch) {
        anims();
        Utils.Drawing(batch, region, position);
    }

    public void anims(){
        float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
        switch (walkState) {
            case LEFT:
                region = (TextureRegion) assets.walkingLeftAnimation.getKeyFrame(walkTimeSeconds);
                lastPosition = 4;
                break;
            case RIGHT:
                region = (TextureRegion) assets.walkingRightAnimation.getKeyFrame(walkTimeSeconds);
                lastPosition = 6;
                break;
            case DOWN:
                region = (TextureRegion) assets.walkingDownAnimation.getKeyFrame(walkTimeSeconds);
                lastPosition = 2;
                break;
            case UP:
                region = (TextureRegion) assets.walkingUpAnimation.getKeyFrame(walkTimeSeconds);
                lastPosition = 8;
                break;
            case STAND:
                switch(lastPosition){
                    case 4:region = assets.left;break;
                    case 6:region = assets.right;break;
                    case 8:region = assets.up;break;
                    case 2:region = assets.down;break;
                }
                break;
        }
    }
}
