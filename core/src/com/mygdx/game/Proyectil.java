package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Proyectil {
    static Texture texture;
    float x, y, w, h, v;
    Temporizador tiempoentredisparos;

    Proyectil(float xNave, float yNave){
        x = xNave;
        y = yNave;
        w = 40;
        h = 80;
        v = 15;
        texture = new Texture("proyectil/fuego.png");
        tiempoentredisparos = new Temporizador(1);
    }

    void render(SpriteBatch batch){
        if (tiempoentredisparos.suena()) batch.draw(texture, x, y, w, h);

    }

    void update(){
        y += v;
    }
}