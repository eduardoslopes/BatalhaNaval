package com.pds.modelo;

import java.net.Socket;

public class Cliente {

	Socket socket;
	
	public Cliente(Socket socket) {
		
		this.socket = socket;
	}
}
