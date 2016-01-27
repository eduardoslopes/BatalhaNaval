package aplicacao.view;

import java.net.URL;
import java.util.ResourceBundle;

import aplicacao.comunicacao.ControladorComunicacao;
import aplicacao.model.Celula;
import aplicacao.model.Embarcacao;
import aplicacao.model.Jogada;
import aplicacao.model.Tabuleiro;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ControladorJogo implements Initializable, ObservadorJogo {

	ControladorComunicacao ctrlcomunicacao;
	
	@FXML GridPane gridTabuleiroInimigo;
	@FXML ImageView imgVez;
	@FXML GridPane gridTabuleiroMeu;
	private Tabuleiro tabuleiroInimigo;
	private Tabuleiro tabuleiroMeu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ctrlcomunicacao = ControladorTelaInicial.ctrlComunicacao;
		ctrlcomunicacao.getInterpretador().setObserverJogo(this);
		
		tabuleiroInimigo = new Tabuleiro(10);
		tabuleiroMeu = ComunicaoTelaMontagemTelaJogo.tabuleiro;
		atualizarTabuleiro(tabuleiroInimigo, gridTabuleiroMeu);
		atualizarTabuleiro(tabuleiroMeu, gridTabuleiroMeu);
		atualizarTabuleiro(tabuleiroInimigo, gridTabuleiroInimigo);
		
		//gridTabuleiroInimigo.setDisable(true);
	}

	
	private void atualizarTabuleiro (Tabuleiro tabuleiro, GridPane grid) {
		for (int i = 1; i <= tabuleiro.getTamanho(); ++i) {
			for (int j = 1; j <= tabuleiro.getTamanho(); ++j) {
				
				ImageView node = new ImageView(tabuleiro.getCelulas().get(i - 1).get(j - 1).getImgPath());
				final int t = i;
				final int sexo = j;
				node.setOnMousePressed(e -> {
					System.out.println(t + "  >  " + sexo);
				});
				
				grid.add(node, i, j);
			}
		}
	}

	@Override
	public void novaJogada (Jogada jogada) {
		boolean embarcacaoAtingida = false;
		Celula celulaAtingida = tabuleiroMeu.getCelulas().get(jogada.getPosX()).get(jogada.getPosY());
		celulaAtingida.setAtingido(true);
		for (Embarcacao embarcacao : tabuleiroMeu.getEmbarcacoes()) {
			for (Celula celula : embarcacao.getCelulas()) {
				if(celula.equals(celulaAtingida)) {
					if (embarcacao.isDestruida()) {
						embarcacao.desenharDestruida ();
					} else {
						celula.setImgPath("/img/embarcacao_destruida.png");
					}
					embarcacaoAtingida = true;
				}
			}
		}
		if (!embarcacaoAtingida) {
			celulaAtingida.setImgPath("/img/bomba.png");
		}
	}
}
