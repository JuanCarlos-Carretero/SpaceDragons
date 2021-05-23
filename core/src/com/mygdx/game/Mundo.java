package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;


public class Mundo {
    FondoPantalla fondo;
    Jugador jugador;

    int vmuerto = 0;

    Sound mundo;

    List<Meteorito> meteoritos = new ArrayList<>();
    List<Meteorito> meteoritosAEliminar = new ArrayList<>();

    List<Alien> aliens = new ArrayList<>();
    List<Alien> aliensAEliminar = new ArrayList<>();

    List<Mother> mothers = new ArrayList<>();
    List<Mother> motherAEliminar = new ArrayList<>();

    List<Proyectil> proyectilAEliminar = new ArrayList<>();
    List<Proyectil_Mother> proyectil_motherAEliminar= new ArrayList<>();

    List<Item> items = new ArrayList<>();
    List<Item> itemAEliminar = new ArrayList<>();

    List<Explosion> explosiones = new ArrayList<>();
    List<Explosion> explosionesAEliminar = new ArrayList<>();

    Temporizador temporizadorNuevoMeteorito;
    Temporizador temporizadorNuevoAlien;
    Temporizador temporizadorNuevaMother;

    Scoreboard scoreboard;


    public void create(boolean menu) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2f);

        if (!menu) {
            mundo = Gdx.audio.newSound(Gdx.files.internal("Sound/Slammin.mp3"));
            mundo.setLooping(mundo.play(0.2f), true);
        }
        inicializarJuego();
    }

    void inicializarJuego() {
        fondo = new FondoPantalla();
        jugador = new Jugador();

        meteoritos.add(new Meteorito());
        aliens.add(new Alien());

        temporizadorNuevoMeteorito = new Temporizador(700);
        temporizadorNuevoAlien = new Temporizador(60);
        temporizadorNuevaMother = new Temporizador(2000);

        scoreboard = new Scoreboard();

        meteoritos.clear();
        aliens.clear();
        mothers.clear();
    }

    void update() {
        Temporizador.tiempoJuego += 1;

        if (temporizadorNuevaMother.suena() && mothers.size()<1){
            mothers.add(new Mother());
            Mother.Mother_muerta = false;
        }

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
        for (Mother mother : mothers) mother.update();

        proyectilAEliminar.clear();
        aliensAEliminar.clear();

        for (Item item : items) {
            if (Util.solapan(jugador.x, jugador.y, jugador.w, jugador.h, item.x, item.y, item.w, item.h)) {
                itemAEliminar.add(item);
                if (!jugador.gameover && !jugador.muerto) {
                    if (Item.item == 0) {
                        jugador.vidas++;
                    }
                    if (Item.item == 1) {
                        if (jugador.alcance < 400) {
                            jugador.alcance *= 1.5f;
                        } else if (jugador.alcance >= 400){
                            jugador.alcance = 400;
                        }
                    }
                    if (Item.item == 2) {
                        if (jugador.velocidad < 15) {
                            jugador.velocidad *= 1.5f;
                        }else if (jugador.velocidad >= 15){
                            jugador.velocidad = 15;
                        }
                    }
                }
            }
            if (item.y <= 0) itemAEliminar.add(item);
        }

        for (Meteorito meteorito : meteoritos) {
            for (Proyectil proyectil : jugador.proyectiles) {
                if (Util.solapan(proyectil.x, proyectil.y, proyectil.w, proyectil.h, meteorito.x, meteorito.y, meteorito.w, meteorito.h)) {
                    proyectilAEliminar.add(proyectil);
                    if(meteorito.vida>0) {
                        meteorito.vida--;
                    } else if (meteorito.vida == 0){
                        meteorito.muerto = true;
                    }
                    if (meteorito.muerto){
                        explosiones.add(new Explosion(meteorito.x, meteorito.y, meteorito.w, meteorito.h));
                        meteoritosAEliminar.add(meteorito);
                        items.add(new Item(meteorito.x, meteorito.y));
                        jugador.puntos += 10;
                    }
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
                    jugador.puntos += 50;
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
        for (Mother mother : mothers) {
            for (Proyectil proyectil : jugador.proyectiles) {
                if (Util.solapan(proyectil.x, proyectil.y, proyectil.w, proyectil.h, mother.x, mother.y, mother.w, mother.h)) {
                    proyectilAEliminar.add(proyectil);
                    if(mother.vida>0) {
                        mother.vida--;
                    } else if (mother.vida == 0){
                        mother.Mother_muerta = true;
                    }
                    if (mother.Mother_muerta){
                        explosiones.add(new Explosion(mother.x, mother.y, mother.w, mother.h));
                        motherAEliminar.add(mother);
                        jugador.puntos += 200;
                    }
                }
            }
            for (Proyectil_Mother proyectil_mother : mother.proyectil_mothers) {
                if (!jugador.gameover && !jugador.muerto && (Util.solapan(mother.x, mother.y, mother.w, mother.h, jugador.x, jugador.y, jugador.w, jugador.h) || Util.solapan(proyectil_mother.x, proyectil_mother.y, proyectil_mother.w, proyectil_mother.h, jugador.x, jugador.y, jugador.w, jugador.h))) {
                    jugador.vidas--;
                    jugador.muerto = true;
                    jugador.respawn.activar();
                    if (jugador.vidas == vmuerto) {
                        jugador.gameover = true;
                    }
                }
            }


        }

        for (Explosion explosion : explosiones) {
            if (explosion.texplosion.suena()){
                explosionesAEliminar.add(explosion);
            }
        }

        for (Explosion explosion : explosionesAEliminar) explosiones.remove(explosion);
        for (Mother mother : motherAEliminar) mothers.remove(mother);
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

        for (Explosion explosion :explosiones) {
            explosion.render(batch);
        }

        for (Mother mother :mothers) {
            mother.render(batch);
        }

        SpaceDragons.hud.jugador(jugador.vidas, jugador.puntos);

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