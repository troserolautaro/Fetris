package com.proyecto.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.proyecto.juego.Juego;
import com.proyecto.utiles.Config;

public class ScreenJuego implements Screen {
 private Juego juego;
 private Juego juego2;
 private OrthographicCamera cam;
 private Stage stage;
	@Override
	public void show() {
		stage= new Stage();
		iniciarCam();
		juego2= new Juego(false);
		juego= new Juego(true);
	}

	public Juego getJuego() {
		return juego;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		juego.update(cam, delta);
		juego2.update(cam, delta);
		juego.render();
		juego2.render();
//		debug();
		cam.update();
	}
	
//	private void debug() {
//		if(Gdx.input.justTouched()) {
//	           System.out.println(cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0))); 
//		}
//	}
//		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
//			
//			cam.position.y+=1;
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
//			
//			cam.position.y-=1;
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
//			cam.position.x-=1;
//			
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
//			cam.position.x+=1;
//			
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
//			cam.zoom-=1/Utiles.PPM;
//			
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.E)) {
//			cam.zoom+=1/Utiles.PPM;
//			
//		}
	
//	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
	private  void iniciarCam() {
			cam= new OrthographicCamera();
			cam.setToOrtho(false, Config.ANCHO/ 2, Config.ALTO / 2);
			cam.zoom=1f;
		cam.update();
	}

}
