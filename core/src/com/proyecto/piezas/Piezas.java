package com.proyecto.piezas;

import com.proyecto.utiles.Utiles;

public enum Piezas {
I(new boolean[][]{  {Utiles.O,Utiles.X,Utiles.O,Utiles.O}, 
					{Utiles.O,Utiles.X,Utiles.O,Utiles.O}, 
					{Utiles.O,Utiles.X,Utiles.O,Utiles.O},
					{Utiles.O,Utiles.X,Utiles.O,Utiles.O}}),

R(new boolean[][]  {{Utiles.X,Utiles.X},
					{Utiles.X,Utiles.X}}),

T(new boolean[][] { {Utiles.O,Utiles.X,Utiles.O}, 
					{Utiles.X,Utiles.X,Utiles.X},
					{Utiles.O,Utiles.O,Utiles.O}}),

L(new boolean[][] { {Utiles.X,Utiles.X,Utiles.O},
					{Utiles.O,Utiles.X,Utiles.O},
					{Utiles.O,Utiles.X,Utiles.O}}),

Linv(new boolean[][] {	{Utiles.O,Utiles.X,Utiles.X},
						{Utiles.O,Utiles.X,Utiles.O},
						{Utiles.O,Utiles.X,Utiles.O}}),

S(new boolean[][]{	{Utiles.O,Utiles.X ,Utiles.X},
					{Utiles.X,Utiles.X,Utiles.O},
					{Utiles.O,Utiles.O,Utiles.O}}),

Z(new boolean[][]{	{Utiles.X,Utiles.X,Utiles.O},
					{Utiles.O,Utiles.X,Utiles.X},
					{Utiles.O,Utiles.O,Utiles.O}});
//prueba(new boolean[][] {{Utiles.X,Utiles.X,Utiles.X,Utiles.X,Utiles.X,Utiles.X,Utiles.X,Utiles.X,Utiles.X,Utiles.X}});

	public final boolean[][] pieza;
	private Piezas(boolean[][] pieza) {
		this.pieza= pieza;
			
	}
//	public static boolean[][][] classicPieces = new boolean[][][]{
//		{{O,X,O,O},
//		 {O,X,O,O},
//		 {O,X,O,O},
//		 {O,X,O,O}},
//		
//		   {{X,X},
//			{X,X}},
//		   
//		   {{O,O,O},
//			{X,X,X},
//			{O,X,O}},
//		   
//		   {{O,X,X},
//			{O,X,O},
//			{O,X,O}},
//		   
//		   {{X,X,O},
//			{O,X,O},
//			{O,X,O}},
//		   
//		   {{O,X,O},
//			{X,X,O},
//			{X,O,O}},
//		   
//		   {{O,X,O},
//			{O,X,X},
//			{O,O,X}}
//		};
	public boolean[][] getPieza() {
		return pieza;
	}
	
}
