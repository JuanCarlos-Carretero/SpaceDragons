package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Mother {
    Animaciones animacion = new Animaciones(15f,true,"mother/mother.png","mother/mother1.png","mother/mother2.png");
    Sound disparo;
    float vida, x, y, w, h, v;
    static boolean Mother_muerta;

    List<Proyectil_Mother> proyectil_mothers;
    List<Proyectil_Mother> proyectil_mothersAEliminar = new ArrayList<>();
    Temporizador disparos;

    Mother(){
        vida = 500;
        x = 140;
        y = 1180;
        w = 1380;
        h = 700;
        v = 1;

        disparo = Gdx.audio.newSound(Gdx.files.internal("Sound/laser.mp3")); ;
        proyectil_mothers = new ArrayList<>();
        disparos = new Temporizador(120,true);
        Mother_muerta = true;
    }

    void render(SpriteBatch batch){
        batch.draw(animacion.getFrame(Temporizador.tiempoJuego), x, y, w, h);

        if (!Mother_muerta){
            for (Proyectil_Mother proyectil_mother: proyectil_mothers) {
                proyectil_mother.render(batch);
            }
            for (Proyectil_Mother proyectil_mother: proyectil_mothersAEliminar){
                proyectil_mothersAEliminar.remove(proyectil_mother);
            }
        }
    }

    public void update() {
        if (disparos.suena()) {
                proyectil_mothers.add(new Proyectil_Mother(SpaceDragons.random.nextInt(1080), y*0.9f));
                disparo.play(0.2f);
        }
        for (Proyectil_Mother proyectil_mother: proyectil_mothers) {
            proyectil_mother.update();
        }

        if(y == 700){
            y = 701;
        } else {
            y -= v;
        }

    }
}