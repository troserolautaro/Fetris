package com.proyecto.juego;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.proyecto.evento.KeyListener;
import com.proyecto.mapas.Mapa;
import com.proyecto.piezas.Colores;
import com.proyecto.piezas.Cuadrado;
import com.proyecto.piezas.Pieza;
import com.proyecto.utiles.Mundo;
import com.proyecto.utiles.Utiles;

public class Juego implements JuegoEventListener{
	private Mapa mapa;
	private boolean nueva;
	public KeyListener io = new KeyListener();
	private float tiempoMov;
	private float intervaloCaida= 0.6f;
	private Pieza p;
	private boolean prueba;
//	private final int alto = 20, ancho = 10;
//	private final int[][] area = new int[alto][ancho];
	public Juego(boolean mapa) {
		Utiles.listeners.add(this);
		Gdx.input.setInputProcessor(io);
		this.mapa = new Mapa(mapa);
		prueba=mapa;
		nueva=true;
	}
	public void update(OrthographicCamera cam, float delta) {
		if(nueva) {
			nuevaPieza();
			nueva=!nueva;
		}
		Mundo.batch.setProjectionMatrix(cam.combined);
		cam.update();
		updatePieza();
	

	}
	public void nuevaPieza() {
		int ind = Utiles.r.nextInt(Colores.values().length-1);
		p = new Pieza(Colores.values()[ind].getDir(), 12,mapa.getSpr().getX()+ mapa.getSpr().getWidth()/2 , mapa.getSpr().getY()+mapa.getSpr().getHeight() - 24);
	}
	public Mapa getMapa() {
		return mapa;
	}

	public void render() {
		Mundo.batch.begin();
		mapa.render();
		p.render();	
		Mundo.batch.end();
	}

	public void dispose() {
	
	}
	public void updatePieza() {
		tiempoMov+=Gdx.graphics.getDeltaTime();
		if(tiempoMov>intervaloCaida) {
			bajarPieza();
//			for (int i = 0; i < p.getTetromino().length; i++) {
//				System.out.println("Cuadrado " + i);
//				System.out.println("X: " + p.getTetromino()[i].getXGrilla());
//				System.out.println("Y: " + p.getTetromino()[i].getYGrilla());
//			}
			
			tiempoMov=0;
			}
			
		}
	
