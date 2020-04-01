package gui;

import domein.Spel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;



public class WelkomScherm extends BorderPane
{
	private final Spel spel;

	private Label titel = new Label("Welkom bij onze Coloretto!");
	private Background achtergrond;
	private Button starten, sluiten;
	private VBox buttons = new VBox(250);
	

	public WelkomScherm(Spel spel)
	{
		this.spel = spel;
		buildGui();
	}


	private void buildGui()
	{
		
		BackgroundImage bgImg = new BackgroundImage(new Image(getClass().getResourceAsStream("/images/background.jpg")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
		BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		achtergrond = new Background(bgImg);
		setBackground(achtergrond);
		
		
		titel.setMaxWidth(Double.MAX_VALUE);
		titel.setAlignment(Pos.TOP_CENTER);
		titel.setPadding(new Insets(200, 0, 0, 0));
		setTop(titel);
		

		starten = new Button("Start Coloretto");
		starten.setMaxWidth(100);
		
		sluiten = new Button("Spel afsluiten");
		sluiten.setMaxWidth(100);
		
		buttons.getChildren().addAll(starten, sluiten);
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(-100, 0, 0, 0));
		setCenter(buttons);

		//btnStart.setOnAction(this.startSpel());


		sluiten.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event) 
			{
				System.exit(0);
			}
		});
	}
	

}
