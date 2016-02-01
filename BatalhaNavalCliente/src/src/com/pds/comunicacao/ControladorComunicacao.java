package com.pds.comunicacao;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import com.pds.controle.Interpretador;
import com.pds.modelo.Jogador;
import com.pds.modelo.Mensagem;
import com.pds.modelo.Partida;

public class ControladorComunicacao implements ObserverReceber {

	private Socket cliente;
	private Partida partida;
	private Jogador jogador;
	private Interpretador interpretador;
	private Enviar enviar;
	private Thread threadReceber;

	public ControladorComunicacao() {

		criarSocket();
		criarMensageiro();

		Receber receber = new Receber(cliente, this);
		threadReceber = new Thread(receber);
		threadReceber.start();
	}

	private void criarMensageiro() {
		try {
			OutputStream os = cliente.getOutputStream();
			PrintStream ps = new PrintStream(os);
			enviar = new Enviar(ps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void criarSocket() {

		try {
			this.cliente = new Socket("localhost", 9999);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setInterpretador(Interpretador interpretador) {

		this.interpretador = interpretador;
	}
	
	
	public Interpretador getInterpretador() {

		return interpretador;
	}
	
	public void enviarMensagem(Mensagem mensagem) {

		enviar.enviar(mensagem);
	}

	@Override
	public void notificaMensagem(Mensagem mensagem) {

		interpretador.messageInterpreter(mensagem);
	}

	public void fechar() {
		try {
			threadReceber.interrupt();
			enviar.fechar();
			cliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Partida getPartida() {

		return partida;
	}

	public void setPartida(Partida partida) {

		this.partida = partida;
	}

	public Jogador getJogador() {

		return jogador;
	}

	public void setJogador(Jogador jogador) {

		this.jogador = jogador;
	}

}
