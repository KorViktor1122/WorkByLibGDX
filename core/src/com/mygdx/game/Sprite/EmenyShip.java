package com.mygdx.game.Sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Base.Sprite;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;

public class EmenyShip extends Sprite {

    private static final float SIZE = 0.2f;
    private static final float MARGIN = 0.1f;

  //  private final Vector2 v0;
    private final Vector2 v;

    private Rect worldBounds;

    public EmenyShip(TextureAtlas atlas) {
        super(atlas.findRegion("enemy0"),1,2,1);
        v = new Vector2(-1.5f,0);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v,delta);
        super.update(delta);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(SIZE);
        float posX = getWidth()/40;
        float posY = worldBounds.getTop()-MARGIN;
        pos.set(posX,posY);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
