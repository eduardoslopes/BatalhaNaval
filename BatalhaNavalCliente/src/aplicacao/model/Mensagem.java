package aplicacao.model;

public class Mensagem {
	private String TAG;
	private Jogada jogada;
	private Jogador jogador;
	
	public static class MontadorMensagem {
		private final String TAG;
		private Jogada jogada;
		private Jogador jogador;
		
		public MontadorMensagem(String TAG){
			this.TAG = TAG;
		}
		
		public MontadorMensagem jogada(Jogada jogada){
			this.jogada = jogada;
			return this;
		}
		
		public MontadorMensagem jogador(Jogador jogador){
			this.jogador = jogador;
			return this;
		}
		
		public Mensagem build(){
			return new Mensagem(this);
		}
		
		
	}
	
	private Mensagem(MontadorMensagem montador){
		TAG = montador.TAG;
		jogada = montador.jogada;
		jogador = montador.jogador;
	}

}
