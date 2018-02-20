package ru.itlab;

import com.badlogic.gdx.Game;

public class MainActivity extends Game {
	@Override
	public void create() {
		setScreen(new GameScreen());
	}

}
