package aplicacao.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import aplicacao.comunicacao.ControladorComunicacao;
import aplicacao.model.Embarcacao;
import aplicacao.model.Encouracado;
import aplicacao.model.Mensagem;
import aplicacao.model.ObservadorTabuleiro;
import aplicacao.model.Patrulha;
import aplicacao.model.PortaAvioes;
import aplicacao.model.Submarino;
import aplicacao.model.TAG;
import aplicacao.model.Tabuleiro;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControladorTelaMontagemTabuleiro implements Initializable, ObservadorTabuleiro {

	private Tabuleiro tabuleiro;
	private ControladorComunicacao ctrlComunicacao;

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

	private enum EMBARCACAO_SELECIONADA {
		PATRULHA(2), SUBMARINO(3), ENCOURACADO(4), PORTA_AVIOES(5), SEM_EMBARCACAO(0);

		private final int tamanho;

		private EMBARCACAO_SELECIONADA(int tamanho) {
			this.tamanho = tamanho;
		}

		private int tamanho() {

			return tamanho;
		}
	}

	private int qtdTotalPortaAvioes = 1;
	private int qtdTotalEncouracados = 1;
	private int qtdTotalSubmarinos = 2;
	private int qtdTotalPatrulhas = 1;

	EMBARCACAO_SELECIONADA embarcacao_selecionada = EMBARCACAO_SELECIONADA.SEM_EMBARCACAO;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ctrlComunicacao = ControladorTelaInicial.ctrlComunicacao;
		ctrlComunicacao.getInterpretador().setObserverTabuleiro(this);
		
		tabuleiro = new Tabuleiro(10);

		imgViewsTabuleiro = new ArrayList<ImageView>();
		ObservableList<Integer> listaPos = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8,
				9, 10);
		cbPosX.setItems(listaPos);
		cbPosY.setItems(listaPos);
		cbPosX.getSelectionModel().select(0);
		cbPosY.getSelectionModel().select(0);

		ObservableList<String> listaOrientacao = FXCollections.observableArrayList("Horizontal",
				"Vertical");

		cbOrientacao.setItems(listaOrientacao);
		cbOrientacao.getSelectionModel().select(0);

		for (int i = 1; i <= tabuleiro.getTamanho(); ++i) {
			for (int j = 1; j <= tabuleiro.getTamanho(); ++j) {
				gridTabuleiro.add(new ImageView("/img/mar.png"), i, j);
			}
		}

		btnInserirEmbarcacao.setOnAction(event -> {

			int tamanho = embarcacao_selecionada.tamanho();
			boolean horizontal = cbOrientacao.getValue().equals("Horizontal");
			int posX = cbPosX.getValue() - 1;
			int posY = cbPosY.getValue() - 1;

			if (horizontal && posX + tamanho > 10 || !horizontal && posY + tamanho > 10) {
				Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setHeaderText("Embarcação ultrapassa os limites do tabuleiro");
				alert1.setContentText("Insira a embarcação em uma posição válida");
				alert1.show();
				return;
			}

			if (horizontal) {
				for (int i1 = posX; i1 <= tamanho; ++i1) {
					if (tabuleiro.getCelulas().get(i1).get(posY).isPreenchido()) {
						Alert alert2 = new Alert(AlertType.ERROR);
						alert2.setHeaderText(
								"Embarcação já existente na faixa de células escolhida");
						alert2.setContentText("Insira a embarcação em uma posição válida");
						alert2.show();
						return;
					}
				}
			} else {
				for (int i2 = posY; i2 <= tamanho; ++i2) {
					if (tabuleiro.getCelulas().get(posX).get(i2).isPreenchido()) {
						Alert alert3 = new Alert(AlertType.ERROR);
						alert3.setHeaderText(
								"Embarcação já existente na faixa de células escolhida");
						alert3.setContentText("Insira a embarcação em uma posição válida");
						alert3.show();
						return;
					}
				}
			}

			switch (embarcacao_selecionada) {
			case PATRULHA:
				if (qtdTotalPatrulhas > 0) {
					Embarcacao patrulha = new Patrulha(tamanho, horizontal, posX, posY,
							tabuleiro);
					tabuleiro.addEmbarcacao(patrulha);
					patrulha.desenhar();
					--qtdTotalPatrulhas;
				} else {
					Alert alert4 = new Alert(AlertType.ERROR);
					alert4.setHeaderText("Total de Embarcações já inserido!");
					alert4.setContentText("Remova uma patrulha para poder re-inserir");
					alert4.show();
					return;
				}
				break;
			case SUBMARINO:
				if (qtdTotalSubmarinos > 0) {
					Embarcacao submarino = new Submarino(tamanho, horizontal, posX, posY,
							tabuleiro);
					tabuleiro.addEmbarcacao(submarino);
					submarino.desenhar();
					--qtdTotalSubmarinos;
				} else {
					Alert alert5 = new Alert(AlertType.ERROR);
					alert5.setHeaderText("Total de Embarcações já inserido!");
					alert5.setContentText("Remova um submarino para poder re-inserir");
					alert5.show();
					return;
				}
				break;
			case ENCOURACADO:
				if (qtdTotalEncouracados > 0) {
					Embarcacao encouracado = new Encouracado(tamanho, horizontal, posX, posY,
							tabuleiro);
					tabuleiro.addEmbarcacao(encouracado);
					encouracado.desenhar();
					--qtdTotalEncouracados;
				} else {
					Alert alert6 = new Alert(AlertType.ERROR);
					alert6.setHeaderText("Total de Embarcações já inserido!");
					alert6.setContentText("Remova um encouraçado para poder re-inserir");
					alert6.show();
				}
				break;
			case PORTA_AVIOES:
				if (qtdTotalPortaAvioes > 0) {
					Embarcacao portaAvioes = new PortaAvioes(tamanho, horizontal, posX, posY,
							tabuleiro);
					tabuleiro.addEmbarcacao(portaAvioes);
					portaAvioes.desenhar();
					--qtdTotalPortaAvioes;
				} else {
					Alert alert7 = new Alert(AlertType.ERROR);
					alert7.setHeaderText("Total de Embarcações já inserido!");
					alert7.setContentText("Remova um porta-avião para poder re-inserir");
					alert7.show();
				}
				break;
			default:
				Alert alert8 = new Alert(AlertType.WARNING);
				alert8.setHeaderText("Embarcação não selecionada!");
				alert8.setContentText("Selecione uma das embarcações listadas.");
				alert8.show();
				break;
			}
			atualizarTabuleiro();
		});

		imgPortaAvioes.setOnMouseClicked(event -> {

			embarcacao_selecionada = EMBARCACAO_SELECIONADA.PORTA_AVIOES;
			imgPortaAvioes.setEffect(new DropShadow(20, Color.DARKBLUE));
			imgEncouracado.setEffect(null);
			imgSubmarino.setEffect(null);
			imgPatrulha.setEffect(null);
		});

		imgEncouracado.setOnMouseClicked(event -> {

			embarcacao_selecionada = EMBARCACAO_SELECIONADA.ENCOURACADO;
			imgPortaAvioes.setEffect(null);
			imgEncouracado.setEffect(new DropShadow(20, Color.DARKBLUE));
			imgSubmarino.setEffect(null);
			imgPatrulha.setEffect(null);
		});

		imgSubmarino.setOnMouseClicked(e -> {

			embarcacao_selecionada = EMBARCACAO_SELECIONADA.SUBMARINO;
			imgPortaAvioes.setEffect(null);
			imgEncouracado.setEffect(null);
			imgSubmarino.setEffect(new DropShadow(20, Color.DARKBLUE));
			imgPatrulha.setEffect(null);
		});

		imgPatrulha.setOnMouseClicked(e -> {

			embarcacao_selecionada = EMBARCACAO_SELECIONADA.PATRULHA;
			imgPortaAvioes.setEffect(null);
			imgEncouracado.setEffect(null);
			imgSubmarino.setEffect(null);
			imgPatrulha.setEffect(new DropShadow(20, Color.DARKBLUE));
		});

		btnPronto.setOnAction(e -> {

			int qtdEmbarcacoesRestantes = qtdTotalPatrulhas + qtdTotalSubmarinos
					+ qtdTotalEncouracados + qtdTotalPortaAvioes;
			if (qtdEmbarcacoesRestantes > 0) {
				Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setHeaderText("Ainda faltam embarcações no tabuleiro!");
				alert1.setContentText("Adicione mais " + qtdEmbarcacoesRestantes
						+ "embarcações no seu tabuleiro para poder jogar.");
				alert1.show();
			} else {
				ComunicaoTelaMontagemTelaJogo.tabuleiro = tabuleiro;
				
				Alert alert2 = new Alert(AlertType.CONFIRMATION);
				alert2.setHeaderText("Você está pronto para a partida?");
				alert2.setContentText(null);
				
				ButtonType btnSim = new ButtonType("Sim");
				ButtonType btnNao = new ButtonType("Nao", ButtonData.CANCEL_CLOSE);
				alert2.getButtonTypes().setAll(btnSim, btnNao);
				
				Optional<ButtonType> btnSelecionado = alert2.showAndWait();
				if (btnSelecionado.isPresent()) {
					if (btnSelecionado.get() == btnSim) {
						String apelido = ctrlComunicacao.getJogador()
								.getApelido();
						String nomePartida = ctrlComunicacao.getPartida()
								.getPartida();
						Mensagem mensagem = new Mensagem.MontadorMensagem(TAG.READY).jogador(apelido)
								.nomePartida(nomePartida).build();
						ctrlComunicacao.enviarMensagem(mensagem);
					}
				}
			}
		});
		
		TelaMontagemTabuleiro.getStage().setOnCloseRequest(e -> {
			
		});
	}

	private void atualizarTabuleiro() {

		for (int i = 1; i <= tabuleiro.getTamanho(); ++i) {
			for (int j = 1; j <= tabuleiro.getTamanho(); ++j) {
				gridTabuleiro.add(new ImageView(tabuleiro.getCelulas().get(i - 1).get(j - 1).getImgPath()), i, j);
			}
		}
	}

	@Override
	public void criarTelaJogo() {

		Platform.runLater(() -> {
			TelaMontagemTabuleiro.getStage().close();
			
			TelaJogo telaJogo = new TelaJogo();
			try {
				telaJogo.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
