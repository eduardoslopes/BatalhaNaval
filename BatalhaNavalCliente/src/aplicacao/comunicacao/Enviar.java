package aplicacao.comunicacao;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import com.google.gson.Gson;

import aplicacao.Mensagem;

/**
 * @author Wanderson
 *
 */
public class Enviar implements Runnable {

	Socket cliente;
	Gson gson;
	Mensagem msg;

	public Enviar(Socket cliente, Mensagem msg) {

		gson = new Gson();
		this.cliente = cliente;
		this.msg = msg;
	}

	@Override
	public void run() {

		String mensagem = gson.toJson(msg);

		PrintStream saidaServidor;
		try {
			saidaServidor = new PrintStream(cliente.getOutputStream());
			saidaServidor.println(mensagem);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
