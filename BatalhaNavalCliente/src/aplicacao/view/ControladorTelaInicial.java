package aplicacao.view;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;

import aplicacao.comunicacao.ControladorComunicacao;
import aplicacao.model.Interpretador;
import aplicacao.model.InterpretadorMensagem;
import aplicacao.model.Jogador;
import aplicacao.model.Mensagem;
import aplicacao.model.Partida;
import aplicacao.model.TAG;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
	@FXML MenuItem iniciarPartida;
	
	public static MediaPlayer mediaSomAmbiente;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

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
		
		Media somAmbiente = new Media(Paths.get("som_inicio.wav").toUri().toString());
		mediaSomAmbiente = new MediaPlayer(somAmbiente);
		mediaSomAmbiente.setCycleCount(MediaPlayer.INDEFINITE);
		mediaSomAmbiente.setVolume(0.3);
		mediaSomAmbiente.play();
		
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
		esperaJogador();
		atualizarLista();
	
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
		mediaSomAmbiente.stop();
		ctrlComunicacao.fechar();
		TelaInicial.getStage().close();
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
	public void atualizarPartida(Partida partida) {

		partidas.add(partida);
	}

	private void esperaJogador() {

		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle("Esperando um jogador");
		alerta.setHeaderText("Esperando um jogador se conectar na sua partida");
		alerta.setContentText(null);
		alerta.show();
	}
	
	@Override
	public void conectarJogo() {
		Platform.runLater(() -> {
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
