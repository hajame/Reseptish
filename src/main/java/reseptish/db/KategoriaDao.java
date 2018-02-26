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
import reseptish.pojo.Kategoria;
import reseptish.pojo.Raakaaine;

/**
 *
 * @author hdheli
 */
public class KategoriaDao {

    private Database db;

    public KategoriaDao(Database db) {
        this.db = db;
    }

    public List<Kategoria> findAll() throws SQLException {
        try (Connection c = db.getConnection()) {
            List<Kategoria> kategoriat = new ArrayList<>();

            ResultSet rs = c.prepareStatement("SELECT * FROM Kategoria").executeQuery();
            while (rs.next()) {
                kategoriat.add(Kategoria.rowToKategoria(rs));
            }

            return kategoriat;

        }
    }
    
    public Kategoria findOne(int id) throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Kategoria WHERE kategoria_id = ?");
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
                    
            return rs.next() ? Kategoria.rowToKategoria(rs) : null;
        }
    }
    
    //Palauttaa lisätyn  kategorian ID:n tai null jos raaka-aine on jo tietokannassa
    public Integer add(String nimi) throws SQLException {
        try (Connection c = db.getConnection()) {
            //Erilainen PostgreSQL:ssä
            PreparedStatement lisaa = c.prepareStatement("INSERT INTO Kategoria (kategoria_nimi) VALUES (?) ON CONFLICT DO NOTHING RETURNING Kategoria_id");
            //tallennetaan kategoriat pienellä
            lisaa.setString(1, nimi.toLowerCase());
            ResultSet rs = lisaa.executeQuery();
            
            return rs.next() ? rs.getInt(1) : null;
            
        }
    }
    
    public Kategoria search(String nimi) throws SQLException {
        try (Connection c = db.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Kategoria WHERE kategoria_nimi = ?");
            ps.setString(1, nimi.toLowerCase());
            
            ResultSet rs = ps.executeQuery();
            
            return rs.next() ? Kategoria.rowToKategoria(rs) : null;
        }
    }
    

}
