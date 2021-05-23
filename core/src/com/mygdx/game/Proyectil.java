package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Proyectil {
    static Texture texture;
    float x, y, w, h, v;

    Proyectil(float xNave, float yNave){
        x = xNave;
        y = yNave;
        w = 40;
        h = 80;
        v = 15;
        texture = new Texture("proyectil/fuego.png");
    }

    void render(SpriteBatch batch){
        batch.draw(texture, x, y, w, h);
    }

    void update(){
        y += v;
    }
}