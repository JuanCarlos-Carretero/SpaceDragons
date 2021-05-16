package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;


public class Mundo {
    FondoPantalla fondo;
    Jugador jugador;
    HUD hud;
    int vmuerto = 0;

    List<Meteorito> meteoritos = new ArrayList<>();
    List<Meteorito> meteoritosAEliminar = new ArrayList<>();
    List<Alien> aliens = new ArrayList<>();
    List<Alien> aliensAEliminar = new ArrayList<>();
    List<Proyectil> proyectilAEliminar = new ArrayList<>();
    List<Items> items = new ArrayList<>();
    List<Items> itemsAEliminar = new ArrayList<>();

    Temporizador temporizadorNuevoMeteorito;
    Temporizador temporizadorNuevoAlien;


    public void create () {
        fondo = new FondoPantalla();
        jugador = new Jugador();


        meteoritos.add(new Meteorito());
        aliens.add(new Alien());

        temporizadorNuevoMeteorito = new Temporizador(700);
        temporizadorNuevoAlien = new Temporizador(120);

        hud = new HUD();

        Jugador.scoreboard = new Scoreboard();
    }

    void update(){
        Temporizador.tiempoJuego += 1;

        if(temporizadorNuevoMeteorito.suena()){
            meteoritos.add(new Meteorito());
        }
        if (temporizadorNuevoAlien.suena()){
            aliens.add(new Alien());
        }
        if (!Jugador.gameover) {
            jugador.update();
        }
        for (Meteorito meteorito: meteoritos) meteorito.update();
        for (Items item: items) item.update();
        for (Alien alien:aliens) alien.update();

        proyectilAEliminar.clear();
        aliensAEliminar.clear();

        for (Items item: items) {
            if (Util.solapan(jugador.x, jugador.y, jugador.w, jugador.h, item.x, item.y, item.w, item.h)) {
                itemsAEliminar.add(item);
                jugador.puntos += 5;
                break;
            }
            if (!Jugador.gameover && !jugador.muerto && Util.solapan(item.x, item.y, item.w, item.h, jugador.x, jugador.y, jugador.w, jugador.h)) {

                if(Items.item == 5){
                    jugador.vidas++;
                }
                if(Items.item == 4){
                    jugador.vidas++;
                }
                if(Items.item == 3){
                    jugador.vidas++;
                }
                if(Items.item == 2){
                    jugador.vidas++;
                }
                if(Items.item == 1){
                    jugador.vidas++;
                }
            }
            if (item.y<=0) itemsAEliminar.add(item);
        }

        for (Meteorito meteorito: meteoritos) {
            for (Proyectil proyectil: jugador.proyectiles) {
                if (Util.solapan(proyectil.x, proyectil.y, proyectil.w, proyectil.h, meteorito.x, meteorito.y, meteorito.w, meteorito.h)) {
                    Items items = new Items(meteorito.x,meteorito.y);
                    proyectilAEliminar.add(proyectil);
                    meteoritosAEliminar.add(meteorito);
                    jugador.puntos += 5;
                    break;
                }
            }

            if (!Jugador.gameover && !jugador.muerto && Util.solapan(meteorito.x, meteorito.y, meteorito.w, meteorito.h, jugador.x, jugador.y, jugador.w, jugador.h)) {
                jugador.vidas--;
                jugador.muerto = true;
                jugador.respawn.activar();
                if (jugador.vidas == vmuerto){
                    Jugador.gameover = true;
                    Jugador.scoreboard.guardarPuntuacion(jugador.puntos);
                }
            }
            if (meteorito.y<=0) meteoritosAEliminar.add(meteorito);
        }
        for (Alien alien: aliens) {
            for (Proyectil proyectil : jugador.proyectiles) {
                if (Util.solapan(proyectil.x, proyectil.y, proyectil.w, proyectil.h, alien.x, alien.y, alien.w, alien.h)) {
                    proyectilAEliminar.add(proyectil);
                    aliensAEliminar.add(alien);
                    jugador.puntos += 5;
                    break;
                }
            }
        }
        for (Alien alien: aliens) {
            for (Proyectil proyectil: jugador.proyectiles) {
                if (Util.solapan(proyectil.x, proyectil.y, proyectil.w, proyectil.h, alien.x, alien.y, alien.w, alien.h)) {
                    proyectilAEliminar.add(proyectil);
                    aliensAEliminar.add(alien);
                    jugador.puntos += 5;
                    break;
                }
            }

            if (!Jugador.gameover && !jugador.muerto && Util.solapan(alien.x, alien.y, alien.w, alien.h, jugador.x, jugador.y, jugador.w, jugador.h)) {
                jugador.vidas--;
                jugador.muerto = true;
                jugador.respawn.activar();
                if (jugador.vidas == vmuerto){
                    Jugador.gameover = true;
                    Jugador.scoreboard.guardarPuntuacion(jugador.puntos);
                }
            }

            if (alien.y<=0) aliensAEliminar.add(alien);
        }

        for (Proyectil proyectil:proyectilAEliminar) jugador.proyectiles.remove(proyectil);
        for (Alien alien:aliensAEliminar) aliens.remove(alien);
        for (Meteorito meteorito:meteoritosAEliminar) meteoritos.remove(meteorito);
        for (Items item:itemsAEliminar) items.remove(item);
    }
    public void render(SpriteBatch batch, BitmapFont font) {
        fondo.render(batch);
        jugador.render(batch);
        hud.render(batch);

        hud.jugador(jugador.vidas, jugador.puntos);

        for (Meteorito meteorito: meteoritos) meteorito.render(batch);
        for (Items item: items) item.render(batch);
        for(Alien alien:aliens) alien.render(batch);

        font.getData().setScale(2f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
    }
}
