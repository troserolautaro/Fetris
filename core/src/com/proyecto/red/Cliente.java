 package com.proyecto.red;

import java.util.ArrayList;

import com.proyecto.juego.Juego;

public class Cliente {

	private ArrayList<Juego> clientes = new ArrayList<Juego>();
	private HiloCliente hc;
	private int id;
	
	public void crearHilo() {
		hc = new HiloCliente();
		hc.start();
	}
	
	public HiloCliente getHc() {
		return this.hc;
	}
	
	public ArrayList<Juego> getClientes(){
		return this.clientes;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
		System.out.println("Se seteo id");
	}
}
