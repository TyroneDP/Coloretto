package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import domein.Kaart;
import domein.Spel;
import domein.Speler;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;


public class SpeelScherm extends GridPane
{
	//in deze klasse alle methodes schrijven dat verband hebben met domeinlaag klasse Spel

	private Spel spel = new Spel();

	private ImageView omgedraaideKaart = new ImageView();

	private List<Speler> spelerLijst;

	private HashMap<String, Image> kaarten = new HashMap<String, Image>();

	private boolean isLaatsteRonde = false;

	private GridPane gridSpelers;

	private GridPane gridScore = new GridPane();

	private GridPane gridLinks = new GridPane();

	private List<Label> namen = new ArrayList<Label>();

	private boolean isKaartGetrokken = false;

	private GridPane gridSpelbord = new GridPane();

	private Label resterendeKaarten = new Label();

	private Button btnR1 = new Button("Neem rij 1");
	private Button btnR2 = new Button("Neem rij 2");
	private Button btnR3 = new Button("Neem rij 3");
	private Button btnR4 = new Button("Neem rij 4");
	private Button btnR5 = new Button("Neem rij 5");

	private GridPane leeg = new GridPane();

	private List<Label> scores = new ArrayList<Label>();

	private Button btnStartRonde = new Button("Start Volgende Ronde");

	private Label bericht;

	private List<FlowPane> kleineKaarten;

