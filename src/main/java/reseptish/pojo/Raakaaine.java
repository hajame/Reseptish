/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptish.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Raakaaine {
    private Integer raakaaineId;
    private String nimi;

    public Raakaaine(Integer raakaaineId, String nimi) {
        this.raakaaineId = raakaaineId;
        this.nimi = nimi;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Integer getRaakaaineId() {
        return raakaaineId;
    }

    public void setRaakaaineId(Integer raakaaineId) {
        this.raakaaineId = raakaaineId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.raakaaineId);
        hash = 67 * hash + Objects.hashCode(this.nimi);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Raakaaine other = (Raakaaine) obj;
        if (!Objects.equals(this.nimi, other.nimi)) {
            return false;
        }
        if (!Objects.equals(this.raakaaineId, other.raakaaineId)) {
            return false;
        }
        return true;
    }
    
    public static Raakaaine rowToRaakaaine(ResultSet rs) throws SQLException {
        return new Raakaaine(rs.getInt("Raakaaine.raakaaineId"), rs.getString("Raakaaine.nimi"));
    }
}
