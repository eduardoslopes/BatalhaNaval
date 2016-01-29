package com.pds.controle;

import com.pds.modelo.Jogador;
import com.pds.modelo.Mensagem;
import com.pds.modelo.TAG;

public class Partida {

	private String nomePartida;
	private Jogador criadorPartida;
	private Jogador convidado;
	private Mensageiro enviaMensagemCriadorPartida;
	private Mensageiro enviaMensagemConvidado;
	private Serializador serializa;
	
	public Partida(String nomePartida, Jogador criadorPartida) {
		this.nomePartida = nomePartida;
		this.criadorPartida = criadorPartida;
		this.enviaMensagemCriadorPartida = new MensageiroPartida(criadorPartida.getFluxoSaida());
		serializa = new Serializador();
	}
	
	public void enviarMensagemMontarTabuleiro() {
		Mensagem mensagem = new Mensagem(TAG.CONNECTGAME);
		String msg = serializa.serializar(mensagem);
		enviaMensagemConvidado.enviarMensagem(msg);		
		enviaMensagemCriadorPartida.enviarMensagem(msg);
	}
	
	public void encaminharMensagem(String mensagem, String apelidoRemetente) {
		try {
			if (criadorPartida.getApelido().equals(apelidoRemetente)) {
				enviaMensagemConvidado.enviarMensagem(mensagem);
			} else if (convidado.getApelido().equals(apelidoRemetente)) {
				enviaMensagemCriadorPartida.enviarMensagem(mensagem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adicionaConvidado(Jogador convidado) {
		this.convidado = convidado;
		enviaMensagemConvidado = new MensageiroPartida(convidado.getFluxoSaida());
	}
	
	public String getNomePartida() {
		return nomePartida;
	}
	
	public Jogador getCriadorPartida() {
		return criadorPartida;
	}

	public void setJogadorPronto(String apelido) {
		if (criadorPartida.getApelido().equals(apelido))
			criadorPartida.setPronto(true);
		else
			convidado.setPronto(true);
		
		if (criadorPartida.getPronto() && convidado.getPronto())
			iniciarPartida();
	}
	
	private void iniciarPartida() {
		Mensagem mensagem = new Mensagem(TAG.STARTGAMECONVIDADO);
		String msg = serializa.serializar(mensagem);
		
		enviaMensagemConvidado.enviarMensagem(msg);
		
		mensagem.setTag(TAG.STARTGAMECRIADOR);
		msg = serializa.serializar(mensagem);
		enviaMensagemCriadorPartida.enviarMensagem(msg);
	}

	public void finalPartida(String apelido) {
		Mensagem mensagem = new Mensagem(TAG.WONGAME);
		String msg = serializa.serializar(mensagem);
		
		if (criadorPartida.getApelido().equals(apelido)) {
			enviaMensagemConvidado.enviarMensagem(msg);
		} else {
			enviaMensagemCriadorPartida.enviarMensagem(msg);
		}
	}

	public void cancelarPartida(String apelido) {
		Mensagem mensagem = new Mensagem(TAG.DISCONNECTGAME);
		String msg = serializa.serializar(mensagem);
		
		if (criadorPartida.getApelido().equals(apelido)) {
			enviaMensagemConvidado.enviarMensagem(msg);
		} else {
			enviaMensagemCriadorPartida.enviarMensagem(msg);
			
		}
	}
}
