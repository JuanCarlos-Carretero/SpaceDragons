package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Meteorito {
    Animaciones animacion = new Animaciones(15f,true,"meteorito/meteorito.png");
    float x, y, w, h, v;

    Meteorito(){
        x = SpaceDragons.random.nextInt(1580);
        y = 1080;
        w = 200;
        h = 200;
        v = 3;
        if (x<0) x = 0;
        if (x>1600) x = 1600;
    }

    void render(SpriteBatch batch){
        batch.draw(animacion.getFrame(Temporizador.tiempoJuego), x, y, w, h);
    }

    void update(){
        y -= v;
    }
}
