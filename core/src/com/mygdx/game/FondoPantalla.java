package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FondoPantalla {
    Texture texture;
    float x, y;

    FondoPantalla(){
        texture =  new Texture("fondo/espacio.jpg");
        x = 0;
        y = 0;
    }

    void render(SpriteBatch batch){
        batch.draw(texture, x, y);
    }
}