package com.proyecto.piezas;

import com.proyecto.utiles.Mundo;
import com.proyecto.utiles.Utiles;

public class Pieza {
	private Cuadrado[] tetromino= new Cuadrado[4];
	private boolean[][] tipoTmp;
	private boolean[][]	tipo;
	private String text;
	private int tamaño;
	private float x,y;
	private int filaX;
	private int filaY;
	public Cuadrado[] getTetromino() {
		return tetromino;
	}

	public Pieza(String text, int tamaño, float x, float y, int filaY, int filaX) {
		this.text=text;
		this.tamaño=tamaño;
		this.x=x;
		this.y=y;
		this.filaX=filaX;
		this.filaY=filaY;
		crearTetromino(text,tamaño,x,y);
	}
	public void setX(float x) {
		this.x = x;
	}

	public int getFilaY() {
		return filaY;
	}

	public void setFilaY(int filaY) {
		this.filaY = filaY;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Pieza(String text, int tamaño, float x, float y,int filaY,int filaX, boolean[][] piezaRotada, float correcionX, float correcionY) {
		this.text=text;
		this.tamaño=tamaño;
		this.x=x;
		this.y=y;
		this.filaX=filaX;
		this.filaY=filaY;
		crearTetromino(piezaRotada,correcionX,correcionY);
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
	
	public boolean[][] getTipoTmp() {
		return tipoTmp;
	}

	public String getText() {
		return text;
	}

	public int getTamaño() {
		return tamaño;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void crearTetromino(boolean[][] piezaRotada, float correcionX, float correcionY) {
		asignarPieza(piezaRotada);
		for (int i = 0; i <tetromino.length; i++) {
			int j=0;
			boolean bandera=false;
			do {
				int k=0;
				do {
					if(tipoTmp[j][k]) {
						tetromino[i]=(new Cuadrado(this.text, this.tamaño, (filaX+k)*this.tamaño+ correcionX, (filaY-j)*this.tamaño+correcionY));
						tipoTmp[j][k]=false;
						bandera=true;
					}
					k++;
				}while(k<tipoTmp[j].length && !bandera);
				j++;
			}while(j<tipoTmp.length && !bandera);
		}
	}
	public int getFilaX() {
		return filaX;
	}

	public void setFilaX(int filaX) {
		this.filaX = filaX;
	}
	
	private void buscarPieza() {
		int ind = Utiles.r.nextInt(Piezas.values().length);
		boolean[][] tmp = Piezas.values()[ind].getPieza();
		tipoTmp = new boolean [tmp.length][tmp[0].length];
		tipo = new boolean [tmp.length][tmp[0].length];
		clonarArray(tmp,tipoTmp);
		clonarArray(tmp,tipo);
	}
	private void asignarPieza(boolean[][] piezaRotada) {
		boolean[][] tmp = piezaRotada;
		tipoTmp = new boolean [tmp.length][tmp[0].length];
		tipo = new boolean [tmp.length][tmp[0].length];
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





