package gui;

import java.util.Optional;

import domein.Spel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SpeelScherm extends BorderPane
{
	//in deze klasse alle methodes schrijven dat verband hebben met domeinlaag klasse Spel

	private Spel spel;
	
	private Button afsluiten = new Button("Spel afsluiten");
	
	private HBox afsluitenbox = new HBox(30);

	public SpeelScherm(Spel spel)
	{
		this.spel = spel;
		BuildSpeelSchermGui();
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
