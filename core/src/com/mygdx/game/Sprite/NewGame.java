package com.mygdx.game.Sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Base.ScaledButton;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.math.Rect;

public class NewGame extends ScaledButton {

    private static final float ANIMATE_INTERVAL = 1f;

    private float animateTimer;
    private boolean scaleUp = true;
    private GameScreen gameScreen;

    public NewGame(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void update(float delta) {
        animateTimer += delta;
        if (animateTimer >= ANIMATE_INTERVAL) {
            animateTimer = 0f;
            scaleUp = !scaleUp;
        }
        if (scaleUp) {
            setScale(getScale() + 0.003f);
        } else {
            setScale(getScale() - 0.003f);
        }
    }


    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.05f);
        setTop(0.1f);
    }

    @Override
    public void action() {
        gameScreen.startNewGame();
    }
}
