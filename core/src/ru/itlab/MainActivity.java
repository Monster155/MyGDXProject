package ru.itlab;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;

public class MainActivity extends Game {

	public GameScreen gs;
	public StartScreen ss;
	Music music;

	@Override
	public void create() {
		gs = new GameScreen();
		ss = new StartScreen();
		setScreen(gs);
		music();
	}

	@Override
	public void render() {
		super.render();
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && getScreen()==gs){
			setScreen(ss);
			stopMusic(true);
		}
		if(Gdx.input.isButtonPressed(Input.Keys.ENTER) && getScreen()==ss) {
			setScreen(gs);
			stopMusic(false);
		}
	}
	public void music(){
		music = Gdx.audio.newMusic(Gdx.files.internal("party.mp3"));
		music.setLooping(true);
		music.play();
	}
	public void stopMusic(boolean doStop){
		if(doStop)music.stop();
		else music.play();
		//music.dispose();
	}
}
