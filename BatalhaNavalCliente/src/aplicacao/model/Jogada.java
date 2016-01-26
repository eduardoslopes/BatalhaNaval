package aplicacao.model;

public class Jogada {

	private int posX;
	private int posY;
	private Jogador jogador;
	private Celula celula;

	public int getPosX() {

		return posX;
	}

	public void setPosX(int posX) {

		this.posX = posX;
	}

	public int getPosY() {

		return posY;
	}

	public void setPosY(int posY) {

		this.posY = posY;
	}

	public Jogador getJogador() {

		return jogador;
	}

	public void setJogador(Jogador jogador) {

		this.jogador = jogador;
	}

	public Celula getCelula() {

		return celula;
	}

	public void setCelula(Celula celula) {

		this.celula = celula;
	}


}
