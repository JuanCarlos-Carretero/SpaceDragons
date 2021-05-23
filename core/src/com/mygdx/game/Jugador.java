package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    boolean gameover;
    Animaciones animacion = new Animaciones(8f, true, "dragon/dragon_pos1.png", "dragon/dragon_pos2.png","dragon/dragon_pos3.png");
    float x, y, w, h, v, a, velocidad, alcance;
    int vidas = 3;
    int puntos = 0;
    int duracion_mejora;
    boolean muerto = false;

    Sound sonidoLlamarada;

    List<Proyectil> proyectiles;
    List<Proyectil> proyectilAEliminar = new ArrayList<>();

    Temporizador respawn;
    Temporizador talcance;
    Temporizador tvelocidad;

    Jugador(){
        x = 500;
        y = 100;
        w = 200;
        h = 150;
        v = 8;
        a = 200;

        velocidad = v;
        alcance = a;

        duracion_mejora = 1400;

        proyectiles = new ArrayList<>();

        respawn = new Temporizador(120, false);
        talcance = new Temporizador(duracion_mejora, true);
        tvelocidad = new Temporizador(duracion_mejora, true);

        sonidoLlamarada = Gdx.audio.newSound(Gdx.files.internal("Sound/llamarada.mp3"));
        sonidoLlamarada.setLooping(sonidoLlamarada.play(0.1f),true);
        sonidoLlamarada.pause();
    }

    void render(SpriteBatch batch){

        if (muerto) batch.setColor(1, 1, 1, 0.25f);
        SpaceDragons.batch.draw(animacion.getFrame(Temporizador.tiempoJuego), x, y, w, h);
        if (muerto) batch.setColor(1, 1, 1, 1);

        for (Proyectil proyectil: proyectiles) {
            proyectil.render(batch);
        }

        for (Proyectil proyectil: proyectiles) {
            if (proyectil.y > y + alcance) {
                proyectilAEliminar.add(proyectil);
            }
        }

        for (Proyectil proyectil: proyectilAEliminar){
            proyectiles.remove(proyectil);
        }
    }

    void update(){
        for (Proyectil proyectil: proyectiles) {
            proyectil.update();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) x += v;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) x -= v;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) y += v;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) y -= v;

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            proyectiles.add(new Proyectil(x+(w/2)*0.82f, y+h*0.9f));
            sonidoLlamarada.resume();
        }else{
            sonidoLlamarada.pause();
        }

        if(x < 0) x = 1530;
        if(x > 1530) x = 0;
        if(y < 0) y = 0;
        if(y > 1080-h) y = 1080-h;
        if(vidas>=3)vidas = 3;

        if (respawn.suena()) {
            muerto = false;
        }

        if (talcance.suena()) {
            alcance = a;
        }
        if (tvelocidad.suena()) {
            velocidad = v;
        }
    }
}