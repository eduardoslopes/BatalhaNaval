package aplicacao.comunicacao;

import java.io.IOException;
import java.net.Socket;

import aplicacao.model.Interpretador;
import aplicacao.model.Jogador;
import aplicacao.model.Mensagem;
import aplicacao.model.Partida;

public class ControladorComunicacao implements ObserverReceber {

	private Socket cliente;
	private Partida partida;
	private Jogador jogador;
	private Interpretador interpretador;

	public ControladorComunicacao() {

		criarSocket();

		Receber receber = new Receber(cliente, this);
		Thread threadReceber = new Thread(receber);
		threadReceber.start();
	}

	private void criarSocket() {

		try {
			this.cliente = new Socket("192.168.0.111", 8888);
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

		Enviar enviar = new Enviar(cliente, mensagem);
		enviar.run();
//		Thread enviarThread = new Thread(enviar);
//		enviarThread.start();
	}

	@Override
	public void notificaMensagem(Mensagem mensagem) {

		interpretador.messageInterpreter(mensagem);
	}

	public void fechar() {

		try {
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
