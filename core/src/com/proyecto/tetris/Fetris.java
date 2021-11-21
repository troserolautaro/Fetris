package com.proyecto.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.pantallas.ScreenFin;
import com.proyecto.pantallas.ScreenJuego;
import com.proyecto.pantallas.ScreenLobby;
import com.proyecto.red.Cliente;
import com.proyecto.utiles.Assets;
import com.proyecto.utiles.Mundo;

public class Fetris extends Game{
	private Cliente cliente;
	private boolean cambio=false;
	private ScreenJuego sj;
	private ScreenLobby sl;
	private ScreenFin sf;
	private boolean fin=false;
	private boolean lobby = false;
	private boolean gano;
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
		if(fin) {
			fin();
		}
		if(lobby) {
			lobby();
		}
	}
	
	@Override
	public void dispose () {
		
	}
	
	private void cambiar() {
		screen.dispose();
		this.setScreen(sj = new ScreenJuego());
		cambio=!cambio;
		super.render();
	}
	public void setLobby(boolean lobby) {
		this.lobby = lobby;
	}

	private void fin() {
		screen.dispose();
		this.setScreen(sf = new ScreenFin(gano));
		fin=!fin;
		super.render();
	}
	private void lobby() {
		screen.dispose();
		this.setScreen(sl= new ScreenLobby());
		lobby=!lobby;
		super.render();
	}
	public void setFin(boolean fin) {
		this.fin = fin;
	}

	public void setGano(boolean gano) {
		this.gano = gano;
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
