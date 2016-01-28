package aplicacao.view;

import java.net.URL;
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
import javafx.stage.Stage;

public class ControladorTelaInicial implements Initializable, ObservadorPartida {

	public static ControladorComunicacao ctrlComunicacao ;
	
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
			String apelido = capturarApelido();
			if (apelido == null) return;
			
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
		if (apelido == null) return;

		TextInputDialog dialogo = new TextInputDialog();
		dialogo.setTitle("Nova Partida");
		dialogo.setHeaderText("Digite um nome para a partida");
		dialogo.setContentText(null);
		Optional<String> partida = dialogo.showAndWait();
		
		if (partida.isPresent() && !partida.get().equals("")) {			
			String nome = partida.get();
			
			Jogador jogador = new Jogador(apelido);
			ctrlComunicacao.setJogador(jogador);
			ctrlComunicacao.setPartida(new Partida(nome, jogador));
			
			Mensagem mensagem = new Mensagem.MontadorMensagem(TAG.CREATEGAME).nomePartida(nome)
					.jogador(apelido).build();
			ctrlComunicacao.enviarMensagem(mensagem);
			esperaJogador();
			atualizarLista();
		}		
	}

	@FXML
	public void atualizarLista() { 
		
		partidas.removeAll(partidas);
		Mensagem msg = new Mensagem.MontadorMensagem(TAG.SEEGAMES).build();
		ctrlComunicacao.enviarMensagem(msg);
	}

	@FXML
	public void sair() {

		ctrlComunicacao.fechar();
		TelaInicial.getStage().close();
	}
	
	public String capturarApelido() {

		TextInputDialog dialogo = new TextInputDialog();
		dialogo.setTitle("Digitar Apelido");
		dialogo.setHeaderText("Digite o seu apelido:");
		dialogo.setContentText(null);
		Optional<String> partida = dialogo.showAndWait();
		if (partida.isPresent()) {
			if (!partida.get().equals("")) {
				return partida.get();
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
