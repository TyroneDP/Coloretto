package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import domein.Kaart;
import domein.Spel;
import domein.Speler;
import domein.Stapel;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class SpeelScherm extends GridPane
{
	//in deze klasse alle methodes schrijven dat verband hebben met domeinlaag klasse Spel

	private Spel spel = new Spel();
	private ImageView omgedraaideKaart = new ImageView();
	private List<Speler> spelerlijst = spel.getSpelers();

	HashMap<String, Image> kaarten = new HashMap<String, Image>();

	private GridPane grdSpelers;

	private GridPane grdRechts = new GridPane();

	StackPane stckStapel = new StackPane();

	private List<Label> spelerNaamLabels = new ArrayList<Label>();
	private List<Label> spelerScoreLabels = new ArrayList<Label>();
	private Label lblStatusMsg;

	private boolean isCardDrawn = false;
	private boolean areAllDecksFull = false;

	GridPane grdSpelbord = new GridPane();

	Label lblCardCount = new Label();

	private Button btnR1 = new Button("Neem rij 1");
	private Button btnR2 = new Button("Neem rij 2");
	private Button btnR3 = new Button("Neem rij 3");
	private Button btnR4 = new Button("Neem rij 4");
	private Button btnR5 = new Button("Neem rij 5");

	public SpeelScherm(Spel spel)
	{
		this.spel = spel;

		maakDeck();

		spel.maakStapel();
		spel.maakRijen();
		spel.bepaalStartSpeler();
		spel.vulStartKleuren();

		spel.bepaalStartKaart();
		
		BuildSpeelSchermGui();
		updateSpelerKaarten();

	}

	//eerst images aan stapel linken, gaat niet zonder try/catch
	private void maakDeck()
	{
		try
		{
			kaarten.put("Blauw", new Image(new FileInputStream("src/images/Blauw.png")));

			kaarten.put("Bruin", new Image(new FileInputStream("src/images/Bruin.png")));

			kaarten.put("Geel", new Image(new FileInputStream("src/images/Geel.png")));

			kaarten.put("Groen", new Image(new FileInputStream("src/images/Groen.png")));

			kaarten.put("Joker", new Image(new FileInputStream("src/images/Joker.png")));

			kaarten.put("Oranje", new Image(new FileInputStream("src/images/Oranje.png")));

			kaarten.put("Paars", new Image(new FileInputStream("src/images/Paars.png")));

			kaarten.put("Rood", new Image(new FileInputStream("src/images/Rood.png")));

			kaarten.put("Plus2", new Image(new FileInputStream("src/images/PlusTwee.png")));

			kaarten.put("achterkant", new Image(new FileInputStream("src/images/Achterkant.png")));

			kaarten.put("rij", new Image(new FileInputStream("src/images/rij.png")));

			kaarten.put("genomenRij", new Image(new FileInputStream("src/images/genomen-rij.png")));

			kaarten.put("puntenkaart", new Image(new FileInputStream("src/images/PuntenKaart.png")));



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


		this.setHgap(1);
		this.setVgap(1);
		this.setPadding(new Insets(1, 1, 1, 1));

		spelerlijst = spel.getSpelers();

		grdSpelers = new GridPane();
		grdSpelers.setAlignment(Pos.CENTER);
		Label lblKaartenSpelers = new Label("Spelers:");
		grdSpelers.add(lblKaartenSpelers, 0, 0);
		int Positie = 1;
		for (int i = 0; i < spel.getSpelers().size(); i++)
		{
			Label lblSpelerNaam = new Label(String.format("%s:", spelerlijst.get(i)));
			Label lblSpelerScore = new Label(String.format("Score Speler %s: %s", i+1,spelerlijst.get(i).berekenScore()));

			spelerNaamLabels.add(lblSpelerNaam);

			spelerScoreLabels.add(lblSpelerScore);
			FlowPane flwSpelerKaarten = new FlowPane();

			setHalignment(lblSpelerScore, HPos.LEFT);
			lblSpelerScore.setMinWidth(100);

			grdSpelers.add(lblSpelerNaam, 0, Positie);
			grdSpelers.add(flwSpelerKaarten, 0, Positie + 1);
			grdSpelers.add(lblSpelerScore, 1, Positie);
			Positie += 2;
		}

		setHalignment(grdSpelers, HPos.RIGHT);
		grdSpelers.setPrefWidth(300);

		this.add(grdSpelers, 1 ,0);


		Label lblStapel = new Label("    Stapel:");
		lblStapel.setStyle("-fx-font-size:30");
		grdSpelbord.add(lblStapel, 0,0 );

		omgedraaideKaart.setFitWidth(150);


		setHalignment(grdSpelbord,HPos.LEFT);


		grdRechts.add(grdSpelbord, 0, 0);

		ImageView imvStapel = new ImageView(kaarten.get("achterkant"));
		imvStapel.setFitWidth(150);
		imvStapel.setPreserveRatio(true);

		grdSpelbord.add(imvStapel,0,1);

		FlowPane fpRij1 = new FlowPane();
		ImageView imvR11 = new ImageView(kaarten.get("rij"));
		imvR11.setFitWidth(100);
		imvR11.setPreserveRatio(true);
		ImageView imvR12 = new ImageView(kaarten.get("rij"));
		imvR12.setFitWidth(100);
		imvR12.setPreserveRatio(true);
		ImageView imvR13 = new ImageView(kaarten.get("rij"));
		imvR13.setFitWidth(100);
		imvR13.setPreserveRatio(true);

		fpRij1.getChildren().add(imvR11);
		fpRij1.getChildren().add(imvR12);
		fpRij1.getChildren().add(imvR13);

		//grdSpelbord.add(fpRij1, 0, 2);


		FlowPane fpRij2 = new FlowPane();
		ImageView imvR21 = new ImageView(kaarten.get("rij"));
		imvR21.setFitWidth(100);
		imvR21.setPreserveRatio(true);
		ImageView imvR22 = new ImageView(kaarten.get("rij"));
		imvR22.setFitWidth(100);
		imvR22.setPreserveRatio(true);
		ImageView imvR23 = new ImageView(kaarten.get("rij"));
		imvR23.setFitWidth(100);
		imvR23.setPreserveRatio(true);

		fpRij2.getChildren().add(imvR21);
		fpRij2.getChildren().add(imvR22);
		fpRij2.getChildren().add(imvR23);

		//grdSpelbord.add(fpRij2, 0, 3);


		FlowPane fpRij3 = new FlowPane();
		ImageView imvR31 = new ImageView(kaarten.get("rij"));
		imvR31.setFitWidth(100);
		imvR31.setPreserveRatio(true);
		ImageView imvR32 = new ImageView(kaarten.get("rij"));
		imvR32.setFitWidth(100);
		imvR32.setPreserveRatio(true);
		ImageView imvR33 = new ImageView(kaarten.get("rij"));
		imvR33.setFitWidth(100);
		imvR33.setPreserveRatio(true);


		fpRij3.getChildren().add(imvR31);
		fpRij3.getChildren().add(imvR32);
		fpRij3.getChildren().add(imvR33);

		//grdSpelbord.add(fpRij3, 0, 4);


		FlowPane fpRij4 = new FlowPane();
		ImageView imvR41 = new ImageView(kaarten.get("rij"));
		imvR41.setFitWidth(100);
		imvR41.setPreserveRatio(true);
		ImageView imvR42 = new ImageView(kaarten.get("rij"));
		imvR42.setFitWidth(100);
		imvR42.setPreserveRatio(true);
		ImageView imvR43 = new ImageView(kaarten.get("rij"));
		imvR43.setFitWidth(100);
		imvR43.setPreserveRatio(true);


		fpRij4.getChildren().add(imvR41);
		fpRij4.getChildren().add(imvR42);
		fpRij4.getChildren().add(imvR43);



		grdRechts.add(fpRij1, 0, 1);
		grdRechts.add(fpRij2, 0, 2);
		grdRechts.add(fpRij3, 0, 3);
		grdRechts.add(fpRij4, 0, 4);

		grdRechts.add(btnR1, 0, 1);
		grdRechts.add(btnR2, 0, 2);
		grdRechts.add(btnR3, 0, 3);
		grdRechts.add(btnR4, 0, 4);	

		fpRij1.setAlignment(Pos.CENTER_RIGHT);
		fpRij2.setAlignment(Pos.CENTER_RIGHT);
		fpRij3.setAlignment(Pos.CENTER_RIGHT);
		fpRij4.setAlignment(Pos.CENTER_RIGHT);

		ImageView pk = new ImageView(kaarten.get("puntenkaart"));
		pk.setFitWidth(150);
		pk.setPreserveRatio(true);

		grdRechts.add(pk, 1, 0);

		grdRechts.setGridLinesVisible(true);
		this.setGridLinesVisible(true);
		grdSpelbord.setGridLinesVisible(true);

		this.add(grdRechts, 0, 0);

		if (spelerlijst.size()==5)
		{
			FlowPane fpRij5 = new FlowPane();
			ImageView imvR51 = new ImageView(kaarten.get("rij"));
			imvR51.setFitWidth(100);
			imvR51.setPreserveRatio(true);
			ImageView imvR52 = new ImageView(kaarten.get("rij"));
			imvR52.setFitWidth(100);
			imvR52.setPreserveRatio(true);
			ImageView imvR53 = new ImageView(kaarten.get("rij"));
			imvR53.setFitWidth(100);
			imvR53.setPreserveRatio(true);

			fpRij5.getChildren().add(imvR51);
			fpRij5.getChildren().add(imvR52);
			fpRij5.getChildren().add(imvR53);

			grdRechts.add(btnR5, 0, 5);
			grdRechts.add(fpRij5, 0,5 );
			fpRij5.setAlignment(Pos.CENTER_RIGHT);

			imvR51.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) 
				{
					if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR51.getImage()) && kaarten.get("rij").equals(imvR51.getImage())) 
					{
						imvR51.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

						omgedraaideKaart.setImage(kaarten.get("achterkant"));

						isCardDrawn = false;
						lblStatusMsg.setText(
								String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
										spel.getHuidigeSpeler()));
						updateStapelTeller();

						spel.bepaalVolgendeSpeler();
					}
					event.consume();

				}});
			imvR52.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) 
				{
					if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR52.getImage()) && kaarten.get("rij").equals(imvR52.getImage())) 
					{
						imvR52.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

						omgedraaideKaart.setImage(kaarten.get("achterkant"));

						isCardDrawn = false;
						lblStatusMsg.setText(
								String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
										spel.getHuidigeSpeler()));
						updateStapelTeller();
						spel.bepaalVolgendeSpeler();
					}
					event.consume();

				}});
			imvR53.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) 
				{
					if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR53.getImage()) && kaarten.get("rij").equals(imvR53.getImage())) 
					{
						imvR53.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

						omgedraaideKaart.setImage(kaarten.get("achterkant"));

						isCardDrawn = false;
						lblStatusMsg.setText(
								String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
										spel.getHuidigeSpeler()));
						updateStapelTeller();
						spel.bepaalVolgendeSpeler();
					}
					event.consume();
				}});

			fpRij5.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) 
				{

					//Rij 5 kaart 1
					if(kaarten.get("Oranje").equals(imvR51.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
					}
					if(kaarten.get("Blauw").equals(imvR51.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
					}
					if(kaarten.get("Bruin").equals(imvR51.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
					}
					if(kaarten.get("Geel").equals(imvR51.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
					}
					if(kaarten.get("Paars").equals(imvR51.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
					}
					if(kaarten.get("Groen").equals(imvR51.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
					}	
					if(kaarten.get("Rood").equals(imvR51.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
					}
					if(kaarten.get("Plus2").equals(imvR51.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
					}
					if(kaarten.get("Joker").equals(imvR51.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
					}

					//Rij 5 kaart 2
					if(kaarten.get("Oranje").equals(imvR52.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
					}
					if(kaarten.get("Blauw").equals(imvR52.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
					}
					if(kaarten.get("Bruin").equals(imvR52.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
					}
					if(kaarten.get("Geel").equals(imvR52.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
					}
					if(kaarten.get("Paars").equals(imvR52.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
					}
					if(kaarten.get("Groen").equals(imvR52.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
					}	
					if(kaarten.get("Rood").equals(imvR52.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
					}
					if(kaarten.get("Plus2").equals(imvR52.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
					}
					if(kaarten.get("Joker").equals(imvR52.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
					}

					//Rij 5 kaart 3 
					if(kaarten.get("Oranje").equals(imvR53.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
					}
					if(kaarten.get("Blauw").equals(imvR53.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
					}
					if(kaarten.get("Bruin").equals(imvR53.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
					}
					if(kaarten.get("Geel").equals(imvR53.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
					}
					if(kaarten.get("Paars").equals(imvR53.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
					}
					if(kaarten.get("Groen").equals(imvR53.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
					}	
					if(kaarten.get("Rood").equals(imvR53.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
					}
					if(kaarten.get("Plus2").equals(imvR53.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
					}
					if(kaarten.get("Joker").equals(imvR53.getImage()))
					{
						spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
					}

					imvR51.setImage(kaarten.get("genomenRij"));
					imvR52.setImage(kaarten.get("genomenRij"));
					imvR53.setImage(kaarten.get("genomenRij"));
					updateSpelerKaarten();
					spel.bepaalVolgendeSpeler();

					event.consume();
				}});

		}



		omgedraaideKaart = new ImageView(kaarten.get("achterkant"));
		grdSpelbord.add(omgedraaideKaart, 1, 1);

		lblStatusMsg = new Label();
		lblStatusMsg.setWrapText(true);
		lblStatusMsg.setText(String.format("%s is aan de beurt trek een kaart van de stapel.",spel.getHuidigeSpeler()));
		grdSpelbord.add(lblStatusMsg,2 , 1);

		imvStapel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (!isCardDrawn && !areAllDecksFull) 
				{
					omgedraaideKaart.setImage(kaarten.get(spel.trekVanStapelFx().getKleur()));
					isCardDrawn = true;
					lblStatusMsg.setText(
							String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
									spel.getHuidigeSpeler()));
				}
				event.consume();
			}});

		imvR11.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR11.getImage()) && kaarten.get("rij").equals(imvR11.getImage())) 
				{
					imvR11.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isCardDrawn = false;
					lblStatusMsg.setText(
							String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
									spel.getHuidigeSpeler()));
					updateStapelTeller();
				}
				event.consume();
			}});	
		imvR12.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR12.getImage()) && kaarten.get("rij").equals(imvR12.getImage())) 
				{
					imvR12.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isCardDrawn = false;
					lblStatusMsg.setText(
							String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
									spel.getHuidigeSpeler()));
					updateStapelTeller();
					spel.bepaalVolgendeSpeler();
				}
				event.consume();

			}});
		imvR13.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR13.getImage()) && kaarten.get("rij").equals(imvR13.getImage())) 
				{
					imvR13.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isCardDrawn = false;
					lblStatusMsg.setText(
							String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
									spel.getHuidigeSpeler()));
					updateStapelTeller();
					spel.bepaalVolgendeSpeler();
				}
				event.consume();
			}});

		imvR21.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR21.getImage()) && kaarten.get("rij").equals(imvR21.getImage())) 
				{
					imvR21.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isCardDrawn = false;
					lblStatusMsg.setText(
							String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
									spel.getHuidigeSpeler()));
					updateStapelTeller();
					spel.bepaalVolgendeSpeler();
				}
				event.consume();

			}});
		imvR22.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR22.getImage()) && kaarten.get("rij").equals(imvR22.getImage())) 
				{
					imvR22.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isCardDrawn = false;
					lblStatusMsg.setText(
							String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
									spel.getHuidigeSpeler()));
					updateStapelTeller();
					spel.bepaalVolgendeSpeler();
				}
				event.consume();

			}});
		imvR23.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR23.getImage()) && kaarten.get("rij").equals(imvR23.getImage())) 
				{
					imvR23.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isCardDrawn = false;
					lblStatusMsg.setText(
							String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
									spel.getHuidigeSpeler()));
					updateStapelTeller();
					spel.bepaalVolgendeSpeler();
				}
				event.consume();

			}});

		imvR31.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR31.getImage()) && kaarten.get("rij").equals(imvR31.getImage())) 
				{
					imvR31.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isCardDrawn = false;
					lblStatusMsg.setText(
							String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
									spel.getHuidigeSpeler()));
					updateStapelTeller();
					spel.bepaalVolgendeSpeler();
				}
				event.consume();

			}});
		imvR32.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR32.getImage()) && kaarten.get("rij").equals(imvR32.getImage())) 
				{
					imvR32.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isCardDrawn = false;
					lblStatusMsg.setText(
							String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
									spel.getHuidigeSpeler()));
					updateStapelTeller();
					spel.bepaalVolgendeSpeler();
				}
				event.consume();

			}});
		imvR33.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) 
			{
				if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR33.getImage()) && kaarten.get("rij").equals(imvR33.getImage())) 
				{
					imvR33.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isCardDrawn = false;
					lblStatusMsg.setText(
							String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
									spel.getHuidigeSpeler()));
					updateStapelTeller();
					spel.bepaalVolgendeSpeler();
				}
				event.consume();

			}});

		imvR41.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR41.getImage()) && kaarten.get("rij").equals(imvR41.getImage())) 
				{
					imvR41.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isCardDrawn = false;
					lblStatusMsg.setText(
							String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
									spel.getHuidigeSpeler()));
					updateStapelTeller();
					spel.bepaalVolgendeSpeler();
				}
				event.consume();

			}});
		imvR42.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR42.getImage()) && kaarten.get("rij").equals(imvR42.getImage())) 
				{
					imvR42.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isCardDrawn = false;
					lblStatusMsg.setText(
							String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
									spel.getHuidigeSpeler()));
					updateStapelTeller();
					spel.bepaalVolgendeSpeler();
				}
				event.consume();

			}});
		imvR43.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isCardDrawn && !areAllDecksFull && !kaarten.get("genomenRij").equals(imvR43.getImage()) && kaarten.get("rij").equals(imvR43.getImage())) 
				{
					imvR43.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isCardDrawn = false;
					lblStatusMsg.setText(
							String.format("%s heeft een kaart genomen, en moet ze leggen bij een rij.",
									spel.getHuidigeSpeler()));
					updateStapelTeller();
					spel.bepaalVolgendeSpeler();
				}
				event.consume();

			}});



		btnR1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{

				//grdSpelbord.getChildren().remove(fpRij1);
				//Rij 1 kaart 1
				if(kaarten.get("Oranje").equals(imvR11.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
				}
				if(kaarten.get("Blauw").equals(imvR11.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
				}
				if(kaarten.get("Bruin").equals(imvR11.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
				}
				if(kaarten.get("Geel").equals(imvR11.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
				}
				if(kaarten.get("Paars").equals(imvR11.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
				}
				if(kaarten.get("Groen").equals(imvR11.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
				}	
				if(kaarten.get("Rood").equals(imvR11.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
				}
				if(kaarten.get("Plus2").equals(imvR11.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
				}
				if(kaarten.get("Joker").equals(imvR11.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
				}

				//Rij 1 kaart 2
				if(kaarten.get("Oranje").equals(imvR12.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
				}
				if(kaarten.get("Blauw").equals(imvR12.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
				}
				if(kaarten.get("Bruin").equals(imvR12.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
				}
				if(kaarten.get("Geel").equals(imvR12.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
				}
				if(kaarten.get("Paars").equals(imvR12.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
				}
				if(kaarten.get("Groen").equals(imvR12.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
				}	
				if(kaarten.get("Rood").equals(imvR12.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
				}
				if(kaarten.get("Plus2").equals(imvR12.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
				}
				if(kaarten.get("Joker").equals(imvR12.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
				}

				//Rij 1 kaart 3 
				if(kaarten.get("Oranje").equals(imvR13.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
				}
				if(kaarten.get("Blauw").equals(imvR13.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
				}
				if(kaarten.get("Bruin").equals(imvR13.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
				}
				if(kaarten.get("Geel").equals(imvR13.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
				}
				if(kaarten.get("Paars").equals(imvR13.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
				}
				if(kaarten.get("Groen").equals(imvR13.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
				}	
				if(kaarten.get("Rood").equals(imvR13.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
				}
				if(kaarten.get("Plus2").equals(imvR13.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
				}
				if(kaarten.get("Joker").equals(imvR13.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
				}



				imvR11.setImage(kaarten.get("genomenRij"));
				imvR12.setImage(kaarten.get("genomenRij"));
				imvR13.setImage(kaarten.get("genomenRij"));

				//imvR11.setEnabled(true);

				spel.bepaalVolgendeSpeler();
				updateSpelerKaarten();

				event.consume();
			}});

		btnR2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{



				//grdSpelbord.getChildren().remove(fpRij1);
				//Rij 2 kaart 1
				if(kaarten.get("Oranje").equals(imvR21.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
				}
				if(kaarten.get("Blauw").equals(imvR21.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
				}
				if(kaarten.get("Bruin").equals(imvR21.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
				}
				if(kaarten.get("Geel").equals(imvR21.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
				}
				if(kaarten.get("Paars").equals(imvR21.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
				}
				if(kaarten.get("Groen").equals(imvR21.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
				}	
				if(kaarten.get("Rood").equals(imvR21.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
				}
				if(kaarten.get("Plus2").equals(imvR21.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
				}
				if(kaarten.get("Joker").equals(imvR21.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
				}

				//Rij 2 kaart 2
				if(kaarten.get("Oranje").equals(imvR22.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
				}
				if(kaarten.get("Blauw").equals(imvR22.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
				}
				if(kaarten.get("Bruin").equals(imvR22.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
				}
				if(kaarten.get("Geel").equals(imvR22.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
				}
				if(kaarten.get("Paars").equals(imvR22.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
				}
				if(kaarten.get("Groen").equals(imvR22.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
				}	
				if(kaarten.get("Rood").equals(imvR22.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
				}
				if(kaarten.get("Plus2").equals(imvR22.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
				}
				if(kaarten.get("Joker").equals(imvR22.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
				}

				//Rij 2 kaart 3 
				if(kaarten.get("Oranje").equals(imvR23.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
				}
				if(kaarten.get("Blauw").equals(imvR23.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
				}
				if(kaarten.get("Bruin").equals(imvR23.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
				}
				if(kaarten.get("Geel").equals(imvR23.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
				}
				if(kaarten.get("Paars").equals(imvR23.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
				}
				if(kaarten.get("Groen").equals(imvR23.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
				}	
				if(kaarten.get("Rood").equals(imvR23.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
				}
				if(kaarten.get("Plus2").equals(imvR23.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
				}
				if(kaarten.get("Joker").equals(imvR23.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
				}


				imvR21.setImage(kaarten.get("genomenRij"));
				imvR22.setImage(kaarten.get("genomenRij"));
				imvR23.setImage(kaarten.get("genomenRij"));
				spel.bepaalVolgendeSpeler();
				updateSpelerKaarten();

				event.consume();
			}});

		btnR3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{




				//Rij 3 kaart 1
				if(kaarten.get("Oranje").equals(imvR31.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
				}
				if(kaarten.get("Blauw").equals(imvR31.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
				}
				if(kaarten.get("Bruin").equals(imvR31.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
				}
				if(kaarten.get("Geel").equals(imvR31.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
				}
				if(kaarten.get("Paars").equals(imvR31.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
				}
				if(kaarten.get("Groen").equals(imvR31.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
				}	
				if(kaarten.get("Rood").equals(imvR31.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
				}
				if(kaarten.get("Plus2").equals(imvR31.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
				}
				if(kaarten.get("Joker").equals(imvR31.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
				}

				//Rij 3 kaart 2
				if(kaarten.get("Oranje").equals(imvR32.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
				}
				if(kaarten.get("Blauw").equals(imvR32.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
				}
				if(kaarten.get("Bruin").equals(imvR32.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
				}
				if(kaarten.get("Geel").equals(imvR32.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
				}
				if(kaarten.get("Paars").equals(imvR32.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
				}
				if(kaarten.get("Groen").equals(imvR32.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
				}	
				if(kaarten.get("Rood").equals(imvR32.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
				}
				if(kaarten.get("Plus2").equals(imvR32.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
				}
				if(kaarten.get("Joker").equals(imvR32.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
				}

				//Rij 3 kaart 3 
				if(kaarten.get("Oranje").equals(imvR33.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
				}
				if(kaarten.get("Blauw").equals(imvR33.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
				}
				if(kaarten.get("Bruin").equals(imvR33.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
				}
				if(kaarten.get("Geel").equals(imvR33.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
				}
				if(kaarten.get("Paars").equals(imvR33.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
				}
				if(kaarten.get("Groen").equals(imvR33.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
				}	
				if(kaarten.get("Rood").equals(imvR33.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
				}
				if(kaarten.get("Plus2").equals(imvR33.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
				}
				if(kaarten.get("Joker").equals(imvR33.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
				}

				imvR31.setImage(kaarten.get("genomenRij"));
				imvR32.setImage(kaarten.get("genomenRij"));
				imvR33.setImage(kaarten.get("genomenRij"));
				spel.bepaalVolgendeSpeler();
				updateSpelerKaarten();
				event.consume();
			}});

		btnR4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{

				//Rij 4 kaart 1
				if(kaarten.get("Oranje").equals(imvR41.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
				}
				if(kaarten.get("Blauw").equals(imvR41.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
				}
				if(kaarten.get("Bruin").equals(imvR41.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
				}
				if(kaarten.get("Geel").equals(imvR41.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
				}
				if(kaarten.get("Paars").equals(imvR41.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
				}
				if(kaarten.get("Groen").equals(imvR41.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
				}	
				if(kaarten.get("Rood").equals(imvR41.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
				}
				if(kaarten.get("Plus2").equals(imvR41.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
				}
				if(kaarten.get("Joker").equals(imvR41.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
				}

				//Rij 2 kaart 2
				if(kaarten.get("Oranje").equals(imvR42.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
				}
				if(kaarten.get("Blauw").equals(imvR42.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
				}
				if(kaarten.get("Bruin").equals(imvR42.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
				}
				if(kaarten.get("Geel").equals(imvR42.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
				}
				if(kaarten.get("Paars").equals(imvR42.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
				}
				if(kaarten.get("Groen").equals(imvR42.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
				}	
				if(kaarten.get("Rood").equals(imvR42.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
				}
				if(kaarten.get("Plus2").equals(imvR42.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
				}
				if(kaarten.get("Joker").equals(imvR42.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
				}

				//Rij 4 kaart 3 
				if(kaarten.get("Oranje").equals(imvR43.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Oranje"));
				}
				if(kaarten.get("Blauw").equals(imvR43.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Blauw"));
				}
				if(kaarten.get("Bruin").equals(imvR43.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Bruin"));
				}
				if(kaarten.get("Geel").equals(imvR43.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Geel"));
				}
				if(kaarten.get("Paars").equals(imvR43.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Paars"));
				}
				if(kaarten.get("Groen").equals(imvR43.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Groen"));
				}	
				if(kaarten.get("Rood").equals(imvR43.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Rood"));
				}
				if(kaarten.get("Plus2").equals(imvR43.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Plus2"));
				}
				if(kaarten.get("Joker").equals(imvR43.getImage()))
				{
					spel.getHuidigeSpeler().getHand().add(new Kaart("Joker"));
				}


				imvR41.setImage(kaarten.get("genomenRij"));
				imvR42.setImage(kaarten.get("genomenRij"));
				imvR43.setImage(kaarten.get("genomenRij"));

				spel.bepaalVolgendeSpeler();
				updateSpelerKaarten();
				event.consume();
			}});

		/*private void checkRijen()
		{
				if (kaarten.get("genomenRij").equals(imvR11.getImage()) && kaarten.get("genomenRij").equals(imvR21.getImage()) && kaarten.get("genomenRij").equals(imvR31.getImage()) && kaarten.get("genomenRij").equals(imvR41.getImage()))
		{
			imvR11.setImage(kaarten.get("achterkant"));
			imvR21.setImage(kaarten.get("achterkant"));
			imvR31.setImage(kaarten.get("achterkant"));
			imvR41.setImage(kaarten.get("achterkant"));
		}
		}*/




		grdSpelbord.add(lblCardCount,1,0);




	}


	private void updateStapelTeller()
	{
		lblCardCount.setText(String.format("(%s)",  spel.getStapel().getKaarten().size()));
		setHalignment(lblCardCount, HPos.CENTER);





	}


	private void updateSpelerKaarten()
	{

		List<FlowPane> flwSpelerKaarten = grdSpelers.getChildren().stream()
				.filter(node -> node instanceof FlowPane) //Filter de lijst om alleen flowpanes te zien
				.map(node -> (FlowPane) node) //Cast de Node objecten naar FlowPane
				.collect(Collectors.toList()); //Collect het resultaat als List
		List<Label> lblSpelers = grdSpelers.getChildren().stream()
				.filter(node -> node instanceof Label)
				.map(node -> (Label) node)
				.collect(Collectors.toList());
		lblSpelers.remove(0);

		for (int i = 0; i < spelerlijst.size(); i++)
		{
			spelerScoreLabels.get(i).setText(String.format("score: %s", spelerlijst.get(i).berekenScore()));
			Speler s = spelerlijst.get(i);
			if (s.equals(spel.getHuidigeSpeler()))
			{
				spelerNaamLabels.get(i).setText(String.format("%s:", spelerlijst.get(i)));
				lblSpelers.get(i + i).setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, Font.getDefault().getSize()));
			}
			else
			{
				spelerNaamLabels.get(i).setText(String.format("%s:", spelerlijst.get(i)));
				lblSpelers.get(i + i).setFont(Font.font("Tahoma", FontWeight.NORMAL, Font.getDefault().getSize()));
			}
			List<Kaart> kaartenSpeler = s.getHand(); //voor de hand van de speler te zien
			Set<Kaart> distinct = new HashSet<Kaart>(kaartenSpeler); // distinct voor de for
			flwSpelerKaarten.get(i).getChildren().clear();  // ?????
			for (Kaart k : distinct)
			{
				ImageView imvKaart = new ImageView(kaarten.get(k.getKleur()));
				imvKaart.setFitWidth(25);
				imvKaart.setPreserveRatio(true);
				flwSpelerKaarten.get(i).getChildren().add(imvKaart); // image van de kaarten
				flwSpelerKaarten.get(i).getChildren().add(new Label("x" + Collections.frequency(kaartenSpeler, k))); // aantal naast kaarten

			}

		}

	}




}