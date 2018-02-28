package ru.itlab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GameScreen implements Screen {

    Level level;
    SpriteBatch batch;

    @Override
    public void show() {
        level = new Level();
        batch = new SpriteBatch();
        music();
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
    public void music(){
        Music music = Gdx.audio.newMusic(Gdx.files.internal("party.mp3"));
        music.setLooping(true);
        music.play();
    }
}
