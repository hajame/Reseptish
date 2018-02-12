/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptish.pojo;

import java.util.Objects;

public class Resepti {
    private Integer id;
    private String nimi;
    private String ohje;
    private String tekija;
    private Integer valmistusaika; //minuutteja

    public Resepti(Integer id, String nimi, String ohje, String tekija, Integer valmistusaika) {
        this.id = id;
        this.nimi = nimi;
        this.ohje = ohje;
        this.tekija = tekija;
        this.valmistusaika = valmistusaika;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getOhje() {
        return ohje;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }

    public String getTekija() {
        return tekija;
    }

    public void setTekija(String tekija) {
        this.tekija = tekija;
    }

    public Integer getValmistusaika() {
        return valmistusaika;
    }

    public void setValmistusaika(Integer valmistusaika) {
        this.valmistusaika = valmistusaika;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.nimi);
        hash = 89 * hash + Objects.hashCode(this.ohje);
        hash = 89 * hash + Objects.hashCode(this.tekija);
        hash = 89 * hash + Objects.hashCode(this.valmistusaika);
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
        final Resepti other = (Resepti) obj;
        if (!Objects.equals(this.nimi, other.nimi)) {
            return false;
        }
        if (!Objects.equals(this.ohje, other.ohje)) {
            return false;
        }
        if (!Objects.equals(this.tekija, other.tekija)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.valmistusaika, other.valmistusaika)) {
            return false;
        }
        return true;
    }
    
    
}
