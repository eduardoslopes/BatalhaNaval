package com.pds.controle;

import java.net.Socket;

import com.pds.modelo.Mensagem;
import com.pds.modelo.TAG;

public abstract class Interpretador {

	public void interpretar(Mensagem mensagem, Socket jogador) {
		switch(mensagem.getTag()){
		case TAG.CONECTGAME:
			conect(mensagem.getApelidoJogador(), mensagem.getNomePartida(), jogador);
			break;
		case TAG.CREATEGAME:
			create(mensagem.getApelidoJogador(), mensagem.getNomePartida(), jogador);
			break;
		case TAG.SEEGAMES:
			seeg(jogador);
			break;
		default:
			comunicaCliente(mensagem);
			break;
		}
	}

	public abstract void create(String nome, String nomePartida, Socket jogador);
	public abstract void conect(String nome, String nomePartida, Socket jogador);
	public abstract void seeg(Socket jogador);
	public abstract void comunicaCliente(Mensagem mensagem);
}
