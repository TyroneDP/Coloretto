package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import domein.Spel;
import domein.Speler;


public class ColorettoApplicatie 
{

	Spel spel = new Spel();

	Scanner invoer = new Scanner(System.in);

	Random random = new Random();

	public void start()
	{
		startSpel();

		maakStapel();

		maakRijen();

		bepaalStartSpeler();
		
		

		do
		{	


			eindeRonde();
			
			controleerLeeg();

			controleerVol();

			rijKeuze();

			//bepaalLaatsteSpeler();		//dit uit commentaar halen om bug te maken van laatsteSpeler dat begint in nieuwe ronde



			laatsteRonde();					//hierin zit berekenScore & geefWinnaar
			if(spel.isSpelBezig() == true) 
			{
				volgendeSpeler();
			}


		}while(spel.isSpelBezig() == true);




	}
	public void startSpel()
	{
		//Welkom message
		System.out.println("Welkom bij het spel Coloretto!");

		//bepaalAantalSpelers
		int aantalSpelers;

		do
		{
			System.out.println("Met hoeveel spelers zijn jullie? : ");

			aantalSpelers = invoer.nextInt();

			if(aantalSpelers != 4 && aantalSpelers != 5)
			{
				System.out.println("Aantal spelers is fout, moet 4 of 5 zijn!");
			}


		}while(aantalSpelers != 4 && aantalSpelers != 5);	


		//bepaalSpelerNamen

		List<String> namen = new ArrayList<String>();

		int teller = 1;

		while (teller <= aantalSpelers )
		{
			System.out.printf("Geef naam speler %d in: %n", teller);

			String naam = invoer.next();

			namen.add(naam);

			spel.getSpelers().add(new Speler(naam));

			spel.getSpelersInRonde().add(new Speler(naam));


			teller++;
		}

		//VulStartKleuren
		spel.vulStartKleuren();

		//bepaalStartKaart
		//Startkaart bepalen
		spel.bepaalStartKaart();


		//Begin eerste ronde
		System.out.println("-----BEGIN RONDE 1-----");							



	}
	public void maakStapel()

	{

		//maakStapel

		spel.maakStapel();
	}
	public void maakRijen()
	{
		//maakRijen
		spel.maakRijen();	
	}
	public void bepaalStartSpeler()
	{
		System.out.printf("De startspeler is: %s%n", spel.bepaalStartSpeler());
	}	
	public void rijKeuze()
	{
		//keuze gebruiker
		int keuze;
		do
		{
			System.out.printf("%s, wat is uw keuze?: \n 1: Kaart van stapel trekken \n 2: Rijkaart trekken ", spel.getHuidigeSpeler() );

			keuze = invoer.nextInt();

			if (keuze == 1) //kaart van stapel trekken
			{
				//trekKaart 
				System.out.printf("%s trok de kaart: %s%n", spel.getHuidigeSpeler() , spel.getStapel().trekKaart()); 

				//geefKeuzeRij		
				int rijKeuze;
				do 
				{
					System.out.println("Op welke rijkaart wilt u de kaart leggen? : ");

					for(int i = 0 ; i <= spel.getSpelers().size()-1 ; i++)
					{
						System.out.printf(" %d: %s %d: ", i+1 , spel.getRijen().get(i).getNaam(), spel.getRijen().get(i).getRijNummer());
						spel.kaartenOpRijen(i);
						System.out.println();
					}	

					rijKeuze = invoer.nextInt();

					spel.setFouteKeuze(false);

					//LegOpRij  					uit Spel
					spel.legOpRij(rijKeuze);



				}while(rijKeuze < 1 || rijKeuze > spel.getSpelers().size());


			} else if(keuze == 2) //een rijkaart met kaarten (of geen) trekken
			{
				//geefTrekKeuze
				int trekKeuze;
				do 
				{
					System.out.println("Welke rijkaart wilt u nemen? : ");

					for(int i = 0 ; i <= spel.getSpelers().size()-1 ; i++)
					{
						System.out.printf(" %d: %s %d: ", i+1 , spel.getRijen().get(i).getNaam(), spel.getRijen().get(i).getRijNummer());
						spel.kaartenOpRijen(i);
						System.out.println();
					}

					trekKeuze = invoer.nextInt();

					spel.setFouteKeuze(false);

					//trekRij
					spel.trekRij(trekKeuze);

				}while(trekKeuze < 1 || trekKeuze > spel.getSpelers().size());


			}

			else
			{
				System.out.println("Foute invoer, keuze moet 1 of 2 zijn!");
			} 



		}while(keuze != 1 && keuze != 2 || spel.isFouteKeuze()== true);
	}
	public void volgendeSpeler() 
	{
		spel.bepaalVolgendeSpeler();
		System.out.printf("De volgende speler is : %s%n",spel.getHuidigeSpeler());
	}

	public void controleerVol() 
	{
		spel.controleerVol();
	}
	
	public void controleerLeeg()
	{
		spel.controleerLeeg();
	}

	public void eindeRonde()
	{
		spel.eindeRonde();
	}

	public void laatsteRonde()
	{
		spel.laatsteRonde();
	}



	public void bepaalLaatsteSpeler()
	{
		//spel.bepaalLaatsteSpeler();											//dit uit commentaar halen om bug te maken van laatsteSpeler dat begint in nieuwe ronde
	}

}
