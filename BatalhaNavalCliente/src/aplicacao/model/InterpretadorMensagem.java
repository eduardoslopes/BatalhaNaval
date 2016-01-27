package aplicacao.model;

public class InterpretadorMensagem extends Interpretador {

	@Override
	public void desconectarJogo() {
		observerJogo.desconectar();
	}

	@Override
	public void fazerJogada(Jogada jogada) {
		observerJogo.novaJogada(jogada);
	}

	@Override
	public void verJogos(String apelido, String partida) {

		Partida p = new Partida(partida, new Jogador(apelido));
		observerPartida.atualizarPartida(p);
	}

	@Override
	public void iniciaJogoConvidado() {
		observerTabuleiro.criarTelaJogo();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		observerJogo.setJogoComoConvidado();
	}

	@Override
	public void iniciaJogoCriador() {
		observerTabuleiro.criarTelaJogo();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		observerJogo.setJogoComoCriador();
	}

	@Override
	public void conectarJogo() {
		observerPartida.conectarJogo();
	}

	@Override
	public void exibeResultado(String imgPath) {
		observerJogo.exibeResultadoJogada(imgPath);
	}


}
