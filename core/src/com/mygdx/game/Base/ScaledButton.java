package com.mygdx.game.Base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class ScaledButton extends Sprite {

    private static final float SCALE = 0.9f;

    private boolean pressed;
    private int pointer;

    public ScaledButton(TextureRegion region) {
        super(region);
    }

    @Override
    public void touchDown(Vector2 touch, int pointer, int button) {
        if (pressed || !isMe(touch)) {
            return;
        }
        this.pointer = pointer;
        pressed = true;
        float scale = SCALE;
    }

    @Override
    public void touchUp(Vector2 touch, int pointer, int button) {
        if (this.pointer != pointer || !pressed) {
            return;
        }
        if (isMe(touch)) {
            action();
        }
        pressed = true;
        float scale = 1f;
    }

    public abstract void action();
}
