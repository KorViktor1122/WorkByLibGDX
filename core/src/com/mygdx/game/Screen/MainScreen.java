package com.mygdx.game.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Base.BaseScreen;
import com.mygdx.game.Sprite.Background;
import com.mygdx.game.Sprite.Logo;
import com.mygdx.game.math.Rect;
public class MainScreen extends BaseScreen {

    private Texture img;
    private Texture bg;
    private Background background;


    private Logo logo;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        bg = new Texture("bg.png");
        background = new Background(bg);
        logo = new Logo(img);

    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw(delta);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        logo.touchDown(touch,pointer,button);
        return false;
    }

    @Override
    public void dispose() {
        img.dispose();
        bg.dispose();
        super.dispose();
    }


    private void update(float delta){
    logo.update(delta);
    }

    private void draw(float delta){
        batch.begin();
        background.draw(batch);
        logo.draw(batch);
        batch.end();
    }
}
