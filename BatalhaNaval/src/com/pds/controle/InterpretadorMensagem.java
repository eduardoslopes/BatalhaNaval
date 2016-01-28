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
	public void criar(String apelido, String nomePartida, Socket sockJogador) {
		distribuidor.criarPartida(apelido, nomePartida, sockJogador);
	}

	@Override
	public void conectar(String apelido, String nomePartida, Socket sockJogador) {
		distribuidor.conectarPartida(apelido, nomePartida, sockJogador);
	}

	@Override
	public void verPartidas(Socket sockJogador) {
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
	public void pronto(String apelido, String nomePartida) {
		distribuidor.jogadorPronto(apelido, nomePartida);
	}

	@Override
	public void perdeu(String apelido, String nomePartida) {
		distribuidor.enviarMensagemFinalPartida(apelido, nomePartida);
	}

	@Override
	public void desconectar(String apelido, String nomePartida) {
		distribuidor.enviarMensagemDesconectar(apelido, nomePartida);
	}
}
