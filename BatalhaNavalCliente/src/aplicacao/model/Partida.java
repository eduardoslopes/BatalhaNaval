package aplicacao.model;

public class Partida {
	private String partida;
	private Jogador jogador;

	public Partida(String partida, Jogador jogador) {
		this.setPartida(partida);
		this.setJogador(jogador);
	}

	public String getJogador() {

		return jogador.getApelido();
	}

	public void setJogador(Jogador jogador) {

		this.jogador = jogador;
	}

	public String getPartida() {

		return partida;
	}

	public void setPartida(String partida) {

		this.partida = partida;
	}

}
