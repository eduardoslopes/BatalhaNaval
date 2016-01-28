package com.pds.controle;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import com.google.gson.Gson;
import com.pds.modelo.Mensagem;
import com.pds.modelo.TAG;

public class EnviaMensagemErroNovaPartida implements ObservadorErroNovaPartida {

	@Override
	public void erroNovaPartida(Socket socketJogador) {
		Mensagem msg = new Mensagem(TAG.GAMEEXISTS);
		Serializador s = new Serializador();
		PrintStream saida = null;
		try {
			saida = new PrintStream(socketJogador.getOutputStream());
			saida.println(s.serializar(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
