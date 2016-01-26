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
		Partida partida = partidas.getPartidaEmEspera(nomePartida);
		partidas.adicionaJogadorPartida(novoJogador, partida.getNomePartida());
		iniciarPartida(partida);
	}
	
	private void iniciarPartida(Partida novaPartida) {
		novaPartida.enviarMensagemInicioPartida();
	}
	
	public void encaminhaMensagem(String apelidoRemetente, String mensagem, String nomePartida) {
		Partida partida = partidas.getPartidaIniciada(nomePartida);
		partida.encaminharMensagem(mensagem, apelidoRemetente);
	}
	
	public List<Partida> getListaPartidasEmEspera() {
		return partidas.getPartidasEmEspera();
	}
}
