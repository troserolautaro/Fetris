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
	public Juego(boolean mapa) {
		Utiles.listeners.add(this);
		Gdx.input.setInputProcessor(io);
		this.mapa = new Mapa(mapa);
	}
	public void update(OrthographicCamera cam, float delta) {
		Mundo.batch.setProjectionMatrix(cam.combined);
		cam.update();
	

	}
	public void nuevaPieza(int text,int pieza) {
		p= new Pieza(Assets.manager.get(Colores.values()[text].getDir(), Texture.class) ,12,mapa.getSpr().getWidth()/2, mapa.getSpr().getHeight() - 24,20,4, pieza, mapa.getSpr().getX(), mapa.getSpr().getY());
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
	

	public void moverPieza(int dir) {
		Cuadrado[] t = 	p.getTetromino();
			for (int i = 0; i <	t.length; i++) {
				float pos=t[i].getSpr().getX()+dir*t[i].getTamaño();
				t[i].getSpr().setX(pos);
			}
			p.setFilaX(p.getFilaX()+dir);
		
	}
	
	public void bajarPieza() {
		for (int i = 0; i < p.getTetromino().length; i++) {
				float pos=p.getTetromino()[i].getSpr().getY()- p.getTetromino()[i].getMovimiento();
				p.getTetromino()[i].getSpr().setY(pos);
			}	
		p.setFilaY(p.getFilaY()-1);
		}
	
	@Override
	public void keyDown(int keycode) {
		if(io.isUp()) {
	//		Mundo.app.getCliente().getHc().enviarMensaje("girar"+ "!" + Mundo.app.getCliente().getId());
		}
		if(io.isDown()) {
			Mundo.app.getCliente().getHc().enviarMensaje("bajar"+ "!" + Mundo.app.getCliente().getId());
		}
		if(io.isRight()) {
			Mundo.app.getCliente().getHc().enviarMensaje("mover"+ "!" + 1 + "!" + Mundo.app.getCliente().getId());
		}
		if(io.isLeft()) {
			Mundo.app.getCliente().getHc().enviarMensaje("mover"+ "!" + -1 + "!" + Mundo.app.getCliente().getId());
		}
		if(io.isSpace()) {
			//Verificar cual de los sprites es el mas alto en todos los X del mismo sprite, y luego ajustar el sprite que esta en el mismo X y ajustar los otros Sprites.
			
		}
	}
	public void girarPieza() { //Odio este codigo
		boolean[][] new_piece = new boolean[p.getTipo().length][p.getTipo()[0].length];
		for(int i = 0; i < p.getTipo().length; i++){
			for(int j = 0; j < p.getTipo()[i].length; j++){
				new_piece[j][ p.getTipo().length - 1 - i ] = p.getTipo()[i][j]; //i=0 j=1
			}
		}
		
			p.girarTetromino(new_piece, mapa.getSpr().getX()+ p.getTamaño(), mapa.getSpr().getY()+p.getTamaño()); 
		
		
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
	
	
	public void guardar() {
		for (int j = 0; j < p.getTetromino().length; j++) {
			mapa.getCuadrados().add(p.getTetromino()[j]);
		}
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
					bandera=false;
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

	
		

	
	


	


	



