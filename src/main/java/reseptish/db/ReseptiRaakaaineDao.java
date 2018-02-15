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
            PreparedStatement ps = c.prepareStatement("SELECT * FROM ReseptiRaakaAine, Resepti, RaakaAine WHERE ReseptiRaakaAine.resepti_id = Resepti.resepti_id AND Raakaaine.raakaaine_id = ReseptiRaakaaine.raakaaine_id");
            
            List<ReseptiRaakaaine> raakaaineet = new ArrayList<>();
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                raakaaineet.add(ReseptiRaakaaine.rowToReseptiRaakaaine(rs));
            }
            
            return raakaaineet;
        }
    }
}
