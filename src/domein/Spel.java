package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;




public class Spel
{

	//attributen

	private List<Speler> spelers = new ArrayList<Speler>();

	private List<Speler> spelersInRonde = new ArrayList<Speler>();

	private boolean laatsteRonde;

	private int ronde = 1;

	private boolean spelBezig = true;

	private List<Rij> rijen = new ArrayList<Rij>();

	private Speler huidigeSpeler;

	private Rij gekozenRij = new Rij();

	private Stapel stapel = new Stapel();

	private List<Kaart> startkleuren = new ArrayList<Kaart>();

	private int score = 0;

	private boolean fouteKeuze = false;

	private Kaart kaart;




	//methodes

	//constructor


	public Spel()
	{

	}

	//getters & setters



	public boolean isFouteKeuze() 
	{
		return fouteKeuze;
	}

	public void setFouteKeuze(boolean fouteKeuze) 
	{
		this.fouteKeuze = fouteKeuze;
	}

	public boolean isSpelBezig() 
	{
		return spelBezig;
	}

	public void setSpelBezig(boolean spelBezig) 
	{
		this.spelBezig = spelBezig;
	}

	public boolean isLaatsteRonde() 
	{
		return laatsteRonde;
	}

	public void setLaatsteRonde(boolean laatsteRonde) 
	{
		this.laatsteRonde = laatsteRonde;
	}

	public int getRonde() 
	{
		return ronde;
	}

	public void setRonde(int ronde) 
	{
		this.ronde = ronde;
	}

	public List<Speler> getSpelersInRonde() 
	{
		return spelersInRonde;
	}

	public Speler getHuidigeSpeler() 
	{
		return huidigeSpeler;
	}

	public List<Speler> getSpelers() 
	{
		return spelers;
	}

	public List<Rij> getRijen() 
	{
		return rijen;
	}

	public Stapel getStapel() 
	{
		return stapel;
	}

	//andere methodes
	public void vulStartKleuren()
	{
		//domeinlaag zetten 

		if(getSpelers().size() == 5) 
		{
			startkleuren.add(new Kaart("Oranje"));
			startkleuren.add(new Kaart("Blauw"));
			startkleuren.add(new Kaart("Bruin"));						//Groen & roos weggenomen voor makkelijker te maken.											
			startkleuren.add(new Kaart("Geel"));
			startkleuren.add(new Kaart("Grijs"));

		} else //aantalSpelers == 4 
		{
			startkleuren.add(new Kaart("Oranje"));
			startkleuren.add(new Kaart("Blauw"));
			startkleuren.add(new Kaart("Bruin"));						//Groen, roos & grijs weggenomen voor makkelijker te maken.												
			startkleuren.add(new Kaart("Geel"));
		}
	}

	public void bepaalStartKaart()
	{
		Random random = new Random();
		for(int i = 0; i < getSpelers().size() ; i++)
		{
			int startkaart = random.nextInt(startkleuren.size());									//domeinlaag zetten 
			Kaart kleur = startkleuren.get(startkaart);
			System.out.printf("%s kreeg de startkaart: %s%n", getSpelers().get(i), kleur);	
			getSpelers().get(i).getHand().add(kleur);
			startkleuren.remove(kleur);
		}
	}

	public void maakStapel()
	{	
		if (getSpelers().size() == 5) 	
		{
			for(int i = 0; i < 8 ;i++)
			{
				stapel.getKaarten().add(new Kaart("Oranje"));
				stapel.getKaarten().add(new Kaart("Blauw"));
				stapel.getKaarten().add(new Kaart("Bruin"));																
				stapel.getKaarten().add(new Kaart("Geel"));
				stapel.getKaarten().add(new Kaart("Grijs"));	
			}

			for(int i = 0; i < 9 ;i++)							    //omdat Groen & Roos uit startkleuren weg zijn
			{
				stapel.getKaarten().add(new Kaart("Groen"));
				stapel.getKaarten().add(new Kaart("Roos"));

			}

			for(int i = 0; i < 3 ;i++)							    
			{
				stapel.getKaarten().add(new Kaart("Joker"));
			}

			for(int i = 0; i < 10 ;i++)							    
			{
				stapel.getKaarten().add(new Kaart("Plus2"));
			}


		} else 

		{
			for(int i = 0; i < 8 ;i++)
			{
				stapel.getKaarten().add(new Kaart("Oranje"));
				stapel.getKaarten().add(new Kaart("Blauw"));
				stapel.getKaarten().add(new Kaart("Bruin"));																
				stapel.getKaarten().add(new Kaart("Geel"));

			}

			for(int i = 0; i < 9 ;i++)							    //omdat Groen & Roos uit startkleuren weg zijn
			{
				stapel.getKaarten().add(new Kaart("Grijs"));	
				stapel.getKaarten().add(new Kaart("Groen"));
				stapel.getKaarten().add(new Kaart("Roos"));

			}

			for(int i = 0; i < 3 ;i++)							    
			{
				stapel.getKaarten().add(new Kaart("Joker"));
			}

			for(int i = 0; i < 10 ;i++)							    
			{
				stapel.getKaarten().add(new Kaart("Plus2"));
			}
		}

		Collections.shuffle(stapel.getKaarten());
	}

	public void legOpRij(int rijKeuze)
	{
		int laatsteKaart;

		gekozenRij = rijen.get(rijKeuze - 1);

		if(gekozenRij.isVol() == false)
		{
			if (gekozenRij.isGenomen() == false) 
			{

				laatsteKaart = stapel.getKaarten().size()-1;

				Kaart kaart = stapel.getKaarten().get(laatsteKaart);

				gekozenRij.getDeKaartenVanDeRij().add(kaart);

				stapel.getKaarten().remove(kaart);

				gekozenRij.setLeeg(false);

			} else 
			{
				System.out.println("Deze Rij is al genomen gelieve een andere te kiezen! ");
			}
		}

		else 
		{
			System.out.println("Deze Rij is vol gelieve een andere te kiezen! ");
		}
	}



