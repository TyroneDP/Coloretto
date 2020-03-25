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

	private Stapel stapel = new Stapel();

	private List<Kaart> startkleuren = new ArrayList<Kaart>();

	private Rij rij = new Rij();

	private int score = 0;

	private boolean fouteKeuze = false;
	
	
	
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
			startkleuren.add(new Kaart("Rood"));
			startkleuren.add(new Kaart("Blauw"));
			startkleuren.add(new Kaart("Bruin"));						//Groen & roos weggenomen voor makkelijker te maken.											
			startkleuren.add(new Kaart("Geel"));
			startkleuren.add(new Kaart("Grijs"));

		} else //aantalSpelers == 4 
		{
			startkleuren.add(new Kaart("Rood"));
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
				stapel.getKaarten().add(new Kaart("Rood"));
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
				stapel.getKaarten().add(new Kaart("Rood"));
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

		if (getSpelers().size()== 4) 
		{	

			if (rijKeuze == 1) 
			{
				if(rij.isVol1() == false)
				{
					if (rij.isGenomen1() == false) 
					{
						laatsteKaart = stapel.getKaarten().size()-1;

						Kaart kaart = stapel.getKaarten().get(laatsteKaart);

						rij.getDeKaartenVanDeRij1().add(kaart);

						stapel.getKaarten().remove(kaart);
					} else 
					{
						System.out.println("Rij 1 is al genomen gelieve een andere te kiezen! ");
						
						setFouteKeuze(true);
					}
				}

				else 
				{
					System.out.println("Rij 1 is vol gelieve een andere te kiezen! ");
					
					setFouteKeuze(true);
				}

			}
			else if(rijKeuze == 2)
			{
				if(rij.isVol2() == false)
				{
					if (rij.isGenomen2() == false) 
					{
						laatsteKaart = stapel.getKaarten().size()-1;

						Kaart kaart = stapel.getKaarten().get(laatsteKaart);

						rij.getDeKaartenVanDeRij2().add(kaart);

						stapel.getKaarten().remove(kaart);
					} else 
					{
						System.out.println("Rij 2 is al genomen gelieve een andere te kiezen! ");
						setFouteKeuze(true);
					}
				}

				else 
				{
					System.out.println("Rij 2 is vol gelieve een andere te kiezen! ");
					setFouteKeuze(true);
				}



			}
			else if(rijKeuze == 3)
			{
				if(rij.isVol3() == false)
				{
					if (rij.isGenomen3() == false) 
					{
						laatsteKaart = stapel.getKaarten().size()-1;

						Kaart kaart = stapel.getKaarten().get(laatsteKaart);

						rij.getDeKaartenVanDeRij3().add(kaart);

						stapel.getKaarten().remove(kaart);
					} else 
					{
						System.out.println("Rij 3 is al genomen gelieve een andere te kiezen! ");
						setFouteKeuze(true);
					}
				}

				else 
				{
					System.out.println("Rij 3 is vol gelieve een andere te kiezen! ");
					setFouteKeuze(true);
				}


			}
			else if(rijKeuze == 4)
			{
				if(rij.isVol4() == false)
				{
					if (rij.isGenomen4() == false) 
					{
						laatsteKaart = stapel.getKaarten().size()-1;

						Kaart kaart = stapel.getKaarten().get(laatsteKaart);

						rij.getDeKaartenVanDeRij4().add(kaart);

						stapel.getKaarten().remove(kaart);
					} else 
					{
						System.out.println("Rij 4 is al genomen gelieve een andere te kiezen! ");
						setFouteKeuze(true);
					}
				}

				else 
				{
					System.out.println("Rij 4 is vol gelieve een andere te kiezen! ");
					setFouteKeuze(true);
				}



			}
			else 
			{
				System.out.println("Foute invoer, keuze moet tussen 1 en 4 liggen!");
			}
		} 
		else //spelers == 5
		{
			if (rijKeuze == 1) 
			{
				if(rij.isVol1() == false)
				{
					if (rij.isGenomen1() == false) 
					{
						laatsteKaart = stapel.getKaarten().size()-1;

						Kaart kaart = stapel.getKaarten().get(laatsteKaart);

						rij.getDeKaartenVanDeRij1().add(kaart);

						stapel.getKaarten().remove(kaart);
					} else 
					{
						System.out.println("Rij 1 is al genomen gelieve een andere te kiezen! ");
						setFouteKeuze(true);
					}
				}

				else 
				{
					System.out.println("Rij 1 is vol gelieve een andere te kiezen! ");
					setFouteKeuze(true);
				}


			}
			else if(rijKeuze == 2)
			{
				if(rij.isVol2() == false)
				{
					if (rij.isGenomen2() == false) 
					{
						laatsteKaart = stapel.getKaarten().size()-1;

						Kaart kaart = stapel.getKaarten().get(laatsteKaart);

						rij.getDeKaartenVanDeRij2().add(kaart);

						stapel.getKaarten().remove(kaart);
					} else 
					{
						System.out.println("Rij 2 is al genomen gelieve een andere te kiezen! ");
						setFouteKeuze(true);
					}
				}

				else 
				{
					System.out.println("Rij 2 is vol gelieve een andere te kiezen! ");
					setFouteKeuze(true);
				}

			}
			else if(rijKeuze == 3)
			{
				if(rij.isVol3() == false)
				{
					if (rij.isGenomen3() == false) 
					{
						laatsteKaart = stapel.getKaarten().size()-1;

						Kaart kaart = stapel.getKaarten().get(laatsteKaart);

						rij.getDeKaartenVanDeRij3().add(kaart);

						stapel.getKaarten().remove(kaart);
					} else 
					{
						System.out.println("Rij 3 is al genomen gelieve een andere te kiezen! ");
						setFouteKeuze(true);
					}
				}

				else 
				{
					System.out.println("Rij 3 is vol gelieve een andere te kiezen! ");
					setFouteKeuze(true);
				}

			}
			else if(rijKeuze == 4)
			{
				if(rij.isVol4() == false)
				{
					if (rij.isGenomen4() == false) 
					{
						laatsteKaart = stapel.getKaarten().size()-1;

						Kaart kaart = stapel.getKaarten().get(laatsteKaart);

						rij.getDeKaartenVanDeRij4().add(kaart);

						stapel.getKaarten().remove(kaart);
					} else 
					{
						System.out.println("Rij 4 is al genomen gelieve een andere te kiezen! ");
						setFouteKeuze(true);
					}
				}

				else 
				{
					System.out.println("Rij 4 is vol gelieve een andere te kiezen! ");
					setFouteKeuze(true);
				}

			}else if(rijKeuze == 5)
			{
				if(rij.isVol5() == false)
				{
					if (rij.isGenomen5() == false) 
					{
						laatsteKaart = stapel.getKaarten().size()-1;

						Kaart kaart = stapel.getKaarten().get(laatsteKaart);

						rij.getDeKaartenVanDeRij5().add(kaart);

						stapel.getKaarten().remove(kaart);
					} else 
					{
						System.out.println("Rij 5 is al genomen gelieve een andere te kiezen! ");
						setFouteKeuze(true);
					}
				}

				else 
				{
					System.out.println("Rij 5 is vol gelieve een andere te kiezen! ");
					setFouteKeuze(true);
				}


			}
			else 
			{
				System.out.println("Foute invoer, keuze moet tussen 1 en 5 liggen!");
				setFouteKeuze(true);
			}

		}
	}

	public void trekRij(int trekKeuze)
	{

		if (getSpelers().size()== 4) 
		{	

			if (trekKeuze == 1) 
			{
				if(rij.isGenomen1() == false)
				{

					for (Kaart kaart : rij.getDeKaartenVanDeRij1()) 
					{
						huidigeSpeler.getHand().add(kaart); //voor elke kaart uit rij 1,voegt hij kaart toe aan hand SPELER1

					}
					rij.setGenomen1(true);					//zet genomen naar true
					rij.getDeKaartenVanDeRij1().clear();	//verwijdert kaarten uit rij 1
					getSpelersInRonde().remove(huidigeSpeler);
				}
				else if (rij.isGenomen1() == true)
				{
					System.out.println("Rij 1 is al genomen, kies een andere.");
					setFouteKeuze(true);
				}


			}
			else if(trekKeuze == 2)
			{
				if(rij.isGenomen2() == false)
				{

					for (Kaart kaart : rij.getDeKaartenVanDeRij2()) 
					{
						huidigeSpeler.getHand().add(kaart); //voor elke kaart uit rij 1,voegt hij kaart toe aan hand SPELER1
					}
					rij.setGenomen2(true);		//zet genomen naar true
					rij.getDeKaartenVanDeRij2().clear();//verwijdert kaarten uit rij 2
					getSpelersInRonde().remove(huidigeSpeler);
				}
				else if (rij.isGenomen2() == true)
				{
					System.out.println("Rij 2 is al genomen, kies een andere.");
					setFouteKeuze(true);
				}

			}
			else if(trekKeuze == 3)
			{
				if(rij.isGenomen3() == false)
				{

					for (Kaart kaart : rij.getDeKaartenVanDeRij3()) {
						huidigeSpeler.getHand().add(kaart); //voor elke kaart uit rij 1,voegt hij kaart toe aan hand SPELER1

					}
					rij.setGenomen3(true);		//zet genomen naar true
					rij.getDeKaartenVanDeRij3().clear();//verwijdert kaarten uit rij 2
					getSpelersInRonde().remove(huidigeSpeler);
				}
				else if (rij.isGenomen3() == true)
				{
					System.out.println("Rij 3 is al genomen, kies een andere.");
					setFouteKeuze(true);
				}


			}
			else if(trekKeuze == 4)
			{
				if(rij.isGenomen4() == false)
				{

					for (Kaart kaart : rij.getDeKaartenVanDeRij4()) 
					{
						huidigeSpeler.getHand().add(kaart); //voor elke kaart uit rij 1,voegt hij kaart toe aan hand SPELER1

					}
					rij.setGenomen4(true);		//zet genomen naar true
					rij.getDeKaartenVanDeRij4().clear();//verwijdert kaarten uit rij 2
					getSpelersInRonde().remove(huidigeSpeler);
				}
				else if (rij.isGenomen4() == true)
				{
					System.out.println("Rij 4 is al genomen, kies een andere.");
					setFouteKeuze(true);
				}


			}
			else 
			{
				System.out.println("Foute invoer, keuze moet tussen 1 en 4 liggen!");
				setFouteKeuze(true);
			}

		} 
		else //spelers == 5
		{

			if (trekKeuze == 1) 
			{
				if(rij.isGenomen1() == false)
				{

					for (Kaart kaart : rij.getDeKaartenVanDeRij1()) 
					{
						huidigeSpeler.getHand().add(kaart); //voor elke kaart uit rij 1,voegt hij kaart toe aan hand SPELER1

					}
					rij.setGenomen1(true);					//zet genomen naar true
					rij.getDeKaartenVanDeRij1().clear();	//verwijdert kaarten uit rij 1
					getSpelersInRonde().remove(huidigeSpeler);
				}
				else if (rij.isGenomen1() == true)
				{
					System.out.println("Rij 1 is al genomen, kies een andere.");
					setFouteKeuze(true);
				}


			}
			else if(trekKeuze == 2)
			{
				if(rij.isGenomen2() == false)
				{

					for (Kaart kaart : rij.getDeKaartenVanDeRij2()) {
						huidigeSpeler.getHand().add(kaart); //voor elke kaart uit rij 1,voegt hij kaart toe aan hand SPELER1

					}
					rij.setGenomen2(true);		//zet genomen naar true
					rij.getDeKaartenVanDeRij2().clear();//verwijdert kaarten uit rij 2
					getSpelersInRonde().remove(huidigeSpeler);
				}
				else if (rij.isGenomen2() == true)
				{
					System.out.println("Rij 2 is al genomen, kies een andere.");
					setFouteKeuze(true);
				}

			}
			else if(trekKeuze == 3)
			{
				if(rij.isGenomen3() == false)
				{

					for (Kaart kaart : rij.getDeKaartenVanDeRij3()) {
						huidigeSpeler.getHand().add(kaart); //voor elke kaart uit rij 1,voegt hij kaart toe aan hand SPELER1

					}
					rij.setGenomen3(true);		//zet genomen naar true
					rij.getDeKaartenVanDeRij3().clear();//verwijdert kaarten uit rij 2
					getSpelersInRonde().remove(huidigeSpeler);
				}
				else if (rij.isGenomen3() == true)
				{
					System.out.println("Rij 3 is al genomen, kies een andere.");
					setFouteKeuze(true);
				}


			}
			else if(trekKeuze == 4)
			{
				if(rij.isGenomen4() == false)
				{

					for (Kaart kaart : rij.getDeKaartenVanDeRij4()) {
						huidigeSpeler.getHand().add(kaart); //voor elke kaart uit rij 1,voegt hij kaart toe aan hand SPELER1

					}
					rij.setGenomen4(true);		//zet genomen naar true
					rij.getDeKaartenVanDeRij4().clear();//verwijdert kaarten uit rij 2
					getSpelersInRonde().remove(huidigeSpeler);
				}
				else if (rij.isGenomen4() == true)
				{
					System.out.println("Rij 4 is al genomen, kies een andere.");
					setFouteKeuze(true);
				}

			}

			else if(trekKeuze == 5)
			{
				if(rij.isGenomen5() == false)
				{
					for (Kaart kaart : rij.getDeKaartenVanDeRij5()) 
					{
						huidigeSpeler.getHand().add(kaart); //voor elke kaart uit rij 1,voegt hij kaart toe aan hand SPELER1

					}
					rij.setGenomen5(true);		//zet genomen naar true
					rij.getDeKaartenVanDeRij5().clear();//verwijdert kaarten uit rij 2
					getSpelersInRonde().remove(huidigeSpeler);
				}
				else if (rij.isGenomen5() == true)
				{
					System.out.println("Rij 5 is al genomen, kies een andere.");
					setFouteKeuze(true);
				}


				else 
				{
					System.out.println("Foute invoer, keuze moet tussen 1 en 5 liggen!");
				}
			}
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


				rij.setGenomen1(false);
				rij.setGenomen2(false);
				rij.setGenomen3(false);
				rij.setGenomen4(false);
				rij.setGenomen5(false);

				rij.setVol1(false);
				rij.setVol2(false);
				rij.setVol3(false);
				rij.setVol4(false);
				rij.setVol5(false);


			}

		}

	}

	/*public Speler bepaalLaatsteSpeler()
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

	public void controleerVol()
	{
		if (rij.getDeKaartenVanDeRij1().size() == 3) 
		{
			rij.setVol1(true);
		} 
		if (rij.getDeKaartenVanDeRij2().size() == 3) 
		{
			rij.setVol2(true);
		} 
		if (rij.getDeKaartenVanDeRij3().size() == 3) 
		{
			rij.setVol3(true);
		}
		if (rij.getDeKaartenVanDeRij4().size() == 3) 
		{
			rij.setVol4(true);
		}
		if (rij.getDeKaartenVanDeRij5().size() == 3) 
		{
			rij.setVol5(true);
		}
	}

	/*temporele methodes

	public void toonAantalSpelers()
	{
		System.out.printf("%s%s%n", "aantal spelers: ", spelers.size());
	}

	public void toonAantalSpelersInRonde()
	{
		System.out.printf("%s%s%n", "aantal spelers in ronde: ", spelersInRonde.size());
	}

	public void toonAantalRijen()
	{
		System.out.printf("%s%s%n", "aantal rijen:", rijen.size());
	}

	public void toonKaartenOpRij1()
	{
		System.out.printf("het aantal kaarten op rij 1: %s%n", rij.getDeKaartenVanDeRij1().size());
	}
	public void toonKaartenOpRij2()
	{
		System.out.printf("het aantal kaarten op rij 2: %s%n", rij.getDeKaartenVanDeRij2().size());
	}
	public void toonKaartenOpRij3()
	{
		System.out.printf("het aantal kaarten op rij 3: %s%n", rij.getDeKaartenVanDeRij3().size());
	}
	public void toonKaartenOpRij4()
	{
		System.out.printf("het aantal kaarten op rij 4: %s%n", rij.getDeKaartenVanDeRij4().size());
	}
	public void toonKaartenOpRij5()
	{
		System.out.printf("het aantal kaarten op rij 5: %s%n", rij.getDeKaartenVanDeRij5().size());
	}


	 */

}