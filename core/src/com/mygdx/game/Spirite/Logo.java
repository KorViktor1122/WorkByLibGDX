package com.mygdx.game.Spirite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Base.Sprite;
import com.mygdx.game.math.Rect;

public class Logo extends Sprite {

    private static final float V_Len = 0.007f;

    private Vector2 touch;
    private Vector2 speed;
    private Vector2 Common;


    public Logo(Texture region) {
        super( new  TextureRegion(region));
        touch = new Vector2();
        speed = new Vector2();
        Common = new Vector2();
    }

    @Override
    public void update(float delta) {
      Common.set(touch);
              if(Common.sub(pos).len() > V_Len){
                  pos.add(speed);
              } else {
                  pos.set(touch);
                  speed.setZero();
              }
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.3f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
       this.touch.set(touch);
       speed.set(touch.sub(pos)).setLength(V_Len);
        return false;
    }
}
