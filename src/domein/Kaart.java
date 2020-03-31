package domein;


public class Kaart 
{
	
	//attributen
	
	private String kleur;
	

	//constructor
	
	public Kaart()				//Defaultconstructor
	{
		
	}
	
	public Kaart(String kleur) 
	{
		setKleur(kleur);
	}

	//getters & setters
	
	
	public String getKleur() 
	{
		 return kleur;
	}

	public void setKleur(String kleur) 
	{
		 this.kleur = kleur;
	}

	
	//andere methodes
	
	
	public String toString() 
	{
		return String.format("%s", kleur );

	}
	
}