package aplicacao.view;

import java.net.URL;
import java.util.ResourceBundle;

import aplicacao.Mensagem;
import aplicacao.comunicacao.ControladorComunicacao;
import aplicacao.model.Jogador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControleTelaLogin implements Initializable {
	
	private static ControladorComunicacao ctrlComunicacao;

	@FXML Button btnEntrar;
	@FXML Button btnCancelar;
	@FXML TextField txNick;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ctrlComunicacao = new ControladorComunicacao();
		
		TelaLogin.getStage().setOnCloseRequest(event -> {
			sair(new ActionEvent());
		});
	}

	@FXML
	public void entrar(ActionEvent event) {		//<<<---------------------------->>>
		String apelido = txNick.getText();
		if (apelido.equals("")) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Erro");
			alerta.setHeaderText(null);
			alerta.setContentText("Para continuar e preciso inserir seu apelido!");
			alerta.show();
		} else {
			Jogador jogador = new Jogador(apelido);
			Mensagem mensagem = new Mensagem(jogador);
			
			try {
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.start(new Stage());
				TelaLogin.getStage().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ctrlComunicacao.enviarMensagem(mensagem);
		}
	}

	@FXML
	public void sair(ActionEvent event) {
		ctrlComunicacao.fechar();
		TelaLogin.getStage().close();
	}

	
	public static ControladorComunicacao getCtrlComunicacao() {

		return ctrlComunicacao;
	}

}
