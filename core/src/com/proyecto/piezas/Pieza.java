package com.proyecto.piezas;

import com.proyecto.utiles.Mundo;
import com.proyecto.utiles.Utiles;

public class Pieza {
	private Cuadrado[] tetromino;
	private boolean[][] tipoTmp;
	private boolean[][]	tipo;
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
					if(tipoTmp[j][k]) {
						tetromino[i]=(new Cuadrado(text, tamaño, x+((k-1)*tamaño), y-(j*tamaño)));
						tipoTmp[j][k]=false;
						bandera=true;
					}
					k++;
				}while(k<tipoTmp[j].length && !bandera);
				j++;
			}while(j<tipoTmp.length && !bandera);
		}
	}
//	public void rotar(boolean[][] tmp) { //44 mas a la izq, 152 mas a la derecha 
//		clonarArray(tmp,tipoTmp);
//		int izq= posIzq();
//		int der= posDer();
//		int alta= posAlta();
//		int baja= posBaja();
//		System.out.println(izq + " " + der + " " +alta + " " + baja);
//		for (int i = 0; i <tetromino.length; i++) {
//			int j=0;
//			boolean bandera=false;
//			int x=0;
//			int y=0;
//			do {
//				int k=0;
//				do {
//					if(tipoTmp[j][k]) { //Fijarme como comparar las dos matrices para obtener bien la posicion con respecoo a la matriz
////						if(k==0) {
////							x=izq;
////							}else if(k==1) {
////								if(izq+1==der) {
////									x=der;
////								}else {
////									x=(izq + der)/2;
////								}
////								
////								}else {
////									if(izq+1==der) {
////									x=der+1;
////									}else {
////										x=der;
////									}
////									
////										}
////						if(j==0) {
////							y=alta;
////							}else if(j==1) {
////								y= (alta + baja)/2;
////							}else {
////								y=baja;
////								}
//						syso(x,y,i);
//						tetromino[i].getSpr().setX((x*tetromino[i].getTamaño())+tetromino[i].getTamaño()*2+8);
//						tetromino[i].getSpr().setY((y*tetromino[i].getTamaño())+tetromino[i].getTamaño()+4);
////						tetromino[i]=(new Cuadrado(text, tamaño, x+((k-1)*tamaño), y-(j*tamaño)));
//						tipoTmp[j][k]=false;
//						bandera=true;
//					}
//					k++;
//				}while(k<tipoTmp[j].length && !bandera);
//				j++;
//			}while(j<tipoTmp.length && !bandera);
//		}
//		clonarArray(tmp,tipo);			
//		
//
//	}

//	private void syso(int x, int y, int i) {
//		System.out.println("---------------------");
//		System.out.println("Cuadrado: " + i);
//		System.out.println("X:");
//		System.out.println(x);
//		System.out.println(x*tetromino[i].getTamaño()+32);
//		System.out.println("Y:");
//		System.out.println(y);
//		System.out.println(y*tetromino[i].getTamaño()+tetromino[i].getTamaño());
//		
//	}

	private void buscarPieza() {
		int ind = Utiles.r.nextInt(Piezas.values().length);
		boolean[][] tmp = Piezas.values()[1].getPieza();
		tipoTmp = new boolean [tmp.length][tmp[0].length];
		tipo = new boolean [tmp.length][tmp[0].length];
		tetromino= new Cuadrado[4];
		clonarArray(tmp,tipoTmp);
		clonarArray(tmp,tipo);
	}

	private void clonarArray(boolean[][] tmp, boolean[][] ttmp) {
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				ttmp[i][j]=tmp[i][j];
			}
		}
		
	}

	public boolean[][] getTipo() {
		return tipo;
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

//	public int posAlta() {
//		int max=0;
//		for (int i = 0; i < tetromino.length; i++) {
//			if(tetromino[i].getYGrilla()>max || i==0) {
//				max=tetromino[i].getYGrilla();
//			}
//		
//		}
//		return max;
//	}
//	public int posBaja() {
//		int min=0;
//		for (int i = 0; i < tetromino.length; i++) {
//			if(tetromino[i].getYGrilla()<min || i==0) {
//				min=tetromino[i].getYGrilla();
//			}
//		
//		}
//		return min;
//	}
//	public int posIzq() {
//		int min=0;
//		for (int i = 0; i < tetromino.length; i++) {
//			if(tetromino[i].getXGrilla()<min || i==0) {
//				min=tetromino[i].getXGrilla();
//			}
//		
//		}
//	return min;
//	}
//	public int posDer() {
//		int max=0;
//		for (int i = 0; i < tetromino.length; i++) {
//			if(tetromino[i].getXGrilla()>max || i==0) {
//				max=tetromino[i].getXGrilla();
//			}
//		
//		}
//		return max;
//	}
//	public int[]  posX() {
//		int[] pos = new int[2];
//		for (int i = 0; i < pos.length; i++) {
//			for (int j = 0; j < tipo.length; j++) {
//				if(i==0) {
//					if(tipo[0][j]) {
//						if(j==0) {
//							pos[i]=posIzq();
//						}else if(j==1) {
//							pos[i]=posIzq()-1; 
//						}
//					}
//				
//				}else if(i==1) {
//					if(tipo[0][j]) {
//						if(j==0) {
//							pos[i]=posDer()+2; 
//						}else if(j==1) {
//							pos[i]=posDer()+1;
//						}
//					}
//				}
//			}
//		}
//		return pos;
//	}
//	public int[]  posY() {
//		int[] pos = new int[tipo.length];
//		return pos;
//	}
//	
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

