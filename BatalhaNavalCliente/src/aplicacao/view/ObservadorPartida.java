package aplicacao.view;

import aplicacao.model.Partida;

public interface ObservadorPartida {
	
	public void atualizarPartida(Partida partida);

	public void conectarJogo();
}
