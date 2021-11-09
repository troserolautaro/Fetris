package com.proyecto.utiles;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import com.proyecto.juego.JuegoEventListener;
import com.proyecto.mapas.Mapa;
import com.proyecto.pantallas.ScreenJuego;


public final class Utiles {
	public static final  boolean X = true;
	public static final  boolean O = false;

	public static Random r = new Random();
	public static Scanner s = new Scanner(System.in);
	public static ArrayList<JuegoEventListener> listeners = new ArrayList <JuegoEventListener>();
	public static final  float  PPM= 100;
	
	public static void delay(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static int ingresarEntero(int min, int max) {
        boolean error = false;
        int valor = 0;
        do {
            error = false;
            try {
                valor = s.nextInt();
                if((valor<min)||(valor>max)) {
                    error = true;
                    System.out.println("Error. Debe ingresar un numero entre " + min + " y " + max);
                }
            }catch(InputMismatchException e) {
                
                error = true;
                s.nextLine();
            }catch(Exception e) {
                System.out.println("Error desconocido");
                error = true;
                s.nextLine();
            }
        }while(error);
        s.nextLine();
        return valor;
    }
	
	public static float ingresarDecimal(float min, float max) {
        boolean error = false;
        float valor = 0;
        do {
            error = false;
            try {
                valor = s.nextFloat();
                if((valor<min)||(valor>max)) {
                    error = true;
                    System.out.println("Error. Debe ingresar un numero entre " + min + " y " + max);
                }
            }catch(InputMismatchException e) {
                System.out.println("Tipo de dato mal ingresado. Vuelva a intentar");
                error = true;
               s.nextLine();
            }catch(Exception e) {
                System.out.println("Error desconocido");
                error = true;
                s.nextLine();
            }
        }while(error);
        s.nextLine();
        return valor;
    }

public static Mapa obtenerMapa() {
	ScreenJuego sj = (ScreenJuego) Mundo.app.getScreen();
	return sj.getJuego().getMapa();
	
	
		
	}

	
}
