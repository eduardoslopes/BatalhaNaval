package com.pds.controle;

import java.net.Socket;
import java.util.List;

import com.pds.modelo.Jogador;

public class ControladorPartida {

	RepositorioPartidas partidas;
	
	public ControladorPartida() {
		partidas = new RepositorioPartidas();
	}
	
	public void novaPartida(String apelido, String nomePartida, Socket socketJogador) {
		Jogador novoJogador = new Jogador(apelido, socketJogador);
		Partida novaPartida = new Partida(nomePartida, novoJogador);
		partidas.adicionaPartidaEspera(novaPartida);
	}
	
	public void conectarPartida(String apelido, String nomePartida, Socket socketJogador) {
		Jogador novoJogador = new Jogador(apelido, socketJogador);
		partidas.adicionaJogadorPartida(novoJogador, nomePartida);
		iniciarPartida(partidas.getPartida(nomePartida));
	}
	
	private void iniciarPartida(Partida novaPartida) {
		if (novaPartida != null) {
			
		}
	}
	
	public List<Partida> getListaPartidasEmEspera() {
		return partidas.getPartidasEmEspera();
	}
}
