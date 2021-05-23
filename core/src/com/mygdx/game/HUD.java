package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD {
    Texture textureHUD = new Texture("hud/hud_completo.png");
    Texture caradragon = new Texture("dragon/cara-dragon.png");
    Texture teclas_awsd = new Texture("hud/teclas_awsd.png");
    Texture teclas_space = new Texture("hud/teclas_space.png");
    Texture teclas_p = new Texture("hud/teclas_p.png");
    BitmapFont font = new BitmapFont();

    int corazones;
    int puntuacion;

    Animaciones corazon1 = new Animaciones(10,true,"corazones/Corazon1.png","corazones/Corazon1-1.png");
    Animaciones corazon2 = new Animaciones(20,true,"corazones/Corazon2.png","corazones/Corazon2-1.png");
    Animaciones corazon3 = new Animaciones(30,true,"corazones/Corazon3.png","corazones/Corazon3-1.png");
    Animaciones muerto = new Animaciones(30,true,"corazones/Muerto.png","corazones/Muerto-1.png");

    float x, y, w, h;
    float xc, yc, wc, hc;

    HUD(){
        x = 0;
        y = 0;
        w = 1920;
        h = 1080;

        xc= 1550;
        yc= 970;
        wc= 130;
        hc= 50;
    }

    void render(SpriteBatch batch){
        batch.draw(textureHUD, x, y, w, h);
        batch.draw(caradragon, 1720, 850, 200, 200);
        font.draw(batch, "PUNTOS:\n"+ puntuacion, 1745, 840);
        batch.draw(teclas_awsd, 1720, 580, 200, 200);
        font.draw(batch, "UP", 1806, 760);
        font.draw(batch, "DOWN", 1790, 615);
        font.draw(batch, "LEFT", 1735, 700);
        font.draw(batch, "RIGHT", 1855, 700);
        batch.draw(teclas_space, 1720, 400, 200, 200);
        font.draw(batch, "SHOOT", 1785, 520);
        batch.draw(teclas_p, 1720, 200, 200, 200);
        font.draw(batch, "PAUSE", 1790, 320);

        if (corazones == 3 && !SpaceDragons.menu){
            batch.draw(corazon3.getFrame(Temporizador.tiempoJuego), xc, yc, wc, hc);
        }
        if (corazones == 2 && !SpaceDragons.menu){
            batch.draw(corazon2.getFrame(Temporizador.tiempoJuego), xc, yc, wc, hc);
        }
        if (corazones == 1 && !SpaceDragons.menu){
            batch.draw(corazon1.getFrame(Temporizador.tiempoJuego), xc, yc, wc, hc);
        }
        if (corazones == 0 && !SpaceDragons.menu){
            batch.draw(muerto.getFrame(Temporizador.tiempoJuego), 1745, 850, 150, 150);
        }

        if (!SpaceDragons.menu) font.draw(batch,""+Temporizador.tiempoJuego, 50, 1000);
        font.getData().setScale(1.3f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
    }

    public void jugador(int vidas, int puntos) {
        corazones = vidas;
        puntuacion = puntos;
    }
}
