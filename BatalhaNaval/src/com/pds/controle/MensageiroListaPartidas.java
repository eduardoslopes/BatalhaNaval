package com.pds.controle;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class MensageiroListaPartidas extends Mensageiro {

	@Override
	public void enviaMensagem(Socket socket, String msg) {
		try {
			PrintStream ps = new PrintStream(socket.getOutputStream());
			ps.println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void enviaListaPartidas(Socket socket, List<String> partidas) {
		for(String partida: partidas){
			enviaMensagem(socket, partida);
		}
	}

}
