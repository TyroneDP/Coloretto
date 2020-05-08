package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Speler 
{

	//attributen

	private String naam;

	private List<Kaart> hand = new ArrayList<Kaart>();

	int score = 0;

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
	public void setScore(int score) 
	{
		this.score = score;
	}
	public int berekenScore() 
	{
		//om aantal van elke kaart bij te houden
		List<Integer> aantal = new ArrayList<Integer>();

		int 	//kleurkaarten			//elke kaart gelijk stellen aan int dat we score kunnen berekenen
		Oranje = 0,  
		Blauw = 0, 
		Bruin = 0, 
		Geel  = 0, 
		Paars = 0,
		Groen = 0, 
		Rood = 0, 

		//speciale kaarten
		Plus2 = 0, 
		Joker = 0;

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



		for (Kaart kaarten: hand) //voor elke kaart dat we toevoegen naar ons hand te checken en "toe te voegen"
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

		for (int i=0 ; i<3 ; i++)                                                    //voor de 3 rijen in hand score toevoegen
		{
			if     ((aantal.get(i)) == 1)
				score += 1;
			else 
				if ( (aantal.get(i))== 2 )
					score += 3;
				else 
					if ((aantal.get(i)) == 3 )
						score += 6;
					else 
						if ( (aantal.get(i)) == 4)
							score += 10;
						else 
							if ((aantal.get(i)) == 5)
								score += 15;
							else 
								if (aantal.get(i) >= 6)
									score += 21;
		}


		for (int i=3; i<7 ; i++)                                                    //vanaf 3 rijen neemt de score af per extra rij (tot 7, want er bestaan 7 kleuren)
		{
			if (1 == (aantal.get(i)))
				score -= 1;
			else 
				if (2 == (aantal.get(i)))
					score -= 3;
				else 
					if (3 == (aantal.get(i)))
						score -= 6;
					else 
						if (4 == (aantal.get(i)))
							score -= 10;
						else 
							if (5 == (aantal.get(i)))
								score -= 15;
							else 
								if (aantal.get(i) >= 6)
									score -= 21;
		}


		//de jokers
		for (int i=0; i<Joker; i++)													//voor Joker automatisch te leggen op de beste plek
		{
			if (aantal.get(0) <= 5)
				aantal.set(0, (aantal.get(0) + 1));
			
			else if (aantal.get(1) <= 5)
				aantal.set(1, (aantal.get(1) + 1));
			
			else aantal.set(2, (aantal.get(2) + 1));
		}

		//de plus 2'en bijvoegen
		score = score + 2 * Plus2;

		return score;
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