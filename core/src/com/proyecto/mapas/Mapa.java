package com.proyecto.mapas;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.proyecto.piezas.Cuadrado;
import com.proyecto.utiles.Config;
import com.proyecto.utiles.Mundo;
import com.proyecto.utiles.Utiles;

public class Mapa {
	private boolean[][] grilla= new boolean[20][10];
	private Texture text;
	private Sprite spr;
	private ArrayList <Cuadrado> cuadrados= new ArrayList<Cuadrado>();
	private int lineas=0;
	private boolean primer;
	public Mapa(boolean primer) {
		text= new Texture("Tetriminos/Board/Board.png");
		spr = new Sprite(text);
		this.primer= primer;
		if(primer) {
			spr.setBounds(32, Config.ALTO/120, (grilla[0].length+2)*12,(grilla.length+2)*12);
		}else {
			spr.setBounds(Config.ANCHO/3, Config.ALTO/120, (grilla[0].length+2)*12,(grilla.length+2)*12);
		}
	}
	public void render() {
		this.spr.draw(Mundo.batch);
		for (int i = 0; i < cuadrados.size(); i++) {
			cuadrados.get(i).getSpr().draw(Mundo.batch);
		}
//		mirarGrilla();
	}
	public boolean[][] getGrilla() {
		return grilla;
	}
	public Sprite getSpr() {
		return spr;
	}
	public void mirarGrilla() {
		for (int i =grilla.length-1; i > -1; i--) {
			for (int j = 0; j < grilla[i].length; j++) {
				System.out.print((grilla[i][j])? "X" : "O");
				System.out.print("|");
			}
			System.out.println();
		}
		System.out.println();	
	}
	public void updateGrilla(Cuadrado t) {
			grilla[t.getYGrilla(spr.getY())][t.getXGrilla(spr.getX())]=true;
			mirarGrilla();
	}
	public ArrayList<Cuadrado> getCuadrados() {
		return cuadrados;
	}
	public void borrarLinea(int y) {
		for (int i = 0; i < grilla[y].length; i++) {
			for (int j = 0; j < cuadrados.size(); j++) {
				if(y==cuadrados.get(j).getYGrilla(spr.getY()) && i==cuadrados.get(j).getXGrilla(spr.getX())) {
					grilla[y][i]=false;
					cuadrados.remove(cuadrados.get(j));
					lineas++;
				}
			}
			
		}
		
//		mirarGrilla();
//		System.out.println(y);
//		
		bajarCuadrados(y);
	
		if(primer) {
			Utiles.listeners.get(0).enviarLineas();
		}else {
			Utiles.listeners.get(1).enviarLineas();
		}
//		mirarGrilla();
	}
	private void bajarCuadrados(int y) {
		for (int i = 0; i < cuadrados.size(); i++) {
			if(cuadrados.get(i).getYGrilla(spr.getY())>y) {
				cuadrados.get(i).getSpr().setY(cuadrados.get(i).getSpr().getY()-cuadrados.get(i).getMovimiento());
				updateGrilla(cuadrados.get(i));
			}
		}		
	}
	public void subirCuadrados(int y) {
		for (int i = 0; i < cuadrados.size(); i++) {
			if(cuadrados.get(i).getYGrilla(spr.getY())>y) {
				cuadrados.get(i).getSpr().setY(cuadrados.get(i).getSpr().getY()+cuadrados.get(i).getMovimiento());
				updateGrilla(cuadrados.get(i));
			}
		}		
	}
	public int getLineas() {
		return lineas;
		
	}
}
