package aplicacao.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import aplicacao.comunicacao.ControladorComunicacao;
import aplicacao.model.Celula;
import aplicacao.model.Embarcacao;
import aplicacao.model.Jogada;
import aplicacao.model.Mensagem;
import aplicacao.model.TAG;
import aplicacao.model.Tabuleiro;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControladorJogo implements Initializable, ObservadorJogo {

	private ControladorComunicacao ctrlcomunicacao;
	private Jogada ultimaJogada;
	private String apelidoJogador;
	private String nomePartida;

	@FXML
	GridPane gridTabuleiroInimigo;
	@FXML
	ImageView imgVez;
	@FXML
	Button btnDesistir;
	@FXML
	GridPane gridTabuleiroMeu;
	private Tabuleiro tabuleiroInimigo;
	private Tabuleiro tabuleiroMeu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ctrlcomunicacao = ControladorTelaInicial.ctrlComunicacao;
		ctrlcomunicacao.getInterpretador().setObserverJogo(this);

		apelidoJogador = ctrlcomunicacao.getJogador().getApelido();
		nomePartida = ctrlcomunicacao.getPartida().getPartida();
		
		tabuleiroInimigo = new Tabuleiro(10);
		tabuleiroMeu = ComunicaoTelaMontagemTelaJogo.tabuleiro;

		atualizarTabuleiroInimigo();
		inserirFundoMar(tabuleiroMeu, gridTabuleiroMeu);
		atualizarTabuleiroMeu();
		
		TelaJogo.getStage().setOnCloseRequest(e -> {
			this.desistirJogo();
		});

	}
	
	private void habilitaVezOponente(){
		gridTabuleiroInimigo.setDisable(true);
		imgVez.setImage(new Image("/img/seta_vez_inimigo.png"));
	}
	
	private void habilitaSuaVez(){
		gridTabuleiroInimigo.setDisable(false);
		imgVez.setImage(new Image("/img/seta_sua_vez.png"));
	}

	@Override
	public void setJogoComoConvidado() {

		habilitaVezOponente();
	}

	@Override
	public void setJogoComoCriador() {

		habilitaSuaVez();
	}

	@Override
	public void exibeResultadoJogada(String imgPath) {
		Platform.runLater(() -> {
			gridTabuleiroInimigo.add(new ImageView(imgPath), ultimaJogada.getPosX() + 1, ultimaJogada.getPosY() + 1);
		});
	}

	@Override
	public void desconectar() {
		Platform.runLater(() -> {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setHeaderText("Oponente desistiu do jogo!");
			alerta.setContentText(null);
			alerta.show();
			
			TelaJogo.getStage().close();		
			TelaInicial telaInicial = new TelaInicial();
			try {
				telaInicial.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	@Override
	public void novaJogada (Jogada jogada) {
		this.habilitaSuaVez();
		boolean embarcacaoAtingida = false;
		Celula celulaAtingida = tabuleiroMeu.getCelulas().get(jogada.getPosX()).get(jogada.getPosY());
		celulaAtingida.setAtingido(true);
		for (Embarcacao embarcacao : tabuleiroMeu.getEmbarcacoes()) {
			for (Celula celula : embarcacao.getCelulas()) {
				if (celula.equals(celulaAtingida)) {

					celula.setImgPath("/img/embarcacao_destruida.png");
					Mensagem mensagem = new Mensagem.MontadorMensagem(TAG.RESULT)
							.imgPath("/img/embarcacao_destruida.png").jogador(apelidoJogador)
							.nomePartida(nomePartida).build();
					ctrlcomunicacao.enviarMensagem(mensagem);

					if (embarcacao.isDestruida()) {
						embarcacao.desenharDestruida();
						
						int posX = embarcacao.getPosX();
						int posY = embarcacao.getPosY();
						Jogada j;
						
						for (Celula c : embarcacao.getCelulas()) {
							
							if (embarcacao.isHorizontal()) {
								j = new Jogada(posX++, posY);
							} else {
								j = new Jogada(posX, posY++);
								
							}
							Mensagem msg = new Mensagem.MontadorMensagem(TAG.DESTROYED)
									.jogada(j).jogador(apelidoJogador).nomePartida(nomePartida).imgPath(c.getImgPath()).build();
							ctrlcomunicacao.enviarMensagem(msg);
						}
					}
					embarcacaoAtingida = true;
				}
			}
			this.habilitaSuaVez();			
		}
		if (!embarcacaoAtingida) {
			celulaAtingida.setImgPath("/img/bomba.png");
			Mensagem mensagem = new Mensagem.MontadorMensagem(TAG.RESULT).imgPath("/img/bomba.png")
					.jogador(apelidoJogador).nomePartida(nomePartida).build();
			ctrlcomunicacao.enviarMensagem(mensagem);
		}
		atualizarTabuleiroMeu();
		if(tabuleiroMeu.todasEmbarcacoesForamDestruidas()){
			Mensagem msgPerdi = new Mensagem.MontadorMensagem(TAG.LOSTGAME).jogador(apelidoJogador).nomePartida(nomePartida).build();
			ctrlcomunicacao.enviarMensagem(msgPerdi);
			Platform.runLater(() -> {
				perdeu();				
			});
		}
	}
	
	@Override
	public void embarcacaoAfundada(int posX, int posY, String imgPath) {
		
		Platform.runLater(() -> {
			gridTabuleiroInimigo.add(new ImageView("/img/mar.png"), posX + 1, posY + 1);
			gridTabuleiroInimigo.add(new ImageView(imgPath), posX + 1, posY + 1);
		});
	}

	@Override
	public void ganhou() {
		Platform.runLater(() -> {	
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Ganhou");
			alert.setHeaderText("Voc� ganhou!!!");
			alert.setContentText("Deseja jogar novamente?");
			
			ButtonType btnSim = new ButtonType("Sim");
			ButtonType btnNao = new ButtonType("Nao", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(btnSim, btnNao);
			
			Optional<ButtonType> btnSelecionado = alert.showAndWait();
			if (btnSelecionado.isPresent()) {
				if (btnSelecionado.get() == btnSim) {
					
					ctrlcomunicacao.fechar();
					TelaJogo.getStage().close();
					
					Platform.runLater(() -> {
						TelaInicial telaInicial = new TelaInicial();
						try {
							telaInicial.start(new Stage());
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
				}
				TelaJogo.getStage().close();				
			}
		});
	}
	
	public void perdeu() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Perdeu");
		alert.setHeaderText("Voc� perdeu!!!");
		alert.setContentText("Deseja jogar novamente?");
		
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Nao", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(btnSim, btnNao);
		
		Optional<ButtonType> btnSelecionado = alert.showAndWait();
		if (btnSelecionado.isPresent()) {
			if (btnSelecionado.get() == btnSim) {
				
				TelaJogo.getStage().close();
				
				Platform.runLater(() -> {
					TelaInicial telaInicial = new TelaInicial();
					try {
						telaInicial.start(new Stage());
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
			ctrlcomunicacao.fechar();
			TelaJogo.getStage().close();				
		}
	}
	
	@FXML
	public void desistirJogo() {
		Platform.runLater(() -> {
			
			TelaJogo.getStage().close();
			Mensagem msg = new Mensagem.MontadorMensagem(TAG.DISCONNECTGAME).jogador(apelidoJogador)
					.nomePartida(nomePartida).build();
			ctrlcomunicacao.enviarMensagem(msg);
			try {
				Thread.sleep(4000);
				ctrlcomunicacao.fechar();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
	
	private void atualizarTabuleiroInimigo() {

		for (int i = 1; i <= tabuleiroInimigo.getTamanho(); ++i) {
			for (int j = 1; j <= tabuleiroInimigo.getTamanho(); ++j) {

				ImageView node = new ImageView(
						tabuleiroInimigo.getCelulas().get(i - 1).get(j - 1).getImgPath());
				final int posX = i;
				final int posY = j;
				
				node.setOnMouseMoved(e -> {
					node.setEffect(new DropShadow(20, Color.DARKBLUE));
				});
				
				node.setOnMouseExited(e -> {
					node.setEffect(null);
				});
				
				node.setOnMousePressed(e -> {
					Jogada jogada = new Jogada(posX - 1, posY - 1);
					this.ultimaJogada = jogada;
					String apelidoJogador = ctrlcomunicacao.getJogador().getApelido();
					String nomePartida = ctrlcomunicacao.getPartida().getPartida();
					Mensagem msg = new Mensagem.MontadorMensagem(TAG.MOVEGAME).jogada(jogada)
							.jogador(apelidoJogador).nomePartida(nomePartida).build();
					ctrlcomunicacao.enviarMensagem(msg);
					
					this.habilitaVezOponente();
				});
				Platform.runLater(() -> {
					this.gridTabuleiroInimigo.add(node, posX, posY);
				});
			}
		}
	}
	
	private void inserirFundoMar(Tabuleiro tabuleiro, GridPane grid){
		for (int i = 1; i <= tabuleiro.getTamanho(); ++i) {
			for (int j = 1; j <= tabuleiro.getTamanho(); ++j) {
				ImageView node = new ImageView("/img/mar.png");
				final int posX = i;
				final int posY = j;
				Platform.runLater(() -> {
					grid.add(node, posX, posY);
				});
			}
		}
	}
	
	private void atualizarTabuleiroMeu() {
		for (int i = 1; i <= tabuleiroMeu.getTamanho(); ++i) {
			for (int j = 1; j <= tabuleiroMeu.getTamanho(); ++j) {

				ImageView node = new ImageView(
						tabuleiroMeu.getCelulas().get(i - 1).get(j - 1).getImgPath());
				final int posX = i;
				final int posY = j;
				Platform.runLater(() -> {
					this.gridTabuleiroMeu.add(node, posX, posY);
				});
			}
		}
	}


}