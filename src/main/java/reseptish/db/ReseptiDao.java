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
import reseptish.pojo.Resepti;

/**
 *
 * @author jaakko
 */
public class ReseptiDao {
    private Database db;

    public ReseptiDao(Database db) {
        this.db = db;
    }
    
    public List<Resepti> findAll() throws SQLException {
        try (Connection c = db.getConnection()) {
            List<Resepti> reseptit = new ArrayList<>();
            
            ResultSet rs = c.prepareStatement("SELECT * FROM Resepti").executeQuery();
            while (rs.next()) {
                reseptit.add(Resepti.rowToResepti(rs));
            }
            
            return reseptit;
        }
    }
    
    public Resepti findOne(int id) throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Resepti WHERE resepti_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return Resepti.rowToResepti(rs);
            } else {
                return null;
            }
        }
    }
    
    public List<Resepti> find(String query) throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Resepti WHERE resepti_nimi LIKE ?");
            ps.setString(1, "%"+query+"%");
            
            ResultSet rs = ps.executeQuery();
            
            List<Resepti> reseptit = new ArrayList<>();
            while (rs.next()) {
                reseptit.add(Resepti.rowToResepti(rs));
            }
            
            return reseptit;
        }
    }
    
    public int count() throws SQLException {
         try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT COUNT(Resepti_id) FROM Resepti");
            
            ResultSet rs = ps.executeQuery();
            
            
            if (rs.next()) {
                return rs.getInt("COUNT(Resepti_id)");
            } else {
                return 0;
            }           
        }
  
    }
    
//Palauttaa lisätyn reseptin ID:n
    public Integer add(Resepti resepti) throws SQLException {
        try (Connection c = db.getConnection()) {
            //Erilainen PostgreSQL:ssä
            PreparedStatement lisaa = c.prepareStatement("INSERT INTO Resepti "
                + "(resepti_nimi, ohje, tekija, valmistusaika) "
                + "VALUES (?, ?, ?, ?)");
            lisaa.setString(1, resepti.getNimi());
            lisaa.setString(2, resepti.getOhje());
            lisaa.setString(3, resepti.getTekija());
            lisaa.setInt(4, resepti.getValmistusaika());
            lisaa.executeUpdate();
            
            ResultSet rs = lisaa.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            
            return id;
        }
    }    
      
    //TODO: delete
}
