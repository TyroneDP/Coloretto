package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domein.Speler;

public class SpelerMapper 
{
	public boolean voegSpelerToe(Speler speler)
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryNieuweSpeler = conn.prepareStatement("INSERT INTO speler (naam, score) VALUES (?,?)");
            queryNieuweSpeler.setString(1, speler.getNaam());
            queryNieuweSpeler.setInt(2, speler.berekenScore());
            queryNieuweSpeler.executeUpdate();
            return true;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
            return false;
        }
    }
	
	public Speler zoekSpeler(String naam)
    {
        Speler speler = null;

        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryZoekGebruiker = conn.prepareStatement("SELECT * FROM speler WHERE naam = ?");
            queryZoekGebruiker.setString(1, naam);
            try (ResultSet rs = queryZoekGebruiker.executeQuery()) {
                if (rs.next()) { // Als er een resultaat gevonden is.
                    speler = new Speler();
                    speler.setNaam(naam);
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }

        return speler;
    }
	
	public List<Speler> zoekAlleSpelers()
    {
        List<Speler> spelers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryAlleSpelers = conn.prepareStatement("SELECT * FROM speler");
            try (ResultSet rs = queryAlleSpelers.executeQuery()) {
                while (rs.next()) {
                    String naam = rs.getString("naam");
                    Speler speler = new Speler();
                    speler.setNaam(naam);
                    spelers.add(speler);
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }

        return spelers;
    }
	
	public List<Speler> geefHighscores(){
        List<Speler> highscores = new ArrayList<>();
        int teller = 1;
        
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryGeefHighscore = conn.prepareStatement("SELECT naam, score AS 'Highscore'" + 
            															"FROM speler" + 
            															"ORDER BY score DESC" + 
            															"LIMIT 10");
            
            try (ResultSet rs = queryGeefHighscore.executeQuery()) {
                while (rs.next() && teller <= 10) { // Als er een resultaat gevonden is.
                    Speler speler = new Speler();
                    String naam = rs.getString("naam");
                    speler.setNaam(naam);
                    speler.setScore(rs.getInt("score"));	
                    highscores.add(speler);
                    teller++;
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
        
        return highscores;
    }
}

