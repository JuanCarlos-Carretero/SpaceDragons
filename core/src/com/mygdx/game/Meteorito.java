package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Meteorito {
    Texture textura;
    Texture textura_explosion;
    float x, y, w, h, v;
    int vida = 50;
    boolean muerto = false;



    Meteorito(){
        textura = new Texture("meteorito/meteorito.png");
        textura_explosion = new Texture("meteorito/meteorito3.png");
        x = SpaceDragons.random.nextInt(1580);
        y = 1080;
        w = 200;
        h = 200;
        v = 3;
        if (x<0) x = 0;
        if (x>1600) x = 1600;


    }

    void render(SpriteBatch batch) {
            batch.draw(textura, x, y, w, h);
    }

    void update(){
        y -= v;
    }
}