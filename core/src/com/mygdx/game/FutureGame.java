package com.mygdx.game;

import com.badlogic.gdx.Game;

import com.mygdx.game.Screen.MainScreen;

public class FutureGame extends Game {


    @Override
    public void create() {
        setScreen(new MainScreen());
    }


//
//		Vector2 v1 = new Vector2(2,2);
//		Vector2 v2 = new Vector2(2, 5);
//		v1.add(v2);
//		System.out.println("v1 add v2 v1.x " + v1.x + " v1.y = " + v1.y);
//		Vector2 v3 = v2.add(v2);
//		System.out.println("v1 add v2 v1.x " + v1.x + " v1.y = " + v1.y);
//		System.out.println("v1 add v2 v.3x = " + v3.x + " v3.y " + v3.y);
//
//		v1.set(7,2);
//		v2.set(3,3);
//		v1.sub(v2);
//		System.out.println(v1);
//		System.out.println(v1.len());
//		System.out.println(v1.nor());
//
//		v1.set(2,5);
//		v2.set(4,2);
//
//		v1.nor();
//		v2.nor();
//		System.out.println(Math.acos(v1.dot(v2)));
	}


