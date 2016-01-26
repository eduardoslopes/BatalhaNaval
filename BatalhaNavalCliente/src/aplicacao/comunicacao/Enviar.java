package aplicacao.comunicacao;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import aplicacao.model.Mensagem;

public class Enviar implements Runnable {

	private Socket cliente;
	private Mensagem msg;
	private Serializador serializador;

	public Enviar(Socket cliente, Mensagem msg) {

		serializador = new Serializador();
		this.cliente = cliente;
		this.msg = msg;
	}

	@Override
	public void run() {

		String mensagem = serializador.serializar(msg);

		PrintStream saidaServidor;
		try {

			Thread.sleep(10);
			
			saidaServidor = new PrintStream(cliente.getOutputStream());
			saidaServidor.println(mensagem);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
