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
                reseptit.add(rowToResepti(rs));
            }
            
            return reseptit;
        }
    }
    
    public Resepti findOne(int id) throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Resepti WHERE reseptiId = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rowToResepti(rs);
            } else {
                return null;
            }
        }
    }
    
    //TODO: delete
    
    private static Resepti rowToResepti(ResultSet rs) throws SQLException {
        return new Resepti(rs.getInt("reseptiId"), rs.getString("nimi"), rs.getString("ohje"), rs.getString("tekija"), rs.getInt("valmistusaika"));
    }
}
