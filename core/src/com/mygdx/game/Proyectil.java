package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Proyectil {
    static Texture texture;
    float x, y, w, h, v;

    Proyectil(float xNave, float yNave, String textura){
        x = xNave;
        y = yNave;
        w = 40;
        h = 80;
        v = 15;
        texture = new Texture(textura);
    }

    void render(SpriteBatch batch){
        batch.draw(texture, x, y, w, h);
    }

    void update(){
        y += v;
    }
}