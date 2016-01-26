package aplicacao.model;

public class Patrulha extends Embarcacao {

	public Patrulha(int tamanho, boolean horizontal, int posX, int posY, Tabuleiro tabuleiro) {
		super(tamanho, horizontal, posX, posY, tabuleiro);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desenhar() {
		if (isHorizontal()) {
			getCelulas().get(0).setImgPath("/img/patrulha1.png");
			getCelulas().get(1).setImgPath("/img/patrulha2.png");
		} else {
			getCelulas().get(0).setImgPath("/img/patrulha_v2.png");
			getCelulas().get(1).setImgPath("/img/patrulha_v1.png");
		}
	}

}
