package com.pds.controle;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class MensageiroListaPartidas extends Mensageiro {

	private List<String> mensagens;
	private PrintStream saida;
	
	public MensageiroListaPartidas(Socket sockJogador, List<String> mensagens) {
		this.mensagens = mensagens;
		try {
			this.saida = new PrintStream(sockJogador.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enviarMensagem() {
		for(String mensagem: mensagens){
			enviarMensagem(mensagem);
		}
	}

	@Override
	public void enviarMensagem(String mensagem) {
		saida.println(mensagem);		
	}

}
