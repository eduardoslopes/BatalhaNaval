package com.pds.modelo;

public class Mensagem {
	private String tag;
	private String nome;
	private String nomePartida;
	
	public Mensagem(String tag, String nome) {
		this.tag = tag;
		this.nome = nome;
	}
	
	public Mensagem(String tag, String nome, String nomePartida) {
		this.tag = tag;
		this.nome = nome;
		this.nomePartida = nomePartida;
	}

	public Mensagem(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setNomePartida(String nomePartida) {
		this.nomePartida = nomePartida;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getNome() {
		return nome;
	}
	
	public String getNomePartida() {
		return nomePartida;
	}
}
