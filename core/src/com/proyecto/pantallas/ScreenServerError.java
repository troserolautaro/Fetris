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

public class ScreenServerError implements Screen {
	private Sprite spr;
	private Sprite txt;
	private OrthographicCamera cam;
	float cd = 0;
	public void show() {
		iniciarCam();
		spr = new Sprite(Assets.manager.get("Tetriminos/Title/ServerErrorMsg.png", Texture.class));
		txt = new Sprite(Assets.manager.get("Tetriminos/Title/ServerErrorAccion.png", Texture.class)); 
		spr.setBounds(Config.ANCHO/2-250, Config.ALTO/2f+100,500, 100);
		txt.setBounds(Config.ANCHO/2-150, Config.ALTO/2f-150,300, 50);
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
		cd +=delta;
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && cd > 3) {
			Mundo.app.getCliente().crearHilo();
		}
		
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