package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;


public class SpaceDragons extends ApplicationAdapter {

	static SpriteBatch batch;

	BitmapFont bitmapFont;
	static Random random = new Random();
	Mundo mundo;

	@Override
	public void create() {
		batch = new SpriteBatch();
		bitmapFont = new BitmapFont();
		bitmapFont.setColor(Color.WHITE);

		mundo = new Mundo();
		mundo.create();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mundo.update();

		batch.begin();
		mundo.render(batch, bitmapFont);
		if (Jugador.gameover){
			Jugador.scoreboard.render(batch, bitmapFont);
		}
		batch.end();
	}
}