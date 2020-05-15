package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Base.BaseScreen;
import com.mygdx.game.Sprite.Background;
import com.mygdx.game.Sprite.EmenyShip;
import com.mygdx.game.Sprite.ShipSpace;
import com.mygdx.game.Sprite.Star;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;

public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private Star[] stars;


    private ShipSpace shipSpace;
    private BulletPool bulletPool;
    private EmenyShip emenyShip;

    private Music music;
    private Sound sound;

    @Override
    public void create() {

        music = Gdx.audio.newMusic(Gdx.files.internal("we_are_the_energy.mp3"));

        music.setVolume(10.0f);
        music.setLooping(true);
        music.play();
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("bg.png");
        background = new Background(bg);
        atlas = new TextureAtlas(Gdx.files.internal("mainAtlas.tpack"));
        stars = new Star[80];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        shipSpace = new ShipSpace(atlas, bulletPool);
        emenyShip = new EmenyShip(atlas);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        free();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        shipSpace.resize(worldBounds);
        emenyShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        music.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        shipSpace.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        shipSpace.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        shipSpace.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        shipSpace.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        bulletPool.updateActiveSprites(delta);
        shipSpace.update(delta);
    }

    private void free() {
        bulletPool.freeAllDestroyed();
    }


    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        bulletPool.drawActiveSprites(batch);
        shipSpace.draw(batch);
        emenyShip.draw(batch);
        batch.end();
    }
}
