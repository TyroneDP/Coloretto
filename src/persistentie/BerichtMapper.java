package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domein.Bericht;
import domein.Gebruiker;

/**
 * Mapper voor de klasse Bericht.
 * Deze klasse werkt gelijkaardig aan GebruikerMapper, met enkele belangrijke verschillen
 * (zie opmerkingen in commentaar).
 */
public class BerichtMapper
{
    /*
     * Opmerking: het bericht dat je toevoegt heeft nog geen id. Dit id zal gegenereerd worden door
     * de databank en ingevuld worden door deze methode.
     */
    public boolean voegBerichtToe(Bericht bericht)
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryNieuwBericht = conn.prepareStatement("INSERT INTO BERICHT (van, aan, inhoud) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            queryNieuwBericht.setString(1, bericht.getVerzender().getGebruikersnaam());
            queryNieuwBericht.setString(2, bericht.getBestemmeling().getGebruikersnaam());
            queryNieuwBericht.setString(3, bericht.getInhoud());
            queryNieuwBericht.executeUpdate();
            
            try (ResultSet rs = queryNieuwBericht.getGeneratedKeys()) {
                if (rs.next()) {
                    int nieuweId = rs.getInt(1);
                    bericht.setId(nieuweId);
                } else {
                    return false;
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
    
    /*
     * Opmerking: wanneer we een bericht uitlezen krijgen we geen Gebruiker objecten (die we nodig
     * hebben) maar enkel Strings (de gebruikersnamen). We gebruiken hier een GebruikerMapper om
     * volledige Gebruiker objecten bekomen. We werken objectgeorienteerd!
     */
    public Bericht zoekBericht(int id)
    {
        Bericht bericht = null;
        GebruikerMapper gm = new GebruikerMapper();
        
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryZoekBericht = conn.prepareStatement("SELECT * FROM bericht WHERE id = ?");
            queryZoekBericht.setInt(1, id);
            try (ResultSet rs = queryZoekBericht.executeQuery()) {
                if (rs.next()) {
                    String van = rs.getString("van");
                    String aan = rs.getString("aan");
                    String inhoud = rs.getString("inhoud");
                    
                    bericht = new Bericht();
                    bericht.setId(id);
                    bericht.setVerzender(gm.zoekGebruiker(van));    // We willen het bijhorende Gebruiker object, niet enkel de gebruikersnaam.
                    bericht.setBestemmeling(gm.zoekGebruiker(aan)); // Daarom gebruiken we hier tweemaal een GebruikerMapper.
                    bericht.setInhoud(inhoud);
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
        
        return bericht;
    }
    
    /*
     * Hier geldt dezelfde opmerking i.v.m. het gebruik van een GebruikerMapper.
     */
    public List<Bericht> zoekBerichtenVanGebruiker(Gebruiker gebruiker)
    {
        List<Bericht> berichten = new ArrayList<>();
        GebruikerMapper gm = new GebruikerMapper();
        
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryBerichtenVan = conn.prepareStatement("SELECT * FROM bericht WHERE van = ?");
            queryBerichtenVan.setString(1, gebruiker.getGebruikersnaam());
            try (ResultSet rs = queryBerichtenVan.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String aan = rs.getString("aan");
                    String inhoud = rs.getString("inhoud");
                    
                    Bericht bericht = new Bericht();
                    bericht.setId(id);
                    bericht.setVerzender(gebruiker);
                    bericht.setBestemmeling(gm.zoekGebruiker(aan));
                    bericht.setInhoud(inhoud);
                    
                    berichten.add(bericht);
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
        
        return berichten;
    }
    
    public boolean pasBerichtAan(Bericht bericht)
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryUpdateBericht = conn.prepareStatement("UPDATE bericht SET van = ?, aan = ?, inhoud = ? WHERE id = ?");
            queryUpdateBericht.setString(1, bericht.getVerzender().getGebruikersnaam());
            queryUpdateBericht.setString(2, bericht.getBestemmeling().getGebruikersnaam());
            queryUpdateBericht.setString(3, bericht.getInhoud());
            queryUpdateBericht.setInt(4, bericht.getId());
            queryUpdateBericht.executeUpdate();
            return true;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
            return false;
        }
    }
    
    public boolean verwijderBericht(Bericht bericht)
    {
        return verwijderBericht(bericht.getId());
    }
    
    public boolean verwijderBericht(int id)
    {
        try (Connection conn = DriverManager.getConnection(MapperConfig.JDBC_URL)) {
            PreparedStatement queryVerwijderBericht = conn.prepareStatement("DELETE FROM bericht WHERE id = ?");
            queryVerwijderBericht.setInt(1, id);
            queryVerwijderBericht.executeUpdate();
            return true;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
            return false;
        }
    }
}
