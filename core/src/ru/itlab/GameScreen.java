package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

    Level level;
    SpriteBatch batch;
    MainActivity mainActivity;

    @Override
    public void show() {
        mainActivity = new MainActivity();
        level = new Level();
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        level.update(delta);
        level.render(batch);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        level.c.assetManager.dispose();
    }
}
