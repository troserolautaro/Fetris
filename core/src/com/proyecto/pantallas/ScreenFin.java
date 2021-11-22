package com.proyecto.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.proyecto.utiles.Assets;
import com.proyecto.utiles.Config;
import com.proyecto.utiles.Mundo;
import com.proyecto.utiles.Utiles;

public class ScreenFin implements Screen {
	private Sprite spr;
	private Sprite txt;
	private OrthographicCamera cam;
	public ScreenFin(boolean ganar) {
		if(ganar) {
			spr = new Sprite(Assets.manager.get("Tetriminos/Title/Ganaste.png", Texture.class));
		}else {
			spr = new Sprite(Assets.manager.get("Tetriminos/Title/Perdiste.png", Texture.class));
		}
		Mundo.app.getCliente().getHc().interrupt();
//		System.out.println((ganar)? "Ganaste":"Perdiste");
	}
	@Override
	public void show() {
		iniciarCam();
		txt = new Sprite(Assets.manager.get("Tetriminos/Title/Continuar.png", Texture.class));
		txt.setBounds(Config.ANCHO/2-150, Config.ALTO/2f-150,300, 50);
		spr.setBounds(Config.ANCHO/2-250, Config.ALTO/2f+100,500, 100);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(delta);
		
		Mundo.batch.begin();
		spr.draw(Mundo.batch);
		txt.draw(Mundo.batch);
		Mundo.batch.end(); 
		
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			Mundo.app.getCliente().crearHilo();
			Mundo.app.setLobby(true);
		}
		
		Utiles.debug(cam);
	}

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
		cam.setToOrtho(false, Config.ANCHO, Config.ALTO);
		cam.zoom=1f;
		cam.update();
}
	public void update(float delta) {
		Mundo.batch.setProjectionMatrix(cam.combined);
		cam.update();
		
	}
}
