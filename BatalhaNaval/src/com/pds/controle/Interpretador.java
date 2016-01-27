package com.pds.controle;

import java.net.Socket;

import com.pds.modelo.Mensagem;
import com.pds.modelo.TAG;

public abstract class Interpretador {

	public void interpretar(Mensagem mensagem, Socket jogador) {
		switch(mensagem.getTag()){
		case TAG.CONNECTGAME:
			conectar(mensagem.getApelidoJogador(), mensagem.getNomePartida(), jogador);
			break;
		case TAG.CREATEGAME:
			criar(mensagem.getApelidoJogador(), mensagem.getNomePartida(), jogador);
			break;
		case TAG.SEEGAMES:
			verPartidas(jogador);
			break;
		case TAG.READY:
			pronto(mensagem.getApelidoJogador(), mensagem.getNomePartida());
			break;
		case TAG.LOSTGAME:
			perdeu(mensagem.getApelidoJogador(), mensagem.getNomePartida());
			break;
		default:
			comunicaCliente(mensagem);
			break;
		}
	}

	public abstract void criar(String apelido, String nomePartida, Socket jogador);
	public abstract void conectar(String apelido, String nomePartida, Socket jogador);
	public abstract void verPartidas(Socket jogador);
	public abstract void comunicaCliente(Mensagem mensagem);
	public abstract void pronto(String apelido, String nomePartida);
	public abstract void perdeu(String apelido, String nomePartida);
}
