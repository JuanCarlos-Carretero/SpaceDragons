package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Dragon_Menu {
    Animaciones animacion;
    float x, y, w, h, v;




    Dragon_Menu(){
        animacion = new Animaciones(8f, true, "dragon/dragon_pos1.png", "dragon/dragon_pos2.png","dragon/dragon_pos3.png");

        x = SpaceDragons.random.nextInt(1580);
        y = -50;
        w = 200;
        h = 200;
        v = 3;
    }

    void render(SpriteBatch batch) {
        batch.draw(animacion.getFrame(Temporizador.tiempomenu), x, y, w, h);
    }

    void update(){
        y += v;
        if (y > 1920){
            y =- 50;
            x = SpaceDragons.random.nextInt(1580);

        }
    }
}
