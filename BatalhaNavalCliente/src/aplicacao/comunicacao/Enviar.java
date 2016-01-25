package aplicacao.comunicacao;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import aplicacao.Mensagem;

/**
 * @author Wanderson
 *
 */
public class Enviar implements Runnable {

	private Socket cliente;
	private Mensagem msg;
	private Serializador serializador;

	public Enviar(Socket cliente, Mensagem msg) {

		this.cliente = cliente;
		this.msg = msg;
	}

	@Override
	public void run() {

		serializador = new Serializador();
		String mensagem = serializador.serializar(msg);
		
		PrintStream saidaServidor;
		try {
			saidaServidor = new PrintStream(cliente.getOutputStream());
			saidaServidor.println(mensagem);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
