package com.pds.controle;

import java.io.PrintStream;

public class MensageiroPartida extends Mensageiro {

	private PrintStream saida;

	public MensageiroPartida(PrintStream saida) {
		this.saida = saida;
	}

	@Override
	public void enviarMensagem(String mensagem) {
		System.out.println("Enviando: " + mensagem);
		saida.println(mensagem);
	}

	@Override
	public void enviarMensagem() {
		
	}
	
}
