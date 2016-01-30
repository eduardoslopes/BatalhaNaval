package com.pds.comunicacao;

import com.pds.modelo.Mensagem;

public interface ObserverReceber {

	public void notificaMensagem(Mensagem mensagem);
}
