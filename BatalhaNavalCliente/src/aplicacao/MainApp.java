
package aplicacao;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent = FXMLLoader.load(this.getClass().getResource("view/TelaMontagemTabuleiro.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Tela MontagemTabuleiro");
		stage.setResizable(false);
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}
}
