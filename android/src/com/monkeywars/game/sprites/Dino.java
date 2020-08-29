package com.monkeywars.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.monkeywars.game.animation.Animation;

public class Dino {
    private Vector3 position;
    private Vector3 velocity;
    private Texture dinoIdleTexture;
    private Texture dinoRunTexture;
    private Texture dinoJumpTexture;
    private Animation dinoAnimation;
    private Animation dinoRunAnimation;
    private Animation dinoJumpAnimation;
    private DinoState dinoState;
    private boolean reachedMaxHeight;
    private boolean shouldFall;

    public Dino(float x, float y, boolean opponent) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0,0);
        dinoIdleTexture = new Texture("dino/idle/dino_idle_spritesheet.png");
        dinoRunTexture = new Texture("dino/run/dino_run_spritesheet.png");
        dinoJumpTexture = new Texture("dino/jump/dino_jump_spritesheet.png");
        dinoAnimation = new Animation(new TextureRegion(dinoIdleTexture), 10, 1f);
        dinoRunAnimation = new Animation(new TextureRegion(dinoRunTexture), 8, 1f);
        if (opponent) {
            dinoRunAnimation.flip();
        }
        dinoJumpAnimation = new Animation(new TextureRegion(dinoJumpTexture), 12, 1f);
        setDinoState(DinoState.RUN);
        reachedMaxHeight = false;
        shouldFall = false;
    }

    public void update(float dt) {
        switch (dinoState) {
            case IDLE:
                dinoAnimation.update(dt);
                break;
            case RUN:
                dinoRunAnimation.update(dt);
                if (position.x > 20) {
                    position.x -= 10;
                }
                break;
            case JUMP:
                dinoJumpAnimation.update(dt);
                if (position.y <= Gdx.graphics.getWidth() / 4 && reachedMaxHeight == false && shouldFall == false) {
                    position.y += 30;
                } else {
                    reachedMaxHeight = true;
                    shouldFall = true;
                }
                if (position.y >= 110 && position.y <= 150 && reachedMaxHeight && shouldFall) {
                    reachedMaxHeight = false;
                    shouldFall = false;
                    position.y = 110;
                    velocity.scl(0);
                    setDinoState(DinoState.RUN);
                } else if (position.x >= Gdx.graphics.getHeight() / 2) {
                    velocity.add(0, -10, 0);
                    velocity.scl(dt);
                    position.add(velocity.x, velocity.y, 0);
                    velocity.scl(1 / dt);
                } else {
                    velocity.add(10, -10, 0);
                    velocity.scl(dt);
                    position.add(velocity.x, velocity.y, 0);
                    velocity.scl(1 / dt);
                }
                break;
            case OPPONENT:
                dinoRunAnimation.update(dt);

                if (position.x == -200) {
                    position.add(Gdx.graphics.getHeight(), 0, 0);
                } else {
                    position.add(-10, 0, 0);
                }
                break;
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        switch (dinoState) {
            case IDLE:
                return dinoAnimation.getFrame();
            case OPPONENT:
            case RUN:
                return dinoRunAnimation.getFrame();
            case JUMP:
                return dinoJumpAnimation.getFrame();
            default:
                return null;
        }
    }

    public DinoState getDinoState() {
        return dinoState;
    }

    public void setDinoState(DinoState dinoState) {
        this.dinoState = dinoState;
    }

    public void dispose() {
        dinoIdleTexture.dispose();
    }

    public enum DinoState {
        IDLE,
        RUN,
        JUMP,
        OPPONENT
    }
}
