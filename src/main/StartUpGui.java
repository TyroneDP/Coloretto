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
		Scene scene = new Scene(ws);
		
        //zorgen dat stage de grootte van scherm inneemt 
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		stage .setX(primaryScreenBounds.getMinX());
		stage.setY(primaryScreenBounds.getMinY());
		stage.setWidth(primaryScreenBounds.getWidth());
		stage.setHeight(primaryScreenBounds.getHeight());

		// We koppelen de Scene aan de Stage (het venster).        
		stage.setScene(scene);
		stage.setTitle("Coloretto");
		stage.show();

		
	}

	public static void main(String[] args) 
	{
		launch(args);
	}

}
