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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
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
		ObservableList<Integer> listaPos = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		cbPosX.setItems(listaPos);
		cbPosY.setItems(listaPos);
		cbPosX.getSelectionModel().select(0);
		cbPosY.getSelectionModel().select(0);

		ObservableList<String> listaOrientacao = FXCollections.observableArrayList("Horizontal", "Vertical");

		cbOrientacao.setItems(listaOrientacao);
		cbOrientacao.getSelectionModel().select(0);

		for (int i = 1; i <= tabuleiro.getTamanho(); ++i) {
			for (int j = 1; j <= tabuleiro.getTamanho(); ++j) {
				gridTabuleiro.add(new ImageView("/img/mar.png"), i, j);
			}
		}

		btnInserirEmbarcacao.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				int tamanho = embarcacao_selecionada.tamanho();
				boolean horizontal = cbOrientacao.getValue().equals("Horizontal");
				int posX = cbPosX.getValue() - 1;
				int posY = cbPosY.getValue() - 1;

				if (horizontal && posX + tamanho > 10 || !horizontal && posY + tamanho > 10) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Embarca��o ultrapassa os limites do tabuleiro");
					alert.setContentText("Insira a embarca��o em uma posi��o v�lida");
					alert.show();
					return;
				}

				if (horizontal) {
					for (int i = posX; i < posX + tamanho; ++i) {
						if (tabuleiro.getCelulas().get(i).get(posY).isPreenchido()) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setHeaderText(
									"Embarca��o j� existente na faixa de c�lulas escolhida");
							alert.setContentText("Insira a embarca��o em uma posi��oo v�lida");
							alert.show();
							return;
						}
					}
				}else if(!horizontal) {
					for (int i = posY; i < posY + tamanho; ++i) {
						if (tabuleiro.getCelulas().get(posX).get(i).isPreenchido()) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setHeaderText(
									"Embarca��o j� existente na faixa de c�lulas escolhida");
							alert.setContentText("Insira a embarca��o em uma posi��o v�lida");
							alert.show();
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
						Alert alert = new Alert(AlertType.ERROR);
						alert.setHeaderText("Total de patrulhas j� inserido!");
						alert.setContentText(null);
						alert.show();
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
						Alert alert = new Alert(AlertType.ERROR);
						alert.setHeaderText("Total de submarinos j� inserido!");
						alert.setContentText(null);
						alert.show();
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
						Alert alert = new Alert(AlertType.ERROR);
						alert.setHeaderText("Total de encoura�ados j� inserido!");
						alert.setContentText(null);
						alert.show();
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
						Alert alert = new Alert(AlertType.ERROR);
						alert.setHeaderText("Total de porta-avi�os j� inserido!");
						alert.setContentText(null);
						alert.show();
					}
					break;
				default:
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText("Embarca��o n�o selecionada!");
					alert.setContentText("Selecione uma das embarca��es listadas.");
					alert.show();
					break;
				}
				atualizarTabuleiro();
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

				int qtdEmbarcacoesRestantes = qtdTotalPatrulhas + qtdTotalSubmarinos
						+ qtdTotalEncouracados + qtdTotalPortaAvioes;
				if (qtdEmbarcacoesRestantes > 0) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Ainda faltam embarca��es no tabuleiro!");
					alert.setContentText("Adicione mais " + qtdEmbarcacoesRestantes
							+ "embarca��es no seu tabuleiro para poder jogar.");
					alert.show();
				} else {
					ComunicaoTelaMontagemTelaJogo.tabuleiro = tabuleiro;
					
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setHeaderText("Voc� est� pronto para a partida?");
					alert.setContentText(null);
					
					ButtonType btnSim = new ButtonType("Sim");
					ButtonType btnNao = new ButtonType("Nao", ButtonData.CANCEL_CLOSE);
					alert.getButtonTypes().setAll(btnSim, btnNao);
					
					Optional<ButtonType> btnSelecionado = alert.showAndWait();
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
			}
		});
		
		TelaMontagemTabuleiro.getStage().setOnCloseRequest(event -> {
			Platform.runLater(() -> {
				TelaMontagemTabuleiro.getStage().close();
				Mensagem msg = new Mensagem.MontadorMensagem(TAG.DISCONNECTGAME)
						.jogador(ctrlComunicacao.getJogador().getApelido())
						.nomePartida(ctrlComunicacao.getPartida().getPartida()).build();
				ctrlComunicacao.enviarMensagem(msg);
				try {
					Thread.sleep(4000);
					ctrlComunicacao.fechar();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
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
			ControladorTelaInicial.mediaSomAmbiente.stop();
			TelaJogo telaJogo = new TelaJogo();
			try {
				telaJogo.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void desconectar() {
		Platform.runLater(() -> {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setHeaderText("Oponente desistiu do jogo!");
			alerta.setContentText("Cancelou a montagem do tabuleiro.");
			alerta.show();
			
			TelaMontagemTabuleiro.getStage().close();		
			TelaInicial telaInicial = new TelaInicial();
			try {
				telaInicial.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
