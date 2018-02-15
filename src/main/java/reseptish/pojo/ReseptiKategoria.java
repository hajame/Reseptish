/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseptish.pojo;

import java.util.Objects;

/**
 *
 * @author hdheli
 */
public class ReseptiKategoria {

    private Resepti resepti;
    private Kategoria kategoria;

    public ReseptiKategoria(Resepti resepti, Kategoria kategoria) {
        this.resepti = resepti;
        this.kategoria = kategoria;
    }

    public Resepti getResepti() {
        return resepti;
    }

    public void setResepti(Resepti resepti) {
        this.resepti = resepti;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.resepti);
        hash = 29 * hash + Objects.hashCode(this.kategoria);
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
        final ReseptiKategoria other = (ReseptiKategoria) obj;
        if (!Objects.equals(this.resepti, other.resepti)) {
            return false;
        }
        if (!Objects.equals(this.kategoria, other.kategoria)) {
            return false;
        }
        return true;
    }
    
    

}
