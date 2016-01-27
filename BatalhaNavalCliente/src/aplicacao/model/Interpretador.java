package aplicacao.model;

import aplicacao.view.ObservadorJogo;
import aplicacao.view.ObservadorPartida;

public abstract class Interpretador {
	
	public ObservadorPartida observerPartida;
	public ObservadorJogo observerJogo;
	public ObservadorTabuleiro observerTabuleiro;

	public void setObserverPartida(ObservadorPartida observerPartida) {
		this.observerPartida = observerPartida;
	}

	
	public void setObserverJogo(ObservadorJogo observerJogo) {
		this.observerJogo = observerJogo;
	}

	
	public void setObserverTabuleiro(ObservadorTabuleiro observerTabuleiro) {
		this.observerTabuleiro = observerTabuleiro;
	}

	public void messageInterpreter(Mensagem msg) {

		switch (msg.getTag()) {
		case TAG.CONECTGAME:
			conectarJogo();
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
		case TAG.STARTGAMECONVIDADO:
			iniciaJogoConvidado();
			break;
		case TAG.STARTGAMECRIADOR:
			iniciaJogoCriador();
			break;
		case TAG.RESULT:
			exibeResultado(msg.getImgPath());
			break;
		}
	}

	public abstract void conectarJogo();

	public abstract void iniciaJogoConvidado();

	public abstract void iniciaJogoCriador();

	public abstract void desconectarJogo();

	public abstract void fazerJogada(Jogada jogada);

	public abstract void verJogos(String apelido, String partida);
	
	public abstract void exibeResultado(String imgPath);

}