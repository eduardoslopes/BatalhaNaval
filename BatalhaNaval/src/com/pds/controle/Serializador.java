package com.pds.controle;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.pds.modelo.Mensagem;

public class Serializador {
	
	public String serializar(Mensagem msg) {
		Gson gson = new Gson();
		return gson.toJson(msg);
	}
	
	public List<String> serializarPartidas(List<Partida> partidas) {
		Gson gson = new Gson();
		List<String> partidasSerializadas = new ArrayList<>();
		for(Partida p : partidas){
			partidasSerializadas.add(gson.toJson(p));
		}
		return partidasSerializadas;
	}
	
}