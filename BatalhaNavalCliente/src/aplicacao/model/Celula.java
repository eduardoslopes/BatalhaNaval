package aplicacao.model;

public class Celula {

	private String imgPath;
	private boolean atingido;
	
	public Celula () {
		imgPath = "/img/mar.png";
		atingido = false;
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
	}
	
	public boolean isAtingido() {
		return atingido;
	}
	
	public void setAtingido(boolean atingido) {
		this.atingido = atingido;
	}

}
