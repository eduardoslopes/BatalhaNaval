package aplicacao.comunicacao;

import com.google.gson.Gson;

import aplicacao.Mensagem;

/**
 * @author Wanderson
 *
 */
public class Descerializador {

	private Gson gson;

	public Mensagem descerializar(String msg) {

		gson = new Gson();
		return gson.fromJson(msg, Mensagem.class);
	}
}
