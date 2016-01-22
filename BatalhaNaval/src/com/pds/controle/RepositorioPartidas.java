package com.pds.controle;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPartidas {

	List<Partida> partidasIniciadas;
	List<Partida> partidasEmEspera;
	
	public RepositorioPartidas() {
		
		partidasIniciadas = new ArrayList<>();
		partidasEmEspera = new ArrayList<>();
	}
}
