package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Explosion {
    static Texture textura_explosion = new Texture("meteorito/meteorito3.png");
    float x, y, w, h;
    Sound explosion;
    Temporizador texplosion;

    Explosion(float xMeteorito, float yMeteorito, float wMeteorito, float hMeteorito){
        texplosion = new Temporizador(20);
        x = xMeteorito;
        y = yMeteorito;
        w = wMeteorito;
        h = hMeteorito;
        explosion = Gdx.audio.newSound(Gdx.files.internal("Sound/explosion.mp3"));
        explosion.play(0.2f);
    }

    void render(SpriteBatch batch){
        batch.draw(textura_explosion, x, y, w, h);
    }
}