package com.proyecto.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.proyecto.utiles.Assets;
import com.proyecto.utiles.Config;
import com.proyecto.utiles.Mundo;

public class ScreenFin implements Screen {
	private Sprite spr;
	private OrthographicCamera cam;
	public ScreenFin(boolean ganar) {
		if(ganar) {
			spr = new Sprite(Assets.manager.get("Tetriminos/Title/Ganaste.png", Texture.class));
		}else {
			spr = new Sprite(Assets.manager.get("Tetriminos/Title/Perdiste.png", Texture.class));
		}
		
//		System.out.println((ganar)? "Ganaste":"Perdiste");
	}
	@Override
	public void show() {
		iniciarCam();
		spr.setBounds(Config.ANCHO/2, Config.ALTO/2f,4000, 4000);
	}

	@Override
	public void render(float delta) {
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
		// TODO Auto-generated method stub
		
	}
	private  void iniciarCam() {
		cam= new OrthographicCamera();
		cam.setToOrtho(false, Config.ANCHO/ 2, Config.ALTO / 2);
		cam.zoom=1f;
		cam.update();
}

}
