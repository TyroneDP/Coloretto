package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class WelkomScherm extends Pane
{
	public WelkomScherm()
	{
		// Eerste grafische component: een label        
        Label lblWelkom = new Label("Welkom bij Coloretto !");



// In dit voorbeeld gebruik we geen layout
// We geven de componenten een vaste positie mee
// Dit doen we met de methoden setLayoutX en setLayoutY        
        lblWelkom.setLayoutX(200);
        lblWelkom.setLayoutY(10);
        
// Alle componenten worden verzameld in ons paneel
        
// componenten toevoegen aan een paneel         
        this.getChildren().addAll(lblWelkom);
	}
}
