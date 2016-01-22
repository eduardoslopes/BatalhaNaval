package aplicacao.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaInicial extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent tela = FXMLLoader.load(this.getClass().getResource("TelaInicial.fxml"));
		Scene cena = new Scene(tela);
		
		primaryStage.setScene(cena);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Batalha Naval");
		primaryStage.show();
	}
	
	public static void main(String[] args) {

		launch(args);
	}

}
