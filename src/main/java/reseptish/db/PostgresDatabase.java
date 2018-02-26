/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptish.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jaakko
 */
public class PostgresDatabase implements Database {
    private String path;
    
    public PostgresDatabase(String dbUrl) {
        this.path = dbUrl;
    }
    
    @Override
    public void init() throws SQLException {
        //TODO: SQL käskyt taulujen luomiseen
        try (Connection c = getConnection()) {
            for (String kasky : luoTaulut()) {
                c.prepareStatement(kasky).execute();
            }
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(path);
    }
    
    private static String[] luoTaulut() {
        return new String[] {
            "CREATE TABLE IF NOT EXISTS Resepti (" +
"	resepti_id serial PRIMARY KEY," +
"	resepti_nimi varchar(50)," +
"	ohje varchar(3000)," +
"	tekija varchar(200)," +
"	valmistusaika integer" +
");", "CREATE TABLE IF NOT EXISTS RaakaAine (" +
"	raakaaine_id serial PRIMARY KEY," +
"	raakaaine_nimi varchar(50) UNIQUE" +
");", "CREATE TABLE IF NOT EXISTS ReseptiRaakaAine (" +
"	resepti_id integer," +
"	raakaaine_id integer," +
"	maara integer," +
"	yksikko varchar(20)," +
"       jarjestysluku integer," +
"	valmistusohje varchar(100)," +
"	FOREIGN KEY (resepti_id) REFERENCES Resepti(resepti_id)," +
"	FOREIGN KEY (raakaaine_id) REFERENCES RaakaAine(raakaaine_id)" +
");", "CREATE TABLE IF NOT EXISTS Kategoria (" +
"	kategoria_id serial PRIMARY KEY," +
"	kategoria_nimi varchar(50) UNIQUE" +
");", "CREATE TABLE IF NOT EXISTS ReseptiKategoria (" +
"	kategoria_id integer," +
"	resepti_id integer," +
"	FOREIGN KEY (kategoria_id) REFERENCES Kategoria(kategoria_id)," +
"	FOREIGN KEY (resepti_id) REFERENCES Resepti(resepti_id)" +
");"
        };
    }
    
}
