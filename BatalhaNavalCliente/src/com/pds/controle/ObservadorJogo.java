package com.pds.controle;

import com.pds.modelo.Jogada;

public interface ObservadorJogo {

	public void novaJogada(Jogada jogada);
	public void setJogoComoConvidado();
	public void setJogoComoCriador();
	public void exibeResultadoJogada(String imgPath);
	public void desconectar();
	public void embarcacaoAfundada(int posX, int posY, String imgPath);
	public void ganhou();
	
}