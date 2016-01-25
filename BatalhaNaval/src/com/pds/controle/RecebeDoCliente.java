package com.pds.controle;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class RecebeDoCliente extends Receber {
	
	private Socket sockJogador;
	private ControladorInterpretacao interpretacao;
	
	public RecebeDoCliente(Socket sockJogador, ControladorInterpretacao interpretacao) {
		this.sockJogador = sockJogador;
		this.interpretacao = interpretacao;
	}
	
	@Override
	void recebe() {
		try {
			Scanner s = new Scanner(sockJogador.getInputStream());
			String msg = null;
			while (s.hasNextLine()) {
				msg = s.nextLine();
				interpretacao.mensagemParaInterpretar(msg, sockJogador);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
