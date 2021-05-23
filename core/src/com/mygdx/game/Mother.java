package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Mother {
    Animaciones animacion = new Animaciones(15f,true,"mother/mother.png","mother/mother1.png","mother/mother2.png");
    Sound disparo;
    float vida, x, y, w, h, v;
    boolean muerta = false;

    List<Proyectil> proyectiles;
    Temporizador disparos;

    Mother(){
        vida = 500;
        x = 140;
        y = 1180;
        w = 1380;
        h = 700;
        v = 1;

        proyectiles = new ArrayList<>();
        disparos = new Temporizador(150,true);
    }

    void render(SpriteBatch batch){
        batch.draw(animacion.getFrame(Temporizador.tiempoJuego), x, y, w, h);

        for (Proyectil proyectil: proyectiles) {
            proyectil.render(batch);
        }
    }

    public void update() {
        if (disparos.suena()) {
            proyectiles.add(new Proyectil(x+(w/2)*0.82f, y+h*0.9f,"proyectil/bala.png"));
            disparo.play();
        }
        if(y == 700){
            y = 701;
        } else {
            y -= v;
        }

    }
}