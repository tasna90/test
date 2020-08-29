package com.monkeywars.game.states.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.monkeywars.game.states.GameStateManager;
import com.monkeywars.game.states.State;
import com.monkeywars.game.states.play.PlayState;

public class MenuState extends State {

    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gamneStateManager) {
        super(gamneStateManager);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {
        this.background.dispose();
        this.playButton.dispose();
    }
}
