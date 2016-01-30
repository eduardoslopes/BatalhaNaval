package com.pds.controle;

import java.net.Socket;

import com.pds.modelo.Mensagem;

public class ControladorInterpretacao {
	private Descerializador descerializador;
	private Interpretador interpretador;
	
	public ControladorInterpretacao(Interpretador interpretador, Descerializador descerializador) {
		this.descerializador = descerializador;
		this.interpretador = interpretador;
	}

	public void mensagemParaInterpretar(String msg, Socket jogador) {
		Mensagem mensagem = descerializador.descerializar(msg);
		interpretador.interpretar(mensagem, jogador);
	}	

}
