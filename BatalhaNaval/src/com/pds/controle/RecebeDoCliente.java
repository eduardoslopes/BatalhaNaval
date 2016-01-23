package com.pds.controle;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class RecebeDoCliente extends Receber {
	
	private Socket jogador;
	private ControladorInterpretacao interpretacao;
	
	public RecebeDoCliente(Socket jogador, ControladorInterpretacao interpretacao) {
		this.jogador = jogador;
		this.interpretacao = interpretacao;
	}
	
	@Override
	void recebe() {
		try {
			Scanner s = new Scanner(jogador.getInputStream());
			String msg = null;
			while (s.hasNextLine()) {
				msg = s.nextLine();
				interpretacao.mensagemParaInterpretar(msg, jogador);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
