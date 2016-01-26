package aplicacao.model;
import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
	private int tamanho;
	private List<List<Celula>> celulas;
	private List<Embarcacao> embarcacoes;

	public Tabuleiro(int tamanho) {
		super();
		this.tamanho = tamanho;
		celulas = new ArrayList<List<Celula>>();
		for (int i = 0; i < tamanho; ++i) {
			celulas.add(new ArrayList<Celula>());
			for (int j = 0; j < tamanho; ++j) {
				celulas.get(i).add(new Celula());
			}
		}
		embarcacoes = new ArrayList<Embarcacao>();
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public List<List<Celula>> getCelulas() {
		return celulas;
	}

	public void setCelulas(List<List<Celula>> celulas) {
		this.celulas = celulas;
	}

	public List<Embarcacao> getEmbarcacoes() {
		return embarcacoes;
	}

	public void setEmbarcacoes(List<Embarcacao> embarcacoes) {
		this.embarcacoes = embarcacoes;
	}
	
	public void addEmbarcacao (Embarcacao e) {
		embarcacoes.add(e);
	}

	public boolean isAtingido (int posX, int posY) {
		return celulas.get(posX).get(posY).isAtingido();
	}
	
	public boolean todasEmbarcacoesForamDestruidas () {
		for (Embarcacao e : embarcacoes) {
			if (e.isDestruida() == false) {
				return false;
			}
		}
		return true;
	}

	public void imprimir() {
		for (int i = 0; i < tamanho; ++i) {
			for (int j = 0; j < tamanho; ++j) {
				System.out.print(celulas.get(i).get(j).getImgPath() + " ");
			}
			System.out.println();
		}
		
	}
}
