package com.proyecto.red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.proyecto.pantallas.ScreenJuego;
import com.proyecto.utiles.Mundo;
import com.proyecto.utiles.Utiles;

public class HiloCliente extends Thread{
	private DatagramSocket socket;
	private InetAddress ipServer;
	private int puerto = 6969;
	private boolean fin = false;
	
	public HiloCliente() {
			try {
				ipServer = InetAddress.getByName("255.255.255.255");
				socket = new DatagramSocket();
			} catch (SocketException | UnknownHostException e) {
				e.printStackTrace();
			}
		
			enviarMensaje("Conexion");

	}

	public void enviarMensaje(String msg) {
		byte[] data = msg.getBytes();
		DatagramPacket dp = new DatagramPacket(data, data.length, ipServer, puerto);
		try {
			socket.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		do {
			byte[] data = new byte[1024];
			DatagramPacket dp = new DatagramPacket(data, data.length);
			try {
				socket.receive(dp);
	
			} catch (IOException e) {
				e.printStackTrace();
			}
			procesarMensaje(dp);
					
		}while(!fin);
	}
	
	private void procesarMensaje(DatagramPacket dp) {
		String msg = (new String(dp.getData())).trim();
		
		String[] comando = msg.split("!");
			if(comando[0].equals("OK")) {
				ipServer = dp.getAddress();
				Mundo.app.getCliente().setId(Integer.valueOf(comando[1]));
			}
			if(comando[0].equals("Empieza")) {
				Mundo.app.setCambio(true);
			}
			
			if(comando[0].equals("crearPieza")) {
				ScreenJuego sj = null;
				try {
					sj = (ScreenJuego) Mundo.app.getScreen();
					
				} catch (Exception e) {
					System.out.println("error");
				}
				if(Integer.valueOf(comando[3])==(Mundo.app.getCliente().getId())) {
					sj.getJuego().nuevaPieza(Integer.valueOf(comando[1]), Integer.valueOf(comando[2]));
				}else {
					sj.getJuego2().nuevaPieza(Integer.valueOf(comando[1]), Integer.valueOf(comando[2]));
				}
				
			}
			if(comando[0].equals("bajar")) {
				ScreenJuego sj = null;
				try {
					sj = (ScreenJuego) Mundo.app.getScreen();
				} catch (Exception e) {
					System.out.println("error");
				}
				if(Integer.valueOf(comando[1])==Mundo.app.getCliente().getId()) {
					sj.getJuego().bajarPieza();
				}else {
					sj.getJuego2().bajarPieza();
				}
			}
			if(comando[0].equals("girar")) {
				ScreenJuego sj = null;
				try {
					sj = (ScreenJuego) Mundo.app.getScreen();
				} catch (Exception e) {
					System.out.println("error");
				}
				sj.getJuego2().girarPieza();
			}
		
		
		
	
	}
}
