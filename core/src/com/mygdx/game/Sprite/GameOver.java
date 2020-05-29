package com.mygdx.game.Sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Base.ScaledButton;
import com.mygdx.game.Base.Sprite;
import com.mygdx.game.Screen.GameScreen;
import com.mygdx.game.Screen.MainScreen;
import com.mygdx.game.math.Rect;

public class GameOver extends Sprite {



    public GameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.08f);
        setTop(0.04f);
    }
}

