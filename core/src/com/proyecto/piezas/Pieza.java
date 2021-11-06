package com.proyecto.piezas;

import com.proyecto.utiles.Mundo;
import com.proyecto.utiles.Utiles;

public class Pieza {
	private Cuadrado[] tetromino = new Cuadrado[4];
	private boolean[][] tipo;
	public Cuadrado[] getTetromino() {
		return tetromino;
	}

	public Pieza(String text, int tamaño, float x, float y) {
		crearTetromino(text,tamaño,x,y);
	}
	
	public void crearTetromino(String text, int tamaño, float x, float y) {
		buscarPieza();
		for (int i = 0; i <tetromino.length; i++) {
			int j=0;
			boolean bandera=false;
			do {
				int k=0;
				do {
					if(tipo[j][k]) {
						tetromino[i]=(new Cuadrado(text, tamaño, x+((k-1)*tamaño), y-(j*tamaño)));
						tipo[j][k]=false;
						bandera=true;
					}
					k++;
				}while(k<tipo[j].length && !bandera);
				j++;
			}while(j<tipo.length && !bandera);
		}
	}

	private void buscarPieza() {
		int ind = Utiles.r.nextInt(7);
		boolean[][] tmp = Piezas.values()[ind].getPieza();
		tipo = new boolean [tmp.length][tmp[0].length];
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				tipo[i][j]=tmp[i][j];
			}
			
		}
	}

	public void render() {
		for (int i = 0; i <this.tetromino.length; i++) {
			this.tetromino[i].getSpr().draw(Mundo.batch);
		}
	}
//	public Vector2 verifPosPieza() {
//		int i;
//	int j;
//		Mapa mapa = Utiles.obtenerMapa(nmap );
//		for ( i= 0; i < mapa.getGrilla().length; i++) {
//			for (j = 0; j < mapa.getGrilla()[i].length; j++) {
//				if (i==(int) (this.x-this.tamaño*3)/12 && j==(int) (this.y-this.tamaño)/12) {
//					
//				}
//		
//		 }
//		}

//		return new Vector2(i,j);
//	}

	public void cMasAlto() {
		
	}
	public void cMasBajo() {
		
	}
	public void cMasIzquierda() {
		
	}
	public void cMasDerecha() {
		
	}
	
	
}




//public void updateGrilla(int a) {
//	for (int i = 0; i < tipo.length; i++) {
//		for (int j = 0; j <tipo[i].length; j++) {
//		if(tipo[i][j]) {
//			mapa.getGrilla()[this.getYGrilla()][this.getXGrilla()]=tipo[i][j];
//			}else if(this.getXGrilla()>0 && this.getXGrilla()<mapa.getGrilla()[i].length) {
//				System.out.println("hola");
//				mapa.getGrilla()[this.getYGrilla()][this.getXGrilla()+a]=tipo[i][j];
//			}
//		}
//	}
//}

