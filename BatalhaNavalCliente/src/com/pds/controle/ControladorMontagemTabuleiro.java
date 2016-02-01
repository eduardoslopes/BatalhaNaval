package com.pds.controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.pds.comunicacao.ControladorComunicacao;
import com.pds.modelo.EfeitosSonoros;
import com.pds.modelo.Embarcacao;
import com.pds.modelo.Encouracado;
import com.pds.modelo.Mensagem;
import com.pds.modelo.Patrulha;
import com.pds.modelo.PortaAvioes;
import com.pds.modelo.Submarino;
import com.pds.modelo.TAG;
import com.pds.modelo.Tabuleiro;
import com.pds.visao.TelaInicial;
import com.pds.visao.TelaJogo;
import com.pds.visao.TelaMontagemTabuleiro;

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
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControladorMontagemTabuleiro implements Initializable, ObservadorTabuleiro {

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
//	@FXML
//	private ComboBox<Integer> cbPosX;
//	@FXML
//	private ComboBox<Integer> cbPosY;
	@FXML
	private ComboBox<String> cbOrientacao;
	@FXML
	private Button btnPronto;
//	@FXML
//	private Button btnInserirEmbarcacao;
	@FXML
	Label qtdPortaAviao;
	@FXML
	Label qtdEncouracado;
	@FXML
	Label qtdSubmarino;
	@FXML
	Label qtdPatrulha;

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

		ObservableList<String> listaOrientacao = FXCollections.observableArrayList("Horizontal",
				"Vertical");

		cbOrientacao.setItems(listaOrientacao);
		cbOrientacao.getSelectionModel().select(0);

		atualizarTabuleiro();

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
					alert.setHeaderText("Ainda faltam embarcacoes no tabuleiro!");
					alert.setContentText("Adicione mais " + qtdEmbarcacoesRestantes
							+ "embarcacoes no seu tabuleiro para poder jogar.");
					alert.show();
				} else {

					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setHeaderText("Voce esta pronto para a partida?");
					alert.setContentText(null);

					ButtonType btnSim = new ButtonType("Sim");
					ButtonType btnNao = new ButtonType("Nao", ButtonData.CANCEL_CLOSE);
					alert.getButtonTypes().setAll(btnSim, btnNao);

					Optional<ButtonType> btnSelecionado = alert.showAndWait();
					if (btnSelecionado.isPresent()) {
						if (btnSelecionado.get() == btnSim) {
							String apelido = ctrlComunicacao.getJogador().getApelido();
							String nomePartida = ctrlComunicacao.getPartida().getPartida();
							Mensagem mensagem = new Mensagem.MontadorMensagem(TAG.READY)
									.jogador(apelido).nomePartida(nomePartida).build();
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

	private void acaoBotaoPronto(int posX, int posY) {
		int tamanho = embarcacao_selecionada.tamanho();
		boolean horizontal = cbOrientacao.getValue().equals("Horizontal");

		if (horizontal && posX + tamanho > 10 || !horizontal && posY + tamanho > 10) {
			Alert alert1 = new Alert(AlertType.ERROR);
			alert1.setHeaderText("Embarcacao ultrapassa os limites do tabuleiro");
			alert1.setContentText("Insira a embarcacao em uma posicao valida");
			alert1.show();
			return;
		}

		if (horizontal) {
			for (int i1 = posX; i1 < posX + tamanho; ++i1) {
				if (tabuleiro.getCelulas().get(i1).get(posY).isPreenchido()) {
					Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setHeaderText("Embarcacao ja existente na faixa de celulas escolhida");
					alert2.setContentText("Insira a embarcacao em uma posicaoo valida");
					alert2.show();
					return;
				}
			}
		} else if (!horizontal) {
			for (int i2 = posY; i2 < posY + tamanho; ++i2) {
				if (tabuleiro.getCelulas().get(posX).get(i2).isPreenchido()) {
					Alert alert3 = new Alert(AlertType.ERROR);
					alert3.setHeaderText("Embarcação já existente na faixa de células escolhida");
					alert3.setContentText("Insira a embarcação em uma posição válida");
					alert3.show();
					return;
				}
			}
		}

		switch (embarcacao_selecionada) {
		case PATRULHA:
			if (qtdTotalPatrulhas > 0) {
				Embarcacao patrulha = new Patrulha(tamanho, horizontal, posX, posY, tabuleiro);
				tabuleiro.addEmbarcacao(patrulha);
				patrulha.desenhar();
				--qtdTotalPatrulhas;
				qtdPatrulha.setText(String.valueOf(qtdTotalPatrulhas));
			} else {
				Alert alert4 = new Alert(AlertType.ERROR);
				alert4.setHeaderText("Total de patrulhas ja inserido!");
				alert4.setContentText(null);
				alert4.show();
				return;
			}
			break;
		case SUBMARINO:
			if (qtdTotalSubmarinos > 0) {
				Embarcacao submarino = new Submarino(tamanho, horizontal, posX, posY, tabuleiro);
				tabuleiro.addEmbarcacao(submarino);
				submarino.desenhar();
				--qtdTotalSubmarinos;
				qtdSubmarino.setText(String.valueOf(qtdTotalSubmarinos));
			} else {
				Alert alert5 = new Alert(AlertType.ERROR);
				alert5.setHeaderText("Total de submarinos ja inserido!");
				alert5.setContentText(null);
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
				qtdEncouracado.setText(String.valueOf(qtdTotalEncouracados));
			} else {
				Alert alert6 = new Alert(AlertType.ERROR);
				alert6.setHeaderText("Total de encouracados ja inserido!");
				alert6.setContentText(null);
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
				qtdPortaAviao.setText(String.valueOf(qtdTotalPortaAvioes));
			} else {
				Alert alert7 = new Alert(AlertType.ERROR);
				alert7.setHeaderText("Total de porta-avioes ja inserido!");
				alert7.setContentText(null);
				alert7.show();
			}
			break;
		default:
			Alert alert8 = new Alert(AlertType.WARNING);
			alert8.setHeaderText("Embarcacao nao selecionada!");
			alert8.setContentText("Selecione uma das embarcacoes listadas.");
			alert8.show();
			break;
		}
		atualizarTabuleiro();
	}

	private void atualizarTabuleiro() {
		for (int i = 1; i <= tabuleiro.getTamanho(); ++i) {
			for (int j = 1; j <= tabuleiro.getTamanho(); ++j) {
				ImageView img = new ImageView(
						tabuleiro.getCelulas().get(i - 1).get(j - 1).getImgPath());

				final int posX = i;
				final int posY = j;

				img.setOnMouseMoved(e -> {
					img.setEffect(new DropShadow(20, Color.DARKBLUE));
					img.setScaleX(1.2);
					img.setScaleY(1.2);
				});

				img.setOnMouseExited(e -> {
					img.setEffect(null);
					img.setScaleX(1);
					img.setScaleY(1);
				});

				img.setOnMousePressed(e -> {
					this.acaoBotaoPronto(posX - 1, posY - 1);
				});
				gridTabuleiro.add(img, i, j);
			}
		}
	}

	@Override
	public void criarTelaJogo() {
		Platform.runLater(() -> {
			TelaMontagemTabuleiro.getStage().close();
			EfeitosSonoros.pararSomInicio();
			TelaJogo telaJogo = new TelaJogo(tabuleiro);
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
