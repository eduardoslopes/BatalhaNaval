package com.pds.controle;

import java.net.Socket;

import com.pds.modelo.Mensagem;

public class ControladorInterpretacao {
	private Descerializador descerializador;
	private Interpretador interpretador;
	private ControladorPartida cPartida;
	private Socket sockJogador;
	
	public ControladorInterpretacao(ControladorPartida cPartida) {
		this.descerializador = new Descerializador();
		this.interpretador = new InterpretadorMensagem();
		this.cPartida = cPartida;
	}

	public void mensagemParaInterpretar(String msg, Socket jogador) {
		sockJogador = jogador;
		Mensagem mensagem = descerializador.decerializar(msg);
		interpretador.interpretar(mensagem, this);
	}

	public void criarPartida(String nome, String nomePartida) {
		cPartida.novaPartida(nome, nomePartida, sockJogador);
	}

	public void conectarEmPartida(String nome, String nomePartida) {
		cPartida.conectarPartida(nome, nomePartida, sockJogador);
	}
	
	

}
