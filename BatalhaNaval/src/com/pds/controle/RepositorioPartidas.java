package com.pds.controle;

import java.util.ArrayList;
import java.util.List;

import com.pds.modelo.Jogador;

public class RepositorioPartidas {

	private List<Partida> partidasIniciadas;
	private List<Partida> partidasEmEspera;
	
	public RepositorioPartidas() {
		partidasIniciadas = new ArrayList<Partida>();
		partidasEmEspera = new ArrayList<Partida>();
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
	
	public Partida getPartidaEmEspera(String nomePartida) {
		for (Partida partida : partidasEmEspera) {
			if (partida.getNomePartida().equals(nomePartida)) {
				return partida;
			}
		}
		return null;
	}
	
	public Partida getPartidaIniciada(String nomePartida) {
		for (Partida partida : partidasIniciadas) {
			if (partida.getNomePartida().equals(nomePartida)) {
				return partida;
			}
		}
		return null;
	}
	
	public void removePartidaIniciada(Partida partida) {
		partidasIniciadas.remove(partida);
	}

	public boolean existePartida(String nomePartida) {
		for (Partida p : partidasEmEspera)
			if (p.getNomePartida().equals(nomePartida))
				return true;
		for (Partida p : partidasIniciadas)
			if (p.getNomePartida().equals(nomePartida))
				return true;
		return false;
	}
}
