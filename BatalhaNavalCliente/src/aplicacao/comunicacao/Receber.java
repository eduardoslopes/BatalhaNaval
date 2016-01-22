package aplicacao.comunicacao;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import com.google.gson.Gson;

import aplicacao.Mensagem;

/**
 * @author Wanderson
 *
 */
public class Receber implements Runnable {

	ObserverReceber observador;
	Socket cliente;
	Gson gson;

	public Receber(Socket cliente, ObserverReceber observer) {

		gson = new Gson();
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
				Mensagem mensagem = gson.fromJson(msg, Mensagem.class);

				observador.notificaMensagem(mensagem);
			}
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
