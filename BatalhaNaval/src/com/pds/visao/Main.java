package com.pds.visao;

import com.pds.controle.ControladorConexao;
import com.pds.controle.ControladorInterpretacao;
import com.pds.controle.ControladorPartida;

public class Main {
	
	public static void main(String[] args){
		ControladorPartida cPartida = new ControladorPartida();
		ControladorInterpretacao cInterpretacao = new ControladorInterpretacao(cPartida);
		ControladorConexao cConexao = new ControladorConexao(8888, cInterpretacao);
		cConexao.executa();
	}

}
