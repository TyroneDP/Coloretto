package gui;

import domein.Spel;
import domein.Speler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class AantalEnNamen extends BorderPane
{
	private Spel spel = new Spel();

	private Background achtergrond;

	private WelkomScherm welkomscherm;

	private SpeelScherm speelscherm;

	private Label titel = new Label("Kies het aantal spelers");

	private Label titelspelers;

	private Button volgende = new Button("Volgende")
			, home = new Button("Home");

	private HBox volgendebox = new HBox(20);

	private HBox homebox = new HBox(20);

	private TextField naamSpeler1, naamSpeler2, naamSpeler3, naamSpeler4, naamSpeler5;

	private VBox textFieldsBox = new VBox(20);

	private Button vierspelers, vijfspelers;

	private HBox vierenvijf = new HBox(20);

	public AantalEnNamen()
	{
		buildAantalEnNamenGui();
	}

	private void buildAantalEnNamenGui()
	{
		//home button 
		home = new Button("Home");
		homebox.setAlignment(Pos.BASELINE_CENTER);
		homebox.setPadding(new Insets(-100, 0, 20, 0));
		setBottom(homebox);

		//titel plaatsen
		titel.getStyleClass().add("titel");
		titel.setMaxWidth(Double.MAX_VALUE);
		titel.setAlignment(Pos.TOP_CENTER);
		titel.setPadding(new Insets(200, 0, 0, 0));
		setTop(titel);

		//volgende plaatsen


		//vierspelers button
		vierspelers = new Button("4");
		vierspelers.setMinWidth(150);
		vierspelers.setMinHeight(150);
		vierspelers.setStyle("-fx-font-size:40");



		//vijfspelers button
		vijfspelers = new Button("5");
		vijfspelers.setMinWidth(150);
		vijfspelers.setMinHeight(150);
		vijfspelers.setStyle("-fx-font-size:40");



		//Alles toevoegen
		vierenvijf.getChildren().addAll(vierspelers, vijfspelers);
		vierenvijf.setAlignment(Pos.CENTER);
		vierenvijf.setMinWidth(500);
		vierenvijf.setPadding(new Insets(-100, 0, 50, 0));
		setCenter(vierenvijf);


		homebox.getChildren().addAll(home);
		homebox.setAlignment(Pos.BASELINE_CENTER);
		homebox.setPadding(new Insets(0, 0, 0, 0));

		setBottom(homebox);


		//Event handlers

		//als ik op 4 druk
		vierspelers.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent evt) 
			{	
				getChildren().removeAll(titel,vierenvijf); //eerst alles removen van scherm 

				naamSpeler1 = new TextField();
				naamSpeler1.setPromptText("Naam speler 1");


				naamSpeler2 = new TextField();
				naamSpeler2.setPromptText("Naam speler 2");


				naamSpeler3 = new TextField();
				naamSpeler3.setPromptText("Naam speler 3");


				naamSpeler4 = new TextField();
				naamSpeler4.setPromptText("Naam speler 4");


				//volgendebox plaatsen
				volgendebox.getChildren().addAll(volgende);
				volgendebox.setAlignment(Pos.CENTER_RIGHT);
				volgendebox.setPadding(new Insets(-100, 20, 0, 0));
				setRight(volgendebox);

				//titel plaatsen
				titelspelers = new Label("Gelieve de namen van de spelers in te voeren");
				titelspelers.getStyleClass().add("titel");
				titelspelers.setMaxWidth(Double.MAX_VALUE);
				titelspelers.setAlignment(Pos.TOP_CENTER);
				titelspelers.setPadding(new Insets(200, 0, 0, 0));
				setTop(titelspelers);


				textFieldsBox.getChildren().addAll(naamSpeler1, naamSpeler2, naamSpeler3, naamSpeler4);
				textFieldsBox.setMaxWidth(500);
				textFieldsBox.setAlignment(Pos.CENTER);
				textFieldsBox.setPadding(new Insets(-100, 0, 0, 0));
				setCenter(textFieldsBox);




			}
		});

		//als ik op 5 druk
		vijfspelers.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent evt) 
			{	
				getChildren().removeAll(titel,vierenvijf); //eerst alles removen van scherm 

				naamSpeler1 = new TextField();
				naamSpeler1.setPromptText("Naam speler 1");


				naamSpeler2 = new TextField();
				naamSpeler2.setPromptText("Naam speler 2");


				naamSpeler3 = new TextField();
				naamSpeler3.setPromptText("Naam speler 3");


				naamSpeler4 = new TextField();
				naamSpeler4.setPromptText("Naam speler 4");


				naamSpeler5 = new TextField();
				naamSpeler5.setPromptText("Naam speler 5");


				//volgendebox plaatsen
				volgendebox.getChildren().addAll(volgende);
				volgendebox.setAlignment(Pos.CENTER_RIGHT);
				volgendebox.setPadding(new Insets(-100, 20, 0, 0));
				setRight(volgendebox);

				//titel plaatsen
				titelspelers = new Label("Gelieve de namen van de spelers in te voeren");
				titelspelers.getStyleClass().add("titel");
				titelspelers.setMaxWidth(Double.MAX_VALUE);
				titelspelers.setAlignment(Pos.TOP_CENTER);
				titelspelers.setPadding(new Insets(200, 0, 0, 0));
				setTop(titelspelers);

				//textfields plaatsen
				textFieldsBox.getChildren().addAll(naamSpeler1, naamSpeler2, naamSpeler3, naamSpeler4, naamSpeler5);
				textFieldsBox.setMaxWidth(500);
				textFieldsBox.setAlignment(Pos.CENTER);
				textFieldsBox.setPadding(new Insets(-100, 0, 0, 0));
				setCenter(textFieldsBox);

			}
		});

		//als ik op home druk
		home.setOnAction(this::toonWelkomScherm);
		//als ik op volgende druk
		volgende.setOnAction(this::toonSpeelScherm);

	}

	private void toonWelkomScherm(ActionEvent event)
	{
		this.welkomscherm = new WelkomScherm();
		getChildren().removeAll(titel,volgendebox,homebox,titelspelers);
		setCenter(welkomscherm);
	}

	private void toonSpeelScherm(ActionEvent event)
	{
		spel.getSpelers().add(new Speler(naamSpeler1.getText()));
		spel.getSpelers().add(new Speler(naamSpeler2.getText()));
		spel.getSpelers().add(new Speler(naamSpeler3.getText()));
		spel.getSpelers().add(new Speler(naamSpeler4.getText()));
		if(naamSpeler5 != null)
			spel.getSpelers().add(new Speler(naamSpeler5.getText()));

		getChildren().remove(titel);
		setCenter(speelscherm);

		//als ik klik background changen
		BackgroundImage bgImg = new BackgroundImage(new Image(getClass().getResourceAsStream("/images/board.png")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		achtergrond = new Background(bgImg);
		setBackground(achtergrond);

		getChildren().removeAll(volgendebox);
		speelscherm = new SpeelScherm(spel);
		this.getChildren().removeAll(homebox);
		getChildren().removeAll(titel,titelspelers);
		setCenter(speelscherm);
	}
}
