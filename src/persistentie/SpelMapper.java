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
	public boolean maakNieuwSpel(Spel spel) 
	{
        List<Speler> spelers = spel.getSpelers();
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryMaakNieuwSpel = conn.prepareStatement("INSERT INTO spel(datum) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            queryMaakNieuwSpel.setDate(1, spel.geefDatum());
            queryMaakNieuwSpel.executeUpdate();

            try (ResultSet rs = queryMaakNieuwSpel.getGeneratedKeys()) {
                while (rs.next()) {
                    int spelID = rs.getInt(1);
                    spel.setSpelID(spelID);
                }
            }

            return true;
        } catch (SQLException sql) {
            for (Throwable tw : sql) {
                tw.printStackTrace();
            }
            return false;
        }
    }
	
	public Spel geefBestaandSpel(int spelID) 
	{
        Spel spel = null;

        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryGeefBestaandSpel = conn.prepareStatement("SELECT * FROM spel WHERE spelID = ?");
            queryGeefBestaandSpel.setInt(1, spelID);

            try (ResultSet rs = queryGeefBestaandSpel.executeQuery()) {
                if (rs.next()) { 
                    spel = new Spel();
                    spel.setSpelID(spelID);
                    Calendar c = Calendar.getInstance();
                    java.util.Date datum = rs.getDate(2);
                    c.setTime(datum);
                    spel.setDatum(c);
                }
            }
        } catch (SQLException sql) {
            for (Throwable tw : sql) {
                tw.printStackTrace();
            }
        }

        return spel;
    }
}
