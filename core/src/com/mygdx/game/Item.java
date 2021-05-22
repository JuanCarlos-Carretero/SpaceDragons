package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Item {
    static int item;
    float x, y, w, h, v;

    Animaciones corazon = new Animaciones(8f,true,"items/corazon.png", "items/corazon1.png" );
    Animaciones velocidad = new Animaciones(8f,true,"items/velocidad.png", "items/velocidad1.png");
    Animaciones alcance = new Animaciones(8f,true,"items/alcance.png", "items/alcance1.png");

    Item(float x, float y){
        this.x = x;
        this.y = y;
        w = 50;
        h = 70;
        v = 1;

        item = SpaceDragons.random.nextInt(2);

    }

    void render(SpriteBatch batch){
        if (item == 0){
            batch.draw(corazon.getFrame(Temporizador.tiempoJuego), x, y, w, h);
        }
        if (item == 1){
            batch.draw(alcance.getFrame(Temporizador.tiempoJuego), x, y, w, h);
        }
        if (item == 2){
            batch.draw(velocidad.getFrame(Temporizador.tiempoJuego), x, y, w, h);
        }

    }

    public void update() {
        y -= v;
    }
}
