package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domein.Gebruiker;

/**
 * Mapper voor de klasse Gebruiker.
 * Deze klasse ondersteunt de basis CRUD-bewerkingen.
 */
public class GebruikerMapper
{
    /*
     * Voegt een nieuwe gebruiker toe aan de databank.
     * De returnwaarde geeft aan of de bewerking geslaagd is.
     */
    public boolean voegGebruikerToe(Gebruiker gebruiker)
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryNieuweGebruiker = conn.prepareStatement("INSERT INTO gebruiker VALUES (?,?)");
            queryNieuweGebruiker.setString(1, gebruiker.getGebruikersnaam());
            queryNieuweGebruiker.setString(2, gebruiker.getNaam());
            queryNieuweGebruiker.executeUpdate();
            return true;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
            return false;
        }
    }

    /*
     * Zoekt de gebruiker met de opgegeven gebruikersnaam op in de databank.
     * Geeft null terug indien er geen gebruiker met deze gebruikersnaam werd gevonden.
     */
    public Gebruiker zoekGebruiker(String gebruikersnaam)
    {
        Gebruiker gebruiker = null;

        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryZoekGebruiker = conn.prepareStatement("SELECT * FROM gebruiker WHERE gnaam = ?");
            queryZoekGebruiker.setString(1, gebruikersnaam);
            try (ResultSet rs = queryZoekGebruiker.executeQuery()) {
                if (rs.next()) { // Als er een resultaat gevonden is.
                    String naam = rs.getString("naam");
                    gebruiker = new Gebruiker();
                    gebruiker.setGebruikersnaam(gebruikersnaam);
                    gebruiker.setNaam(naam);
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }

        return gebruiker;
    }

    /*
     * Zoekt alle gebruikers op in de databank.
     */
    public List<Gebruiker> zoekAlleGebruikers()
    {
        List<Gebruiker> gebruikers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryAlleGebruikers = conn.prepareStatement("SELECT * FROM gebruiker");
            try (ResultSet rs = queryAlleGebruikers.executeQuery()) {
                while (rs.next()) {
                    String gebruikersnaam = rs.getString("gnaam");
                    String naam = rs.getString("naam");
                    Gebruiker gebruiker = new Gebruiker();
                    gebruiker.setGebruikersnaam(gebruikersnaam);
                    gebruiker.setNaam(naam);
                    gebruikers.add(gebruiker);
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }

        return gebruikers;
    }

    /*
     * Past een bestaande gebruiker aan in de databank.
     * De returnwaarde geeft aan of de bewerking geslaagd is.
     */
    public boolean pasGebruikerAan(Gebruiker gebruiker)
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryUpdateGebruiker = conn.prepareStatement("UPDATE gebruiker SET naam = ? WHERE gnaam = ?");
            queryUpdateGebruiker.setString(1, gebruiker.getNaam());
            queryUpdateGebruiker.setString(2, gebruiker.getGebruikersnaam());
            queryUpdateGebruiker.executeUpdate();
            return true;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
            return false;
        }
    }

    /*
     * Verwijdert de opgegeven gebruiker uit de databank.
     * De returnwaarde geeft aan of de bewerking geslaagd is.
     */
    public boolean verwijderGebruiker(Gebruiker gebruiker)
    {
        return verwijderGebruiker(gebruiker.getGebruikersnaam());
    }

    /*
     * Verwijdert de gebruiker met de opgegeven gebruikersnaam uit de databank.
     * De returnwaarde geeft aan of de bewerking geslaagd is.
     */
    public boolean verwijderGebruiker(String gebruikersnaam)
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryVerwijderGebruiker = conn.prepareStatement("DELETE FROM gebruiker WHERE gnaam = ?");
            queryVerwijderGebruiker.setString(1, gebruikersnaam);
            queryVerwijderGebruiker.executeUpdate();
            return true;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
            return false;
        }
    }
}
