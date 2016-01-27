package com.pds.controle;

import java.net.Socket;

import com.pds.modelo.Mensagem;

public class InterpretadorMensagem extends Interpretador {
	private Serializador serializador;
	private DistribuidorResponsabilidades distribuidor;
	
	public InterpretadorMensagem(Serializador serializador, DistribuidorResponsabilidades distribuidor) {
		this.serializador = serializador;
		this.distribuidor = distribuidor;
	}

	@Override
	public void create(String apelido, String nomePartida, Socket sockJogador) {
		distribuidor.criarPartida(apelido, nomePartida, sockJogador);
	}

	@Override
	public void conect(String apelido, String nomePartida, Socket sockJogador) {
		distribuidor.conectarPartida(apelido, nomePartida, sockJogador);
	}

	@Override
	public void seeg(Socket sockJogador) {
		distribuidor.verPartidasEmEspera(sockJogador, serializador);		
	}

	@Override
	public void comunicaCliente(Mensagem mensagem) {
		String apelidoDono = mensagem.getApelidoJogador();
		String nomePartida = mensagem.getNomePartida();
		String msg = serializador.serializar(mensagem);
		distribuidor.enviaMensagemCliente(msg, apelidoDono, nomePartida);		
	}

	@Override
	public void ready(String apelido, String nomePartida) {
		distribuidor.jogadorPronto(apelido, nomePartida);
	}
}
