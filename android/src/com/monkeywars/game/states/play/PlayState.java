package com.monkeywars.game.states.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.monkeywars.game.sprites.Dino;
import com.monkeywars.game.states.GameStateManager;
import com.monkeywars.game.states.State;

public class PlayState extends State {

    private Dino dino;
    private Dino dino2;
    private Texture background;

    public PlayState(GameStateManager gamneStateManager) {
        super(gamneStateManager);

        //our character
        dino = new Dino(20, 110, false);
        dino.setDinoState(Dino.DinoState.IDLE);

        //opponents
        dino2 = new Dino(Gdx.graphics.getHeight(), 110, true);
        dino2.setDinoState(Dino.DinoState.OPPONENT);

        //background
        background = new Texture("backgrounds/play_state_background.png");
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        //camera
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            dino.setDinoState(Dino.DinoState.JUMP);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        dino.update(dt);
        dino2.update(dt);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        spriteBatch.draw(dino.getTexture(), dino.getPosition().x, dino.getPosition().y, Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        spriteBatch.draw(dino2.getTexture(), dino2.getPosition().x, dino2.getPosition().y, Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        dino.dispose();
        background.dispose();
    }
}
