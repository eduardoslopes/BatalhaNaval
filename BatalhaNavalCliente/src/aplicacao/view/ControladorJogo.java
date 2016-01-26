package aplicacao.view;

import java.net.URL;
import java.util.ResourceBundle;

import aplicacao.model.Jogada;
import aplicacao.model.Tabuleiro;
import javafx.fxml.Initializable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

public class ControladorJogo implements Initializable, ObservadorJogo {

	@FXML GridPane gridTabuleiroInimigo;
	@FXML ImageView imgVez;
	@FXML GridPane gridTabuleiroMeu;
	private Tabuleiro tabuleiroInimigo;
	private Tabuleiro tabuleiroMeu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tabuleiroInimigo = new Tabuleiro(10);
		tabuleiroMeu = ComunicaoTelaMontagemTelaJogo.tabuleiro;
		atualizarTabuleiro(tabuleiroInimigo, gridTabuleiroMeu);
		atualizarTabuleiro(tabuleiroMeu, gridTabuleiroMeu);
		atualizarTabuleiro(tabuleiroInimigo, gridTabuleiroInimigo);
		
		gridTabuleiroInimigo.setDisable(true);
	}

	@Override
	public void novaJogada(Jogada jogada) {
		
		
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

}
