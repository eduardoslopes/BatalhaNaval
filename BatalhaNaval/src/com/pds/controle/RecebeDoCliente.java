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
	public void recebe() {
		try {
			Scanner s = new Scanner(sockJogador.getInputStream());
			String msg = null;
			while (s.hasNextLine()) {
				msg = s.nextLine();
				System.out.println("Recebendo: " + msg);
				interpretacao.mensagemParaInterpretar(msg, sockJogador);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		recebe();
	}
}
