package aplicacao.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaJogo extends Application  {

	private static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("TelaJogo.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.setTitle("Jogando");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {

		launch(args);
	}

	public static Stage getStage() {

		return stage;
	}

}
