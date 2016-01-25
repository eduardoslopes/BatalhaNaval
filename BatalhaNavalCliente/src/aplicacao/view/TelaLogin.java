package aplicacao.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaLogin extends Application {

	private static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;

		Parent root = FXMLLoader.load(this.getClass().getResource("TelaLogin.fxml"));
		Scene cena = new Scene(root, 240, 310);

		primaryStage.setScene(cena);
		primaryStage.setTitle("Entrar");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static Stage getStage() {

		return stage;
	}
	
	public static void main(String[] args) {

		launch(args);
	}

}
