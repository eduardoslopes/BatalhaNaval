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
	public void create(String nome, String nomePartida, Socket sockJogador) {
		distribuidor.criarPartida(nome, nomePartida, sockJogador);
	}

	@Override
	public void conect(String nome, String nomePartida, Socket sockJogador) {
		distribuidor.conectarPartida(nome, nomePartida, sockJogador);
	}

	@Override
	public void seeg(Socket sockJogador) {
		distribuidor.verPartidasEmEspera(sockJogador, serializador);		
	}

	@Override
	public void comunicaCliente(Mensagem mensagem) {
		String nomeDono = mensagem.getApelidoJogador();
		String nomePartida = mensagem.getNomePartida();
		String msg = serializador.serializar(mensagem);
		distribuidor.enviaMensagemCliente(msg, nomeDono, nomePartida);		
	}

	

}
