package main;

import domein.Spel;
import gui.WelkomScherm;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import persistentie.SpelMapper;
import persistentie.SpelerMapper;

public class StartUpGui extends Application
{
	
	
	@Override
	public void start(Stage stage)
	{
		Spel spel = new Spel();
		WelkomScherm ws = new WelkomScherm(spel);

		// We maken een Scene op basis van het hoofdpaneel en een gewenste grootte
		Scene scene = new Scene(ws);
		
		//css toevoegen
		scene.getStylesheets().add(getClass().getResource("/css/OnzeCss.css").toExternalForm());  
		
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
		SpelerMapper gebruikerMapper = new SpelerMapper();
		SpelMapper spelMapper = new SpelMapper();
        
        /*
        // We maken opnieuw twee gebruikers aan.
        Gebruiker alice = new Gebruiker();
        alice.setGebruikersnaam("alice");
        alice.setNaam("Alice");
        Gebruiker bob = new Gebruiker();
        bob.setGebruikersnaam("bob");
        bob.setNaam("Bob");

        // We slaan deze gebruikers op in de databank.
        gebruikerMapper.voegGebruikerToe(alice);
        gebruikerMapper.voegGebruikerToe(bob);

        // We lezen de gebruikers uit en drukken ze af, als test.
        List<Gebruiker> gebruikers = gebruikerMapper.zoekAlleGebruikers();
        for (Gebruiker gebruiker : gebruikers) {
            System.out.printf("Gebruiker gevonden met gebruikersnaam: %s en echte naam: %s%n", gebruiker.getGebruikersnaam(), gebruiker.getNaam());
        }

        // We maken ook een bericht aan en slaan dit op in de databank.
        Bericht bericht = new Bericht();
        bericht.setVerzender(alice);
        bericht.setBestemmeling(bob);
        bericht.setInhoud("Dag Bob!");
        berichtMapper.voegBerichtToe(bericht);
        
        // We controleren even of dit bericht een id gekregen heeft.
        System.out.printf("Bericht aangemaakt met id %d%n", bericht.getId());
        
        // We passen de inhoud van dit bericht aan en slaan het opnieuw op.
        bericht.setInhoud("Dag Bob, alles goed?");
        berichtMapper.pasBerichtAan(bericht);
        
        // We lezen alle berichten van Alice uit en drukken ze af, als test.
        List<Bericht> berichten = berichtMapper.zoekBerichtenVanGebruiker(alice);
        for (Bericht b : berichten) {
            System.out.printf("Bericht gevonden met id: %d, bestemmeling: %s en inhoud: %s%n", b.getId(), b.getBestemmeling().getGebruikersnaam(), b.getInhoud());
        }
        
        // We verwijderen tenslotte weer alle gegevens.
        berichtMapper.verwijderBericht(bericht);
        gebruikerMapper.verwijderGebruiker(alice);
        gebruikerMapper.verwijderGebruiker(bob);
        
        */
		launch(args);
	}

}
