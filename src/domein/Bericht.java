package domein;

import domein.Gebruiker;

public class Bericht
{
    private int id;
    private Gebruiker verzender;
    private Gebruiker bestemmeling;
    private String inhoud;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Gebruiker getVerzender()
    {
        return verzender;
    }

    public void setVerzender(Gebruiker verzender)
    {
        this.verzender = verzender;
    }

    public Gebruiker getBestemmeling()
    {
        return bestemmeling;
    }

    public void setBestemmeling(Gebruiker bestemmeling)
    {
        this.bestemmeling = bestemmeling;
    }

    public String getInhoud()
    {
        return inhoud;
    }

    public void setInhoud(String inhoud)
    {
        this.inhoud = inhoud;
    }
}
