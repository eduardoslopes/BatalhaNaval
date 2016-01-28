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
		if (!partidas.existePartida(nomePartida)) {
			Jogador novoJogador = new Jogador(apelido, socketJogador);
			Partida novaPartida = new Partida(nomePartida, novoJogador);
			partidas.adicionaPartidaEspera(novaPartida);
		} else {
			ObservadorErroNovaPartida erro = new EnviaMensagemErro();
			erro.erroNovaPartida(socketJogador);
		}
	}
	
	public void conectarPartida(String apelido, String nomePartida, Socket socketJogador) {
		Partida partida = partidas.getPartidaEmEspera(nomePartida);
		if (!partida.getCriadorPartida().equals(apelido)) {
			Jogador novoJogador = new Jogador(apelido, socketJogador);
			partidas.adicionaJogadorPartida(novoJogador, partida.getNomePartida());
			montarTabuleiro(partida);			
		} else {
			ObservadorErroConectarPartida erro = new EnviaMensagemErro();
			erro.erroConectarPartida(socketJogador);
		}
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
		partidas.removePartidaIniciada(partida);
	}

	public void desconectarPartida(String apelido, String nomePartida) {
		try {
			Partida partida = partidas.getPartidaIniciada(nomePartida);
			partida.cancelarPartida(apelido);
			partidas.removePartidaIniciada(partida);
		} catch (NullPointerException e) {
			System.out.println("Ja foi enviada a mensagem");
		}
	}
}
