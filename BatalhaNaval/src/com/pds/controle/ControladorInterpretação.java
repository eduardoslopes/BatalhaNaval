package com.pds.controle;

import java.net.Socket;

import com.pds.modelo.Mensagem;

public class ControladorInterpretação {
	private Descerializador descerializador;
	private Interpretador interpretador;
	
	public ControladorInterpretação() {
		this.descerializador = new Descerializador();
		this.interpretador = new Interpretador(this);
	}

	public void mensagemParaInterpretar(String msg, Socket jogador) {
		Mensagem mensagem = descerializador.decerializar(msg);
		interpretador.interpretar(mensagem);
		
	}

}
