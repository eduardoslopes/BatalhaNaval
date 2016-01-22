package com.pds.controle;

public abstract class Mensageiro implements Runnable {

	protected abstract void enviarMensagem();
	
	public abstract void setMensagem(String mensagem);
}
