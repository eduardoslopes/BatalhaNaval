package com.pds.modelo;

import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class EfeitosSonoros {
	private static MediaPlayer mediaSomAmbiente;
	private static MediaPlayer mediaSomInicio;

	public static void tocarSomVitoria() {
		Media somVitoria = new Media(Paths.get("src", "sound", "som_vitoria.wav").toUri().toString());
		MediaPlayer mediaSomVitoria = new MediaPlayer(somVitoria);
		mediaSomVitoria.setVolume(0.7);
		mediaSomVitoria.play();
	}

	public static void tocarSomDerrota() {
		Media somDerrota = new Media(Paths.get("src", "sound", "som_derrota.wav").toUri().toString());
		MediaPlayer mediaSomDerrota = new MediaPlayer(somDerrota);
		mediaSomDerrota.setVolume(1);
		mediaSomDerrota.play();
	}

	public static void tocarSomBomba() {
		Media somBomba = new Media(Paths.get("src", "sound", "som_bomba.wav").toUri().toString());
		MediaPlayer mediaSomBomba = new MediaPlayer(somBomba);
		mediaSomBomba.setVolume(1);
		mediaSomBomba.play();
	}

	public static void tocarSomJogo() {
		Media somAmbiente = new Media(Paths.get("src", "sound", "som_jogo.wav").toUri().toString());
		mediaSomAmbiente = new MediaPlayer(somAmbiente);
		mediaSomAmbiente.setCycleCount(MediaPlayer.INDEFINITE);
		mediaSomAmbiente.setVolume(0.3);
		mediaSomAmbiente.play();
	}
	
	public static void pararSomJogo() {
		mediaSomAmbiente.stop();
	}
	
	public static void tocarSomInicio(){
		Media somAmbiente = new Media(Paths.get("src", "sound", "som_inicio.wav").toUri().toString());
		mediaSomInicio = new MediaPlayer(somAmbiente);
		mediaSomInicio.setCycleCount(MediaPlayer.INDEFINITE);
		mediaSomInicio.setVolume(0.3);
		mediaSomInicio.play();
	}
	
	public static void pararSomInicio() {
		mediaSomInicio.stop();
	}
	
}
