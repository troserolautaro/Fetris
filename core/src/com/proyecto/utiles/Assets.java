package com.proyecto.utiles;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.proyecto.piezas.Colores;

public class Assets {
	public static final AssetManager manager = new AssetManager();	
	public static void load() {
		for (int i = 0; i < Colores.values().length; i++) {
			manager.load(Colores.values()[i].getDir(),Texture.class);
		}
	}
	 
}
