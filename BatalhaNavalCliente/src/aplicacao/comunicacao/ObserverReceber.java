package aplicacao.comunicacao;

import aplicacao.model.Mensagem;

public interface ObserverReceber {

	public void notificaMensagem(Mensagem mensagem);
}
