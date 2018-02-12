/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptish.pojo;

import java.util.Objects;

/**
 *
 * @author jaakko
 */
public class Kategoria {

    private Integer kategoriaId;
    private String nimi;

    public Kategoria(Integer kategoriaId, String nimi) {
        this.kategoriaId = kategoriaId;
        this.nimi = nimi;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Integer getKategoriaId() {
        return kategoriaId;
    }

    public void setKategoriaId(Integer kategoriaId) {
        this.kategoriaId = kategoriaId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.kategoriaId);
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
        final Kategoria other = (Kategoria) obj;
        if (!Objects.equals(this.nimi, other.nimi)) {
            return false;
        }
        if (!Objects.equals(this.kategoriaId, other.kategoriaId)) {
            return false;
        }
        return true;
    }

    
    
}
