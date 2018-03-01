package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Level {

    Character c;
    BitmapFont font;
    long shootStartTime = 0;
    private Array<Bullet> bullets = new Array<Bullet>();
    public Array<Enemy> enes = new Array<Enemy>();
    //public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    public Level() {
        c = new Character();

        font = new BitmapFont();
        font.setColor(1f, 0f, 0f, 1f);

        enes.add(new Enemy(Gdx.graphics.getWidth()/2, 0));
        enes.add(new Enemy(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()));
        enes.add(new Enemy(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/2));
        enes.add(new Enemy(0, Gdx.graphics.getHeight()/2));
    }

    public void update(float delta){
        // Update Character
        c.update(delta);

        // Update Bullets
        if(Gdx.input.isKeyPressed(Input.Keys.J) && MathUtils.nanoToSec*(TimeUtils.nanoTime()-shootStartTime)
                > 60/Constants.SHOOT_RATE) {
            shootStartTime = TimeUtils.nanoTime();
            bullets.add(new Bullet(c.getPosition(), c.getWalkState()));
        }
        for (Bullet bullet : bullets) {
            bullet.update(delta, enes);
            if (!bullet.active)
                bullets.removeValue(bullet, false);
        }

        // Update Enemy
        for(Enemy enemy : enes){
            if(enemy.health < 1) enes.removeValue(enemy, false);
            enemy.update(delta, c.getPosition());
        }
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        Utils.Drawing(batch, Constants.BACKGROUND);
        c.render(batch);
        for(Enemy enemy : enes)
            enemy.render(batch);
        for (Bullet bullet : bullets)
            bullet.render(batch);
        if(enes.size < 1)
            font.draw(batch, "You WIN!", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        batch.end();
    }
}
