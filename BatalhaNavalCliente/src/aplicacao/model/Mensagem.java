package aplicacao.model;

import aplicacao.Jogada;

public class Mensagem{
	
	private String tag;
	private Jogada jogada;
	private String apelidoJogador;
	private String nomePartida;
	
	public static class MontadorMensagem {
		private final String tag;
		private Jogada jogada;
		private String apelidoJogador;
		private String nomePartida;
		
		public MontadorMensagem(String TAG){
			this.tag = TAG;
		}
		
		public MontadorMensagem jogada(Jogada jogada){
			this.jogada = jogada;
			return this;
		}
		
		public MontadorMensagem jogador(String apelidoJogador){
			this.apelidoJogador = apelidoJogador;
			return this;
		}
		
		public MontadorMensagem nomePartida(String nomePartida){
			this.nomePartida = nomePartida;
			return this;
		}
		
		public Mensagem build(){
			return new Mensagem(this);
		}
		
		
	}
	
	private Mensagem(MontadorMensagem montador){
		tag = montador.tag;
		jogada = montador.jogada;
		nomePartida = montador.nomePartida;
		apelidoJogador = montador.apelidoJogador;
	}

	public Jogada getJogada() {
		return jogada;
	}

	public String getApelidoJogador() {
		return apelidoJogador;
	}

	public String getNomePartida() {
		return nomePartida;
	}

	public String getTag() {
		return tag;
	}

	

}