package aplicacao.model;

public class Encouracado extends Embarcacao {

	public Encouracado(int tamanho, boolean horizontal, int posX, int posY, Tabuleiro tabuleiro) {
		super(tamanho, horizontal, posX, posY, tabuleiro);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desenhar() {
		if (isHorizontal()) {
			getCelulas().get(0).setImgPath("/img/encouracado1.png");
			getCelulas().get(1).setImgPath("/img/encouracado2.png");
			getCelulas().get(2).setImgPath("/img/encouracado3.png");
			getCelulas().get(3).setImgPath("/img/encouracado4.png");
		} else {
			getCelulas().get(0).setImgPath("/img/encouracado_v4.png");
			getCelulas().get(1).setImgPath("/img/encouracado_v3.png");
			getCelulas().get(2).setImgPath("/img/encouracado_v2.png");
			getCelulas().get(3).setImgPath("/img/encouracado_v1.png");
		}

	}

}
