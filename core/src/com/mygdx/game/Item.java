package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Item {
    static int item;
    float x, y, w, h, v;

    Texture corazon = new Texture("items/corazon.png");
    // Texture velocidad = new Texture("items/velocidad.png");
    // Texture alcance = new Texture("items/alcance.png");
    // Texture ulti = new Texture("items/ulti.png");

    Item(float x, float y){
        this.x = x;
        this.y = y;
        w = 50;
        h = 70;
        v = 1;

        item = 5; //SpaceDragons.random.nextInt(5);
    }

    void render(SpriteBatch batch){
        if (item == 5){
            batch.draw(corazon, x, y, w, h);
        }
        /* if (item == 4){
            batch.draw(velocidad, x, y, w, h);
        }
        if (item == 3){
            batch.draw(alcance, x, y, w, h);
        }
        if (item == 2){
            batch.draw(ulti, x, y, w, h);
        }
        if (item == 1){
            batch.draw(corazon, x, y, w, h);
        } */
    }

    public void update() {
        y -= v;
    }
}
