package com.pds.controle;

import com.pds.modelo.Cliente;

public class Partida {

	private Cliente criadorPartida;
	private Cliente convidado;
	
	public Partida(Cliente criadorPartida) {
		
		this.criadorPartida = criadorPartida;
	}
	
	public void setConvidado(Cliente convidado) {
		this.convidado = convidado;
	}
}
