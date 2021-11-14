package com.proyecto.red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class HiloCliente extends Thread{

	private DatagramSocket socket;
	private InetAddress ipServer;
	private int puerto = 6767;
	private boolean fin = false;
	
	public HiloCliente() {
			try {
				ipServer = InetAddress.getByName("26.33.181.120");
				socket = new DatagramSocket();
			} catch (SocketException | UnknownHostException e) {
				e.printStackTrace();
			}
			enviarMensaje("Conexion!");

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
		
		if(comando.length > 1) {
			
		
		
		}
	
	}
}
