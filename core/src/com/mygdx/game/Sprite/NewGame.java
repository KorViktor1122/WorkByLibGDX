package com.mygdx.game.Sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Base.Sprite;
import com.mygdx.game.math.Rect;

public class NewGame extends Sprite{

    private Game game;

    public NewGame(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("button_new_game"));
        this.game = game;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.08f);
        setTop(-0.1f);
    }

}
