package com.proyecto.piezas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.proyecto.utiles.Mundo;

public class Cuadrado {
	private  int tama�o;
	private Sprite spr;
	private  int movimiento;
//	private boolean activo;
//	public boolean isActivo() {
//		return activo;
//	}

	public Cuadrado(Texture text,int tama�o, float x, float y) {
//		this.activo=activo;
		this.tama�o=tama�o;
		this.movimiento=tama�o;
		this.spr=new Sprite(text);
		this.spr.setBounds(x,y,this.tama�o,this.tama�o);
	}

	public int getXGrilla(float pos) {
		return	(int) ((spr.getX()-(pos))/this.tama�o)-1;
	}
	public int getYGrilla(float pos) {
//		System.out.println((spr.getY()-(pos))/this.tama�o-1);
		return  (int) ((spr.getY()-(pos))/this.tama�o)-1;
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
	public int getTama�o() {
		return tama�o;
	}

//	public void setX(float x) {
//		this.x = x;
//	}
//	public void setY(float y) {
//		this.y = y;
//	}
}