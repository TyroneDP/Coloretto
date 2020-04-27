package gui;

import java.util.ArrayList;
import java.util.List;

import domein.Spel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
	private Spel spel;
	
	private Background achtergrond;

	private WelkomScherm welkomscherm;

	private SpeelScherm speelscherm;

	private Label titel = new Label("Kies aantal spelers en vul de namen in");

	private Button volgende, home;

	private HBox volgendebox = new HBox(20);

	private HBox homebox = new HBox(20);
	
	private HBox choicebox = new HBox(20);
	
	private List<TextField> textFields = new ArrayList<TextField>();
	
	private VBox textFieldsBox = new VBox(20);

	public AantalEnNamen()
	{
		buildAantalEnNamenGui();
	}
	
	


	private void buildAantalEnNamenGui()
	{
		//home button 
		home = new Button("Home");
		homebox.getChildren().addAll(home);
		homebox.setAlignment(Pos.BASELINE_CENTER);
		homebox.setPadding(new Insets(-100, 0, 20, 0));
		setBottom(homebox);

		//Onze titel plaatsen
		titel.getStyleClass().add("titel");
		titel.setMaxWidth(Double.MAX_VALUE);
		titel.setAlignment(Pos.TOP_CENTER);
		titel.setPadding(new Insets(200, 0, 0, 0));
		setTop(titel);
	
		//volgende plaatsen
		volgende = new Button("Volgende");

		volgendebox.getChildren().addAll(volgende);
		volgendebox.setAlignment(Pos.CENTER_RIGHT);
		volgendebox.setPadding(new Insets(-100, 20, 0, 0));
		setRight(volgendebox);


		
		
		//optie voor 4 of 5 spelers
		ComboBox<Integer> aantalspelers = new ComboBox<Integer>(FXCollections.observableArrayList(4, 5));
		choicebox.getChildren().addAll(aantalspelers);
		choicebox.setAlignment(Pos.CENTER);
		
		setCenter(choicebox);
		
		
		//textfields
		for (int i = 0; i < 5; i++)
		{
			
			
			this.getChildren().add(new Label(String.format("Speler %s: ", (i + 1))));
			TextField txtNaam = new TextField();
			txtNaam.setDisable(true);
			txtNaam.setTooltip(new Tooltip("Selecteer eerst het aantal spelers."));
			this.getChildren().add(txtNaam);
			textFieldsBox.getChildren().addAll(textFields);
			
			textFieldsBox.setAlignment(Pos.CENTER_LEFT);
			setLeft(textFieldsBox);
			
		}
		
	
	
		//Event handlers

		//als ik op home druk
		home.setOnAction(this::toonWelkomScherm);
		//als ik op volgende druk
		volgende.setOnAction(this::toonSpeelScherm);
	
	}




	private void toonWelkomScherm(ActionEvent event)
	{
		this.welkomscherm = new WelkomScherm();
		getChildren().removeAll(titel,volgendebox);
		setCenter(welkomscherm);
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
		
		getChildren().removeAll(volgendebox);
		speelscherm = new SpeelScherm(spel);
		this.getChildren().remove(homebox);
		getChildren().remove(titel);
		setCenter(speelscherm);
	}
}
