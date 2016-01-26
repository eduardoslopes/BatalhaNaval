package aplicacao.model;

public class Mensagem{
	private String TAG;
	private Jogada jogada;
	private String apelidoJogador;
	private String nomePartida;
	
	public static class MontadorMensagem {
		private final String TAG;
		private Jogada jogada;
		private String apelidoJogador;
		private String nomePartida;
		
		public MontadorMensagem(String TAG){
			this.TAG = TAG;
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
		TAG = montador.TAG;
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

	public String getTAG() {
		return TAG;
	}

	

}
