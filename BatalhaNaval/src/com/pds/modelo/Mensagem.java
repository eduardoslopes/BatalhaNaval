package com.pds.modelo;

public class Mensagem {
	private String tag;
	private String apelidoJogador;
	private String nomePartida;
	
	public Mensagem(String tag, String apelidoJogador) {
		this.tag = tag;
		this.apelidoJogador = apelidoJogador;
	}
	
	public Mensagem(String tag, String apelidoJogador, String nomePartida) {
		this.tag = tag;
		this.apelidoJogador = apelidoJogador;
		this.nomePartida = nomePartida;
	}

	public Mensagem(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void setApelidoJogador(String apelidoJogador) {
		this.apelidoJogador = apelidoJogador;
	}

	public void setNomePartida(String nomePartida) {
		this.nomePartida = nomePartida;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getApelidoJogador() {
		return apelidoJogador;
	}
	
	public String getNomePartida() {
		return nomePartida;
	}
}
