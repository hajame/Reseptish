/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptish.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import reseptish.pojo.Raakaaine;
import reseptish.pojo.Resepti;

/**
 *
 * @author jaakko
 */
public class RaakaaineDao {
    private Database db;

    public RaakaaineDao(Database db) {
        this.db = db;
    }
    
    public List<Raakaaine> findAll() throws SQLException {
        try (Connection c = db.getConnection()) {
            List<Raakaaine> raakaaineet = new ArrayList<>();
            
            ResultSet rs = c.prepareStatement("SELECT * FROM RaakaAine").executeQuery();
            while (rs.next()) {
                raakaaineet.add(Raakaaine.rowToRaakaaine(rs));
            }
            
            return raakaaineet;
        }
    }
    
    public Raakaaine findOne(int id) throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM RaakaAine WHERE raakaaine_id = ?");
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
                    
            return rs.next() ? Raakaaine.rowToRaakaaine(rs) : null;
        }
    }
    
    //Palauttaa lisätyn raaka-aineen ID:n tai null jos raaka-aine on jo tietokannassa
    public Integer add(String nimi) throws SQLException {
        try (Connection c = db.getConnection()) {
            //Erilainen PostgreSQL:ssä
            PreparedStatement lisaa = c.prepareStatement("INSERT INTO RaakaAine (raakaaine_nimi) VALUES (?) RETURNING ID ON CONFLICT DO NOTHING");
            lisaa.setString(1, nimi);
            lisaa.executeUpdate();
            
            ResultSet rs = lisaa.getGeneratedKeys();
            
            return rs.next() ? rs.getInt(1) : null;
            
        }
    }
    
    public Raakaaine search(String nimi) throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM RaakaAine WHERE raakaaine_nimi = ?");
            ps.setString(1, nimi);
            
            ResultSet rs = ps.executeQuery();
            
            return rs.next() ? Raakaaine.rowToRaakaaine(rs) : null;
        }
    }
    
    //TODO: delete
}
