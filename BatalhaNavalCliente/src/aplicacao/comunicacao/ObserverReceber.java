package aplicacao.comunicacao;

import aplicacao.Mensagem;

/**
 * @author Wanderson
 *
 */
public interface ObserverReceber {

	public Mensagem notificaMensagem(Mensagem mensagem);
}
