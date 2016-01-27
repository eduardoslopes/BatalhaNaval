package com.pds.controle;

import java.net.Socket;
import java.util.List;

public class DistribuidorResponsabilidades {
	private ControladorPartida cPartidas;
	
	public DistribuidorResponsabilidades(ControladorPartida cPartida) {
		this.cPartidas = cPartida;
	}

	public void criarPartida(String apelido, String nomePartida, Socket socketJogador) {
		cPartidas.novaPartida(apelido, nomePartida, socketJogador);		
	}

	public void conectarPartida(String apelido, String nomePartida, Socket socketJogador) {
		cPartidas.conectarPartida(apelido, nomePartida, socketJogador);
	}

	public void verPartidasEmEspera(Socket socketJogador, Serializador serializador) {
		List<Partida> partidas = cPartidas.getListaPartidasEmEspera();
		List<String> mensagens = serializador.serializarPartidas(partidas);
		Mensageiro mensageiro = new MensageiroListaPartidas(socketJogador, mensagens);
		Thread t = new Thread(mensageiro);
		t.start();
	}

	public void enviaMensagemCliente(String msg, String apelidoDono, String nomePartida) {
		cPartidas.encaminhaMensagem(apelidoDono, msg, nomePartida);		
	}

	public void jogadorPronto(String apelido, String nomePartida) {
		cPartidas.jogadorPronto(apelido, nomePartida);
	}
}
