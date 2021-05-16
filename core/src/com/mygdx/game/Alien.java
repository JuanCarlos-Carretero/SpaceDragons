package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Alien {
    Animaciones animacion = new Animaciones(15f,true,"enemigo/alien/alien1.png","enemigo/alien/alien2.png","enemigo/alien/alien3.png");
    float x, y, w, h, vx, vy;
    Temporizador cambioVelocidad;

    Alien(){
        x = SpaceDragons.random.nextInt(1730);
        y = 1080;
        w = 50;
        h = 70;
        vx = 2;
        vy = 1;
        cambioVelocidad = new Temporizador(60);
    }

    void render(SpriteBatch batch){
        batch.draw(animacion.getFrame(Temporizador.tiempoJuego), x, y, w, h);
    }

    public void update() {
        y += vy;
        x += vx;

        if(cambioVelocidad.suena()){
            vy = SpaceDragons.random.nextInt(6)-3;
            vx = SpaceDragons.random.nextInt(6)-3;
        }
        if(x < 0) x = 1650;
        if(x > 1650) x = 0;
        if(y > 1080) y = 1080;
    }
}