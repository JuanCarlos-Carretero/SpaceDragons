package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD {
    Texture textureHUD = new Texture("hud/HUD.jpg");
    Texture caradragon = new Texture("dragon/cara-dragon.png");
    Texture teclas_awsd = new Texture("hud/teclas_awsd.png");
    Texture teclas_space = new Texture("hud/teclas_space.png");
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
        x = 1720;
        y = 0;
        w = 200;
        h = 1080;

        xc= 1770;
        yc= 1000;
        wc= 100;
        hc= 40;
    }

    void render(SpriteBatch batch){
        batch.draw(textureHUD, x, y, w, h);
        batch.draw(caradragon, x+25, y+850, 150, 150);
        batch.draw(teclas_awsd, x+15, y+600, 175, 175);
        batch.draw(teclas_space, x+15, y+500, 175, 175);

        if (corazones == 3){
            batch.draw(corazon3.getFrame(Temporizador.tiempoJuego), xc, yc, wc, hc);
        }
        if (corazones == 2){
            batch.draw(corazon2.getFrame(Temporizador.tiempoJuego), xc, yc, wc, hc);
        }
        if (corazones == 1){
            batch.draw(corazon1.getFrame(Temporizador.tiempoJuego), xc, yc, wc, hc);
        }
        if (corazones == 0){
            batch.draw(muerto.getFrame(Temporizador.tiempoJuego), 1745, 850, 150, 150);
        }
        font.draw(batch, "PUNTOS:\n"+ puntuacion, 1745, 840);

        font.getData().setScale(1.3f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
    }

    public void jugador(int vidas, int puntos) {
        corazones = vidas;
        puntuacion = puntos;
    }
}
