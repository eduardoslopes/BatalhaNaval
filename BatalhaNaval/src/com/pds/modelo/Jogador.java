package com.pds.modelo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Jogador {

	private String apelido;
	private Socket socket;
	private boolean pronto;
	
	public Jogador(String apelido, Socket socket) {
		this.apelido = apelido;
		this.socket = socket;
		this.pronto = false;
	}
	
	public PrintStream getFluxoSaida() {
		try {
			return new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getApelido() {
		return this.apelido;
	}
	
	public boolean getPronto() {
		return this.pronto;
	}
	
	public void setPronto(boolean valor) {
		this.pronto = valor;
	}
}
