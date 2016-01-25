package com.pds.controle;

import java.util.ArrayList;
import java.util.List;

import com.pds.modelo.Jogador;

public class RepositorioPartidas {

	private List<Partida> partidasIniciadas;
	private List<Partida> partidasEmEspera;
	
	public RepositorioPartidas() {
		partidasIniciadas = new ArrayList<>();
		partidasEmEspera = new ArrayList<>();
	}
	
	public void adicionaPartidaEspera(Partida novaPartida) {
		partidasEmEspera.add(novaPartida);
	}

	public void adicionaJogadorPartida(Jogador novoJogador, String nomePartida) {
		for (Partida partida : partidasEmEspera) {
			if (partida.getNomePartida().equals(nomePartida)) {
				partida.adicionaConvidado(novoJogador);
				partidasEmEspera.remove(partida);
				partidasIniciadas.add(partida);
				break;
			}
		}
	} 
	
	public List<Partida> getPartidasEmEspera() {
		return partidasEmEspera;
	}
	
	public Partida getPartida(String nomePartida) {
		for (Partida partida : partidasEmEspera) {
			if (partida.getNomePartida().equals(nomePartida)) {
				return partida;
			}
		}
		return null;
	}
}