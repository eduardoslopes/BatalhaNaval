package aplicacao.mensagem;

import aplicacao.Jogada;
import aplicacao.model.Mensagem;
import aplicacao.model.TAG;

public abstract class Interpretador {

	public void messageInterpreter(Mensagem msg) {

		switch (msg.getTag()) {
		case TAG.CONECTGAME:
			conectarJogo();
			break;
		case TAG.CREATEGAME:
			criarJogo();
			break;

		case TAG.DESCONECTGAME:
			desconectarJogo();
			break;

		case TAG.MOVEGAME:
			fazerJogada(msg.getJogada());
			break;

		case TAG.SEEGAMES:
			verJogos(msg.getApelidoJogador(), msg.getNomePartida());
			break;
		}
	}

	public abstract void conectarJogo();

	public abstract void desconectarJogo();

	public abstract void fazerJogada(Jogada jogada);

	public abstract void verJogos(String apelido, String partida);

	public abstract void criarJogo();

}