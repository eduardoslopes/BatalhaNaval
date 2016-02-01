package com.pds.controle;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class MensageiroListaPartidas2 extends Mensageiro {

	private List<String> mensagens;
	private PrintStream saida;
	
	public MensageiroListaPartidas2(Socket sockJogador, List<String> mensagens) {
		this.mensagens = mensagens;
		try {
			this.saida = new PrintStream(sockJogador.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		enviarMensagem();		
	}

	@Override
	public void enviarMensagem() {
		for(String mensagem: mensagens){
			saida.println(mensagem);
		}
	}
	
	@Override
	public void setMensagem(String mensagem) {
		
	}


}
