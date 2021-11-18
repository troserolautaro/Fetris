package com.proyecto.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.proyecto.utiles.Config;
import com.proyecto.utiles.Mundo;

public class ScreenLobby implements Screen {
	 private OrthographicCamera cam;
	 private Sprite spr;
	@Override
	public void show() {
		iniciarCam();
		spr= new Sprite(new Texture("Tetriminos\\Title\\Title_No_BG.png"));
		spr.setBounds(Config.ANCHO/2-350, Config.ALTO/1.5f,700, 100);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Mundo.batch.begin();
		spr.draw(Mundo.batch);
		Mundo.batch.end();
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
		cam.setToOrtho(false, Config.ANCHO/ 2, Config.ALTO / 2);
		cam.zoom=1f;
	cam.update();
}

}
