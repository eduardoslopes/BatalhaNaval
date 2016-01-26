package aplicacao.comunicacao;

import com.google.gson.Gson;

import aplicacao.model.Mensagem;

/**
 * @author Wanderson
 *
 */
public class Serializador {

	private Gson gson;

	public Serializador() {
		gson = new Gson();
	}

	public String serializar(Mensagem msg) {

		String mensagem = gson.toJson(msg);
		return mensagem;
	}

}
