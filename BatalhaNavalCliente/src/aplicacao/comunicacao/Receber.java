package aplicacao.comunicacao;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import aplicacao.model.Mensagem;

public class Receber implements Runnable {

	private ObserverReceber observador;
	private Descerializador descerializador;
	private Socket cliente;

	public Receber(Socket cliente, ObserverReceber observer) {

		descerializador = new Descerializador();
		observador = observer;
		this.cliente = cliente;
	}

	@Override
	public void run() {

		Scanner entrada;

		try {
			entrada = new Scanner(cliente.getInputStream());

			while (entrada.hasNextLine()) {
				String msg = entrada.nextLine();
				Mensagem mensagem = descerializador.descerializar(msg);
				observador.notificaMensagem(mensagem);
			}
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
