package com.pds.controle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ControladorConexao {
	private int porta; 
	private ServerSocket server;
	
	public ControladorConexao(int porta){
		this.porta = porta;
	}
	
	void executa(){
		try {
			ControladorInterpretação cInterpretacao = new ControladorInterpretação();
			server = new ServerSocket(porta);
			Socket jogador = server.accept();
			RecebeDoCliente recebedor = new RecebeDoCliente(jogador, cInterpretacao);
			recebedor.start();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
