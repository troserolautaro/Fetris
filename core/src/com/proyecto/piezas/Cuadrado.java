package com.proyecto.piezas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.proyecto.utiles.Mundo;

public class Cuadrado {
	private  int tamaño;
	private Sprite spr;
	private  int movimiento;
//	private boolean activo;
//	public boolean isActivo() {
//		return activo;
//	}

	public Cuadrado(Texture text,int tamaño, float x, float y) {
//		this.activo=activo;
		this.tamaño=tamaño;
		this.movimiento=tamaño;
		this.spr=new Sprite(text);
		this.spr.setBounds(x,y,this.tamaño,this.tamaño);
	}

	public int getXGrilla(float pos) {
		return	(int) ((spr.getX()-(pos))/this.tamaño)-1;
	}
	public int getYGrilla(float pos) {
//		System.out.println((spr.getY()-(pos))/this.tamaño-1);
		return  (int) ((spr.getY()-(pos))/this.tamaño)-1;
	}

	public void render() {
		this.spr.draw(Mundo.batch);
	}
	
	public Sprite getSpr() {
		return spr;
	}
	public int getMovimiento() {
		return movimiento;
	}
	public int getTamaño() {
		return tamaño;
	}

//	public void setX(float x) {
//		this.x = x;
//	}
//	public void setY(float y) {
//		this.y = y;
//	}
}