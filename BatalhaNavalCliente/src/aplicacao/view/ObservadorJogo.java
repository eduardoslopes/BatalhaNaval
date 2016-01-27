package aplicacao.view;

import aplicacao.model.Jogada;

public interface ObservadorJogo {

	public void novaJogada(Jogada jogada);
	public void setJogoComoConvidado();
	public void setJogoComoCriador();
	public void exibeResultadoJogada(String imgPath);
	public void desconectar();
	public void embarcacaoAfundada(int posX, int posY, String imgPath);
	public void ganhou();
	
}