package aplicacao.model;

public class Submarino extends Embarcacao {

	public Submarino(int tamanho, boolean horizontal, int posX, int posY, Tabuleiro tabuleiro) {
		super(tamanho, horizontal, posX, posY, tabuleiro);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desenhar() {
		if (isHorizontal()) {
			getCelulas().get(0).setImgPath("/img/submarino1.png");
			getCelulas().get(1).setImgPath("/img/submarino2.png");
			getCelulas().get(2).setImgPath("/img/submarino3.png");
		} else {
			getCelulas().get(0).setImgPath("/img/submarino_v3.png");
			getCelulas().get(1).setImgPath("/img/submarino_v2.png");
			getCelulas().get(2).setImgPath("/img/submarino_v1.png");
		}
	}

	@Override
	public void desenharDestruida() {
		if (isHorizontal()) {
			getCelulas().get(0).setImgPath("/img/submarino/submarino1_dest.png");
			getCelulas().get(1).setImgPath("/img/submarino/submarino2_dest.png");
			getCelulas().get(2).setImgPath("/img/submarino/submarino3_dest.png");
		} else {
			getCelulas().get(0).setImgPath("/img/submarino/submarino_v3_dest.png");
			getCelulas().get(1).setImgPath("/img/submarino/submarino_v2_dest.png");
			getCelulas().get(2).setImgPath("/img/submarino/submarino_v1_dest.png");
		} 
	}
}
