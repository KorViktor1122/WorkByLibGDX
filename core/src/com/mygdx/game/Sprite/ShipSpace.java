package com.mygdx.game.Sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Base.Sprite;
import com.mygdx.game.math.Rect;

public class ShipSpace extends Sprite {

    private static final float V_LEN = 0.5f;
    private static final float MARGIN = 0.08f;

    private Vector2 starPossion;
    private Vector2 choosePoint;
    private Vector2 touch;
    private Vector2 commont;

    public ShipSpace(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
        starPossion = new Vector2();
        choosePoint = new Vector2();
        touch = new Vector2();
        commont = new Vector2();

    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.13f);
        setBottom(worldBounds.getBottom() + MARGIN);
        setRight((worldBounds.getRight()/2) - MARGIN);
        }

    @Override
    public void update(float delta) {
        super.update(delta);
        commont.set(touch);
        if(commont.sub(starPossion).len() > V_LEN){
            starPossion.add(choosePoint);
        } else {
            starPossion.set(touch);
            choosePoint.setZero();
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        touch.set(starPossion.x, Gdx.graphics.getHeight()-starPossion.y);
        System.out.println("touch X = " + touch.x + " touch Y = " + touch.y);
        choosePoint.set(touch.cpy().sub(starPossion));
        choosePoint.setLength(V_LEN);
        return super.touchDown(touch, pointer, button);
    }

}
