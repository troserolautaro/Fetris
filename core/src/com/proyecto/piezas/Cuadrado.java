package com.proyecto.piezas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.proyecto.utiles.Mundo;

public class Cuadrado {
	private  int tama�o;
	private Texture  text;
	private Sprite spr;
	private  int movimiento;
	public Cuadrado(String text,int tama�o, float x, float y) {
		this.tama�o=tama�o;
		this.movimiento=tama�o;
		this.text=new Texture(text);
		this.spr=new Sprite(this.text);
		this.spr.setBounds(x,y,this.tama�o,this.tama�o);
	}

	public int getXGrilla() {
		return (int) ((spr.getX()-32)/this.tama�o);
	}
	public int getYGrilla() {
		return (int) ((spr.getY()-this.tama�o)/this.tama�o);
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