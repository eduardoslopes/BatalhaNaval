package com.pds.modelo;

import java.net.Socket;

public class Jogador {

	private String apelido;
	private Socket socket;
	
	public Jogador(String apelido, Socket socket) {
		
		this.apelido = apelido;
		this.socket = socket;
	}
}
