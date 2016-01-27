package aplicacao.view;

import java.net.URL;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ControladorJogo implements Initializable, ObservadorJogo {

	private ControladorComunicacao ctrlcomunicacao;
	private Jogada ultimaJogada;

	@FXML
	GridPane gridTabuleiroInimigo;
	@FXML
	ImageView imgVez;
	@FXML
	GridPane gridTabuleiroMeu;
	private Tabuleiro tabuleiroInimigo;
	private Tabuleiro tabuleiroMeu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ctrlcomunicacao = ControladorTelaInicial.ctrlComunicacao;
		System.out.println("observerJogo");
		ctrlcomunicacao.getInterpretador().setObserverJogo(this);

		tabuleiroInimigo = new Tabuleiro(10);
		tabuleiroMeu = ComunicaoTelaMontagemTelaJogo.tabuleiro;

		atualizarTabuleiro(tabuleiroInimigo, gridTabuleiroMeu);
		atualizarTabuleiro(tabuleiroMeu, gridTabuleiroMeu);
		atualizarTabuleiro(tabuleiroInimigo, gridTabuleiroInimigo);

	}

	private void atualizarTabuleiro(Tabuleiro tabuleiro, GridPane grid) {

		for (int i = 1; i <= tabuleiro.getTamanho(); ++i) {
			for (int j = 1; j <= tabuleiro.getTamanho(); ++j) {

				ImageView node = new ImageView(
						tabuleiro.getCelulas().get(i - 1).get(j - 1).getImgPath());
				final int posX = i;
				final int posY = j;
				
				node.setOnMouseMoved(e -> {
					node.setEffect(new DropShadow(20, Color.HOTPINK));
				});
				
				node.setOnMouseExited(e -> {
					node.setEffect(null);
				});
				
				node.setOnMousePressed(e -> {
					Jogada jogada = new Jogada(posX, posY);
					this.ultimaJogada = jogada;
					String apelidoJogador = ctrlcomunicacao.getJogador().getApelido();
					String nomePartida = ctrlcomunicacao.getPartida().getPartida();
					Mensagem msg = new Mensagem.MontadorMensagem(TAG.MOVEGAME).jogada(jogada).jogador(apelidoJogador).nomePartida(nomePartida).build();
					ctrlcomunicacao.enviarMensagem(msg);
				});

				grid.add(node, i, j);
			}
		}
	}

	@Override
	public void setJogoComoConvidado() {

		gridTabuleiroInimigo.setDisable(true);
		imgVez.setImage(new Image("/img/seta_vez_inimigo.png"));
	}

	@Override
	public void setJogoComoCriador() {

		imgVez.setImage(new Image("/img/seta_sua_vez.png"));
	}

	@Override
	public void exibeResultadoJogada(String imgPath) {
		System.out.println(ultimaJogada.getPosX()+ "   -->   "+ ultimaJogada.getPosY());
		Platform.runLater(() -> gridTabuleiroInimigo.add(new ImageView(imgPath), ultimaJogada.getPosX(), ultimaJogada.getPosY()));
	}

	@Override
	public void desconectar() {

		// TODO Auto-generated method stub
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
						String apelidoJogador = ctrlcomunicacao.getJogador().getApelido();
						String nomePartida = ctrlcomunicacao.getPartida().getPartida();
						Mensagem mensagem = new Mensagem.MontadorMensagem(TAG.RESULT).imgPath("/img/embarcacao_destruida.png")
								.jogador(apelidoJogador).nomePartida(nomePartida).build();
						ctrlcomunicacao.enviarMensagem(mensagem);
					}
					embarcacaoAtingida = true;
				}
			}
		}
		if (!embarcacaoAtingida) {
			celulaAtingida.setImgPath("/img/bomba.png");
			String apelidoJogador = ctrlcomunicacao.getJogador().getApelido();
			String nomePartida = ctrlcomunicacao.getPartida().getPartida();
			Mensagem mensagem = new Mensagem.MontadorMensagem(TAG.RESULT).imgPath("/img/bomba.png")
					.jogador(apelidoJogador).nomePartida(nomePartida).build();
			ctrlcomunicacao.enviarMensagem(mensagem);
		}
	}

}
