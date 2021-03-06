package com.pds.controle;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.pds.modelo.Mensagem;
import com.pds.modelo.TAG;

public class Serializador {
	
	public String serializar(Mensagem msg) {
		Gson gson = new Gson();
		return gson.toJson(msg);
	}
	
	public List<String> serializarPartidas(List<Partida> partidas) {
		Gson gson = new Gson();
		List<String> msgPartidasSerializadas = new ArrayList<>();
		for(Partida p : partidas){
			Mensagem msg = new Mensagem(TAG.SEEGAMES);
			msg.setApelidoJogador(p.getCriadorPartida().getApelido());
			msg.setNomePartida(p.getNomePartida());
			msgPartidasSerializadas.add(gson.toJson(msg));
		}
		return msgPartidasSerializadas;
	}
	
}
