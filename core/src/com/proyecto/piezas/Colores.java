package com.proyecto.piezas;

public enum Colores {
AZUL("Blue.png"), 
VERDE("Green.png"),
CELESTE("LightBlue.png"), 
NARANJA("Orange.png"), 
VIOLETA("Purple.png"),
ROJO("Red.png"), 
AMARILLO("Yellow.png"),
GRIS("Gray.png");
 private String dir;	

	
private Colores (String dir ) {
	this.dir="Tetriminos/Single Blocks/" + dir;
}


	public String getDir() {
	return this.dir;	
	}
}
