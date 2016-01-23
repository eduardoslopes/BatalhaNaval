package com.pds.controle;

import java.net.Socket;

public abstract class Mensageiro extends Thread {
	
	public abstract void enviaMensagem(Socket socket, String msg);
}
