package aplicacao.comunicacao;

import java.net.Socket;

public class Enviar implements Runnable {

	Socket cliente;
	
	public Enviar(Socket cliente) {
		
		this.cliente = cliente;
	}
	
	@Override
	public void run() {
		
		
	}

}
