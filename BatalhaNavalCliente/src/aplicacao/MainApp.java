package aplicacao;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Batalha Naval Multiplayer");
		initRootLayout ();
		showTelaServidoresOverview ();
	}
	
	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showTelaServidoresOverview() {
		try {
			FXMLLoader loader = new FXMLLoader ();
			loader.setLocation(MainApp.class.getResource("view/TelaServidoresOverview.fxml"));
			AnchorPane telaServidoresOverview = (AnchorPane) loader.load();
			rootLayout.setCenter(telaServidoresOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage () {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
