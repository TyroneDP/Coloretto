package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Speler 
{

	//attributen

	private String naam;

	private List<Kaart> hand = new ArrayList<Kaart>();

	private List<Integer> aantal;

	private int score;

	//methodes


	//constructor
	public Speler()
	{

	}

	public Speler(String naam)
	{
		setNaam(naam);
	}

	//getters & setters

	public int berekenScore() 
	{
		score = 0;

		aantal = new ArrayList<Integer>();

		int 	Oranje = 0,  
				Blauw = 0, 
				Bruin = 0, 
				Geel  = 0, 
				Paars = 0,
				Groen = 0, 
				Rood = 0, 
				Plus2 = 0, 
				Joker = 0;

		for (Kaart kaarten: hand) 						//voor elke kaart dat we toevoegen naar ons hand te checken en "toe te voegen"
		{

			if ("Oranje".equals(kaarten.getKleur()) )
			{
				Oranje++;
			}

			else if ("Blauw".equals(kaarten.getKleur()))
			{
				Blauw++;
			}

			else if ("Bruin".equals(kaarten.getKleur()))
			{
				Bruin++;
			}

			else if ("Geel".equals(kaarten.getKleur()))
			{
				Geel++;
			}

			else if ("Paars".equals(kaarten.getKleur()))
			{
				Paars++;
			}

			else if ("Groen".equals(kaarten.getKleur()))
			{
				Groen++;
			}

			else if ("Rood".equals(kaarten.getKleur()))
			{
				Rood++;
			}

			else if ("Plus2".equals(kaarten.getKleur()))
			{
				Plus2++;
			}

			else if ("Joker".equals(kaarten.getKleur()))
			{
				Joker++;
			}
		}


		//alles in onze lijst van aantallen steken
		aantal.add(Oranje);
		aantal.add(Blauw);
		aantal.add(Bruin);
		aantal.add(Geel);
		aantal.add(Paars);
		aantal.add(Groen);
		aantal.add(Rood);
		aantal.add(Plus2);
		aantal.add(Joker);

		//anders foute score
		Collections.sort(aantal, Collections.reverseOrder());	//elke waarde sorteren, maar is van klein naar groot, hierna reverseorder op doen anders foute score




		for (int x=0 ; x < Joker; x++)								//voor Joker automatisch te leggen op de beste plek
		{
			for(int y=0; y<3; y++)
			{
				if(aantal.get(y) < 6)
					aantal.set(y, (aantal.get(y) + 1));

			}

		}




		//+ het berekenen van de score voor 3 rijen
		for(int i=0 ; i<3 ; i++)	              //voor de 3 rijen in hand score toevoegen
		{
			if     (aantal.get(i) == 1)
			{
				score = score + 1;
			}

			else if(aantal.get(i) == 2)
			{
				score = score + 3;
			}

			else if(aantal.get(i) == 3)
			{
				score = score + 6;
			}

			else if(aantal.get(i) == 4)
			{
				score = score + 10;
			}

			else if(aantal.get(i) == 5)
			{
				score = score + 15;
			}

			else if(aantal.get(i) >= 6)
			{
				score = score + 21;
			}
		}

		//- het berekenen van de minscore vanaf 3 rijen
		for (int i=3; i<7 ; i++)	          //voor de 3 rijen in hand score toevoegen
		{
			if     (aantal.get(i) == 1)
			{
				score = score - 1;
			}

			else if(aantal.get(i) == 2)
			{
				score = score - 3;
			}

			else if(aantal.get(i) == 3)
			{
				score = score - 6;
			}

			else if(aantal.get(i) == 4)
			{
				score = score - 10;
			}

			else if(aantal.get(i) == 5)
			{
				score = score - 15;
			}

			else if(aantal.get(i) >= 6)
			{
				score = score - 21;
			}
		}





		score = 2 * Plus2 + score;

		return score;
	}	
	
	


	public void setScore(int score) 
	{
		this.score = score;
	}

	public List<Kaart> getHand() 
	{
		return hand;
	}


	public String getNaam() 
	{
		return naam;
	}

	public void setNaam(String naam) 
	{
		this.naam = naam;
	}


	//andere methodes


	public String toString() 
	{
		return String.format("%s", naam);

	}

}