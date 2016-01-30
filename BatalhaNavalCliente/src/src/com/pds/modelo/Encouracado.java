package com.pds.modelo;

public class Encouracado extends Embarcacao {

	public Encouracado(int tamanho, boolean horizontal, int posX, int posY, Tabuleiro tabuleiro) {
		super(tamanho, horizontal, posX, posY, tabuleiro);
	}

	@Override
	public void desenhar() {
		if (isHorizontal()) {
			getCelulas().get(0).setImgPath("/img/encouracado/encouracado1.png");
			getCelulas().get(1).setImgPath("/img/encouracado/encouracado2.png");
			getCelulas().get(2).setImgPath("/img/encouracado/encouracado3.png");
			getCelulas().get(3).setImgPath("/img/encouracado/encouracado4.png");
		} else {
			getCelulas().get(0).setImgPath("/img/encouracado/encouracado_v4.png");
			getCelulas().get(1).setImgPath("/img/encouracado/encouracado_v3.png");
			getCelulas().get(2).setImgPath("/img/encouracado/encouracado_v2.png");
			getCelulas().get(3).setImgPath("/img/encouracado/encouracado_v1.png");
		}

	}

	@Override
	public void desenharDestruida() {
		if (isHorizontal()) {
			getCelulas().get(0).setImgPath("/img/encouracado/encouracado1_dest.png");
			getCelulas().get(1).setImgPath("/img/encouracado/encouracado2_dest.png");
			getCelulas().get(2).setImgPath("/img/encouracado/encouracado3_dest.png");
			getCelulas().get(3).setImgPath("/img/encouracado/encouracado4_dest.png");
		} else {
			getCelulas().get(0).setImgPath("/img/encouracado/encouracado_v4_dest.png");
			getCelulas().get(1).setImgPath("/img/encouracado/encouracado_v3_dest.png");
			getCelulas().get(2).setImgPath("/img/encouracado/encouracado_v2_dest.png");
			getCelulas().get(3).setImgPath("/img/encouracado/encouracado_v1_dest.png");
		} 
	}

}
