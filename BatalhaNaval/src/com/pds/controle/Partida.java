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
		try {
			Thread novaThreadEnviaMensagem = null;
			if (criadorPartida.getApelido().equals(apelidoRemetente)) {
				enviaMensagemConvidado.setMensagem(mensagem);
				novaThreadEnviaMensagem = new Thread(enviaMensagemConvidado);
			} else if (convidado.getApelido().equals(apelidoRemetente)) {
				enviaMensagemCriadorPartida.setMensagem(mensagem);
				novaThreadEnviaMensagem = new Thread(enviaMensagemCriadorPartida);
			}
			novaThreadEnviaMensagem.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adicionaConvidado(Jogador convidado) {
		this.convidado = convidado;
	}
	
	public String getNomePartida() {
		return nomePartida;
	}
}
