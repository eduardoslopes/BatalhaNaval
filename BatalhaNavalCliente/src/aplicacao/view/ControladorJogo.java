package aplicacao.view;

import java.net.URL;
import java.util.ResourceBundle;

import aplicacao.model.Jogada;
import aplicacao.model.Tabuleiro;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

public class ControladorJogo implements Initializable, ObserverJogo {

	@FXML GridPane gridTabuleiroInimigo;
	@FXML ImageView imgVez;
	@FXML GridPane gridTabuleiroMeu;
	private Tabuleiro tabuleiroInimigo;
	private Tabuleiro tabuleiroMeu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabuleiroInimigo = new Tabuleiro(10);
		tabuleiroMeu = new Tabuleiro(10);
	}

	@Override
	public void novaJogada(Jogada jogada) {

		
	}

}
