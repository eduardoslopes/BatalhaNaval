package aplicacao.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Wanderson
 *
 */
public class TelaInicial extends Application {

	private static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		Parent tela = FXMLLoader.load(this.getClass().getResource("TelaInicial.fxml"));
		Scene cena = new Scene(tela, 350, 490);
		primaryStage.setScene(cena);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Batalha Naval");
		primaryStage.show();
	}

	public static void main(String[] args) {

		launch(args);
	}

	public static Stage getStage() {

		return stage;
	}

}
