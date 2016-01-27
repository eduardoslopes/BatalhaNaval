package aplicacao.model;

public class InterpretadorMensagem extends Interpretador {

	@Override
	public void desconectarJogo() {
		
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
	}

	@Override
	public void iniciaJogoCriador() {
		observerTabuleiro.criarTelaJogo();
	}

	@Override
	public void conectarJogo() {
		
		observerPartida.conectarJogo();
	}

}
