package aplicacao.model;

public class test {
	public static void main(String[] args) {
		
		//FactoryMensagem mensagem = FactoryMensagem.obterFactory(mensagem);
		//Fazer o build dentro de Factory Mensagem e Apagar essas concretas que herdam de FactoryMensagem
		
		Jogada jogada = new Jogada();
		Mensagem msg = new Mensagem.MontadorMensagem("ds").jogada(jogada).build();
		System.out.println(msg);
	}
	
}