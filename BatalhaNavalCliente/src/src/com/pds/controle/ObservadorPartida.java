package com.pds.controle;

import com.pds.modelo.Partida;

public interface ObservadorPartida {
	
	public void atualizarPartidaLista(Partida partida);

	public void conectarJogo();
}
