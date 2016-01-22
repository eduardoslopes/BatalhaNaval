package aplicacao.comunicacao;

import java.io.IOException;
import java.net.Socket;

import aplicacao.Mensagem;
import aplicacao.mensagem.InterpretadorMensagem;

/**
 * @author Wanderson
 *
 */
public class ControladorComunicacao implements ObserverReceber {

	private Socket cliente;

	public ControladorComunicacao() {

		criarSocket();

		Receber receber = new Receber(cliente, this);
		Thread receberThread = new Thread(receber);
		receberThread.start();
	}

	/**
	 * criacao do socket
	 */
	private void criarSocket() {

		try {
			this.cliente = new Socket("", 6666);
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
	public Mensagem notificaMensagem(Mensagem mensagem) {

		InterpretadorMensagem interpretador = new InterpretadorMensagem();
		interpretador.interpretar(mensagem);
		return mensagem;
	}

}
