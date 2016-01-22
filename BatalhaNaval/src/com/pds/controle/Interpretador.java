package com.pds.controle;

import com.pds.modelo.Mensagem;
import com.pds.modelo.TAG;

public abstract class Interpretador {

	public void interpretar(Mensagem mensagem, ControladorInterpretacao controlador) {
		switch(mensagem.getTag()){
		case TAG.CONECTG:
			conect(mensagem.getNome(), mensagem.getNomePartida(), controlador);
			break;
		case TAG.CREATEG:
			create(mensagem.getNome(), mensagem.getNomePartida(), controlador);
			break;
		}
	}

	public abstract void create(String nome, String nomePartida, ControladorInterpretacao controlador);
	public abstract void conect(String nome, String nomePartida, ControladorInterpretacao controlador);

}
