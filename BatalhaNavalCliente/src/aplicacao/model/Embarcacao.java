
package aplicacao.model;
import java.util.ArrayList;
import java.util.List;

public abstract class Embarcacao {
	private int tamanho;
	private boolean horizontal;
	private int posX;
	private int posY;
	private List<Celula> celulas;
	
	public Embarcacao(int tamanho, boolean horizontal, int posX, int posY, Tabuleiro tabuleiro) {
		super();
		this.tamanho = tamanho;
		this.horizontal = horizontal;
		this.posX = posX;
		this.posY = posY;
		setCelulas(new ArrayList<Celula>());
		try {
			if (horizontal) {
				for (int i = 0; i < tamanho; ++i) {
					getCelulas().add(tabuleiro.getCelulas().get(posX + i).get(posY));
				}
			} else {
				for (int i = 0; i < tamanho; ++i) {
					getCelulas().add(tabuleiro.getCelulas().get(posX).get(posY + i));
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Erro: ");
		}
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

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

	public List<Celula> getCelulas() {
		return celulas;
	}

	public void setCelulas(List<Celula> celulas) {
		this.celulas = celulas;
	}
	
	public boolean isDestruida () {
		for (Celula celula : celulas) {
			if (celula.isAtingido() == false) {
				return false;
			}
		}
		return true;
	}
	
	public abstract void desenhar ();

	
}
