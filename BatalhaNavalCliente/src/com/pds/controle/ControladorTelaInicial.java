package com.pds.controle;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.pds.comunicacao.ControladorComunicacao;
import com.pds.modelo.EfeitosSonoros;
import com.pds.modelo.Jogador;
import com.pds.modelo.Mensagem;
import com.pds.modelo.Partida;
import com.pds.modelo.TAG;
import com.pds.visao.TelaInicial;
import com.pds.visao.TelaMontagemTabuleiro;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControladorTelaInicial implements Initializable, ObservadorPartida {

	public static ControladorComunicacao ctrlComunicacao;
	
	private ObservableList<Partida> partidas;
	
	@FXML AnchorPane painel;
	@FXML Button btnIniciarPartida;
	@FXML Button btnCriarPartida;
	@FXML Button btnAtualizar;
	@FXML TableView<Partida> listaPartidas;
	@FXML TableColumn<Partida, String> colunaJogador;
	@FXML TableColumn<Partida, String> colunaPartida;
	@FXML ContextMenu contextoLista;
	
	private Alert alertaEsperaJogador;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EfeitosSonoros.pararSomJogo();
		EfeitosSonoros.tocarSomInicio();
		ctrlComunicacao = new ControladorComunicacao();
		Interpretador interpretador = new InterpretadorMensagem();
		interpretador.setObserverPartida(this);
		ctrlComunicacao.setInterpretador(interpretador);
		
		Mensagem mensagem = new Mensagem.MontadorMensagem(TAG.SEEGAMES).build();
		ctrlComunicacao.enviarMensagem(mensagem);
		
		colunaJogador.setCellValueFactory(new PropertyValueFactory<>("jogador"));
		colunaPartida.setCellValueFactory(new PropertyValueFactory<>("partida"));
		
		partidas = FXCollections.observableArrayList();
		listaPartidas.setItems(partidas);
		
		TelaInicial.getStage().setOnCloseRequest(e -> {
			sair();
		});
		
	}

	@FXML
	public void iniciarPartida(ActionEvent event) {

		SelectionModel<Partida> selectionModel = listaPartidas.getSelectionModel();
		Partida partidaSelecionada = selectionModel.getSelectedItem();
		if (partidaSelecionada == null) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setHeaderText(null);
			alerta.setContentText("Selecione uma partida da lista");
			alerta.show();
		} else {
			String apelido;
			
			do {
				apelido = capturarApelido();
				if (apelido == null) return;
			} while (partidaSelecionada.getJogador().equals(apelido));
			
			ctrlComunicacao.setJogador(new Jogador(apelido));
			ctrlComunicacao.setPartida(partidaSelecionada);
			
			Mensagem mensagem = new Mensagem.MontadorMensagem(TAG.CONNECTGAME).jogador(apelido)
					.nomePartida(partidaSelecionada.getPartida()).build();
			ctrlComunicacao.enviarMensagem(mensagem);
		}
		
	}

	@FXML
	public void criarPartida() {
		
		String apelido = capturarApelido();
		String nomePartida;
		if (apelido == null) return;
		do {
			nomePartida = capturarNomePartida();
			if (nomePartida == null) return;
		} while (existePartida(nomePartida));
			
		Jogador jogador = new Jogador(apelido);
		ctrlComunicacao.setJogador(jogador);
		ctrlComunicacao.setPartida(new Partida(nomePartida, jogador));

		Mensagem mensagem = new Mensagem.MontadorMensagem(TAG.CREATEGAME).nomePartida(nomePartida)
				.jogador(apelido).build();
		ctrlComunicacao.enviarMensagem(mensagem);
		listaPartidas.setDisable(false);
		esperaJogador();
	}

	private boolean existePartida(String nomePartida) {
		for (Partida partida : partidas) {
			if (nomePartida.equals(partida.getPartida()))
				return true;
		}
		return false;
	}

	private String capturarNomePartida() {
		TextInputDialog dialogo = new TextInputDialog();
		dialogo.setTitle("Nova Partida");
		dialogo.setHeaderText("Digite um nome para a partida");
		dialogo.setContentText(null);
		Optional<String> partida = dialogo.showAndWait();
		if (partida.isPresent()) {
			if (!partida.get().equals("")) {
				return partida.get();
			} else {
				return capturarNomePartida();
			}
		}
		return null;
	}

	@FXML
	public void atualizarLista() { 
		partidas.removeAll(partidas);
		Mensagem msg = new Mensagem.MontadorMensagem(TAG.SEEGAMES).build();
		ctrlComunicacao.enviarMensagem(msg);
	}

	@FXML
	public void sair() {
		Platform.runLater(() -> {
			
			TelaInicial.getStage().close();

			try {
				Thread.sleep(1000);
				ctrlComunicacao.fechar();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
	
	public String capturarApelido() {
		TextInputDialog dialogo = new TextInputDialog();
		dialogo.setTitle("Digitar Apelido");
		dialogo.setHeaderText("Digite o seu apelido:");
		dialogo.setContentText(null);
		Optional<String> apelido = dialogo.showAndWait();
		if (apelido.isPresent()) {
			if (!apelido.get().equals("")) {
				return apelido.get();
			} else {
				return capturarApelido();
			}
		}
		return null;
	}

	public ObservableList<Partida> getPartidas() {
		return partidas;
	}

	@Override
	public void atualizarPartidaLista(Partida partida) {
		partidas.add(partida);
	}

	private void esperaJogador() {
		alertaEsperaJogador = new Alert(AlertType.INFORMATION);
		alertaEsperaJogador.setTitle("Esperando um jogador");
		alertaEsperaJogador.setHeaderText("Esperando um jogador se conectar na sua partida");
		alertaEsperaJogador.setContentText(null);
		alertaEsperaJogador.show();
	}
	
	@Override
	public void conectarJogo() {
		Platform.runLater(() -> {
			if (alertaEsperaJogador != null)
				alertaEsperaJogador.close();
			TelaInicial.getStage().close();
			TelaMontagemTabuleiro telaMontagem = new TelaMontagemTabuleiro();
			try {
				telaMontagem.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
