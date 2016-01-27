package aplicacao.model;

import aplicacao.model.TAG;

public abstract class Interpretador {

	public void messageInterpreter(Mensagem msg){
		switch(msg.getTAG()){
		case TAG.CONECTGAME:
			conectarJogo();
			break;
		case TAG.CREATEGAME :
			jogoCriado();
			break;
			
		case TAG.DESCONECTGAME :
			desconectarJogo();
			break;
			
		case TAG.MOVEGAME :
			fazerJogada(msg.getJogada());
			break;
			
		case TAG.SEEGAMES :
			verJogo(msg.getApelidoJogador(), msg.getNomePartida());
			break;
		}
	}
	
	public abstract void conectarJogo();
	public abstract void desconectarJogo();
	public abstract void fazerJogada(Jogada jogada);
	public abstract void verJogo(String apelido,String nomePartida);
	public abstract void jogoCriado();	
	
}
