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
	private boolean primer;
	public Mapa(boolean primer) {
		text= new Texture("Tetriminos/Board/Board.png");
		spr = new Sprite(text);
		this.primer= primer;
		if(primer) {
			spr.setBounds(36, Config.ALTO/120, (grilla[0].length+2)*12,(grilla.length+2)*12);
		}else {
			spr.setBounds(Config.ANCHO/3, Config.ALTO/120, (grilla[0].length+2)*12,(grilla.length+2)*12);
		}
	}
	public void render() {
		this.spr.draw(Mundo.batch);
		for (int i = 0; i < cuadrados.size(); i++) {
			cuadrados.get(i).getSpr().draw(Mundo.batch);
		}
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
	public void agregarAGrilla(Cuadrado t) {
			grilla[t.getYGrilla(spr.getY())][t.getXGrilla(spr.getX())]=true;
	}
	public void quitarAGrilla(Cuadrado t) {
		grilla[t.getYGrilla(spr.getY())][t.getXGrilla(spr.getX())]=false;
	}
	public ArrayList<Cuadrado> getCuadrados() {
		return cuadrados;
	}
	public void borrarLinea(int y) {
		ArrayList<Cuadrado> tmpBorrar = new ArrayList <Cuadrado>();
		for (int j = 0; j < cuadrados.size(); j++) {
				if(y==cuadrados.get(j).getYGrilla(spr.getY())) {
					grilla[y][cuadrados.get(j).getXGrilla(spr.getX())]=false;
					tmpBorrar.add(cuadrados.get(j));
				}
		}
		for (int i = 0; i < tmpBorrar.size(); i++) {
			int j=0;
			boolean bandera=false;
			do {
				if(tmpBorrar.get(i)==cuadrados.get(j)) {
					cuadrados.remove(cuadrados.get(j));
					bandera=false;
				}
				j++;
			}while(j<cuadrados.size() && !bandera);
		}
		tmpBorrar.removeAll(tmpBorrar);
	}
	
	public void bajarCuadrados() {
		mirarGrilla();
		boolean bandera=false;
		for (int i =grilla.length-1; i > -1; i--) {
			int tmp=0;
			for (int j = 0; j < grilla[i].length; j++) {
				if(grilla[i][j]) {
//					System.out.println("entro");
					bandera=true;
				}else {
					tmp++;
				}
			}
			if(tmp==grilla[i].length && bandera) {
				for (int j = 0; j < cuadrados.size(); j++) {
					if(cuadrados.get(j).getYGrilla(spr.getY()) > i) {
						cuadrados.get(j).getYGrilla(spr.getY());
//						cuadrados.get(j).getSpr().setY(cuadrados.get(j).getSpr().getY()-(cuadrados.get(j).getMovimiento()));
//						agregarAGrilla(cuadrados.get(j));
//						mirarGrilla();
					}
				}
			}
		}		
	}
	public Cuadrado[] burbuja(Cuadrado[] arreglo){
    Cuadrado auxiliar;
    Cuadrado[] arregloOrdenado;
    for(int i = 2; i < arreglo.length; i++)
    {
      for(int j = 0;j < arreglo.length-i;j++)
      {
        if(arreglo[j].getYGrilla(spr.getY()) > arreglo[j+1].getYGrilla(spr.getY())) {
          auxiliar = arreglo[j];
          arreglo[j] = arreglo[j+1];
          arreglo[j+1] = auxiliar;
        }   
      }
    }
    arregloOrdenado = arreglo;
    return arregloOrdenado;
  }
	
	public void subirCuadrados(int y) {
		for (int i = 0; i < cuadrados.size(); i++) {
			if(cuadrados.get(i).getYGrilla(spr.getY())>=y) {
				cuadrados.get(i).getSpr().setY(cuadrados.get(i).getSpr().getY()+cuadrados.get(i).getMovimiento());
				agregarAGrilla(cuadrados.get(i));
			}
		}		
	}

	
}
