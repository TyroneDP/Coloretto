package main;



import gui.ColorettoApplicatie;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gui.WelkomScherm;

public class StartUp extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		WelkomScherm root = new WelkomScherm();
		// We maken een Scene op basis van het hoofdpaneel en een gewenste grootte
		Scene scene = new Scene(root, 500, 300);

		// We koppelen de Scene aan de Stage (het venster).        
		primaryStage.setScene(scene);

		// We geven een titel aan het venster en tonen we het op het scherm.   
		primaryStage.setTitle("Welkom bij Coloretto");
		primaryStage.show();

	}

	public static void main(String[] args) 
	{
		new ColorettoApplicatie().start();
	}

}
