package aplicacao.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaMontagemTabuleiro extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Distribua suas embarca��es!");
		Parent root = FXMLLoader.load(this.getClass().getResource("TelaMontagemTabuleiro.fxml"));
		Scene scene = new Scene(root);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
