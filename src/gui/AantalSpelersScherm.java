package gui;

import domein.Spel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class AantalSpelersScherm extends BorderPane
{
	private Spel spel = new Spel();

	private WelkomScherm welkomscherm;
	
	private SpelerNamenScherm spelernamenscherm;

	private Label titel = new Label("Met hoeveel spelers zijn jullie ?");

	private Button vierspelers, vijfspelers, home;

	private HBox vierenvijf = new HBox(20);
	
	private HBox homebox = new HBox(20);

	//private ChoiceDialog<String> choice = new ChoiceDialog( defaultChoice, collection );  //voor optie eventueel van 4 of 5 te laten aanduiden



	public AantalSpelersScherm(Spel spel)
	{
		this.spel = spel;
		buildAantalSpelersSchermGui();
	}


	private void buildAantalSpelersSchermGui() 
	{
		
		
		
		//home button 
		home = new Button("Home");
		

		//Onze titel plaatsen
		titel.getStyleClass().add("titel");
		titel.setMaxWidth(Double.MAX_VALUE);
		titel.setAlignment(Pos.TOP_CENTER);
		titel.setPadding(new Insets(200, 0, 0, 0));
		setTop(titel);

		//vierspelers button
		vierspelers = new Button("4");
		vierspelers.setMaxWidth(200);
		

		//SluitButton
		vijfspelers = new Button("5");
		vijfspelers.setMaxWidth(200);
		


		//Alles toevoegen
		vierenvijf.getChildren().addAll(vierspelers, vijfspelers, home );
		vierenvijf.setAlignment(Pos.CENTER);
		vierenvijf.setPadding(new Insets(-100, 0, 0, 0));
		setCenter(vierenvijf);
		
		
		homebox.getChildren().addAll(home);
		homebox.setAlignment(Pos.BASELINE_CENTER);
		homebox.setPadding(new Insets(-100, 0, 0, 0));
		setBottom(homebox);
		
		
		//Event handlers

		//als ik op home druk
		home.setOnAction(this::toonWelkomScherm);
		//als ik op vierspelers druk
		vierspelers.setOnAction(this::toonSpelerNamenScherm);
		//als ik op vijfspelers druk
		vijfspelers.setOnAction(this::toonSpelerNamenScherm);

	}

	private void toonWelkomScherm(ActionEvent event)
	{
		this.welkomscherm = new WelkomScherm();
		getChildren().remove(titel);
		setCenter(welkomscherm);
	}

	private void toonSpelerNamenScherm(ActionEvent event)
	{
		spelernamenscherm = new SpelerNamenScherm(spel);
		this.getChildren().remove(homebox);
		getChildren().remove(titel);
		setCenter(spelernamenscherm);
	}




}

