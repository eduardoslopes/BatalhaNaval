package com.pds.controle;

import com.pds.modelo.Jogador;

public class Partida {

	private String nomePartida;
	private Jogador criadorPartida;
	private Jogador convidado;
	
	public Partida(String nomePartida, Jogador criadorPartida) {
		this.nomePartida = nomePartida;
		this.criadorPartida = criadorPartida;
	}
	
	public void adicionaConvidado(Jogador convidado) {
		this.convidado = convidado;
	}
	
	public String getNomePartida() {
		return nomePartida;
	}
}
