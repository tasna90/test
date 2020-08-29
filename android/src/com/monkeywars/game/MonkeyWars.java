package com.monkeywars.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.monkeywars.game.states.GameStateManager;
import com.monkeywars.game.states.menu.MenuState;

public class MonkeyWars extends ApplicationAdapter {

	private GameStateManager gameStateManeger;
	private SpriteBatch batch;
	private Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameStateManeger = new GameStateManager();
		//Gdx.gl.glClearColor(1,0,0,1);
		gameStateManeger.push(new MenuState(gameStateManeger));
	}

	@Override
	public void render () {
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManeger.update(Gdx.graphics.getDeltaTime()); // update first, then render the game states.
		gameStateManeger.render(batch);
	}
}
