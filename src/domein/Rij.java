package domein;

import java.util.ArrayList;
import java.util.List;

public class Rij 
{
	//attributen

	private String naam;

	private int rijNummer;

	private int aantalKaarten = 0;

	private boolean vol1;
	private boolean vol2;
	private boolean vol3;
	private boolean vol4;
	private boolean vol5;

	private boolean genomen1;
	private boolean genomen2;
	private boolean genomen3;
	private boolean genomen4;
	private boolean genomen5;

	private List<Kaart> deKaartenVanDeRij1 = new ArrayList<Kaart>();		
	private List<Kaart> deKaartenVanDeRij2 = new ArrayList<Kaart>();	
	private List<Kaart> deKaartenVanDeRij3 = new ArrayList<Kaart>();	
	private List<Kaart> deKaartenVanDeRij4 = new ArrayList<Kaart>();	
	private List<Kaart> deKaartenVanDeRij5 = new ArrayList<Kaart>();	


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

	public String getNaam() 
	{
		return naam;
	}

	public void setNaam(String naam) 
	{
		this.naam = naam;
	}

	public boolean isGenomen1() 
	{
		return genomen1;
	}

	public void setGenomen1(boolean genomen1) 
	{
		this.genomen1 = genomen1;
	}

	public boolean isGenomen2() {
		return genomen2;
	}

	public void setGenomen2(boolean genomen2) 
	{
		this.genomen2 = genomen2;
	}

	public boolean isGenomen3() 
	{
		return genomen3;
	}

	public void setGenomen3(boolean genomen3) 
	{
		this.genomen3 = genomen3;
	}

	public boolean isGenomen4() 
	{
		return genomen4;
	}

	public void setGenomen4(boolean genomen4) 
	{
		this.genomen4 = genomen4;
	}

	public boolean isGenomen5() 
	{
		return genomen5;
	}

	public void setGenomen5(boolean genomen5) 
	{
		this.genomen5 = genomen5;
	}

	public boolean isVol1() 
	{
		return vol1;
	}

	public void setVol1(boolean vol1) 
	{
		this.vol1 = vol1;
	}

	public boolean isVol2()
	{
		return vol2;
	}

	public void setVol2(boolean vol2)
	{
		this.vol2 = vol2;
	}

	public boolean isVol3()
	{
		return vol3;
	}

	public void setVol3(boolean vol3)
	{
		this.vol3 = vol3;
	}

	public boolean isVol4()
	{
		return vol4;
	}

	public void setVol4(boolean vol4)
	{
		this.vol4 = vol4;
	}

	public boolean isVol5()
	{
		return vol5;
	}

	public void setVol5(boolean vol5)
	{
		this.vol5 = vol5;
	}

	public List<Kaart> getDeKaartenVanDeRij1() 
	{
		return deKaartenVanDeRij1;
	}

	public List<Kaart> getDeKaartenVanDeRij2()
	{
		return deKaartenVanDeRij2;
	}

	public List<Kaart> getDeKaartenVanDeRij3()
	{
		return deKaartenVanDeRij3;
	}

	public List<Kaart> getDeKaartenVanDeRij4()
	{
		return deKaartenVanDeRij4;
	}

	public List<Kaart> getDeKaartenVanDeRij5()
	{
		return deKaartenVanDeRij5;
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

	/*andere methodes

	public void toonKaartenOpRij1()
	{
		System.out.printf("het aantal kaarten op rij 1: %s%n", getDeKaartenVanDeRij1().size());
	}
	
	
	*/
}
