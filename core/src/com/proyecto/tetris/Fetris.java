package com.proyecto.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.pantallas.ScreenJuego;
import com.proyecto.pantallas.ScreenLobby;
import com.proyecto.red.Cliente;
import com.proyecto.utiles.Assets;
import com.proyecto.utiles.Mundo;

public class Fetris extends Game{
	private Cliente cliente;
	private boolean cambio;
	private ScreenJuego sj;
	private ScreenLobby sl;
	@Override
	public void create () {
		Assets.load();
		Assets.manager.finishLoading();
		
		Mundo.app = this;
		Mundo.batch= new SpriteBatch();
		cliente = new Cliente();
		cliente.crearHilo();
		this.setScreen(sl = new ScreenLobby());
	}

	@Override
	public void render () {
		super.render();
		if(cambio) {
			cambiar();
		}
	}
	
	@Override
	public void dispose () {
		Mundo.batch.dispose();
	}
	
	public void cambiar() {
		screen.dispose();
		this.setScreen(sj = new ScreenJuego());
		cambio=!cambio;
	}
	
	public void setCambio(boolean cambio) {
		this.cambio = cambio;
	}
	public ScreenJuego getSJ() {
		return sj;
	}
	public Cliente getCliente() {
		return cliente;
	}
}
