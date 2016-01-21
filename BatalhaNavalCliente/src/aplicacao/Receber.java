package aplicacao;

import java.net.Socket;

public class Receber implements Runnable {

	ObserverReceber observador;
	Socket cliente;
	
	public Receber(Socket cliente, ObserverReceber observer) {
		
		observador = observer;
		this.cliente = cliente;
	}
	
	@Override
	public void run() {
		
		
		
	}

}
