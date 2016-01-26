package com.pds.controle;

import java.net.Socket;
import java.util.List;

public class DistribuidorResponsabilidades {
	private ControladorPartida cPartidas;
	
	public DistribuidorResponsabilidades(ControladorPartida cPartida) {
		this.cPartidas = cPartida;
	}

	public void criarPartida(String nome, String nomePartida, Socket socketJogador) {
		cPartidas.novaPartida(nome, nomePartida, socketJogador);		
	}

	public void conectarPartida(String nome, String nomePartida, Socket socketJogador) {
		cPartidas.conectarPartida(nome, nomePartida, socketJogador);
	}

	public void verPartidasEmEspera(Socket socketJogador, Serializador serializador) {
		List<Partida> partidas = cPartidas.getListaPartidasEmEspera();
		List<String> mensagens = serializador.serializarPartidas(partidas);
		Mensageiro mensageiro = new MensageiroListaPartidas(socketJogador, mensagens);
		Thread t = new Thread(mensageiro);
		t.start();
	}

	public void enviaMensagemCliente(String msg, String nomeDono, String nomePartida) {
		cPartidas.encaminhaMensagem(nomeDono, msg, nomePartida);		
	}
}
