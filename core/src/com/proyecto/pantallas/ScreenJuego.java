package com.proyecto.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.proyecto.juego.Juego;
import com.proyecto.utiles.Config;
import com.proyecto.utiles.Mundo;

public class ScreenJuego implements Screen {
 private Juego juego;
 private Juego juego2;
 private OrthographicCamera cam;
	@Override
	public void show() {
		iniciarCam();
		juego2= new Juego(false);
		juego= new Juego(true);
		Mundo.app.getCliente().getHc().enviarMensaje("creado");
	}

	public Juego getJuego() {
		return juego;
	}
	public Juego getJuego2() {
		return juego2;
	}

	@Override
	public void render(float delta) {
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			update(delta);
			juego.render();
			juego2.render();
//			debug();			
		
		
	}
	public void update(float delta) {
		Mundo.batch.setProjectionMatrix(cam.combined);
		cam.update();
		
	}
	


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {

		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		
	}
	private  void iniciarCam() {
			cam= new OrthographicCamera();
			cam.setToOrtho(false, Config.ANCHO/ 2, Config.ALTO / 2);
			cam.zoom=1f;
			cam.update();
	}

	

}
