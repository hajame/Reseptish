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
import java.util.Map.Entry;
import java.util.TreeMap;
import reseptish.pojo.Kategoria;
import reseptish.pojo.ReseptiKategoria;
import reseptish.pojo.ReseptiRaakaaine;

/**
 *
 * @author hdheli
 */
public class ReseptiKategoriaDao {
    private Database db;

    public ReseptiKategoriaDao(Database db) {
        this.db = db;
    }
    
     public List<ReseptiKategoria> findAll() throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM ReseptiKategoria, Resepti, Kategoria WHERE Resepti.resepti_id=ReseptiKategoria.resepti_id AND ReseptiKategoria.kategoria_id=Kategoria.kategoria_id");
            
            List<ReseptiKategoria> kategoriat = new ArrayList<>();
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kategoriat.add(ReseptiKategoria.rowToReseptiKategoria(rs));
            }
            
            return kategoriat;
        }
    }
     
    public List<ReseptiKategoria> findAllForResepti(int id) throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM ReseptiKategoria, Resepti, Kategoria WHERE ReseptiKategoria.resepti_id = Resepti.resepti_id AND Kategoria.kategoria_id = ReseptiKategoria.kategoria_id AND Resepti.resepti_id = ?");
            ps.setInt(1, id);
            
            List<ReseptiKategoria> kategoriat = new ArrayList<>();
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kategoriat.add(ReseptiKategoria.rowToReseptiKategoria(rs));
            }
            
            return kategoriat;
        }
    }     
     
    //kategoriat suosituimmuusjärjestyksessä (montako reseptiä kategoriassa)  
     public Map<Integer, Kategoria> kategoriaCount() throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT *, COUNT(ReseptiKategoria.resepti_id) FROM ReseptiKategoria, Resepti, Kategoria WHERE Resepti.resepti_id=ReseptiKategoria.resepti_id AND ReseptiKategoria.kategoria_id=Kategoria.kategoria_id GROUP BY Kategoria.Kategoria_id");
     
            Map<Integer, Kategoria> tulokset = new TreeMap<>(Comparator.reverseOrder());
                 
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tulokset.put(rs.getInt("COUNT(ReseptiKategoria.resepti_id)"), Kategoria.rowToKategoria(rs));
            }
          
//            for (Map.Entry<Integer, Kategoria> entry : tulokset.entrySet()) {
//             System.out.println(entry.getKey()+" : "+entry.getValue());
//                }
            
            
            return tulokset;
        }
    } 
    
    public Integer add(ReseptiKategoria reseptiKategoria) throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("INSERT INTO ReseptiKategoria (resepti_id, kategoria_id) VALUES (?,?)");
            ps.setInt(1, reseptiKategoria.getResepti().getReseptiId());
            ps.setInt(2, reseptiKategoria.getKategoria().getKategoriaId());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            return rs.next() ? rs.getInt(1) : null;
        }
    }
    
}
