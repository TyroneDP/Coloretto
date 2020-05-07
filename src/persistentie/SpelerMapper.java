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
	public boolean maakNieuweSpeler(Speler speler)
	{
		try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
			PreparedStatement queryMaakNieuweSpeler = conn.prepareStatement("INSERT INTO speler (naam, score) VALUES (?,?)");
			queryMaakNieuweSpeler.setString(1, speler.getNaam());
			queryMaakNieuweSpeler.setInt(2, speler.berekenScore());
			queryMaakNieuweSpeler.executeUpdate();
			return true;
		} catch (SQLException sql) {
			for (Throwable tw : sql) {
				tw.printStackTrace();
			}
			return false;
		}
	}

	public Speler zoekSpeler(String naam)
	{
		Speler speler = null;

		try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
			PreparedStatement queryZoekSpeler = conn.prepareStatement("SELECT * FROM speler WHERE naam = ?");
			queryZoekSpeler.setString(1, naam);
			try (ResultSet rs = queryZoekSpeler.executeQuery()) {
				if (rs.next()) 
				{
					speler = new Speler();
					speler.setNaam(naam);
				}
			}
		} catch (SQLException sql) {
			for (Throwable tw : sql) {
				tw.printStackTrace();
			}
		}

		return speler;
	}

	public List<Speler> zoekAlleSpelers()
	{
		List<Speler> spelers = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
			PreparedStatement queryZoekAlleSpelers = conn.prepareStatement("SELECT naam FROM speler");
			try (ResultSet rs = queryZoekAlleSpelers.executeQuery()) {
				while (rs.next()) {
					String naam = rs.getString("naam");
					Speler speler = new Speler();
					speler.setNaam(naam);
					spelers.add(speler);
				}
			}
		} catch (SQLException sql) {
			for (Throwable tw : sql) {
				tw.printStackTrace();
			}
		}

		return spelers;
	}

	public List<Speler> geefHighscores()
	{
		List<Speler> highscores = new ArrayList<>();
		int teller = 1;

		try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
			PreparedStatement queryGeefHighscore = conn.prepareStatement("SELECT naam, score AS 'Highscore'" + 
					"FROM speler" + 
					"ORDER BY score DESC" + 
					"LIMIT 10");

			try (ResultSet rs = queryGeefHighscore.executeQuery()) {
				while (rs.next() && teller <= 10) { 
					Speler speler = new Speler();
					String naam = rs.getString("naam");
					speler.setNaam(naam);
					speler.setScore(rs.getInt("score"));	
					highscores.add(speler);
					teller++;
				}
			}
		} catch (SQLException sql) {
			for (Throwable tw : sql) {
				tw.printStackTrace();
			}
		}

		return highscores;
	}
}

