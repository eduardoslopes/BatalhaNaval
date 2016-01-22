package com.pds.controle;

import com.google.gson.Gson;
import com.pds.modelo.Mensagem;

public class Descerializador {

	public Mensagem decerializar(String msg) {
		Gson gson = new Gson();
		return gson.fromJson(msg, Mensagem.class);
	}

}
