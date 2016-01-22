package com.pds.controle;

public class InterpretadorMensagem extends Interpretador {

	@Override
	public void create(String nome, String nomePartida, ControladorInterpretacao controlador) {
		controlador.criarPartida(nome, nomePartida);
	}

	@Override
	public void conect(String nome, String nomePartida, ControladorInterpretacao controlador) {
		controlador.conectarEmPartida(nome, nomePartida);
	}

}
