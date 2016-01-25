package com.pds.controle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ControladorConexao {
	private int porta;
	private ServerSocket server;
	private ControladorInterpretacao cInterpretacao;

	public ControladorConexao(int porta, ControladorInterpretacao cInterpretacao) {
		this.porta = porta;
		this.cInterpretacao = cInterpretacao;
	}

	public void executa() {
		try {
			while (true) {
				server = new ServerSocket(porta);
				Socket jogador = server.accept();
				Receber recebedor = new RecebeDoCliente(jogador, cInterpretacao);
				recebedor.start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
