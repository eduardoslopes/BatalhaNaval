package aplicacao.model;

public class Mensagem{
	private String tag;
	private Jogada jogada;
	private String apelidoJogador;
	private String nomePartida;
	
	public static class MensagemBuilder {
		private final String TAG;
		private Jogada jogada;
		private String apelidoJogador;
		private String nomePartida;
		
		public MensagemBuilder(String TAG){
			this.TAG = TAG;
		}
		
		public MensagemBuilder jogada(Jogada jogada){
			this.jogada = jogada;
			return this;
		}
		
		public MensagemBuilder jogador(String apelidoJogador){
			this.apelidoJogador = apelidoJogador;
			return this;
		}
		
		public MensagemBuilder nomePartida(String nomePartida){
			this.nomePartida = nomePartida;
			return this;
		}
		
		public Mensagem build(){
			return new Mensagem(this);
		}
		
		
	}
	
	private Mensagem(MensagemBuilder montador){
		tag = montador.TAG;
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
		return tag;
	}

}
