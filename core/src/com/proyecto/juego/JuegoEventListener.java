package com.proyecto.juego;

import java.util.EventListener;

public interface JuegoEventListener extends EventListener {
	public void keyDown(int keycode);
	public void keyUp(int keycode);
	public void recibirLineas(int lineas,int bloqueBorrado);
	public void añadirLinea(int y, int bloqueBorrado);
}	
