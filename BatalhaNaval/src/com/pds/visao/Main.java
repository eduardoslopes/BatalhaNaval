package com.pds.visao;

import com.pds.controle.ControladorConexao;
import com.pds.controle.ControladorInterpretacao;
import com.pds.controle.ControladorPartida;
import com.pds.controle.Descerializador;
import com.pds.controle.DistribuidorResponsabilidades;
import com.pds.controle.Interpretador;
import com.pds.controle.InterpretadorMensagem;
import com.pds.controle.Serializador;

public class Main {
	
	public static void main(String[] args){
		ControladorPartida cPartida = new ControladorPartida();
		Serializador serializador = new Serializador();
		DistribuidorResponsabilidades distribuidor = new DistribuidorResponsabilidades(cPartida);
		Interpretador interpretador = new InterpretadorMensagem(serializador, distribuidor);
		Descerializador descerializador = new Descerializador();
		ControladorInterpretacao cInterpretacao = new ControladorInterpretacao(interpretador, descerializador);
		ControladorConexao cConexao = new ControladorConexao(8888, cInterpretacao);
		cConexao.executa();
	}

}