	public boolean verifCaida(Cuadrado[] t) { //Verificar colisiones en Y de las piezas
		boolean moverse =true;
		int i=0;
		
		do { //Verificar si esta colisionando con el mapa
			Cuadrado c=t[i];
		float posYAux=c.getSpr().getY();
		posYAux -=c.getMovimiento();
		if(posYAux <= mapa.getSpr().getY() ) {
				moverse=false;
		}else {
				if(mapa.getCuadrados().size()>0){
					if(colisionCuadrado(c,c.getSpr().getX(),posYAux)) { //Si devuelve falso no colisiona, por ende puede moverse
						moverse=false;
					}
				
			}
		}
		i++;
		}while(i<t.length && moverse);
		
		
		if(!moverse) {//Si colisiona y no puede moverse en el eje vertical, guardar el sprite y generar una nueva pieza
			for (int j = 0; j < p.getTetromino().length; j++) {
//				System.out.println(p.getTetromino()[j].getYGrilla(j));
				mapa.getCuadrados().add(p.getTetromino()[j]);
				mapa.agregarAGrilla(p.getTetromino()[j]);	
			}
			verifLineaCompl();
			nueva=true;
		}
		
		return moverse;
	}


public boolean verifMov(Cuadrado[] t, float dir) { //Verificar colisiones en X de las piezas
	boolean mov = true;
	int i=0;
	
	do {
		Cuadrado c=t[i];
	float posXAux=c.getSpr().getX();
	posXAux +=dir * c.getMovimiento();
	if(dir>0) {
		if(posXAux >=  mapa.getSpr().getX()+ mapa.getSpr().getWidth()- c.getTamaño()) {
			mov=false;
		}else {
			if(mapa.getCuadrados().size()>0) {
				if(colisionCuadrado(c,posXAux,c.getSpr().getY())){
					mov=false;
				}
			}
		}
	}else {
		
		if(posXAux < mapa.getSpr().getX() + c.getTamaño()) {
			mov=false;
		}else {
			if(mapa.getCuadrados().size()>0) {
				if(colisionCuadrado(c,posXAux,c.getSpr().getY())){
					mov=false;
				}	
			}
		}
	}
	i++;
	}while(i<t.length && mov);
	
	return mov;
	}


public void moverPieza(float dir) {
	Cuadrado[] t = 	p.getTetromino();
	if(verifMov(t,dir)) {//Si devuelve verdadero puede avanzar
		for (int i = 0; i <	t.length; i++) {
			float pos=t[i].getSpr().getX()+dir*t[i].getTamaño();
			t[i].getSpr().setX(pos);
//			System.out.println(pos);
		}
//		System.out.println("-------------------");
	}
//	for (int i = 0; i < t.length; i++) {
//		t[i].setX(verifMov(t[i],dir));
//		t[i].getSpr().setX(t[i].getX());
//	}
	
}
public boolean colisionCuadrado(Cuadrado c, float posAuxX, float posAuxY) {
	boolean colision = false;
	int i=0;
	do {
		if(posAuxY==mapa.getCuadrados().get(i).getSpr().getY() && posAuxX == mapa.getCuadrados().get(i).getSpr().getX()) {
			colision=true;
		}
		i++;
	}while(i<mapa.getCuadrados().size() && !colision);
		

	return colision;
}
public void bajarPieza() {
	if(verifCaida(p.getTetromino())) {
		for (int i = 0; i < p.getTetromino().length; i++) {
			float pos=p.getTetromino()[i].getSpr().getY()- p.getTetromino()[i].getMovimiento();
			p.getTetromino()[i].getSpr().setY(pos);
//			System.out.println(pos);
		}
//		System.out.println("-------------------");
	}
}
@Override
public void keyDown(int keycode) {
	if(io.isUp()) {
//		girarPieza();
		mapa.burbuja(mapa.getCuadrados());
	}
	if(io.isDown()) {
//		System.out.println("bajo");
		bajarPieza();
	}
	if(io.isRight()) {
		moverPieza(1);
	}
	if(io.isLeft()) {
		moverPieza(-1);
	}
	if(io.isSpace()) {
		//Verificar cual de los sprites es el mas alto en todos los X del mismo sprite, y luego ajustar el sprite que esta en el mismo X y ajustar los otros Sprites.
		
	}
}
private void girarPieza() { //Odio este codigo
	boolean[][] new_piece = new boolean[p.getTipo().length][p.getTipo()[0].length];
	for(int i = 0; i < p.getTipo().length; i++){
		for(int j = 0; j < p.getTipo()[i].length; j++){
			new_piece[j][ p.getTipo().length - 1 - i ] = p.getTipo()[i][j]; //i=0 j=1
			
			
			
			System.out.print(" | ");
			System.out.print(p.getTipo()[i][j]? "X":"O");
			System.out.print(" | ");
////			System.out.print(new_piece[i][j]?"X":"O");
//			System.out.print(" | ");
		}
		
	
		System.out.println(" ");
	}
//	p.comparacion(new_piece);
//	p.rotar(new_piece);
	System.out.println(" ");
//	for (int i= 0; i < new_piece.length; i++) {
//		for(int j = 0; j < new_piece[i].length; j++){
//			System.out.print(" | ");
//			System.out.print(new_piece[i][j]? "X":"O");
//			System.out.print(" | ");
//		
//		}
//		System.out.println(" ");
//	}
//	System.out.println(" ");

//		t[i].getSpr().setX(t[i].getSpr().getY());
//		new_piece[j][ piece.length - 1 - i ] = piece[i][j]; 
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

public void verifLineaCompl() {
	int linea =0;
	for (int i = 0; i < mapa.getGrilla().length; i++) { //Posicion Y
		int tmp=0;
		for (int j = 0; j < mapa.getGrilla()[i].length; j++) {//Posicion X
			if(mapa.getGrilla()[i][j]) {
				tmp++;
			}
		}
		if(tmp==mapa.getGrilla()[i].length) {
			mapa.borrarLinea(i);  
			linea++;
		}
	}
	if(linea>0) {
		mapa.bajarCuadrados();
	}

//		System.out.println(min);
//		mapa.mirarGrilla();
	}

@Override
public void enviarLineas() {
	
	for (int i = 0; i < Utiles.listeners.size(); i++) {
		if(Utiles.listeners.get(i)!=this) {
			System.out.println(Utiles.listeners.get(i));
//			Utiles.listeners.get(i).recibirLineas(1);
		}
	}
	
	
}
@Override
public void recibirLineas(int linea) {
//		System.out.println(lineas);
		int bloqueBorrado = Utiles.r.nextInt(mapa.getGrilla()[0].length );
			mapa.subirCuadrados(0);
			añadirLineas(0, bloqueBorrado);
}
private void añadirLineas(int y, int bloqueBorrado) {
	mapa.mirarGrilla();
	for (int i = 0; i < mapa.getGrilla()[y].length; i++) {
		if(i!=bloqueBorrado) {
			if(!prueba) {
//				System.out.println(i); 
			}
			mapa.getCuadrados().add((new Cuadrado(Colores.GRIS.getDir(), 12, i*12 + mapa.getSpr().getX()+12 , y + mapa.getSpr().getY() +12 )));
			
		}
		
	}
	for (int i = 0; i < mapa.getCuadrados().size(); i++) {
		mapa.agregarAGrilla(mapa.getCuadrados().get(i));
	}
	System.out.println(prueba? "Juego 1" : "Juego 2");
	mapa.mirarGrilla();
}
}

	
		

	
	


	


	



