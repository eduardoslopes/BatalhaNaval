package com.pds.controle;

import java.net.Socket;

import com.pds.modelo.Jogador;

public class ControladorPartida {

	RepositorioPartidas partidas;
	
	public ControladorPartida() {
		
		partidas = new RepositorioPartidas();
	}
	
	public void novaPartida(String apelido, String nomePartida, Socket socketJogador) {
		
		Jogador novoJogador = new Jogador(apelido, socketJogador);
		Partida novaPartida = new Partida(nomePartida, novoJogador);
	}
	
	public void conectarPartida(String apelido, String nomePartida, Socket socketJogador) {
		
		Jogador novoJogador = new Jogador(apelido, socketJogador);
		partidas.adicionaJogadorPartida(apelido, nomePartida);
	}
}
