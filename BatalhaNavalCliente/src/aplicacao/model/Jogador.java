package aplicacao.model;

public class Jogador {
	public String apelido;

	public Jogador (String nickname) {
		this.apelido = nickname;
	}

	public String getNickname() {
		return apelido;
	}

	public void setNickname(String nickname) {
		this.apelido = nickname;
	}
	
}
