/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptish.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author jaakko
 */
public interface Database {
    void init() throws SQLException;
    Connection getConnection() throws SQLException;
}
