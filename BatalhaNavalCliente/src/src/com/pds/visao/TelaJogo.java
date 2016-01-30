package com.pds.visao;

import com.pds.controle.ControladorJogo;
import com.pds.modelo.Tabuleiro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaJogo extends Application  {

	private static Stage stage;
	private Tabuleiro tabuleiro;
	
	public TelaJogo(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		FXMLLoader fx = new FXMLLoader(getClass().getResource("TelaJogo.fxml"));
		Parent root = fx.load();
				
		ControladorJogo controladorJogo = fx.getController();
		controladorJogo.setTabuleiroMeu(tabuleiro);
		
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.setTitle("Jogando");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static Stage getStage() {

		return stage;
	}

}
