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

    private Resepti reseptiId;
    private Kategoria kategoriaId;

    public ReseptiKategoria(Resepti reseptiId, Kategoria kategoriaId) {
        this.reseptiId = reseptiId;
        this.kategoriaId = kategoriaId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.reseptiId);
        hash = 89 * hash + Objects.hashCode(this.kategoriaId);
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
        if (!Objects.equals(this.reseptiId, other.reseptiId)) {
            return false;
        }
        if (!Objects.equals(this.kategoriaId, other.kategoriaId)) {
            return false;
        }
        return true;
    }

    public Resepti getReseptiId() {
        return reseptiId;
    }

    public void setReseptiId(Resepti reseptiId) {
        this.reseptiId = reseptiId;
    }

    public Kategoria getKategoriaId() {
        return kategoriaId;
    }

    public void setKategoriaId(Kategoria kategoriaId) {
        this.kategoriaId = kategoriaId;
    }

}
