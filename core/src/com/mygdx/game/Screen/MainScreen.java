package com.mygdx.game.Screen;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Base.BaseScreen;
import com.mygdx.game.Spirite.Background;
import com.mygdx.game.math.Rect;
public class MainScreen extends BaseScreen {


    private Texture img;
    private Texture bg;
    private Background background;

    @Override
    public void show() {
        super.show();
        img = new Texture("SpaceWall.jpg");
        bg = new Texture("bg.png");
        background = new Background(bg);
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        bg.dispose();
        super.dispose();
    }

}
