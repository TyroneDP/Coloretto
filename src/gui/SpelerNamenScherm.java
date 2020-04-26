package gui;

import domein.Spel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SpelerNamenScherm extends BorderPane
{
	private Spel spel = new Spel();

	private WelkomScherm welkomscherm = new WelkomScherm();

	private AantalSpelersScherm aantalspelersscherm;

	private SpeelScherm speelscherm = new SpeelScherm(spel);

	private Background achtergrond;

	private Label titel = new Label("Geef speler namen in");

	private TextField naamSpeler1, naamSpeler2, naamSpeler3, naamSpeler4, naamSpeler5;

	private Button home, terug, volgende;

	private VBox textfields = new VBox(30);

	private HBox homeenterug = new HBox(20);

	private HBox volgendebox = new HBox(10);

	//private ChoiceDialog<String> choice = new ChoiceDialog( defaultChoice, collection );  //voor optie eventueel van 4 of 5 te laten aanduiden



	public SpelerNamenScherm(Spel spel)
	{
		this.spel = spel;
		buildSpelerNamenGui();
	}


	private void buildSpelerNamenGui() 
	{

		//textfields
		naamSpeler1 = new TextField();
		naamSpeler2 = new TextField();
		naamSpeler3 = new TextField();
		naamSpeler4 = new TextField();
		naamSpeler5 = new TextField();

		naamSpeler1.setPromptText("Naam speler 1: ");
		naamSpeler2.setPromptText("Naam speler 2: ");
		naamSpeler3.setPromptText("Naam speler 3: ");
		naamSpeler4.setPromptText("Naam speler 4: ");
		naamSpeler5.setPromptText("Naam speler 5: ");

		//terug button
		terug = new Button("Terug");

		//home button 
		home = new Button("Home");

		//volgende button
		volgende = new Button("Volgende");

		//Onze titel plaatsen
		titel.getStyleClass().add("titel");
		titel.setMaxWidth(Double.MAX_VALUE);
		titel.setAlignment(Pos.TOP_CENTER);
		titel.setPadding(new Insets(200, 0, 0, 0));
		setTop(titel);


		//Alles toevoegen
		textfields.getChildren().addAll(naamSpeler1, naamSpeler2, naamSpeler3, naamSpeler4, naamSpeler5);
		textfields.setMaxWidth(500);
		textfields.setAlignment(Pos.CENTER);
		textfields.setPadding(new Insets(-100, 0, 0, 0));
		setCenter(textfields);

		homeenterug.getChildren().addAll(home, terug);
		homeenterug.setAlignment(Pos.BOTTOM_CENTER);
		homeenterug.setPadding(new Insets(-100, 0, 0, 0));
		setBottom(homeenterug);

		volgendebox.getChildren().addAll(volgende);
		volgendebox.setAlignment(Pos.CENTER_RIGHT);
		volgendebox.setPadding(new Insets(-100, 0, 0, 0));
		setRight(volgendebox);


		//Event handlers


		//als ik op home druk
		home.setOnAction(this::toonWelkomScherm);

		//als ik op terug druk
		terug.setOnAction(this::toonAantalSpelersScherm);

		//als ik op volgende druk
		volgende.setOnAction(this::toonSpeelScherm);


	}

	private void toonWelkomScherm(ActionEvent event)
	{
		this.welkomscherm = new WelkomScherm();
		getChildren().removeAll(titel, volgendebox );
		setCenter(welkomscherm);
	}

	private void toonAantalSpelersScherm(ActionEvent event)
	{
		this.aantalspelersscherm = new AantalSpelersScherm(spel);
		getChildren().removeAll(titel,homeenterug,volgendebox );
		setCenter(aantalspelersscherm);
		
	}

	private void toonSpeelScherm(ActionEvent event)
	{


		this.speelscherm = new SpeelScherm(spel);
		getChildren().remove(titel);
		setCenter(speelscherm);

		//als ik klik background changen
		BackgroundImage bgImg = new BackgroundImage(new Image(getClass().getResourceAsStream("/images/board.png")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
		BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		achtergrond = new Background(bgImg);
		setBackground(achtergrond);
		
		getChildren().removeAll(homeenterug, volgendebox);
	}




}

