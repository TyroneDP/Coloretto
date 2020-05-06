package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import domein.Spel;
import domein.Speler;

public class SpelMapper 
{
	public boolean voegSpelToe(Spel spel) 
	{
        List<Speler> spelers = spel.getSpelers();
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryNieuwSpel = conn.prepareStatement("INSERT INTO spel(datum) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            queryNieuwSpel.setDate(1, spel.geefDatum());
            queryNieuwSpel.executeUpdate();

            try (ResultSet rs = queryNieuwSpel.getGeneratedKeys()) {
                while (rs.next()) {
                    int spelID = rs.getInt(1);
                    spel.setSpelID(spelID);
                }
            }

            return true;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
            return false;
        }
    }
	
	public Spel geefSpel(int spelID) 
	{
        Spel spel = null;

        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryGeefSpel = conn.prepareStatement("SELECT * FROM spel WHERE spelID = ?");
            queryGeefSpel.setInt(1, spelID);

            try (ResultSet rs = queryGeefSpel.executeQuery()) {
                if (rs.next()) { // Als er een resultaat gevonden is.
                    spel = new Spel();
                    spel.setSpelID(spelID);
                    Calendar cal = Calendar.getInstance();
                    java.util.Date datum = rs.getDate(2);
                    cal.setTime(datum);
                    spel.setDatum(cal);
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }

        return spel;
    }
}
