package com.mygdx.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Base.BaseScreen;
import com.mygdx.game.Sprite.Background;
import com.mygdx.game.Sprite.Bullet;
import com.mygdx.game.Sprite.EmenyShip;
import com.mygdx.game.Sprite.GameOver;
import com.mygdx.game.Sprite.NewGame;
import com.mygdx.game.Sprite.ShipSpace;
import com.mygdx.game.Sprite.Star;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;
import com.mygdx.game.pool.EnemyPool;
import com.mygdx.game.pool.ExplosionPool;
import com.mygdx.game.region.EnemyEmitter;

import java.util.List;

public class GameScreen extends BaseScreen {


    public GameScreen(Game game) {
        this.game = game;
    }

    private enum State {PLAYING, GAME_OVER}
    private Game game;
    private boolean pressed;
    private int pointer;

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private Star[] stars;
    private ShipSpace shipSpace;
    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    private ExplosionPool explosionPool;
    private Music music;
    private EnemyEmitter enemyEmitter;
    private State state;
    private GameOver gameOver;
    private NewGame newGame;

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
        explosionPool = new ExplosionPool(atlas);
        enemyPool = new EnemyPool(bulletPool,explosionPool,worldBounds);
        shipSpace = new ShipSpace(atlas, bulletPool,explosionPool);
        enemyEmitter = new EnemyEmitter(atlas,enemyPool);
        gameOver = new GameOver(atlas);
        newGame = new NewGame(atlas, game);

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();
        state = State.PLAYING;

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollision();
        free();
        startTouch();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        shipSpace.resize(worldBounds);
        enemyEmitter.resize(worldBounds);
        gameOver.resize(worldBounds);
        newGame.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        enemyPool.dispose();
        explosionPool.dispose();
        music.dispose();
        shipSpace.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.PLAYING) {
            shipSpace.keyDown(keycode);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.PLAYING) {
            shipSpace.keyUp(keycode);
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (state == State.PLAYING) {
            shipSpace.touchDown(touch, pointer, button);
        }
        return false;

    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (state == State.PLAYING) {
            shipSpace.touchUp(touch, pointer, button);
        }
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        explosionPool.updateActiveSprites(delta);
        if (state == State.PLAYING) {
            shipSpace.update(delta);
            bulletPool.updateActiveSprites(delta);
            enemyPool.updateActiveSprites(delta);
            enemyEmitter.generate(delta);
        }
    }

    private void checkCollision() {
        if (state != State.PLAYING) {
            return;
        }
        List<EmenyShip> enemyList = enemyPool.getActiveObjects();
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (EmenyShip emenyShip : enemyList) {
            float minDist = emenyShip.getHalfWidth() + shipSpace.getHalfWidth();
            if (shipSpace.pos.dst(emenyShip.pos) < minDist) {
                emenyShip.destroy();
                shipSpace.damage(emenyShip.getDamage());
                continue;
            }
            for (Bullet bullet : bulletList) {
                if (bullet.getOwner() != shipSpace ||  bullet.isDestroyed()) {
                    continue;
                }
                if (emenyShip.isBulletCollision(bullet)) {
                    emenyShip.damage(bullet.getDamage());
                    bullet.destroy();
                }
            }
        }
        for (Bullet bullet : bulletList) {
            if (bullet.getOwner() == shipSpace || bullet.isDestroyed()) {
                continue;
            }
            if (shipSpace.isBulletCollision(bullet)) {
                shipSpace.damage(bullet.getDamage());
                bullet.destroy();
            }
        }
        if (shipSpace.isDestroyed()) {
            state = State.GAME_OVER;
        }
    }

    private void free() {
        bulletPool.freeAllDestroyed();
        enemyPool.freeAllDestroyed();
        explosionPool.freeAllDestroyed();
    }

    private void startTouch(){
        if(Gdx.input.isTouched(pointer)){
             if(state != State.PLAYING){
                game.setScreen(new GameScreen(game));
            }
        }
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        if (state == State.PLAYING) {
            shipSpace.draw(batch);
            bulletPool.drawActiveSprites(batch);
            enemyPool.drawActiveSprites(batch);
        } else if(state == State.GAME_OVER){
            gameOver.draw(batch);
            newGame.draw(batch);
        }
        explosionPool.drawActiveSprites(batch);
        batch.end();
    }
}
