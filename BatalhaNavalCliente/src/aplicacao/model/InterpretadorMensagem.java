package aplicacao.model;

import aplicacao.view.ControladorJogo;

public class InterpretadorMensagem extends Interpretador{
	
	private ControladorJogo controladorJogo;
	
	public InterpretadorMensagem(ControladorJogo controladorJogo) {
		this.controladorJogo = controladorJogo;
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
	public void verJogos() {
		
		
	}

	@Override
	public void criarJogo() {
		
	}
	

}
