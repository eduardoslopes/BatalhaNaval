package aplicacao.model;

public class InterpretadorMensagem {
	
//	public boolean conectG(InterfaceMensagem mensagem){
//		if(mensagem.getTAG().equals("CONECTG")){
//			return true;
//		}
//		return false;
//	}
	
	public boolean desconectG(InterfaceMensagem mensagem){
		if(mensagem.getTAG().equals("DESCONECTG")){
			return true;
		}
		return false;
		
	}
	
	public boolean move(InterfaceMensagem mensagem){
		if(mensagem.getTAG().equals("MOVE")){
			return true;
		}
		return false;
		
	}
	
//	public boolean createG(InterfaceMensagem mensagem){
//		if(mensagem.getTAG().equals("CREATEG")){
//			return true;
//		}
//		return false;
//		
//	}
}
