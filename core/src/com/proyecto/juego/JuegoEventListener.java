package com.proyecto.juego;

import java.util.EventListener;

public interface JuegoEventListener extends EventListener {

	public void keyDown(int keycode);
	public void keyUp(int keycode);
	public void enviarLineas();
	public void recibirLineas(int lineas);
	
}	
