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
	private Mapa mapa2;
	private boolean nueva;
	public KeyListener io = new KeyListener();
	private float tiempoMov;
	private float intervaloCaida= 0.6f;
	private Pieza p;
	
//	private final int alto = 20, ancho = 10;
//	private final int[][] area = new int[alto][ancho];
	public Juego() {
		Utiles.listener=this;
		Gdx.input.setInputProcessor(io);
		this.mapa = new Mapa(true);
		this.mapa2= new Mapa(false);
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
		int ind = Utiles.r.nextInt(7);
		p = new Pieza(Colores.values()[ind].getDir(), 12,mapa.getSpr().getX()+ mapa.getSpr().getWidth()/2 , mapa.getSpr().getY()+mapa.getSpr().getHeight() - 24);
	}
	public Mapa getMapa() {
		return mapa;
	}
	public Mapa getMapa2() {
		return mapa2;
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
				if(mapa.getSprites().size()>0){
					if(colisionSprite(c,c.getSpr().getX(),posYAux)) { //Si devuelve falso no colisiona, por ende puede moverse
						moverse=false;
					}
				
			}
		}
		i++;
		}while(i<t.length && moverse);
		
		
		if(!moverse) {//Si colisiona y no puede moverse en el eje vertical, guardar el sprite y generar una nueva pieza
			for (int j = 0; j < p.getTetromino().length; j++) {
				mapa.getSprites().add(p.getTetromino()[j].getSpr());
			}
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
			if(mapa.getSprites().size()>0) {
				if(colisionSprite(c,posXAux,c.getSpr().getY())){
					mov=false;
				}
			}
		}
	}else {
		if(posXAux < mapa.getSpr().getX() + c.getTamaño()) {
			mov=false;
		}else {
			if(mapa.getSprites().size()>0) {
				if(colisionSprite(c,posXAux,c.getSpr().getY())){
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
		}
	}
//	for (int i = 0; i < t.length; i++) {
//		t[i].setX(verifMov(t[i],dir));
//		t[i].getSpr().setX(t[i].getX());
//	}
	
}
public boolean colisionSprite(Cuadrado c, float posAuxX, float posAuxY) {
	boolean colision = false;
	int i=0;
	do {
		if(posAuxY==mapa.getSprites().get(i).getY() && posAuxX == mapa.getSprites().get(i).getX()) {
			colision=true;
		}
		i++;
	}while(i<mapa.getSprites().size() && !colision);
		

	return colision;
}
public void bajarPieza() {
	if(verifCaida(p.getTetromino())) {
		for (int i = 0; i < p.getTetromino().length; i++) {
			float pos=p.getTetromino()[i].getSpr().getY()- p.getTetromino()[i].getMovimiento();
			p.getTetromino()[i].getSpr().setY(pos);
		}
	}
}
@Override
public void keyDown(int keycode) {
	if(io.isUp()) {
		System.out.println("pieza");
		girarPieza();
	}
	if(io.isDown()) {
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
private void girarPieza() {
	Cuadrado[] t = p.getTetromino();
	for (int i = 0; i < t.length; i++) {
		t[i].getSpr().rotate90(false);
	}
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
	if(mapa.getSprites().size()>0) {
		for (int i = 0; i < mapa.getSprites().size(); i++) {
			mapa.getSprites().get(i).getX();
		}
		
	}
}
}
	
		

	
	


	


	



