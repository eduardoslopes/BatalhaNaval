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
		montarTabuleiro(partida);
	}
	
	private void montarTabuleiro(Partida novaPartida) {
		novaPartida.enviarMensagemMontarTabuleiro();
	}
	
	public void encaminhaMensagem(String apelidoRemetente, String mensagem, String nomePartida) {
		Partida partida = partidas.getPartidaIniciada(nomePartida);
		partida.encaminharMensagem(mensagem, apelidoRemetente);
	}
	
	public List<Partida> getListaPartidasEmEspera() {
		return partidas.getPartidasEmEspera();
	}

	public void jogadorPronto(String apelido, String nomePartida) {
		Partida partida = partidas.getPartidaIniciada(nomePartida);
		partida.setJogadorPronto(apelido);
	}

	public void enviarMensagemFinalPartida(String apelido, String nomePartida) {
		Partida partida = partidas.getPartidaIniciada(nomePartida);
		partida.finalPartida(apelido);
	}

	public void desconectarPartida(String apelido, String nomePartida) {
		Partida partida = partidas.getPartidaIniciada(nomePartida);
		partida.cancelarPartida(apelido);
	}
}
