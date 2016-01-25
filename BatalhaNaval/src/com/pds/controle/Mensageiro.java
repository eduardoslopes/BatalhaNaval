package com.pds.controle;

import java.net.Socket;
public abstract class Mensageiro implements Runnable {
	
	public abstract void enviarMensagem();
	
	public abstract void setMensagem(String mensagem);
}
