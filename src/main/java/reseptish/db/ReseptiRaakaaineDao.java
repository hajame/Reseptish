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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import reseptish.pojo.Raakaaine;
import reseptish.pojo.ReseptiRaakaaine;

/**
 *
 * @author jaakko
 */
public class ReseptiRaakaaineDao {
    private Database db;

    public ReseptiRaakaaineDao(Database db) {
        this.db = db;
    }
    
    public List<ReseptiRaakaaine> findAll() throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM ReseptiRaakaAine, Resepti, RaakaAine WHERE ReseptiRaakaAine.resepti_id = Resepti.resepti_id AND RaakaAine.raakaaine_id = ReseptiRaakaAine.raakaaine_id");
            
            List<ReseptiRaakaaine> raakaaineet = new ArrayList<>();
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                raakaaineet.add(ReseptiRaakaaine.rowToReseptiRaakaaine(rs));
            }
            
            return raakaaineet;
        }
    }
    
    public List<ReseptiRaakaaine> findAllForResepti(int id) throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM ReseptiRaakaAine, Resepti, RaakaAine WHERE ReseptiRaakaAine.resepti_id = Resepti.resepti_id AND RaakaAine.raakaaine_id = ReseptiRaakaAine.raakaaine_id AND Resepti.resepti_id = ?");
            ps.setInt(1, id);
            
            List<ReseptiRaakaaine> raakaaineet = new ArrayList<>();
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                raakaaineet.add(ReseptiRaakaaine.rowToReseptiRaakaaine(rs));
            }
            
            return raakaaineet;
        }
    }
    
    public Map<Raakaaine, Integer> raakaAineCount() throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT *, count(ReseptiRaakaAine.resepti_id) FROM ReseptiRaakaAine, RaakaAine WHERE RaakaAine.raakaaine_id = ReseptiRaakaAine.raakaaine_id GROUP BY RaakaAine.raakaaine_id");
     
            Map<Raakaaine, Integer> tulokset = new HashMap<>();
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tulokset.put(Raakaaine.rowToRaakaaine(rs), rs.getInt("count(ReseptiRaakaAine.resepti_id)"));
            }
            
            return tulokset;
        }
    }
}
