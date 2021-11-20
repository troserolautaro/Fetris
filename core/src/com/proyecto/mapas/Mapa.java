package com.proyecto.mapas;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.proyecto.piezas.Cuadrado;
import com.proyecto.utiles.Config;
import com.proyecto.utiles.Mundo;
import com.proyecto.utiles.Utiles;

public class Mapa {
	private int ancho=10;
	private int alto=20;
	private Texture text;
	private Sprite spr;
	private ArrayList <Cuadrado> cuadrados= new ArrayList<Cuadrado>();
	private boolean primer;
	public Mapa(boolean primer) {
		text= new Texture("Tetriminos/Board/Board.png");
		spr = new Sprite(text);
		this.primer= primer;
		if(primer) {
			spr.setBounds(36, Config.ALTO/120, (ancho+2)*12,(alto+2)*12);
		}else {
			spr.setBounds(Config.ANCHO/3, Config.ALTO/120, (ancho+2)*12,(alto+2)*12);
		}
	}
	public void render() {
		this.spr.draw(Mundo.batch);
		for (int i = 0; i < cuadrados.size(); i++) {
			cuadrados.get(i).getSpr().draw(Mundo.batch);
		}
	}
	
	public Sprite getSpr() {
		return spr;
	}

	
	public ArrayList<Cuadrado> getCuadrados() {
		return cuadrados;
	}
	public int getAncho() {
		return ancho;
	}
	public int getAlto() {
		return alto;
	}

	
//		for (int i = 0; i < arreglo.size(); i++) {
//			for(int j = 0;j < arreglo.size()-i;j++) {
//				if(arreglo.get(j).getYGrilla(spr.getY()) > arreglo.get(j+1).getYGrilla(spr.getY())) {
//					auxiliar = arreglo.get(j);
//					arreglo.set(j, arreglo.get(j+1));
//					arreglo.set(j+1, auxiliar);
//				}   
//			}
//	}
	
  


	
}
