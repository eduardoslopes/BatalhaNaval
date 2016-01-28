package aplicacao.model;

public class InterpretadorMensagem extends Interpretador {

	@Override
	public void desconectarJogo() {
		if (observerJogo != null)
			observerJogo.desconectar();
		else
			observerTabuleiro.desconectar();
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

	@Override
	public void ganhou() {

		observerJogo.ganhou();
		
	}

	@Override
	public void embarcacaoAfundada(Jogada jogada, String imgPath) {

		observerJogo.embarcacaoAfundada(jogada.getPosX(), jogada.getPosY(), imgPath);
	}


}
