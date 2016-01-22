package com.pds.controle;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class RecebeDoCliente extends Receber {
	
	private Socket jogador;
	private ControladorInterpretação interpretacao;
	
	public RecebeDoCliente(Socket jogador, ControladorInterpretação interpretacao) {
		this.jogador = jogador;
		this.interpretacao = interpretacao;
	}
	
	

	@Override
	void recebe() {
		try {
			Scanner s = new Scanner(jogador.getInputStream());
			String msg = s.nextLine();
			interpretacao.mensagemParaInterpretar(msg, jogador);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
