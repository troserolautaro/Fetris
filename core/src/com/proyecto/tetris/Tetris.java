package com.proyecto.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.pantallas.ScreenJuego;
import com.proyecto.utiles.Mundo;

public class Tetris extends Game{
	
	@Override
	public void create () {
		Mundo.app = this;
		Mundo.batch= new SpriteBatch();
		this.setScreen(new ScreenJuego());
		
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		Mundo.batch.dispose();
		
	}
}
