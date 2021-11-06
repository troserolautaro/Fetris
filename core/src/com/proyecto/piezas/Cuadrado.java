package com.proyecto.piezas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.proyecto.utiles.Mundo;

public class Cuadrado {
	private  int tamaño;
	private Texture  text;
	private Sprite spr;
	private float x,y;
	private  int movimiento;
	public Cuadrado(String text,int tamaño, float x, float y) {
		this.tamaño=tamaño;
		this.movimiento=tamaño;
		this.x=x;
		this.y=y;
		this.text=new Texture(text);
		this.spr=new Sprite(this.text);
		this.spr.setBounds(this.x,this.y,this.tamaño,this.tamaño);
	}
	
//	public int getXGrilla() {
//		return (int) ((this.x-this.tamaño*3)/this.tamaño);
//	}
//	public int getYGrilla() {
//		return (int) ((this.y-this.tamaño)/this.tamaño);
//	}
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