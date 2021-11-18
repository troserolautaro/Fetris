 package com.proyecto.red;

public class Cliente {

//	private ArrayList<Juego> clientes = new ArrayList<Juego>();
	private HiloCliente hc;
	private int id;
	
	public void crearHilo() {
		hc = new HiloCliente();
		hc.start();
	}
	
	public HiloCliente getHc() {
		return this.hc;
	}
	
//	public ArrayList<Juego> getClientes(){
//		return this.clientes;
//	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
