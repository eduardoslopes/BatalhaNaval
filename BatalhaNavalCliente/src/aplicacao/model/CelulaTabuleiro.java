package aplicacao.model;

public class CelulaTabuleiro {

	public static final int TAMANHO_DEFAULT = 50;
	public String conteudo;
	public boolean foiAtingida;
	
	public CelulaTabuleiro () {
		this.conteudo = "";
		foiAtingida = false;
	}
	
	public CelulaTabuleiro (String conteudo) {
		this.conteudo = conteudo;
		foiAtingida = false;
	}
}
