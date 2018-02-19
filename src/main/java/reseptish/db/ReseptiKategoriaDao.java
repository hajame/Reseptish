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
     
    //kategoriat suosituimmuusjärjestyksessä (montako reseptiä kategoriassa)  
     public Map<Integer, Kategoria> kategoriaCount() throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT Kategoria.nimi, COUNT(Resepti.resepti_id) FROM ReseptiKategoria, Resepti, Kategoria WHERE Resepti.resepti_id=ReseptiKategoria.resepti_id AND ReseptiKategoria.kategoria_id=Kategoria.kategoria_id GROUP BY Kategoria.Kategoria_id ORDER BY COUNT(Resepti.resepti_id) desc");
     
            Map<Integer, Kategoria> tulokset = new TreeMap<>();
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tulokset.put(rs.getInt("COUNT(distinct Resepti.resepti_id)"), Kategoria.rowToKategoria(rs));
            }
            
            return tulokset;
        }
    } 
     
    
    
}
