package com.pds.controle;

import com.pds.modelo.Jogador;

public class Partida {

	private String nomePartida;
	private Jogador criadorPartida;
	private Jogador convidado;
	private Mensageiro enviaMensagemCriadorPartida;
	private Mensageiro enviaMensagemConvidado;
	
	public Partida(String nomePartida, Jogador criadorPartida) {
		this.nomePartida = nomePartida;
		this.criadorPartida = criadorPartida;
		this.enviaMensagemCriadorPartida = new MensageiroPartida(criadorPartida.getFluxoSaida());
	}
	
	public void iniciar() {
		enviaMensagemConvidado = new MensageiroPartida(convidado.getFluxoSaida());
	}
	
	public void enviarMensagem(String mensagem, String apelidoRemetente) {
		if (criadorPartida.getApelido().equals(apelidoRemetente)) {
			enviaMensagemConvidado.setMensagem(mensagem);
			Thread novaThreadEnviaMensagem = new Thread(enviaMensagemConvidado);
			novaThreadEnviaMensagem.start();
		} else if (convidado.getApelido().equals(apelidoRemetente)) {
			enviaMensagemCriadorPartida.setMensagem(mensagem);
			Thread novaThreadEnviaMensagem = new Thread(enviaMensagemCriadorPartida);
			novaThreadEnviaMensagem.start();
		}
	}
	
	public void adicionaConvidado(Jogador convidado) {
		this.convidado = convidado;
	}
	
	public String getNomePartida() {
		return nomePartida;
	}
}
