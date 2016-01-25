package com.pds.controle;

public abstract class Mensageiro implements Runnable {
	
	public abstract void enviarMensagem();
	
	public abstract void setMensagem(String mensagem);
}
