package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Proyectil_Mother {
    static Texture texture;
    float x, y, w, h, v;
    List<Proyectil_Mother> proyectiles_Mother;
    List<Proyectil_Mother> proyectiles_MotherAEliminar = new ArrayList<>();

    Proyectil_Mother(float xMother, float yMother){
        x = xMother;
        y = yMother;
        w = 80;
        h = 120;
        v = 5;
        texture = new Texture("proyectil/bala.png");
        proyectiles_Mother = new ArrayList<>();
    }

    void render(SpriteBatch batch){
        for (Proyectil_Mother proyectil_mother : proyectiles_Mother) {
            proyectil_mother.update();
        }
        if (y==0) {
            for (Proyectil_Mother proyectil_mother : proyectiles_MotherAEliminar) {
                proyectiles_MotherAEliminar.remove(proyectil_mother);
            }
        }
        batch.draw(texture, x, y, w, h);
    }

    void update(){
        y -= v;
    }
}