package aplicacao.model;

public abstract class FactoryMensagem {
	public static FactoryMensagem obterFactory(InterfaceMensagem mensagem){
		if(mensagem.getTAG().equals("CONECTG")){
			return new ConcreteFactoryMensagemConectouJogo();
		}
		
		else if(mensagem.getTAG().equals("DESCONECTG")){
			return new ConcreteFactoryMensagemDesconectou();
		}
		
		else if(mensagem.getTAG().equals("CREATEG")){
			return new ConcreteFactoryMensagemCriouJogo();
		}
		
		else if(mensagem.getTAG().equals("MOVE")){
			return new ConcreteFactoryMensagemEnviouJogada();
		}
	
		else if(mensagem.getTAG().equals("SEEG")){
			return new ConcreteFacotoryMensagemVerJogos();
		}
        return null;
   }

}