	public SpeelScherm(Spel spel)
	{
		this.spel = spel;

		spel.maakStapel();
		spel.maakRijen();
		spel.bepaalStartSpeler();
		spel.vulStartKleuren();
		spel.bepaalStartKaart();

		maakDeck();

		buildRechterSchermGui();
		buildLinkerSpeelSchermGui();
		vernieuwSpelersEnScore();
		resterendeKaarten();

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


	private void buildRechterSchermGui()
	{
		spelerLijst = spel.getSpelers();

		gridSpelers = new GridPane();
		gridSpelers.setAlignment(Pos.CENTER);
		Label lblSpeler = new Label("Spelers");
		setValignment(lblSpeler, VPos.TOP);
		lblSpeler.getStyleClass().add("titelSpelers");


		Label lblScore = new Label("Score");
		lblScore.getStyleClass().add("titelSpelers");
		setValignment(lblScore, VPos.TOP);
		setHalignment(lblScore, HPos.CENTER);

		gridScore.setPrefWidth(300);

		this.add(lblSpeler, 2, 0);
		this.add(lblScore, 3, 0);
		this.add(gridScore, 3, 0);
		gridScore.setAlignment(Pos.CENTER);
		gridScore.setVgap(30);


		Label Speler1Naam = new Label(String.format("%s",spelerLijst.get(0)));
		Label Speler2Naam = new Label(String.format("%s",spelerLijst.get(1)));
		Label Speler3Naam = new Label(String.format("%s",spelerLijst.get(2)));
		Label Speler4Naam = new Label(String.format("%s",spelerLijst.get(3)));
		Speler1Naam.setStyle("-fx-font-size:15");
		Speler2Naam.setStyle("-fx-font-size:15");
		Speler3Naam.setStyle("-fx-font-size:15");
		Speler4Naam.setStyle("-fx-font-size:15");
		Speler1Naam.getStyleClass().add("vet");
		Speler2Naam.getStyleClass().add("vet");
		Speler3Naam.getStyleClass().add("vet");
		Speler4Naam.getStyleClass().add("vet");

		Label Speler1Score = new Label((String.format("Score %s: %s", spelerLijst.get(0),spelerLijst.get(0).berekenScore())));
		Label Speler2Score = new Label((String.format("Score %s: %s", spelerLijst.get(1),spelerLijst.get(1).berekenScore())));
		Label Speler3Score = new Label((String.format("Score %s: %s", spelerLijst.get(2),spelerLijst.get(2).berekenScore())));
		Label Speler4Score = new Label((String.format("Score %s: %s", spelerLijst.get(3),spelerLijst.get(3).berekenScore())));
		Speler1Score.setStyle("-fx-font-size:15");
		Speler2Score.setStyle("-fx-font-size:15");
		Speler3Score.setStyle("-fx-font-size:15");
		Speler4Score.setStyle("-fx-font-size:15");
		setHalignment(Speler1Score, HPos.CENTER);
		setHalignment(Speler2Score, HPos.CENTER);
		setHalignment(Speler3Score, HPos.CENTER);
		setHalignment(Speler4Score, HPos.CENTER);
		Speler1Score.getStyleClass().add("vet");
		Speler2Score.getStyleClass().add("vet");
		Speler3Score.getStyleClass().add("vet");
		Speler4Score.getStyleClass().add("vet");

		FlowPane Speler1Kaarten = new FlowPane();
		FlowPane Speler2Kaarten = new FlowPane();
		FlowPane Speler3Kaarten = new FlowPane();
		FlowPane Speler4Kaarten = new FlowPane();

		gridSpelers.add(Speler1Naam, 0, 1);
		gridSpelers.add(Speler2Naam, 0, 3);
		gridSpelers.add(Speler3Naam, 0, 5);
		gridSpelers.add(Speler4Naam, 0, 7);

		namen.add(Speler1Naam);
		namen.add(Speler2Naam);
		namen.add(Speler3Naam);
		namen.add(Speler4Naam);

		gridSpelers.add(Speler1Kaarten, 0, 2);
		gridSpelers.add(Speler2Kaarten, 0, 4);
		gridSpelers.add(Speler3Kaarten, 0, 6);
		gridSpelers.add(Speler4Kaarten, 0, 8);

		gridScore.add(Speler1Score, 0, 2);
		gridScore.add(Speler2Score, 0, 4);
		gridScore.add(Speler3Score, 0, 6);
		gridScore.add(Speler4Score, 0, 8);

		scores.add(Speler1Score);
		scores.add(Speler2Score);
		scores.add(Speler3Score);
		scores.add(Speler4Score);

		Speler1Score.setMinWidth(100);
		Speler2Score.setMinWidth(100);
		Speler3Score.setMinWidth(100);
		Speler4Score.setMinWidth(100);

		if(spelerLijst.size()==5)
		{
			Label Speler5Naam = new Label(String.format("%s",spelerLijst.get(4)));
			Speler5Naam.setStyle("-fx-font-size:15");
			Label Speler5Score = new Label((String.format("Score %s : %s", spelerLijst.get(4), spelerLijst.get(4).berekenScore())));
			Speler5Score.setStyle("-fx-font-size:15");
			setHalignment(Speler5Score, HPos.CENTER);
			FlowPane Speler5Kaarten = new FlowPane();
			gridSpelers.add(Speler5Naam, 0, 9);
			namen.add(Speler5Naam);
			gridSpelers.add(Speler5Kaarten, 0, 10);
			gridScore.add(Speler5Score, 0, 10);
			scores.add(Speler5Score);
			Speler5Score.setMinWidth(100);
			Speler5Score.getStyleClass().add("vet");
			Speler5Naam.getStyleClass().add("vet");
		}

		gridSpelers.setPrefWidth(300);
		gridSpelers.setVgap(10);

		leeg.setPrefWidth(300);
		this.add(leeg, 1 , 0);
		this.add(gridSpelers, 2, 0);


	}

	private void buildLinkerSpeelSchermGui()
	{	
		this.setHgap(1);
		this.setVgap(1);
		this.setPadding(new Insets(1, 1, 1, 1));

		Label lblStapel = new Label("    Stapel:");
		lblStapel.setStyle("-fx-font-size:30");
		gridSpelbord.add(lblStapel, 0,0 );

		omgedraaideKaart.setFitWidth(150);

		setHalignment(gridSpelbord,HPos.LEFT);

		gridLinks.add(gridSpelbord, 0, 0);

		ImageView imvStapel = new ImageView(kaarten.get("achterkant"));
		imvStapel.setFitWidth(150);
		imvStapel.setPreserveRatio(true);

		gridSpelbord.add(imvStapel,0,1);

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



		gridLinks.add(fpRij1, 0, 1);
		gridLinks.add(fpRij2, 0, 2);
		gridLinks.add(fpRij3, 0, 3);
		gridLinks.add(fpRij4, 0, 4);

		gridLinks.add(btnR1, 0, 1);
		gridLinks.add(btnR2, 0, 2);
		gridLinks.add(btnR3, 0, 3);
		gridLinks.add(btnR4, 0, 4);	

		fpRij1.setAlignment(Pos.CENTER_RIGHT);
		fpRij2.setAlignment(Pos.CENTER_RIGHT);
		fpRij3.setAlignment(Pos.CENTER_RIGHT);
		fpRij4.setAlignment(Pos.CENTER_RIGHT);

		ImageView pk = new ImageView(kaarten.get("puntenkaart"));
		pk.setFitWidth(150);
		pk.setPreserveRatio(true);

		gridLinks.add(pk, 1, 0);

		/*gridLinks.setGridLinesVisible(true);
		this.setGridLinesVisible(true);								//als men de lijnen van de GridPanes wilt zien
		gridSpelbord.setGridLinesVisible(true);
		gridSpelers.setGridLinesVisible(true);
		gridScore.setGridLinesVisible(true);
		 */


		this.add(gridLinks, 0, 0);

		if (spelerLijst.size()==5)
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


			gridLinks.add(fpRij5, 0,5 );
			fpRij5.setAlignment(Pos.CENTER_RIGHT);

			imvR51.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() 
			{
				@Override
				public void handle(MouseEvent event) 
				{
					if (isKaartGetrokken && !kaarten.get("genomenRij").equals(imvR51.getImage()) && kaarten.get("rij").equals(imvR51.getImage())) 
					{
						imvR51.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

						omgedraaideKaart.setImage(kaarten.get("achterkant"));

						isKaartGetrokken = false;

						resterendeKaarten();

						spel.bepaalVolgendeSpeler();
						bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
					}
				}});
			imvR52.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) 
				{
					if (isKaartGetrokken && !kaarten.get("genomenRij").equals(imvR52.getImage()) && kaarten.get("rij").equals(imvR52.getImage())) 
					{
						imvR52.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

						omgedraaideKaart.setImage(kaarten.get("achterkant"));

						isKaartGetrokken = false;

						resterendeKaarten();
						spel.bepaalVolgendeSpeler();
						bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
					}


				}});
			imvR53.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) 
				{
					if (isKaartGetrokken && !kaarten.get("genomenRij").equals(imvR53.getImage()) && kaarten.get("rij").equals(imvR53.getImage())) 
					{
						imvR53.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

						omgedraaideKaart.setImage(kaarten.get("achterkant"));

						isKaartGetrokken = false;

						resterendeKaarten();
						spel.bepaalVolgendeSpeler();
						bericht.setText(String.format("%s ",spel.getHuidigeSpeler()));
					}

				}});

			btnR5.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) 
				{
					if(!kaarten.get("genomenRij").equals(imvR51.getImage()) && !isKaartGetrokken)
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

						//Rij 1 kaart 2
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
						spel.bepaalVolgendeSpeler();
						bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));

					}
					vernieuwSpelersEnScore();
				}});

			btnStartRonde.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) 
				{
					if (kaarten.get("genomenRij").equals(imvR11.getImage()) && kaarten.get("genomenRij").equals(imvR21.getImage()) && kaarten.get("genomenRij").equals(imvR31.getImage()) && kaarten.get("genomenRij").equals(imvR41.getImage())) 
					{

						imvR51.setImage(kaarten.get("rij"));
						imvR52.setImage(kaarten.get("rij"));
						imvR53.setImage(kaarten.get("rij"));



						bericht.setText(String.format("De volgende ronde is begonnen, %s is aan de beurt.",
								spel.getHuidigeSpeler()));
					}

				}});
			gridLinks.add(btnR5, 0, 5);
		}



		omgedraaideKaart = new ImageView(kaarten.get("achterkant"));
		gridSpelbord.add(omgedraaideKaart, 1, 1);

		bericht = new Label();
		bericht.setPrefWidth(350);
		bericht.setAlignment(Pos.CENTER);
		bericht.setWrapText(true);
		bericht.setText(String.format("Het is aan speler %s, trek een kaart.",spel.getHuidigeSpeler()));
		gridSpelbord.add(bericht,2 , 1);
		bericht.getStyleClass().add("vet");

		imvStapel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (!isKaartGetrokken) 
				{
					omgedraaideKaart.setImage(kaarten.get(spel.trekVanStapelFx().getKleur()));
					isKaartGetrokken = true;
					bericht.setText(
							String.format("%s trok een kaart, leg ze op een rij",
									spel.getHuidigeSpeler()));
				}

			}});

		imvR11.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isKaartGetrokken && !kaarten.get("genomenRij").equals(imvR11.getImage()) && kaarten.get("rij").equals(imvR11.getImage())) 
				{
					imvR11.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isKaartGetrokken = false;

					resterendeKaarten();
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}

			}});	
		imvR12.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isKaartGetrokken && !kaarten.get("genomenRij").equals(imvR12.getImage()) && kaarten.get("rij").equals(imvR12.getImage())) 
				{
					imvR12.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isKaartGetrokken = false;

					resterendeKaarten();
					spel.bepaalVolgendeSpeler();
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}


			}});
		imvR13.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isKaartGetrokken && !kaarten.get("genomenRij").equals(imvR13.getImage()) && kaarten.get("rij").equals(imvR13.getImage())) 
				{
					imvR13.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isKaartGetrokken = false;

					resterendeKaarten();
					spel.bepaalVolgendeSpeler();
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}

			}});

		imvR21.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isKaartGetrokken && !kaarten.get("genomenRij").equals(imvR21.getImage()) && kaarten.get("rij").equals(imvR21.getImage())) 
				{
					imvR21.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isKaartGetrokken = false;

					resterendeKaarten();
					spel.bepaalVolgendeSpeler();
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}


			}});
		imvR22.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isKaartGetrokken && !kaarten.get("genomenRij").equals(imvR22.getImage()) && kaarten.get("rij").equals(imvR22.getImage())) 
				{
					imvR22.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isKaartGetrokken = false;

					resterendeKaarten();
					spel.bepaalVolgendeSpeler();
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}


			}});
		imvR23.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isKaartGetrokken && !kaarten.get("genomenRij").equals(imvR23.getImage()) && kaarten.get("rij").equals(imvR23.getImage())) 
				{
					imvR23.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isKaartGetrokken = false;

					resterendeKaarten();
					spel.bepaalVolgendeSpeler();
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}


			}});

		imvR31.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isKaartGetrokken && !kaarten.get("genomenRij").equals(imvR31.getImage()) && kaarten.get("rij").equals(imvR31.getImage())) 
				{
					imvR31.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isKaartGetrokken = false;

					resterendeKaarten();
					spel.bepaalVolgendeSpeler();
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}


			}});
		imvR32.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isKaartGetrokken&& !kaarten.get("genomenRij").equals(imvR32.getImage()) && kaarten.get("rij").equals(imvR32.getImage())) 
				{
					imvR32.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isKaartGetrokken = false;

					resterendeKaarten();
					spel.bepaalVolgendeSpeler();
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}


			}});
		imvR33.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) 
			{
				if (isKaartGetrokken && !kaarten.get("genomenRij").equals(imvR33.getImage()) && kaarten.get("rij").equals(imvR33.getImage())) 
				{
					imvR33.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isKaartGetrokken = false;

					resterendeKaarten();
					spel.bepaalVolgendeSpeler();
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}


			}});

		imvR41.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isKaartGetrokken&& !kaarten.get("genomenRij").equals(imvR41.getImage()) && kaarten.get("rij").equals(imvR41.getImage())) 
				{
					imvR41.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isKaartGetrokken = false;

					resterendeKaarten();
					spel.bepaalVolgendeSpeler();
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}


			}});
		imvR42.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isKaartGetrokken && !kaarten.get("genomenRij").equals(imvR42.getImage()) && kaarten.get("rij").equals(imvR42.getImage())) 
				{
					imvR42.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isKaartGetrokken = false;

					resterendeKaarten();
					spel.bepaalVolgendeSpeler();
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}


			}});
		imvR43.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if (isKaartGetrokken && !kaarten.get("genomenRij").equals(imvR43.getImage()) && kaarten.get("rij").equals(imvR43.getImage())) 
				{
					imvR43.setImage(kaarten.get(spel.trekGetrokkenKaartFx().getKleur()));

					omgedraaideKaart.setImage(kaarten.get("achterkant"));

					isKaartGetrokken = false;

					resterendeKaarten();
					spel.bepaalVolgendeSpeler();
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}


			}});



		btnR1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{
				if(!kaarten.get("genomenRij").equals(imvR11.getImage()) && !isKaartGetrokken)
				{
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
					spel.bepaalVolgendeSpeler();
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}

				vernieuwSpelersEnScore();

			}});

		btnR2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{

				if(!kaarten.get("genomenRij").equals(imvR21.getImage()) && !isKaartGetrokken)
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
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}


				vernieuwSpelersEnScore();


			}});

		btnR3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) 
			{

				if(!kaarten.get("genomenRij").equals(imvR31.getImage()) && !isKaartGetrokken)
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
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}
				vernieuwSpelersEnScore();

			}});

		btnR4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
				if(!kaarten.get("genomenRij").equals(imvR41.getImage()) && !isKaartGetrokken)
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
					bericht.setText(String.format("Het is aan speler %s",spel.getHuidigeSpeler()));
				}

				vernieuwSpelersEnScore();

			}});

		btnStartRonde.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
				if (kaarten.get("genomenRij").equals(imvR11.getImage()) && kaarten.get("genomenRij").equals(imvR21.getImage()) && kaarten.get("genomenRij").equals(imvR31.getImage()) && kaarten.get("genomenRij").equals(imvR41.getImage()) && !isLaatsteRonde) 
				{
					imvR11.setImage(kaarten.get("rij"));
					imvR12.setImage(kaarten.get("rij"));
					imvR13.setImage(kaarten.get("rij"));

					imvR21.setImage(kaarten.get("rij"));
					imvR22.setImage(kaarten.get("rij"));
					imvR23.setImage(kaarten.get("rij"));

					imvR31.setImage(kaarten.get("rij"));
					imvR32.setImage(kaarten.get("rij"));
					imvR33.setImage(kaarten.get("rij"));

					imvR41.setImage(kaarten.get("rij"));
					imvR42.setImage(kaarten.get("rij"));
					imvR43.setImage(kaarten.get("rij"));



					bericht.setText(String.format("De volgende ronde is begonnen, %s is aan de beurt.",
							spel.getHuidigeSpeler()));



				}
				if(isLaatsteRonde == true)
				{
					btnStartRonde.setText("Klik om de winnaar te zien!");
					bericht.setText(String.format("%s is de winnaar",spel.berekenWinnaar()));


				}

			}});
		leeg.setAlignment(Pos.CENTER);
		leeg.add(btnStartRonde, 0,1);

		gridSpelbord.add(resterendeKaarten,1,0);
		resterendeKaarten.getStyleClass().add("vet");

	}

	private void vernieuwSpelersEnScore()
	{
		//https://stackoverflow.com/questions/35740543/check-instanceof-in-stream
		kleineKaarten = gridSpelers.getChildren().stream().filter(fp -> fp instanceof FlowPane).map(fp -> (FlowPane) fp).collect(Collectors.toList());


		for (int i = 0; i < spelerLijst.size(); i++)
		{
			scores.get(i).setText(String.format("Score %s: %s", spelerLijst.get(i) , spelerLijst.get(i).berekenScore()));


			kleineKaarten.get(i).getChildren().clear();  // Als we een rij nemen, krijgen de spelers nog een paar startkaarten


			Speler alleSpelers = spelerLijst.get(i);



			for (Kaart kaart: alleSpelers.getHand())
			{
				ImageView imvKaart = new ImageView(kaarten.get(kaart.getKleur()));
				kleineKaarten.get(i).getChildren().add(imvKaart);
				imvKaart.setPreserveRatio(true);
				imvKaart.setFitWidth(30);
			}

		}

	}
	private void resterendeKaarten()
	{

		if(spel.getStapel().getKaarten().size() == 15) 
		{
			bericht.setText(String.format("De laatste ronde is begonnen, %s is aan de beurt.",spel.getHuidigeSpeler()));
			isLaatsteRonde = true;
			btnStartRonde.setText("Klik om de winnaar te zien");
		}

		resterendeKaarten.setText(String.format("Aantal kaarten: %s",  spel.getStapel().getKaarten().size()));
		setHalignment(resterendeKaarten, HPos.CENTER);
	}
}