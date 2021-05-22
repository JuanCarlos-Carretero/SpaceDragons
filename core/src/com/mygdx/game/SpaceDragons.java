package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;


public class SpaceDragons extends ApplicationAdapter {

	static SpriteBatch batch;
	Dragon_Menu dragonMenu1;
	Texture tmenu;
	Texture menuTransparente;
	Texture titulo;
	Animaciones press_start;
	Animaciones dragon;

	BitmapFont font;
	static Random random = new Random();
	Mundo mundo;
	static boolean menu = true;
	boolean pausa = true;
	Sound musica_menu;

	@Override
	public void create() {
		dragonMenu1 = new Dragon_Menu();
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		tmenu = new Texture("menu/menu.jpg");
		menuTransparente = new Texture("menu/back.png");
		dragon = new Animaciones(8f, true, "dragon/dragon_pos1.png", "dragon/dragon_pos2.png","dragon/dragon_pos3.png");
		titulo = new Texture("menu/Titulo.png");
		press_start = new Animaciones(8f,true,"menu/pressenter.png","menu/pressenter1.png","menu/pressenter2.png","menu/pressenter3.png");
		mundo = new Mundo();
		mundo.create(menu);

		musica_menu = Gdx.audio.newSound(Gdx.files.internal("Sound/Dance Of Death.mp3"));
		musica_menu.setLooping(musica_menu.play(0.2f),true);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1,1,1,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
			menu = false;
			pausa = false;
			musica_menu.stop();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
			pausa = !pausa;
		}
		if (!pausa) {
			mundo.update();
		}

		batch.begin();
		mundo.render(batch, font);
		Temporizador.tiempomenu += 1;

		if (menu){
			batch.draw(tmenu, 0, 0, 1920, 1080);

			dragonMenu1.render(batch);
			dragonMenu1.update();

			batch.draw(titulo,400,200,900,1100);
			batch.draw(press_start.getFrame(Temporizador.tiempomenu),600,-200,800,1000);

		} else {
			if (pausa){
				batch.draw(menuTransparente, 0, 0, 1920, 1080);
				font.draw(batch, "PAUSE", 1720/2f, 670);
				font.draw(batch, "PRESS KEY ENTER OR P", 720, 620);
				font.draw(batch, "FOR CONTINUE", 800, 570);
			}
		}
		batch.end();
	}
}