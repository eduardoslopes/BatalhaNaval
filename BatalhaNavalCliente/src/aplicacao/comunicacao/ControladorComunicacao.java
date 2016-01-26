package aplicacao.comunicacao;

import java.io.IOException;
import java.net.Socket;

import aplicacao.ControladorJogo;
import aplicacao.mensagem.Interpretador;
import aplicacao.mensagem.InterpretadorMensagem;
import aplicacao.model.Mensagem;
import aplicacao.view.ObservadorPartida;

public class ControladorComunicacao implements ObserverReceber {

	private Socket cliente;
	private Interpretador interpretador;
	private ObservadorPartida observer;

	public ControladorComunicacao(ObservadorPartida observerLista) {

		criarSocket();
		this.observer = observer;
		interpretador = new InterpretadorMensagem(new ControladorJogo(), observerLista);

		Receber receber = new Receber(cliente, this);
		Thread threadReceber = new Thread(receber);
		threadReceber.start();
	}

	/**
	 * criacao do socket
	 */
	private void criarSocket() {

		try {
			this.cliente = new Socket("192.168.0.111", 8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param mensagem
	 *            mensagem ja montada
	 */
	public void enviarMensagem(Mensagem mensagem) {

		Enviar enviar = new Enviar(cliente, mensagem);
		Thread enviarThread = new Thread(enviar);
		enviarThread.start();
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

}
