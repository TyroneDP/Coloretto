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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class WelkomScherm extends BorderPane
{
	private Spel spel;
	private AantalEnNamen aantalennamen;
	private Label titel = new Label("Welkom bij onze Coloretto!");
	private Background achtergrond;
	private Button starten, sluiten, hervatten;
	private VBox buttons = new VBox(150); //150 padding ?
	private ImageView iconVoorStarten = new ImageView(new Image(getClass().getResourceAsStream("/images/Play.png")));
	private ImageView iconVoorSluiten = new ImageView(new Image(getClass().getResourceAsStream("/images/Quit.png")));

	public WelkomScherm()
	{
		BuildGui();
	}


	public WelkomScherm(Spel spel)
	{
		this.spel = spel;
		BuildGui();
	}

	private void BuildGui()
	{
		//Background opvullen
		BackgroundImage bgImg = new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background.jpg")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
		BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		achtergrond = new Background(bgImg);
		setBackground(achtergrond);

		//Onze titel plaatsen
		titel.getStyleClass().add("titel");
		titel.setMaxWidth(Double.MAX_VALUE);
		titel.setAlignment(Pos.TOP_CENTER);
		titel.setPadding(new Insets(200, 0, 0, 0));
		setTop(titel);

		//hervatten button
		hervatten = new Button("Laatste spel hervatten");

		//StartButton
		starten = new Button("Start Coloretto", iconVoorStarten);

		iconVoorStarten.setFitHeight(30);
		iconVoorStarten.setFitWidth(30);

		starten.setMaxWidth(200);


		//SluitButton
		sluiten = new Button("Spel afsluiten", iconVoorSluiten);
		iconVoorSluiten.setFitHeight(30);
		iconVoorSluiten.setFitWidth(30);
		sluiten.setMaxWidth(200);
		//sluiten.getStyleClass().add("sluiten");


		//Alles toevoegen
		buttons.getChildren().addAll(starten, sluiten, hervatten);
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(-100, 0, 0, 0));
		setCenter(buttons);


		//Event handlers

		//als ik op sluiten druk
		sluiten.setOnAction(new EventHandler<ActionEvent>()
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

		//als ik op starten druk
		starten.setOnAction(this::toonAantalEnNamen);

	}

	private void toonAantalEnNamen(ActionEvent event)
	{	
		//nieuwe class loaden als ik klik
		aantalennamen = new AantalEnNamen();
		getChildren().removeAll(titel);
		setCenter(aantalennamen);
	}


}
