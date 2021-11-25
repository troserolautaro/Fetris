package com.proyecto.juego;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.proyecto.evento.KeyListener;
import com.proyecto.mapas.Mapa;
import com.proyecto.piezas.Colores;
import com.proyecto.piezas.Cuadrado;
import com.proyecto.piezas.Pieza;
import com.proyecto.utiles.Assets;
import com.proyecto.utiles.Config;
import com.proyecto.utiles.Mundo;
import com.proyecto.utiles.Utiles;

public class Juego implements JuegoEventListener{
	private Mapa mapa;
	public KeyListener io = new KeyListener();
	private Pieza pieza;
	private Pieza sigP;
	private Pieza piezaGuardada;
	private float correcionX;
	private float correcionY;
	private boolean mov = true;
	private boolean cambiar=true;
	public Juego(boolean mapa) {
		Utiles.listeners.add(this);
		Gdx.input.setInputProcessor(io);
		this.mapa = new Mapa(mapa);
		
	}
	
	private void iniciarCorrecion() {
		correcionY= mapa.getSpr().getY()+pieza.getTamaño();
		correcionX=  mapa.getSpr().getX()+ pieza.getTamaño();
	}

	public void nuevaPieza(int text, int pieza, int corX) {
		sigP = new Pieza(Assets.manager.get(Colores.values()[text].getDir(),Texture.class), 12,Config.ANCHO/4+corX , Config.ALTO/4 + 12 ,19,4,pieza,0,0);
	}
	public void sigPieza() {
		pieza = new Pieza(sigP.getText(),sigP.getTamaño(), 
						mapa.getSpr().getWidth()/2,
						mapa.getSpr().getHeight() - 24,
						sigP.getFilaY(),
						sigP.getFilaX(),
						sigP.getPieza(),
						mapa.getSpr().getX(),
						mapa.getSpr().getY());
		iniciarCorrecion();
	}
	
	
	
	public Mapa getMapa() {
		return mapa;
	}

	public void render() {
		Mundo.batch.begin();
		mapa.render();
		if(pieza!=null) {
			pieza.render();	
			sigP.render();
			if(piezaGuardada!=null) {
				piezaGuardada.render();
			}
		}
		Mundo.batch.end();
	}
	
	
	public void dispose() {
	
	}
	

	public void moverPiezaL(int dir) {
		Cuadrado[] t = 	pieza.getTetromino();
		if(verifMov(t, dir)) {
			for (int i = 0; i <	t.length; i++) {
				float pos=t[i].getSpr().getX()+dir*t[i].getTamaño();
				t[i].getSpr().setX(pos);
			}
			pieza.setFilaX(pieza.getFilaX()+dir);
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
		pieza.mover(filaX, pieza.getFilaY(),correcionX ,correcionY);
	}
	
	public void bajarPieza() {
		if(verifCaida(pieza.getTetromino())) {
			for (int i = 0; i < pieza.getTetromino().length; i++) {
				float pos=pieza.getTetromino()[i].getSpr().getY()- pieza.getTetromino()[i].getMovimiento();
				pieza.getTetromino()[i].getSpr().setY(pos);
			}	
			pieza.setFilaY(pieza.getFilaY()-1);
		}
		}
		
	
	public void bajarPieza(int filaY, int filaX) {
			pieza.mover(filaX, filaY,correcionX ,correcionY);
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
			if(io.isC()) {
				if(cambiar) {
					guardarPieza();
					Mundo.app.getCliente().getHc().enviarMensaje("guardarPieza"+"!"+ Mundo.app.getCliente().getId());
					cambiar=!cambiar;
				}
				
			}
		}
	}
	private void guardarPieza() {
		if(piezaGuardada== null) {
			piezaGuardada = new Pieza(pieza.getText(),
					pieza.getTamaño(),
					mapa.getSpr().getX()-24,
					mapa.getSpr().getY()+mapa.getSpr().getHeight()-32,
					19,
					4,
					pieza.getPieza(),
					0,
					0);
		
		}else {
			Pieza auxP = new Pieza(
					pieza.getText(), 
					pieza.getTamaño(),
					mapa.getSpr().getX()-24,
					mapa.getSpr().getY()+mapa.getSpr().getHeight()-32,
					19,
					4,
					pieza.getPieza(),
					0,0);
			
			pieza= new Pieza(
					piezaGuardada.getText(),
					piezaGuardada.getTamaño(),
					pieza.getX(),
					pieza.getY() ,
					19,
					4,
					piezaGuardada.getPieza(),
					mapa.getSpr().getX(),
					mapa.getSpr().getY());
			
			piezaGuardada = new Pieza(
					auxP.getText(),
					auxP.getTamaño(),
					auxP.getX(),
					auxP.getY() ,
					auxP.getFilaY(),
					auxP.getFilaX(),
					auxP.getPieza(),
					0,0);
			
		}
		
	}

	public void girarPieza(int filaX, int filaY) { //Odio este codigo
		boolean[][] new_piece = new boolean[pieza.getTipo().length][pieza.getTipo()[0].length];
		for(int i = 0; i < pieza.getTipo().length; i++){
			for(int j = 0; j < pieza.getTipo()[i].length; j++){
				new_piece[j][ pieza.getTipo().length - 1 - i ] = pieza.getTipo()[i][j]; //i=0 j=1
			}
		}
			pieza.setFilaX(filaX);
			pieza.setFilaY(filaY);
			pieza.girarTetromino(new_piece,correcionX,correcionY ); 
		
		
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
	
	
	public void guardarMapa(int filaX, int filaY) {
		pieza.mover(filaX, filaY, correcionX, correcionY);
		for (int j = 0; j < pieza.getTetromino().length; j++) {
			mapa.getCuadrados().add(pieza.getTetromino()[j]);
		}
		
		cambiar=true;
		
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
	
	
	public void bajarCuadrados(int y) {
		for (int j = 0; j < mapa.getCuadrados().size(); j++) {
			if(mapa.getCuadrados().get(j).getYGrilla(mapa.getSpr().getY()) > y) {
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

	
		

	
	


	


	



