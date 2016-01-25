package aplicacao.comunicacao;

import aplicacao.Mensagem;

/**
 * @author Wanderson
 *
 */
public interface ObserverReceber {

	public void notificaMensagem(Mensagem mensagem);
}
