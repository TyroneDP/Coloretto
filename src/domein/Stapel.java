package domein;

import java.util.ArrayList;
import java.util.List;



public class Stapel 
{
	//attributen

	private int aantal;

	private List<Kaart> kaarten = new ArrayList<Kaart>();

	//methodes


	//constructor

	public Stapel() 
	{

	}


	//getters & setters

	public int getAantal() 
	{
		return aantal;
	}


	public List<Kaart> getKaarten() 
	{
		return kaarten;
	}

	//andere methodes


	public Kaart trekKaart()											

	{
		int laatsteKaart = kaarten.size()-1;

		Kaart kaart = kaarten.get(laatsteKaart);

		return kaart;

	}


	
	/*temporele methodes

	public void toonAantalKaarten()
	{
		System.out.printf("%s %s %n", "aantal kaarten: ", kaarten.size());
	}

	*/
}
