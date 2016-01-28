package com.pds.controle;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import com.google.gson.Gson;
import com.pds.modelo.Mensagem;
import com.pds.modelo.TAG;

public class EnviaMensagemErro implements ObservadorErroNovaPartida, ObservadorErroConectarPartida {

	Serializador serializador;
	
	public EnviaMensagemErro() {
		serializador = new Serializador();
	}
	
	@Override
	public void erroNovaPartida(Socket socketJogador) {
		Mensagem msg = new Mensagem(TAG.GAMEEXISTS);
		
		PrintStream saida = null;
		try {
			saida = new PrintStream(socketJogador.getOutputStream());
			saida.println(serializador.serializar(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void erroConectarPartida(Socket socketJogador) {
		Mensagem msg = new Mensagem(TAG.NICKEXISTS);
		
		PrintStream saida = null;
		try {
			saida = new PrintStream(socketJogador.getOutputStream());
			saida.println(serializador.serializar(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
