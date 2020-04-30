package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Base.BaseScreen;

public class MainScreen extends BaseScreen {

    private Texture img;

    private Vector2 starPossion;
    private Vector2 choosepoint;
    private Vector2 longvector;
    private Vector2 finishPossion;
    private Vector2 speedVektor;
    private Vector2 gravity;
    private Vector2 touch;


    @Override
    public void show() {
        super.show();
        img = new Texture("mavr-2.jpg");
        starPossion = new Vector2();
        longvector = new Vector2();
        choosepoint = new Vector2();
        finishPossion = new Vector2();
        speedVektor = new Vector2(1,1);
        gravity = new Vector2(0,-0.009f);
        touch = new Vector2();
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        starPossion = new Vector2(0,0);
        choosepoint = new Vector2(touch.set(touch.x, touch.y));
        starPossion.add(choosepoint);
        starPossion.len();
        System.out.println(" start X " + starPossion.x + " start Y " + starPossion.y);
        System.out.println("Lenght = " + starPossion.len());
        starPossion.add(speedVektor);
        speedVektor.add(gravity);
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
        starPossion.set(touch);
        return super.touchDown(screenX, screenY, pointer, button);
    }


}
