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
    private Resepti resepti;
    private Raakaaine raakaaine;
    private Integer maara;
    private String yksikko;
    private Integer jarjestysluku;
    private String valmistusohje;

    public ReseptiRaakaaine(Resepti resepti, Raakaaine raakaaine, Integer maara, String yksikko, Integer jarjestysluku, String valmistusohje) {
        this.resepti = resepti;
        this.raakaaine = raakaaine;
        this.maara = maara;
        this.yksikko = yksikko;
        this.jarjestysluku = jarjestysluku;
        this.valmistusohje = valmistusohje;
    }

    public Resepti getResepti() {
        return resepti;
    }

    public void setResepti(Resepti resepti) {
        this.resepti = resepti;
    }

    public Raakaaine getRaakaaine() {
        return raakaaine;
    }

    public void setRaakaaine(Raakaaine raakaaine) {
        this.raakaaine = raakaaine;
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
        hash = 97 * hash + Objects.hashCode(this.resepti);
        hash = 97 * hash + Objects.hashCode(this.raakaaine);
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
        if (!Objects.equals(this.resepti, other.resepti)) {
            return false;
        }
        if (!Objects.equals(this.raakaaine, other.raakaaine)) {
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
