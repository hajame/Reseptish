/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptish.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jaakko
 */
public class SQLiteDatabase implements Database {
    private String path;
    
    public SQLiteDatabase(File file) {
        this.path = file.getAbsolutePath();
    }
    
    public SQLiteDatabase() {
        this.path = "sqlite:memory:";
    }
    
    @Override
    public void init() throws SQLException {
        //TODO: SQL käskyt taulujen luomiseen
        try (Connection c = getConnection()) {
            
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:"+path);
    }
    
}
