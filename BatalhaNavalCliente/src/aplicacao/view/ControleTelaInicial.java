package aplicacao.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import aplicacao.Mensagem;
import aplicacao.comunicacao.ControladorComunicacao;
import aplicacao.model.Jogador;
import aplicacao.model.Partida;
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

/**
 * @author Wanderson
 *
 */
public class ControleTelaInicial implements Initializable {

	private ControladorComunicacao ctrlComunicacao;
	
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

		ctrlComunicacao = ControleTelaLogin.getCtrlComunicacao();
		partidas = FXCollections.observableArrayList();
		
		colunaJogador.setCellValueFactory(new PropertyValueFactory<>("jogador"));
		colunaPartida.setCellValueFactory(new PropertyValueFactory<>("partida"));
		
		listaPartidas.setItems(partidas);

		Partida p = new Partida("sfsfs", new Jogador("Wanderson"));	//<<<---------------------------->>>
		partidas.add(p);
		
		TelaInicial.getStage().setOnCloseRequest(event -> {
			sair();
		});		
	}

	@FXML
	public void iniciarPartida(ActionEvent event) { //<<<----------------------------->>>

		SelectionModel<Partida> selectionModel = listaPartidas.getSelectionModel();
		Partida partidaSelecionada = selectionModel.getSelectedItem();
		if (partidaSelecionada == null) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setHeaderText(null);
			alerta.setContentText("Selecione uma partida da lista");
			alerta.show();
		} else {
			Mensagem mensagem = new Mensagem(partidaSelecionada);
			ctrlComunicacao.enviarMensagem(mensagem);
		}
	}

	@FXML
	public void criarPartida() { //<<<----------------------------->>>
		
		TextInputDialog dialogo = new TextInputDialog();
		dialogo.setTitle("Nova Partida");
		dialogo.setHeaderText("Digite um nome para a partida");
		dialogo.setContentText(null);
		Optional<String> partida = dialogo.showAndWait();
		
		if (partida.isPresent() && !partida.get().equals("")) {
						
			String nome = partida.get();
			
			partidas.add(new Partida(nome, new Jogador("eu")));
			
		}
		
	}

	@FXML
	public void atualizarLista() {	//<<<----------------------------->>>

		Mensagem msg = new Mensagem(TAG.ATUALIZAR_LISTA);
		ctrlComunicacao.enviarMensagem(msg);
	}

	@FXML
	public void sair() {

		ctrlComunicacao.fechar();
		TelaInicial.getStage().close();
	}
	
	
	public ObservableList<Partida> getPartidas() {

		return partidas;
	}

}
