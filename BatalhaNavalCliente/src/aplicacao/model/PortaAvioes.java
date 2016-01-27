package aplicacao.model;

public class PortaAvioes extends Embarcacao {

	public PortaAvioes(int tamanho, boolean horizontal, int posX, int posY, Tabuleiro tabuleiro) {
		super(tamanho, horizontal, posX, posY, tabuleiro);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void desenhar() {
		// TODO Auto-generated method stub
		if (isHorizontal()) {
			getCelulas().get(0).setImgPath("/img/porta_aviao1.png");
			getCelulas().get(1).setImgPath("/img/porta_aviao2.png");
			getCelulas().get(2).setImgPath("/img/porta_aviao3.png");
			getCelulas().get(3).setImgPath("/img/porta_aviao4.png");
			getCelulas().get(4).setImgPath("/img/porta_aviao5.png");
		} else {
			getCelulas().get(0).setImgPath("/img/porta_aviao_v5.png");
			getCelulas().get(1).setImgPath("/img/porta_aviao_v4.png");
			getCelulas().get(2).setImgPath("/img/porta_aviao_v3.png");
			getCelulas().get(3).setImgPath("/img/porta_aviao_v2.png");
			getCelulas().get(4).setImgPath("/img/porta_aviao_v1.png");
		}
	}

	@Override
	public void desenharDestruida() {
		if (isHorizontal()) {
			getCelulas().get(0).setImgPath("/img/porta_aviao/porta_aviao1_dest.png");
			getCelulas().get(1).setImgPath("/img/porta_aviao/porta_aviao2_dest.png");
			getCelulas().get(2).setImgPath("/img/porta_aviao/porta_aviao3_dest.png");
			getCelulas().get(3).setImgPath("/img/porta_aviao/porta_aviao4_dest.png");
		} else {
			getCelulas().get(0).setImgPath("/img/porta_aviao/porta_aviao_v4_dest.png");
			getCelulas().get(1).setImgPath("/img/porta_aviao/porta_aviao_v3_dest.png");
			getCelulas().get(2).setImgPath("/img/porta_aviao/porta_aviao_v2_dest.png");
			getCelulas().get(3).setImgPath("/img/porta_aviao/porta_aviao_v1_dest.png");
		} 
	}
	
}
