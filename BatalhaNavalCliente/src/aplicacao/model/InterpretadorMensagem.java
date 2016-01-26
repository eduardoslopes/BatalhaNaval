package aplicacao.model;

import aplicacao.view.ObservadorPartida;

public class InterpretadorMensagem extends Interpretador {

	private ObservadorPartida observer;


	public InterpretadorMensagem(ObservadorPartida observerLista) {
		observer = observerLista;
	}

	@Override
	public void conectarJogo() {

	}

	@Override
	public void desconectarJogo() {

	}

	@Override
	public void fazerJogada(Jogada jogada) {

	}

	@Override
	public void criarJogo() {

	}

	@Override
	public void verJogos(String apelido, String partida) {

		Partida p = new Partida(partida, new Jogador(apelido));
		observer.atualizarPartida(p);
	}

}
