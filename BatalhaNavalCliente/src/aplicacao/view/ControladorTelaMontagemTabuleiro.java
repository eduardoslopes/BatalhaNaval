package aplicacao.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import aplicacao.model.Embarcacao;
import aplicacao.model.Tabuleiro;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class ControladorTelaMontagemTabuleiro implements Initializable {

	private Tabuleiro tabuleiro;

	@FXML
	private AnchorPane rootPane;

	@FXML
	private GridPane gridTabuleiro;

	@FXML
	private List<ImageView> imgViewsTabuleiro;

	@FXML
	private ImageView imgPortaAvioes;

	@FXML
	private ImageView imgEncouracado;

	@FXML
	private ImageView imgSubmarino;

	@FXML
	private ImageView imgPatrulha;

	@FXML
	private ComboBox<Integer> cbPosX;

	@FXML
	private ComboBox<Integer> cbPosY;

	@FXML
	private ComboBox<String> cbOrientacao;

	@FXML 
	private Button btnPronto;

	@FXML
	private Button btnInserirEmbarcacao;
	
	enum EMBARCACAO_SELECIONADA {
		PORTA_AVIOES,
		ENCOURACADO,
		SUBMARINO,
		PATRULHA
	}
	
	EMBARCACAO_SELECIONADA embarcacao_selecionada;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tabuleiro = new Tabuleiro(10);
		imgViewsTabuleiro = new ArrayList<ImageView>();
		ObservableList<Integer> listaPos = FXCollections.observableArrayList(
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		cbPosX.setItems(listaPos);
		cbPosY.setItems(listaPos);
		cbPosX.getSelectionModel().select(0);
		cbPosY.getSelectionModel().select(0);

		ObservableList<String> listaOrientacao = FXCollections.observableArrayList("Horizontal", "Vertical");

		cbOrientacao.setItems(listaOrientacao);
		cbOrientacao.getSelectionModel().select(0);

		btnInserirEmbarcacao.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int posX = cbPosX.getValue();
				int posY = cbPosY.getValue();
				Embarcacao e;
				boolean horizontal = cbOrientacao.getValue().equals("Horizontal");
				switch (embarcacao_selecionada) {
					case PORTA_AVIOES:
						e = new Embarcacao(5, "/img/porta_avioes.png", posX, posY, horizontal);
						tabuleiro.inserirEmbarcacao(e);
						Alert alert = new Alert(null);
						alert.setContentText("Embarcacao inserida!!!");
						break;
					case ENCOURACADO:
						e = new Embarcacao(4, "/img/encouracado.png", posX, posY, horizontal);
						tabuleiro.inserirEmbarcacao(e);
						break;
					case SUBMARINO:
						e = new Embarcacao(3, "/img/submarino.png", posX, posY, horizontal);
						tabuleiro.inserirEmbarcacao(e);
						break;
					case PATRULHA:
						e = new Embarcacao(2, "/img/patrulha.png", posX, posY, horizontal);
						tabuleiro.inserirEmbarcacao(e);
						break;
				}
			}
		});
		
		imgPortaAvioes.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				embarcacao_selecionada = EMBARCACAO_SELECIONADA.PORTA_AVIOES;
				imgPortaAvioes.setEffect(new DropShadow(20, Color.DARKBLUE));
				imgEncouracado.setEffect(null);
				imgSubmarino.setEffect(null);
				imgPatrulha.setEffect(null);
			}
		});
		
		imgEncouracado.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				embarcacao_selecionada = EMBARCACAO_SELECIONADA.ENCOURACADO;
				imgPortaAvioes.setEffect(null);
				imgEncouracado.setEffect(new DropShadow(20, Color.DARKBLUE));
				imgSubmarino.setEffect(null);
				imgPatrulha.setEffect(null);
			}
		});
		
		imgSubmarino.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				embarcacao_selecionada = EMBARCACAO_SELECIONADA.SUBMARINO;
				imgPortaAvioes.setEffect(null);
				imgEncouracado.setEffect(null);
				imgSubmarino.setEffect(new DropShadow(20, Color.DARKBLUE));
				imgPatrulha.setEffect(null);
			}
		});
		
		imgPatrulha.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				embarcacao_selecionada = EMBARCACAO_SELECIONADA.PATRULHA;
				imgPortaAvioes.setEffect(null);
				imgEncouracado.setEffect(null);
				imgSubmarino.setEffect(null);
				imgPatrulha.setEffect(new DropShadow(20, Color.DARKBLUE));
			}
		});

		btnPronto.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO esperar oponente estar preparado. ou reconfigurar tabuleiro.
				
			}
		});
	
	}

}
