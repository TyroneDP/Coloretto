package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import domein.Spel;
import domein.Speler;
import domein.Stapel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SpeelScherm extends BorderPane
{
	//in deze klasse alle methodes schrijven dat verband hebben met domeinlaag klasse Spel

	private Spel spel;
	
	private Stapel stapel;	
	
	private Button afsluiten = new Button("Spel afsluiten");
	
	private HBox afsluitenbox = new HBox(20);
	
	//private List<Speler> spelers = spel.getSpelers();
	
	 
	
	

	public SpeelScherm(Spel spel)
	{
		this.spel = spel;
		maakStapel();
		BuildSpeelSchermGui();
	}
	
	//eerst images aan stapel linken, gaat niet zonder try/catch
	private void maakStapel()
	{
		
		HashMap<String, Image> kaarten = new HashMap<String, Image>();
		
		try
		{
			kaarten.put("Blauw", new Image(new FileInputStream("src/images/Blauw.png")));
			
			kaarten.put("Bruin", new Image(new FileInputStream("src/images/Bruin.png")));
			
			kaarten.put("Geel", new Image(new FileInputStream("src/images/Geel.png")));
			
			kaarten.put("Groen", new Image(new FileInputStream("src/images/Groen.png")));
			
			kaarten.put("Joker", new Image(new FileInputStream("src/images/Joker.jpg")));
			
			kaarten.put("Oranje", new Image(new FileInputStream("src/images/Oranje.png")));
			
			kaarten.put("Paars", new Image(new FileInputStream("src/images/Paars.png")));
			
			kaarten.put("Rood", new Image(new FileInputStream("src/images/Rood.png")));
			
			kaarten.put("Plus2", new Image(new FileInputStream("src/images/PlusTwee.jpg")));
			
			kaarten.put("Achterkant", new Image(new FileInputStream("src/images/Achterkant.png")));
		}
		catch (FileNotFoundException exception)
		{
			Alert waarschuwing = new Alert(AlertType.ERROR);
			waarschuwing.setTitle("Image van kaart niet gevonden");
			waarschuwing.setHeaderText(null);
			waarschuwing.setContentText("Kon de file niet vinden in /src/images");
			waarschuwing.showAndWait();
		}
		
	}

	private void BuildSpeelSchermGui()
	{	
		//afsluiten button & box
		afsluitenbox.getChildren().addAll(afsluiten);
		afsluitenbox.setAlignment(Pos.BOTTOM_CENTER);
		afsluitenbox.setPadding(new Insets(-100, 0, 0, 0));
		setRight(afsluitenbox);

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Event handlers

		//als ik op sluiten druk
		afsluiten.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent evt) 
			{
				Alert notify = new Alert(AlertType.CONFIRMATION);
				notify.setTitle("Afsluiten");
				notify.setHeaderText("Wenst u de applicatie af te sluiten?");


				Optional<ButtonType> result = notify.showAndWait();
				if(result.get()== ButtonType.OK)
				{
					System.exit(0);
				}
			}
		});


	}
	
	
	
}
