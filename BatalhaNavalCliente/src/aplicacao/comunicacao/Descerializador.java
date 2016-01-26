package aplicacao.comunicacao;

import com.google.gson.Gson;

import aplicacao.model.Mensagem;

public class Descerializador {

	private Gson gson;

	public Mensagem descerializar(String msg) {

		gson = new Gson();
		return gson.fromJson(msg, Mensagem.class);
	}
}
