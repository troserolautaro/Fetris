package com.proyecto.utiles;

import com.badlogic.gdx.InputProcessor;

public class KeyEventListener implements InputProcessor{
	private boolean jump = false, right = false, left = false;
    private boolean click = false;
    
    @Override
    public boolean keyDown(int keycode) {
		return false;
	}
//        if(keycode==Keys.SPACE) {
//            Mundo.app.getCliente().getHc().enviarMensaje("Ejecutar!Salto!" + Mundo.app.getCliente().getId());
//            jump = true;
//        }
//        if(keycode == Keys.D) {
//            Mundo.app.getCliente().getHc().enviarMensaje("Ejecutar!Derecha!" + Mundo.app.getCliente().getId());
//            right = true;
//        }
//        if(keycode == Keys.A) {
//            Mundo.app.getCliente().getHc().enviarMensaje("Ejecutar!Izquierda!" + Mundo.app.getCliente().getId());
//            left = true;
//        }
//        return false;
//    }
//
    	@Override
    	public boolean keyUp(int keycode) {
    		return false;
    	}
//        if(keycode==Keys.SPACE) {
//            jump = false;
//            Mundo.app.getCliente().getHc().enviarMensaje("DejarEjecutar!Salto!" + Mundo.app.getCliente().getId());
//        }
//        if(keycode == Keys.D) {
//            right = false;
//            Mundo.app.getCliente().getHc().enviarMensaje("DejarEjecutar!Derecha!" + Mundo.app.getCliente().getId());
//
//        }
//        if(keycode == Keys.A) {
//            left = false;
//            Mundo.app.getCliente().getHc().enviarMensaje("DejarEjecutar!Izquierda!" + Mundo.app.getCliente().getId());
//
//        }
//        return false;
//    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        click = true;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        click = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }
    
    public boolean isClicking() {
        return click;
    }
    
    public boolean isJumping() {
        return jump;
    }
    
    public boolean isRight() {
        return right;
    }
    
    public boolean isLeft() {
        return left;
    }

}
