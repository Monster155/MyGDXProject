package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Utils {

    public static void Drawing(SpriteBatch batch, Texture texture){
        batch.draw(texture,
                0,
                0,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
    }

    public static void Drawing(SpriteBatch batch,  TextureRegion region, Vector2 position){
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
}
