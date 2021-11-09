package com.proyecto.evento;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.proyecto.utiles.Utiles;

public class KeyListener implements InputProcessor {
	
	private boolean up = false, down = false, right = false, left = false, space=false;

	@Override
	public boolean keyDown(int keycode) {
		if(keycode==Keys.DOWN) down = true;
		if(keycode==Keys.UP) up = true;
		if(keycode==Keys.LEFT) left = true;
		if(keycode==Keys.RIGHT) right = true;	
		if(keycode==Keys.SPACE) space = true;
		for (int i = 0; i < Utiles.listeners.size(); i++) {
			Utiles.listeners.get(i).keyDown(keycode);
		}
	
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode==Keys.DOWN) down = false;
		if(keycode==Keys.UP) up = false;
		if(keycode==Keys.LEFT) left = false;
		if(keycode==Keys.RIGHT) right = false;	
		if(keycode==Keys.SPACE) space = false;
		for (int i = 0; i < Utiles.listeners.size(); i++) {
			Utiles.listeners.get(i).keyUp(keycode);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isLeft() {
		return left;
	}
	public boolean isSpace() {
		return space;
	}
	
	

}
