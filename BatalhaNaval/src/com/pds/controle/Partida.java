package com.pds.controle;

import com.pds.modelo.Jogador;
import com.pds.modelo.Mensagem;

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
	
	public void enviarMensagemInicioPartida() {
		
		/*
		 * Esse codigo abaixo devera enviar mensagens de confirmacao de inicio de uma partida
		 * Para que os usuarios possam carregar a proxima tela
		 */
		
		/*
		 * Aqui deve haver um codigo para gerar uma nova mensagem Json
		 */
		
		Mensagem mensagem = new Mensagem("STARTGAME", null);
		Serializador serializa = new Serializador();
		String msg = serializa.serializar(mensagem);
		
		Thread threadEnviaMensagem1 = new Thread(enviaMensagemConvidado);
		enviaMensagemConvidado.setMensagem(msg);
		threadEnviaMensagem1.start();
		
		Thread threadEnviaMensagem2 = new Thread(enviaMensagemCriadorPartida);
		enviaMensagemCriadorPartida.setMensagem(msg);
		threadEnviaMensagem2.start();
	}
	
	/*
	 * Envia mensagem entre os clientes
	 * Tudo que for alterado em um lado da partida deverá ser atualizado no outro
	 */
	public void encaminharMensagem(String mensagem, String apelidoRemetente) {
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
}
