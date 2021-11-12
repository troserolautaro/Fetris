package com.proyecto.juego;

import java.util.EventListener;

public interface JuegoEventListener extends EventListener {
	public void keyDown(int keycode);
	public void keyUp(int keycode);
	public void enviarLineas(int lineas);
	public void recibirLineas(int lineas);
	
}	
