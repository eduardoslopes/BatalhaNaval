package aplicacao.model;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

	public static final int QTD_TOTAL_EMBARCACOES = 4;
	public int tamanho;
	private List<CelulaTabuleiro> celulas;
	private List<Embarcacao> embarcacoes;

	public Tabuleiro (int tamanho) {
		celulas = new ArrayList<CelulaTabuleiro>();
		embarcacoes = new ArrayList<Embarcacao> ();
		for (int i = 0; i < tamanho * tamanho; ++i) {
			celulas.add(new CelulaTabuleiro());
		}
	}
	
	public String getConteudoCelula (int posX, int posY) {
		return celulas.get(tamanho * posX + posY).conteudo;
	}

	public void setConteudoCelula (int posX, int posY, String conteudo) {
		celulas.get(tamanho * posX + posY).conteudo = conteudo;
	}
	
	public boolean getCelulaAtingida (int posX, int posY) {
		return celulas.get(tamanho * posX + posY).foiAtingida;
	}
	
	public void setCelulaAtingida (int posX, int posY) {
		CelulaTabuleiro celula = celulas.get(tamanho * posX + posY);
		if (celula.foiAtingida == false) {
			celula.foiAtingida = true;
		}
	}
	
	public int getTamanhoCelulas () {
		return CelulaTabuleiro.TAMANHO_DEFAULT;
	}
	
	public boolean celulaVazia (int posX, int posY) {
		return celulas.get(tamanho * posX + posY).conteudo.equals("");
	}
	

	public boolean inserirEmbarcacao (Embarcacao embarcacao) {
		if (embarcacao.horizontal) {
			for (int i = embarcacao.posX; i < embarcacao.posX + embarcacao.tamanho; ++i) {
				if (celulaVazia(i, embarcacao.posY)) {
					setConteudoCelula(i, embarcacao.posY, embarcacao.imgPath);
				} else {
					return false;
				}
			}
		} else {
			for (int i = embarcacao.posY; i < embarcacao.posY + embarcacao.tamanho; ++i) {
				if (celulaVazia(embarcacao.posX, i)) {
					setConteudoCelula(embarcacao.posX, i, embarcacao.imgPath);
				} else {
					return false;
				}
			}
		}
		return true;
	}

}
