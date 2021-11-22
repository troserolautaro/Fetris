package com.proyecto.juego;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.proyecto.evento.KeyListener;
import com.proyecto.mapas.Mapa;
import com.proyecto.piezas.Colores;
import com.proyecto.piezas.Cuadrado;
import com.proyecto.piezas.Pieza;
import com.proyecto.utiles.Assets;
import com.proyecto.utiles.Mundo;
import com.proyecto.utiles.Utiles;

public class Juego implements JuegoEventListener{
	private Mapa mapa;
	public KeyListener io = new KeyListener();
	private Pieza p;
//	private float tiempoMov;
//	private float intervaloCaida= 0.6f;
	private float correcionX;
	private float correcionY;
	private boolean mov = true;
	public Juego(boolean mapa) {
		Utiles.listeners.add(this);
		Gdx.input.setInputProcessor(io);
		this.mapa = new Mapa(mapa);
		
	}
	
	private void iniciarCorrecion() {
		correcionY= mapa.getSpr().getY()+p.getTamaño();
		correcionX=  mapa.getSpr().getX()+ p.getTamaño();
	}

	public void nuevaPieza(int text,int pieza) {
		p= new Pieza(Assets.manager.get(Colores.values()[text].getDir(), Texture.class) ,12,mapa.getSpr().getWidth()/2, mapa.getSpr().getHeight() - 24,20,4, pieza, mapa.getSpr().getX(), mapa.getSpr().getY());
		iniciarCorrecion();
	}
	
	public Mapa getMapa() {
		return mapa;
	}

	public void render() {
		Mundo.batch.begin();
		mapa.render();
		if(p!=null) {
			p.render();		
		}
		Mundo.batch.end();
	}
	
	
	public void dispose() {
	
	}
	

	public void moverPiezaL(int dir) {
		Cuadrado[] t = 	p.getTetromino();
		if(verifMov(t, dir)) {
			for (int i = 0; i <	t.length; i++) {
				float pos=t[i].getSpr().getX()+dir*t[i].getTamaño();
				t[i].getSpr().setX(pos);
			}
			p.setFilaX(p.getFilaX()+dir);
		}
			
	}
	
	private boolean verifMov(Cuadrado[] t,int dir) {
		int i=0;
		boolean mov=true;
		do {
			Cuadrado c=t[i];
		float posXAux=c.getSpr().getX();
		posXAux +=dir * c.getMovimiento();
		if(dir>0) {
			if(posXAux >=  mapa.getSpr().getX()+ mapa.getSpr().getWidth()- c.getTamaño()) {
				mov=false;
			}
		}else {
			if(posXAux < mapa.getSpr().getX() + c.getTamaño()) {
				mov=false;
			}
		}
		i++;
		}while(i<t.length && mov);
		return mov;
	}
	
	
	public boolean verifCaida(Cuadrado[] t) { //Verificar colisiones en Y de las piezas
		boolean moverse =true;
		float posYAux;
		int i=0;
		do { //Verificar si esta colisionando con el mapa
			Cuadrado c=t[i];
		posYAux=c.getSpr().getY();
		posYAux -=c.getMovimiento();
		if(posYAux <= mapa.getSpr().getY() ) {
				moverse=false;
		}
		i++;
		}while(i<t.length && moverse);
		return moverse;
	}
	
	
	
	public void moverPieza(int filaX) {
		p.mover(filaX, p.getFilaY(),correcionX ,correcionY);
	}
	
	public void bajarPieza() {
		if(verifCaida(p.getTetromino())) {
			for (int i = 0; i < p.getTetromino().length; i++) {
				float pos=p.getTetromino()[i].getSpr().getY()- p.getTetromino()[i].getMovimiento();
				p.getTetromino()[i].getSpr().setY(pos);
			}	
			p.setFilaY(p.getFilaY()-1);
		}
		}
		
	
	public void bajarPieza(int filaY, int filaX) {
			p.mover(filaX, filaY,correcionX ,correcionY);
		}
	