	public void trekRij(int trekKeuze)
	{
		gekozenRij = rijen.get(trekKeuze - 1);

		if(gekozenRij.isGenomen() == false && gekozenRij.isLeeg() == false)
		{

			for (Kaart kaart : gekozenRij.getDeKaartenVanDeRij()) 
			{
				huidigeSpeler.getHand().add(kaart); //voor elke kaart uit rij 1,voegt hij kaart toe aan hand SPELER1

			}
			gekozenRij.setGenomen(true);					//zet genomen naar true
			gekozenRij.getDeKaartenVanDeRij().clear();		//verwijdert kaarten uit rij 1
			getSpelersInRonde().remove(huidigeSpeler);


		}else if (gekozenRij.isGenomen() == true )
		{
			System.out.println("Rij is al genomen, kies een andere.");
			setFouteKeuze(true);
		}else if (gekozenRij.isLeeg() == true )
		{
			System.out.println("Rij is leeg, kies een andere.");
			setFouteKeuze(true);
		}



	}


	public Speler bepaalStartSpeler()
	{ 

		Random random = new Random();

		int gekozenSpeler = random.nextInt(spelersInRonde.size());			//kan hier fout inzitten?

		Speler startSpeler = spelersInRonde.get(gekozenSpeler);

		huidigeSpeler = startSpeler;

		return startSpeler;

	}

	public void maakRijen()
	{

		for(int i = 1; i <= spelers.size() ;i++)
		{
			rijen.add(new Rij("", i));
		}

	}

	public void laatsteRonde()
	{

		if(stapel.getKaarten().size() == 15 && isLaatsteRonde() == false)
		{ 
			System.out.println("-----LAATSTE RONDE-----");

			setLaatsteRonde(true);
		}

		if (spelersInRonde.size() == 0 && isLaatsteRonde()) 
		{

			System.out.printf("De winnaar is: %s, met %s punten, gefeliciteerd!%n", this.berekenWinnaar(), score );

			System.out.println("Het spel is afgelopen, danku om mee te spelen!");

			System.out.println("Dit spel werd mede mogelijk gemaakt door Lowie, Jonas en Tyrone.");

			setSpelBezig(false);
		}



	}

	public Speler berekenWinnaar() 
	{
		//int score = 0; hier ?
		Speler winnaar = null;
		for (Speler spelers : getSpelers()) 
		{
			if (spelers.berekenScore() > score) 
			{
				score = spelers.berekenScore();
				winnaar = spelers;
			}
		}
		return winnaar;
	}

	public void bepaalVolgendeSpeler()
	{

		if (spelersInRonde.size() == 0) 
		{
			eindeRonde();
			huidigeSpeler = spelersInRonde.get((spelersInRonde.indexOf(huidigeSpeler)+1)%spelersInRonde.size());	//hier zit fout in 
			/*huidigeSpeler = spelersInRonde.remove(0); 
			spelersInRonde.add(huidigeSpeler);*/
		}else 
		{
			huidigeSpeler = spelersInRonde.get((spelersInRonde.indexOf(huidigeSpeler)+1)%spelersInRonde.size());	//hier zit fout in 
			/*huidigeSpeler = spelersInRonde.remove(0); 
			spelersInRonde.add(huidigeSpeler);*/
		}
	}


	public void eindeRonde() 
	{
		if (spelersInRonde.size() == 0 && isLaatsteRonde() == false) 
		{

			{
				for(int i=0; i < spelers.size(); i++)
				{
					spelersInRonde.add(spelers.get(i));
				}

				setRonde(getRonde() + 1);

				System.out.printf("-----BEGIN RONDE %s-----%n", getRonde());

				//huidigeSpeler = spelersInRonde.get(laatsteSpeler);				om laatstespeler aan zet bij de volgende ronde te laten beginnen

				for (Rij gekozenRij: this.getRijen() ) 
				{
					gekozenRij.setGenomen(false);

					gekozenRij.setVol(false);
				}

			}

		}

	}

	/*
	public Speler bepaalLaatsteSpeler()
	{
		Speler laatsteSpeler = null;
		if(spelersInRonde.size() == 1)
		{
			int hulpLaatsteSpeler = spelers.indexOf(spelersInRonde.get(0));				//dit uit commentaar halen om bug te maken van laatsteSpeler dat begint in nieuwe ronde
			laatsteSpeler = spelersInRonde.get(hulpLaatsteSpeler);

		}

		return laatsteSpeler;
	}
	 */

	public String kaartenOpRijen(int rijKeuze)
	{

		kaart = new Kaart();									//anders nullpointerexception

		gekozenRij = rijen.get(rijKeuze);

		for (Kaart kaart : gekozenRij.getDeKaartenVanDeRij())			
		{
			System.out.printf("%s, " , kaart.getKleur());
		}
		
		return kaart.getKleur();
	}


	public void controleerVol()
	{

		for (Rij gekozenRij: getRijen())
		{
			if (gekozenRij.getDeKaartenVanDeRij().size() == 3)
			{
				gekozenRij.setVol(true);
			}
		}

	}

	public void controleerLeeg()
	{

		for (Rij gekozenRij: getRijen())
		{
			if (gekozenRij.getDeKaartenVanDeRij().size() != 0)
			{
				gekozenRij.setLeeg(false);
			}
			else 
			{
				gekozenRij.setLeeg(true);
			}
		}

	}
}