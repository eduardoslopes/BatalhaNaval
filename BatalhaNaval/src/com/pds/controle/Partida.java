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
		
		Thread threadEnviaMensagem1 = new Thread(enviaMensagemConvidado);
		enviaMensagemConvidado.setMensagem(msg);
		threadEnviaMensagem1.start();
		
		Thread threadEnviaMensagem2 = new Thread(enviaMensagemCriadorPartida);
		enviaMensagemCriadorPartida.setMensagem(msg);
		threadEnviaMensagem2.start();
	}
	
	public void encaminharMensagem(String mensagem, String apelidoRemetente) {
		System.out.println("Encaminhando: " + mensagem);
		try {
			Thread threadEnviaMensagem = null;
			if (criadorPartida.getApelido().equals(apelidoRemetente)) {
				enviaMensagemConvidado.setMensagem(mensagem);
				threadEnviaMensagem = new Thread(enviaMensagemConvidado);
			} else if (convidado.getApelido().equals(apelidoRemetente)) {
				enviaMensagemCriadorPartida.setMensagem(mensagem);
				threadEnviaMensagem = new Thread(enviaMensagemCriadorPartida);
			}
			threadEnviaMensagem.start();
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
		System.out.println("Enviando: " + msg);
		
		Thread threadEnviaMensagem1 = new Thread(enviaMensagemConvidado);
		enviaMensagemConvidado.setMensagem(msg);
		threadEnviaMensagem1.start();
		
		mensagem.setTag(TAG.STARTGAMECRIADOR);
		msg = serializa.serializar(mensagem);
		System.out.println("Enviando: " + msg);
		Thread threadEnviaMensagem2 = new Thread(enviaMensagemCriadorPartida);
		enviaMensagemCriadorPartida.setMensagem(msg);
		threadEnviaMensagem2.start();
	}

	public void finalPartida(String apelido) {
		Mensagem mensagem = new Mensagem(TAG.WONGAME);
		String msg = serializa.serializar(mensagem);
	
		System.out.println("Enviando: " + msg);
		
		Thread enviarFinalPartida = null;
		if (criadorPartida.getApelido().equals(apelido)) {
			enviaMensagemConvidado.setMensagem(msg);
			enviarFinalPartida = new Thread(enviaMensagemConvidado);
		} else {
			enviaMensagemCriadorPartida.setMensagem(msg);
			enviarFinalPartida = new Thread(enviaMensagemCriadorPartida);
		}
		enviarFinalPartida.start();
	}

	public void cancelarPartida(String apelido) {
		Mensagem mensagem = new Mensagem(TAG.DISCONNECTGAME);
		String msg = serializa.serializar(mensagem);
	
		System.out.println("Enviando: " + msg);
		
		Thread enviarCancelarPartida = null;
		if (criadorPartida.getApelido().equals(apelido)) {
			enviaMensagemConvidado.setMensagem(msg);
			enviarCancelarPartida = new Thread(enviaMensagemConvidado);
		} else {
			enviaMensagemCriadorPartida.setMensagem(msg);
			enviarCancelarPartida = new Thread(enviaMensagemCriadorPartida);
		}
		enviarCancelarPartida.start();
	}
}
