package com.pds.controle;

import java.net.Socket;

import java.io.PrintStream;

public class MensageiroPartida extends Mensageiro {

	private PrintStream saida;
	private String mensagem;
	
	
	public MensageiroPartida(PrintStream saida) {
		// TODO Auto-generated constructor stub
		this.saida = saida;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		enviarMensagem();
	}

	@Override
	public void enviarMensagem() {
		// TODO Auto-generated method stub
		saida.println(mensagem);
		mensagem = null;
	}
	
	@Override
	public void setMensagem(String mensagem) {
		// TODO Auto-generated method stub
		this.mensagem = mensagem;
	}
}
