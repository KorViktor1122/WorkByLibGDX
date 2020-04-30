package com.mygdx.game;

import com.badlogic.gdx.Game;

import com.mygdx.game.Screen.MainScreen;

public class FutureGame extends Game {
    @Override
    public void create() {
        setScreen(new MainScreen());
    }
}


