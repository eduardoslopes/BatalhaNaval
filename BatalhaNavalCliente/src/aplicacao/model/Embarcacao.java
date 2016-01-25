package aplicacao.model;

import java.util.ArrayList;
import java.util.List;

public class Embarcacao {

	private enum ESTADOS_CELULAS {
		NAO_ATINGIDO,
		ATINGIDO,
	}
	
	public int tamanho;
	public boolean horizontal;
	public String imgPath;
	public int posX;
	public int posY;
	List<ESTADOS_CELULAS> estadosCelulas;

	public Embarcacao (int tamanho, String imgPath, int posX, int posY, boolean horizontal) {
		estadosCelulas = new ArrayList<ESTADOS_CELULAS>();
		this.tamanho = tamanho;
		this.imgPath = imgPath;
		this.posX = posX;
		this.posY = posY;
		estadosCelulas = new ArrayList<ESTADOS_CELULAS>();
		for (int i = 0; i < tamanho; ++i) {
			estadosCelulas.set(i, ESTADOS_CELULAS.NAO_ATINGIDO);
		}
		this.horizontal = horizontal;
	}

	public boolean estaAfundada () {
		for (ESTADOS_CELULAS estado : estadosCelulas) {
			if (estado == ESTADOS_CELULAS.NAO_ATINGIDO) {
				return false;
			}
		}
		return true;
	}
	
	public boolean celulaAtingida (int indice) {
		return estadosCelulas.get(indice) == ESTADOS_CELULAS.ATINGIDO;
	}

}
