package main;

import domein.Spel;
import gui.WelkomScherm;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StartUpGui extends Application
{
	
	
	@Override
	public void start(Stage stage)
	{
		Spel spel = new Spel();
		WelkomScherm ws = new WelkomScherm(spel);

		// We maken een Scene op basis van het hoofdpaneel en een gewenste grootte
		Scene scene = new Scene(ws,1000,500);
		
		//css toevoegen
		scene.getStylesheets().add(getClass().getResource("/css/OnzeCss.css").toExternalForm());  

		// We koppelen de Scene aan de Stage (het venster).        
		stage.setScene(scene);
		stage.setTitle("Coloretto");
		stage.show();
		stage.setMaximized(true);
		//stage.setFullScreen(true);
			
	}

	public static void main(String[] args) 
	{
		
		launch(args);
	}

}
