package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Base.BaseScreen;
import com.mygdx.game.Sprite.Background;
import com.mygdx.game.Sprite.ShipSpace;
import com.mygdx.game.Sprite.Star;
import com.mygdx.game.math.Rect;

public class GameScreen extends BaseScreen {

    private static final float V_LEN = 0.5f;

    private Texture bg;
    private TextureAtlas ship;
    private Background background;
    private ShipSpace shipSpace;
    private TextureAtlas atlas;
    private Star[] stars;


    @Override
    public void show() {
        super.show();
        bg = new Texture("bg.png");
        background = new Background(bg);
        ship = new TextureAtlas("mainAtlas.tpack");
        shipSpace = new ShipSpace(ship);
        atlas = new TextureAtlas(Gdx.files.internal("menuAtlas.tpack"));
        stars = new Star[256];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        shipSpace.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }

    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }

    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        shipSpace.draw(batch);
        batch.end();
    }
}
