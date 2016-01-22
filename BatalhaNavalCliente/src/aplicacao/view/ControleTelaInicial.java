package aplicacao.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

public class ControleTelaInicial implements Initializable {

	@FXML Button btnIniciarPartida;
	@FXML Button btnCriarPartida;
	@FXML Button btnAtualizar;
	@FXML ListView<String> listaPartidas;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	@FXML
	public void iniciarPartida(ActionEvent event) {

	}

	@FXML
	public void criarPartida(ActionEvent event) {

		TextInputDialog criarPartida = new TextInputDialog();
		criarPartida.setTitle("Criar nova partida");
		criarPartida.setHeaderText("Digite o seu Nick:");

		Optional<String> nick = criarPartida.showAndWait();
	}

	@FXML
	public void atualizar(ActionEvent event) {

	}

}
