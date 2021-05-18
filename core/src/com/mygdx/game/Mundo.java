package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;


public class Mundo {
    FondoPantalla fondo;
    Jugador jugador;
    HUD hud;
    int vmuerto = 2;

    List<Meteorito> meteoritos = new ArrayList<>();
    List<Meteorito> meteoritosAEliminar = new ArrayList<>();
    List<Alien> aliens = new ArrayList<>();
    List<Alien> aliensAEliminar = new ArrayList<>();
    List<Proyectil> proyectilAEliminar = new ArrayList<>();
    List<Item> items = new ArrayList<>();
    List<Item> itemAEliminar = new ArrayList<>();

    Temporizador temporizadorNuevoMeteorito;
    Temporizador temporizadorNuevoAlien;
    Scoreboard scoreboard;


    public void create() {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2f);

        inicializarJuego();
    }

    void inicializarJuego() {
        fondo = new FondoPantalla();
        jugador = new Jugador();
        meteoritos.add(new Meteorito());
        aliens.add(new Alien());

        temporizadorNuevoMeteorito = new Temporizador(700);
        temporizadorNuevoAlien = new Temporizador(120);

        hud = new HUD();

        scoreboard = new Scoreboard();

    }

    void update() {
        Temporizador.tiempoJuego += 1;

        if (temporizadorNuevoMeteorito.suena()) {
            meteoritos.add(new Meteorito());
        }
        if (temporizadorNuevoAlien.suena()) {
            aliens.add(new Alien());
        }
        if (!jugador.gameover) jugador.update();

        for (Meteorito meteorito : meteoritos) meteorito.update();
        for (Item item : items) item.update();
        for (Alien alien : aliens) alien.update();

        proyectilAEliminar.clear();
        aliensAEliminar.clear();

        for (Item item : items) { //  a b c d e
            if (Util.solapan(jugador.x, jugador.y, jugador.w, jugador.h, item.x, item.y, item.w, item.h)) {
                itemAEliminar.add(item);

                if (!jugador.gameover && !jugador.muerto) {
                    if (Item.item == 5) {
                        jugador.vidas++;
                    }
                    if (Item.item == 4) {
                        jugador.v++;
                    }
                    if (Item.item == 3) {
                        jugador.vidas++;
                    }
                    if (Item.item == 2) {
                        jugador.vidas++;
                    }
                    if (Item.item == 1) {
                        jugador.vidas++;
                    }
                }
            }

            if (item.y <= 0) itemAEliminar.add(item);
        }

        for (Meteorito meteorito : meteoritos) {
            for (Proyectil proyectil : jugador.proyectiles) {
                if (Util.solapan(proyectil.x, proyectil.y, proyectil.w, proyectil.h, meteorito.x, meteorito.y, meteorito.w, meteorito.h)) {
                    items.add(new Item(meteorito.x, meteorito.y));
                    proyectilAEliminar.add(proyectil);
                    meteoritosAEliminar.add(meteorito);
                    jugador.puntos += 5;
                }
            }

            if (!jugador.gameover && !jugador.muerto && Util.solapan(meteorito.x, meteorito.y, meteorito.w, meteorito.h, jugador.x, jugador.y, jugador.w, jugador.h)) {
                jugador.vidas--;
                jugador.muerto = true;
                jugador.respawn.activar();
                if (jugador.vidas == vmuerto) {
                    jugador.gameover = true;
                }
            }
            if (meteorito.y <= 0) meteoritosAEliminar.add(meteorito);
        }

        for (Alien alien : aliens) {
            for (Proyectil proyectil : jugador.proyectiles) {
                if (Util.solapan(proyectil.x, proyectil.y, proyectil.w, proyectil.h, alien.x, alien.y, alien.w, alien.h)) {
                    proyectilAEliminar.add(proyectil);
                    aliensAEliminar.add(alien);
                    jugador.puntos += 5;
                }
            }

            if (!jugador.gameover && !jugador.muerto && Util.solapan(alien.x, alien.y, alien.w, alien.h, jugador.x, jugador.y, jugador.w, jugador.h)) {
                jugador.vidas--;
                jugador.muerto = true;
                jugador.respawn.activar();
                if (jugador.vidas == vmuerto) {
                    jugador.gameover = true;
                }
            }
            if (alien.y <= 0) aliensAEliminar.add(alien);
        }

        for (Proyectil proyectil : proyectilAEliminar) jugador.proyectiles.remove(proyectil);
        for (Alien alien : aliensAEliminar) aliens.remove(alien);
        for (Meteorito meteorito : meteoritosAEliminar) meteoritos.remove(meteorito);
        for (Item item : itemAEliminar) items.remove(item);

        if (jugador.gameover) {
            int result = scoreboard.update(jugador.puntos);
            if (result == 1) {
                inicializarJuego();
            } else if (result == 2) {
                Gdx.app.exit();
            }
        }
    }

    public void render(SpriteBatch batch, BitmapFont font) {

        fondo.render(batch);
        jugador.render(batch);
        hud.render(batch);

        hud.jugador(jugador.vidas, jugador.puntos);

        for (Meteorito meteorito : meteoritos) meteorito.render(batch);
        for (Item item : items) item.render(batch);
        for (Alien alien : aliens) alien.render(batch);

        font.getData().setScale(2f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        if (jugador.gameover) {
            scoreboard.render(batch, font);
        }
    }
}
