package aplicacao;

import java.io.IOException;
import java.net.Socket;

public class ControladorComunicacao implements ObserverReceber {
	
	private Socket cliente;
	
	
	public ControladorComunicacao() {
		
		
		criarSocket();
		
		Enviar enviar = new Enviar(cliente);
		Thread enviarThread = new Thread(enviar);
		enviarThread.start();
		
		Receber receber = new Receber(cliente, this);
		Thread receberThread = new Thread(receber);
		receberThread.start();
		
	}
	
	//Criacao do socket 
	public void criarSocket() {
		try {
			this.cliente = new Socket("", 6666);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notificaMensagem(String mensagem) {
		
	}
	
	
}
