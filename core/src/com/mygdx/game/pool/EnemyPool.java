package com.mygdx.game.pool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.Base.SpritesPool;
import com.mygdx.game.Sprite.EmenyShip;
import com.mygdx.game.math.Rect;

public class EnemyPool extends SpritesPool<EmenyShip> {
    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private Rect worldBounds;
    private Sound sound;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
    }

    @Override
    public void updateActiveSprites(float delta) {
        super.updateActiveSprites(delta);
    }

    @Override
    protected EmenyShip newObject() {
        return new EmenyShip( bulletPool, explosionPool, worldBounds, sound);
    }

    @Override
    public void dispose() {
        super.dispose();
        sound.dispose();
    }
}
