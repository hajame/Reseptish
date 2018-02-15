/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptish.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author jaakko
 */
public class ReseptiRaakaaine {
    private Resepti reseptiId;
    private Raakaaine raakaaineId;
    private Integer maara;
    private String yksikko;
    private Integer jarjestysluku;
    private String valmistusohje;

    public ReseptiRaakaaine(Resepti reseptiId, Raakaaine raakaaineId, Integer maara, String yksikko, Integer jarjestysluku, String valmistusohje) {
        this.reseptiId = reseptiId;
        this.raakaaineId = raakaaineId;
        this.maara = maara;
        this.yksikko = yksikko;
        this.jarjestysluku = jarjestysluku;
        this.valmistusohje = valmistusohje;
    }

    public Resepti getReseptiId() {
        return reseptiId;
    }

    public void setReseptiId(Resepti reseptiId) {
        this.reseptiId = reseptiId;
    }

    public Raakaaine getRaakaaineId() {
        return raakaaineId;
    }

    public void setRaakaaineId(Raakaaine raakaaineId) {
        this.raakaaineId = raakaaineId;
    }

    public Integer getMaara() {
        return maara;
    }

    public void setMaara(Integer maara) {
        this.maara = maara;
    }

    public String getYksikko() {
        return yksikko;
    }

    public void setYksikko(String yksikko) {
        this.yksikko = yksikko;
    }

    public Integer getJarjestysluku() {
        return jarjestysluku;
    }

    public void setJarjestysluku(Integer jarjestysluku) {
        this.jarjestysluku = jarjestysluku;
    }

    public String getValmistusohje() {
        return valmistusohje;
    }

    public void setValmistusohje(String valmistusohje) {
        this.valmistusohje = valmistusohje;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.reseptiId);
        hash = 97 * hash + Objects.hashCode(this.raakaaineId);
        hash = 97 * hash + Objects.hashCode(this.maara);
        hash = 97 * hash + Objects.hashCode(this.yksikko);
        hash = 97 * hash + Objects.hashCode(this.jarjestysluku);
        hash = 97 * hash + Objects.hashCode(this.valmistusohje);
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
        final ReseptiRaakaaine other = (ReseptiRaakaaine) obj;
        if (!Objects.equals(this.yksikko, other.yksikko)) {
            return false;
        }
        if (!Objects.equals(this.valmistusohje, other.valmistusohje)) {
            return false;
        }
        if (!Objects.equals(this.reseptiId, other.reseptiId)) {
            return false;
        }
        if (!Objects.equals(this.raakaaineId, other.raakaaineId)) {
            return false;
        }
        if (!Objects.equals(this.maara, other.maara)) {
            return false;
        }
        if (!Objects.equals(this.jarjestysluku, other.jarjestysluku)) {
            return false;
        }
        return true;
    }

    public static ReseptiRaakaaine rowToReseptiRaakaaine(ResultSet rs) throws SQLException {
        return new ReseptiRaakaaine(Resepti.rowToResepti(rs), Raakaaine.rowToRaakaaine(rs), rs.getInt("maara"), rs.getString("yksikko"), rs.getInt("jarjestysluku"), rs.getString("valmistusohje"));
    }
}
