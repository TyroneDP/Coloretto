package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Speler 
{

	//attributen

	private String naam;

	private List<Kaart> hand = new ArrayList<Kaart>();



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

		List<Integer> aantal = new ArrayList<Integer>();

		int score = 0;

		int 	Oranje = 0,  
				Blauw = 0, 
				Bruin = 0, 
				Geel  = 0, 
				Paars = 0,
				Groen = 0, 
				Rood = 0, 
				Plus2 = 0, 
				Joker = 0;
		
		for (Kaart k: hand) 
		{

			if 		   (k.getKleur() == "Blauw") Blauw++;
			else 	if (k.getKleur() == "Bruin") Bruin++;
			else	if (k.getKleur() == "Rood") Rood++;
			else	if (k.getKleur() == "Groen") Groen++;
			else	if (k.getKleur() == "Oranje") Oranje++;
			else	if (k.getKleur() == "Paars") Paars++;
			else	if (k.getKleur() == "Geel") Geel++;
			else	if (k.getKleur() == "Plus2") Plus2++;
			else	if (k.getKleur() == "Joker") Joker++;

		}

		aantal.add(Blauw);
		aantal.add(Bruin);
		aantal.add(Rood);
		aantal.add(Groen);
		aantal.add(Oranje);
		aantal.add(Paars);
		aantal.add(Geel);

		Collections.sort(aantal, Collections.reverseOrder());



		for (int i=0; i<Joker; i++)								//voor Joker automatisch te leggen op de beste plek
		{
			if (aantal.get(0) <= 5)
				aantal.set(0, (aantal.get(0) + 1));
			else if (aantal.get(1) <= 5)
				aantal.set(1, (aantal.get(1) + 1));
			else aantal.set(2, (aantal.get(2) + 1));
		}

		for (int i=0 ; i<3 ; i++)													//voor de 3 rijen score toevoegen
		{
			if (Integer.valueOf(1).equals(aantal.get(i)))
				score += 1;
			else if (Integer.valueOf(2).equals(aantal.get(i)))
				score += 3;
			else if (Integer.valueOf(3).equals(aantal.get(i)))
				score += 6;
			else if (Integer.valueOf(4).equals(aantal.get(i)))
				score += 10;
			else if (Integer.valueOf(5).equals(aantal.get(i)))
				score += 15;
			else if (aantal.get(i) >= 6)
				score += 21;
		}
		
		for (int i=3; i<7 ; i++)													//vanaf 3 rijen neemt de score af per extra rij (tot 7, want er bestaan 7 kleuren)
		{
			if (Integer.valueOf(1).equals(aantal.get(i)))
				score -= 1;
			else if (Integer.valueOf(2).equals(aantal.get(i)))
				score -= 3;
			else if (Integer.valueOf(3).equals(aantal.get(i)))
				score -= 6;
			else if (Integer.valueOf(4).equals(aantal.get(i)))
				score -= 10;
			else if (Integer.valueOf(5).equals(aantal.get(i)))
				score -= 15;
			else if (aantal.get(i) >= 6)
				score -= 21;
		}

		score += 2 * Plus2;
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