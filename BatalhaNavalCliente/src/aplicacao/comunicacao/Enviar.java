package aplicacao.comunicacao;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import aplicacao.model.Mensagem;

public class Enviar {

	private Socket cliente;
	private Mensagem msg;
	private Serializador serializador;

	public Enviar(Socket cliente, Mensagem msg) {

		serializador = new Serializador();
		this.cliente = cliente;
		this.msg = msg;
	}

	
	public void run() {

		String mensagem = serializador.serializar(msg);

		PrintStream saidaServidor;
		try {
			
			saidaServidor = new PrintStream(cliente.getOutputStream());
			
			System.out.println("enviando: " + mensagem);
			
			saidaServidor.println(mensagem);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
