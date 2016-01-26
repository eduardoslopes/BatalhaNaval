package aplicacao.mensagem;

import aplicacao.ControladorJogo;
import aplicacao.Jogada;
import aplicacao.model.Jogador;
import aplicacao.model.Partida;
import aplicacao.view.ObservadorPartida;

public class InterpretadorMensagem extends Interpretador {
	
	private ControladorJogo controladorJogo;
	private ObservadorPartida observer;
	
	public InterpretadorMensagem(ControladorJogo controladorJogo) {
		this.controladorJogo = controladorJogo;
	}

	public InterpretadorMensagem(ControladorJogo controladorJogo,
			ObservadorPartida observerLista) {
				this.controladorJogo = controladorJogo;
				observer = observerLista;
	}

	@Override
	public void conectarJogo() {
		
		
	}

	@Override
	public void desconectarJogo() {
		
	}

	@Override
	public void fazerJogada(Jogada jogada) {
		controladorJogo.receberJogada(jogada);
	}

	@Override
	public void criarJogo() {
		
	}

	@Override
	public void verJogos(String apelido, String partida) {
		Partida p = new Partida(partida, new Jogador(apelido));
		observer.atualizarPartida(p);		
	}

}
