package com.pds.comunicacao;

import java.io.PrintStream;

import com.pds.modelo.Mensagem;

public class Enviar {

	private PrintStream cliente;
	private Serializador serializador;

	public Enviar(PrintStream cliente) {

		serializador = new Serializador();
		this.cliente = cliente;
	}

	public void enviar(Mensagem msg) {
		String mensagem = serializador.serializar(msg);
		cliente.println(mensagem);
		System.out.println("enviando: " + mensagem);
	}

	public void fechar() {
		cliente.close();
	}

}