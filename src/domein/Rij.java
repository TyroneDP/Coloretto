package domein;

import java.util.ArrayList;
import java.util.List;

public class Rij 
{
	//attributen

	private String naam;

	private int rijNummer;

	private int aantalKaarten = 0;

	private boolean vol;
	
	private boolean genomen;
	
	private List<Kaart> deKaartenVanDeRij = new ArrayList<Kaart>();	
	
	private boolean leeg;
	
	private static final int MAX_SIZE = 3;

	//methodes

	

	//constructor
	public Rij()
	{

	}

	public Rij(String naam, int rijNummer)
	{
		setNaam("Rij");
		setRijNummer(rijNummer);

	}
	
	//getters & setters
	
	public boolean isLeeg() 
	{
		return leeg;
	}

	public void setLeeg(boolean leeg) 
	{
		this.leeg = leeg;
	}
	
	public List<Kaart> getDeKaartenVanDeRij() 
	{
		return deKaartenVanDeRij;
	}
	
	
	public String getNaam() 
	{
		return naam;
	}

	public boolean isVol() 
	{
		
		return vol;
	}

	public void setVol(boolean vol) 
	{
		this.vol = vol;
	}

	public boolean isGenomen() 
	{
		return genomen;
	}

	public void setGenomen(boolean genomen) 
	{
		this.genomen = genomen;
	}

	public void setNaam(String naam) 
	{
		this.naam = naam;
	}

	public int getRijNummer() 
	{
		return rijNummer;
	}

	public void setRijNummer(int nummer) 
	{
		this.rijNummer = nummer;
	}

	public int getAantalKaarten() 
	{
		return aantalKaarten;
	}

	public void setAantalKaarten(int aantalKaarten) 
	{
		this.aantalKaarten = aantalKaarten;
	}

}
