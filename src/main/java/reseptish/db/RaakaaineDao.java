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
            
            ResultSet rs = c.prepareStatement("SELECT * FROM Raakaaine").executeQuery();
            while (rs.next()) {
                raakaaineet.add(rowToRaakaaine(rs));
            }
            
            return raakaaineet;
        }
    }
    
    public Raakaaine findOne(int id) throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Raakaaine WHERE raakaaineId = ?");
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
                    
            return rs.next() ? rowToRaakaaine(rs) : null;
        }
    }
    
    //TODO: delete
    
    private static Raakaaine rowToRaakaaine(ResultSet rs) throws SQLException {
        return new Raakaaine(rs.getInt("raakaaineId"), rs.getString("nimi"));
    }
}