	@Override
	public void keyDown(int keycode) {
		if(mov) {
			if(io.isUp()) {
				Mundo.app.getCliente().getHc().enviarMensaje("girar"+ "!" + Mundo.app.getCliente().getId());
				
			}
			if(io.isDown()) {
				bajarPieza();
				Mundo.app.getCliente().getHc().enviarMensaje("bajar"+ "!" + Mundo.app.getCliente().getId());
				
			}
			if(io.isRight()) {
				moverPiezaL(1);
				Mundo.app.getCliente().getHc().enviarMensaje("mover"+ "!" + 1 + "!" + Mundo.app.getCliente().getId());
				
			}
			if(io.isLeft()) {
				moverPiezaL(-1);
				Mundo.app.getCliente().getHc().enviarMensaje("mover"+ "!" + -1 + "!" + Mundo.app.getCliente().getId());
				
			}
			if(io.isSpace()) {
				//Verificar cual de los sprites es el mas alto en todos los X del mismo sprite, y luego ajustar el sprite que esta en el mismo X y ajustar los otros Sprites.
				
			}
		}
	}
	public void girarPieza(int filaX, int filaY) { //Odio este codigo
		boolean[][] new_piece = new boolean[p.getTipo().length][p.getTipo()[0].length];
		for(int i = 0; i < p.getTipo().length; i++){
			for(int j = 0; j < p.getTipo()[i].length; j++){
				new_piece[j][ p.getTipo().length - 1 - i ] = p.getTipo()[i][j]; //i=0 j=1
			}
		}
			p.setFilaX(filaX);
			p.setFilaY(filaY);
			p.girarTetromino(new_piece,correcionX,correcionY ); 
		
		
		}
		
	
	@Override
	public void keyUp(int keycode) {
		if(io.isUp()) {
			
		}
		if(io.isDown()) {
			
		}
		if(io.isRight()) {
			
		}
		if(io.isLeft()) {
			
		}
		if(io.isSpace()) {
			
		}
	}
	
	
	@Override
	public void recibirLineas(int lineas, int bloqueBorrado) {
			for (int i = 0; i < lineas; i++) {
				subirCuadrados(0);
				añadirLinea(0,bloqueBorrado);
			}
	}

	
	@Override
	public void añadirLinea(int y, int bloqueBorrado) {
		for (int i = 0; i < mapa.getAncho(); i++) {
			if(i!=bloqueBorrado) {
				mapa.getCuadrados().add((new Cuadrado(Assets.manager.get(Colores.AMARILLO.getDir(),Texture.class), 12, i*12 + mapa.getSpr().getX()+12 , y + mapa.getSpr().getY() +12 )));
			}
			
		}
	}
	
	
	public void guardar(int filaX, int filaY) {
		p.mover(filaX, filaY, correcionX, correcionY);
		for (int j = 0; j < p.getTetromino().length; j++) {
			mapa.getCuadrados().add(p.getTetromino()[j]);
		}
		mov=true;
		
	}
	public void setMov(boolean mov) {
		this.mov = mov;
		
	}
	
	public void borrarLinea(int y) {
		ArrayList<Cuadrado> tmpBorrar = new ArrayList <Cuadrado>();
		for (int j = 0; j < mapa.getCuadrados().size(); j++) {
				if(y==mapa.getCuadrados().get(j).getYGrilla(mapa.getSpr().getY())) {
					tmpBorrar.add(mapa.getCuadrados().get(j));
				}
		}
		for (int i = 0; i < tmpBorrar.size(); i++) {
			int j=0;
			boolean bandera=false;
			do {
				if(tmpBorrar.get(i)==mapa.getCuadrados().get(j)) {
					mapa.getCuadrados().remove(mapa.getCuadrados().get(j));
					bandera=true;
				}
				j++;
			}while(j<mapa.getCuadrados().size() && !bandera);
		}
		tmpBorrar.removeAll(tmpBorrar);	
	}
	
	
	public void bajarCuadrados(int i) {
		for (int j = 0; j < mapa.getCuadrados().size(); j++) {
			if(mapa.getCuadrados().get(j).getYGrilla(mapa.getSpr().getY()) > i) {
				mapa.getCuadrados().get(j).getSpr().setY(mapa.getCuadrados().get(j).getSpr().getY()-(mapa.getCuadrados().get(j).getMovimiento()));
			}
		}
	}
	
	
	public void subirCuadrados(int y) {
		for (int i = 0; i < mapa.getCuadrados().size(); i++) {
			if(mapa.getCuadrados().get(i).getYGrilla(mapa.getSpr().getY())>=y) {
				mapa.getCuadrados().get(i).getSpr().setY(mapa.getCuadrados().get(i).getSpr().getY()+mapa.getCuadrados().get(i).getMovimiento());
			}
		}
	}


}

	
		

	
	


	


	



