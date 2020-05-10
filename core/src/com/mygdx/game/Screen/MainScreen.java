package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Base.BaseScreen;

public class MainScreen extends BaseScreen {

    private Texture img;

    private static final float V_Len = 0.8f;

    private Vector2 starPossion;
    private Vector2 choosePoint;
    private Vector2 touch;
    private Vector2 common;

    @Override
    public void show() {
        super.show();
        img = new Texture("mavr-2.jpg");
        starPossion = new Vector2();
        choosePoint = new Vector2();
        touch = new Vector2();
        common = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        common.set(touch);
        if(common.sub(starPossion).len() > V_Len) {
            starPossion.add(choosePoint);
        } else {
            starPossion.set(touch);
            choosePoint.setZero();
        }
        batch.begin();
        batch.draw(img, starPossion.x, starPossion.y);
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight()-screenY);
        System.out.println("touch X = " + touch.x + " touch Y = " + touch.y);
        choosePoint.set(touch.cpy().sub(starPossion));
        choosePoint.setLength(V_Len);
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
