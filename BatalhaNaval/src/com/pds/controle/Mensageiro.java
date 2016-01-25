package com.pds.controle;

import java.net.Socket;
public abstract class Mensageiro implements Runnable {

public abstract class Mensageiro extends Thread {
	
	public abstract void enviarMensagem(Socket socket, String msg);
	protected abstract void enviarMensagem();
	
	public abstract void setMensagem(String mensagem);
}
