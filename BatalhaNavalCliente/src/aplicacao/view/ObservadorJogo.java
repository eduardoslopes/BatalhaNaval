package aplicacao.view;

import aplicacao.model.Jogada;

public interface ObservadorJogo {

	public void novaJogada(Jogada jogada);
	public void setJogoComoConvidado();
	public void setJogoComoCriador();
	public void exibeResultadoJogada(String imgPath);
	public void desconectar();
	
}
