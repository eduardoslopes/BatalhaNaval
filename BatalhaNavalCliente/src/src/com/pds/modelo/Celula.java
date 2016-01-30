package com.pds.modelo;

public class Celula {

	private String imgPath;
	private boolean atingido;
	private boolean preenchido;

	public Celula () {
		imgPath = "/img/mar.png";
		atingido = false;
		preenchido = false;
	}
	
	public Celula(String imgPath) {
		this.imgPath = imgPath;
		atingido = false;
	}
	
	public String getImgPath() {
		return imgPath;
	}
	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
		if (!imgPath.equals("/img/mar.png")) {
			this.preenchido = true;
		}
	}
	
	public boolean isAtingido() {
		return atingido;
	}
	
	public void setAtingido(boolean atingido) {
		this.atingido = atingido;
	}

	public boolean isPreenchido() {
		return preenchido;
	}
	
	

}
