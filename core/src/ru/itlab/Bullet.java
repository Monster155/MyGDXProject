package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import ru.itlab.Enums.WalkState;

public class Bullet {

    public boolean active;
    private Vector2 position;
    WalkState walkState;
    Vector2 bS = Constants.B_SIZE;

    public Bullet(Vector2 position, WalkState walkState) {
        this.walkState = walkState;
        this.position = new Vector2(position.x, position.y);
        active = true;
    }

    public void update(float delta, Array<Enemy> enemies) {
        switch (walkState) {
            case LEFT:
                position.x -= delta * Constants.BULLET_SPEED;
                break;
            case LEFTandDOWN:
                position.x -= delta * Constants.BULLET_SPEED / Math.sqrt(2);
                position.y -= delta * Constants.BULLET_SPEED / Math.sqrt(2);
                break;
            case LEFTandUP:
                position.x -= delta * Constants.BULLET_SPEED / Math.sqrt(2);
                position.y += delta * Constants.BULLET_SPEED / Math.sqrt(2);
                break;
            case RIGHT:
                position.x += delta * Constants.BULLET_SPEED;
                break;
            case RIGHTandDOWN:
                position.x += delta * Constants.BULLET_SPEED / Math.sqrt(2);
                position.y -= delta * Constants.BULLET_SPEED / Math.sqrt(2);
                break;
            case RIGHTandUP:
                position.x += delta * Constants.BULLET_SPEED / Math.sqrt(2);
                position.y += delta * Constants.BULLET_SPEED / Math.sqrt(2);
                break;
            case UP:
                position.y += delta * Constants.BULLET_SPEED;
                break;
            case DOWN:
                position.y -= delta * Constants.BULLET_SPEED;
                break;
        }

        for (Enemy enemy : enemies) {
            if (Math.abs(position.x+bS.x/2-(enemy.position.x+enemy.eS.x/2)) <= enemy.eS.x+bS.x &&
                    Math.abs(position.y+bS.y/2-(enemy.position.y+enemy.eS.y/2)) <= enemy.eS.y+bS.y) {
                active = false;
                enemy.health -= Constants.BULLET_DAMAGE;
                break;
            }
        }


        if(position.x+bS.x/2 > Gdx.graphics.getWidth() || position.x+bS.x/2 < 0 ||
                position.y+bS.y/2 > Gdx.graphics.getHeight() || position.y+bS.y/2 < 0)
            active = false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(Constants.BULLET,
                position.x,
                position.y,
                Constants.BULLET.getWidth(),
                Constants.BULLET.getHeight());
    }
}
