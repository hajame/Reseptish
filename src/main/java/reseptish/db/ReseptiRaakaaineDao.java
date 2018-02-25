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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import reseptish.pojo.Raakaaine;
import reseptish.pojo.Resepti;
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
    
    public Map<String, Integer> raakaAineCount() throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT *, count(ReseptiRaakaAine.resepti_id) FROM ReseptiRaakaAine, RaakaAine WHERE RaakaAine.raakaaine_id = ReseptiRaakaAine.raakaaine_id GROUP BY RaakaAine.raakaaine_id");
     
            Map<String, Integer> tulokset = new TreeMap<>();
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getInt("count(ReseptiRaakaAine.resepti_id)"));
                tulokset.put(rs.getString("Raakaaine_nimi"), rs.getInt("count(ReseptiRaakaAine.resepti_id)"));
            }
         
            //System.out.println(tulokset);
            return tulokset;
        }
    }
    
    public Integer add(ReseptiRaakaaine reseptiRaakaaine) throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("INSERT INTO ReseptiRaakaaine (resepti_id, raakaaine_id, maara, yksikko, jarjestysluku, valmistusohje) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, reseptiRaakaaine.getResepti().getReseptiId());
            ps.setInt(2, reseptiRaakaaine.getRaakaaine().getRaakaaineId());
            ps.setInt(3, reseptiRaakaaine.getMaara());
            ps.setString(4, reseptiRaakaaine.getYksikko());
            ps.setInt(5, reseptiRaakaaine.getJarjestysluku());
            ps.setString(6, reseptiRaakaaine.getValmistusohje());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            return rs.next() ? rs.getInt(1) : null;
        }
    }
    
    public List<Resepti> findReseptiWithRaakaaaine(String raakaaine) throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM ReseptiRaakaAine, Resepti, RaakaAine WHERE ReseptiRaakaAine.resepti_id = Resepti.resepti_id AND RaakaAine.raakaaine_id = ReseptiRaakaAine.raakaaine_id AND RaakaAine.raakaaine_nimi LIKE ?");
            ps.setString(1, "%"+raakaaine+"%");
            
            List<Resepti> resepti = new ArrayList<>();
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resepti.add(Resepti.rowToResepti(rs));
            }
            
            return resepti;
        }
    }
}
