package com.fresh.dto;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author juanc
 */

public class TipoTarimaD implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idTarimaPk;
    private String nombre;
    private BigDecimal peso;
    private Character estatus;

    public TipoTarimaD() {
    }

    public TipoTarimaD(Integer idTarimaPk) {
        this.idTarimaPk = idTarimaPk;
    }

    public Integer getIdTarimaPk() {
        return idTarimaPk;
    }

    public void setIdTarimaPk(Integer idTarimaPk) {
        this.idTarimaPk = idTarimaPk;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTarimaPk != null ? idTarimaPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTarimaD)) {
            return false;
        }
        TipoTarimaD other = (TipoTarimaD) object;
        if ((this.idTarimaPk == null && other.idTarimaPk != null) || (this.idTarimaPk != null && !this.idTarimaPk.equals(other.idTarimaPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.TipoTarima[ idTarimaPk=" + idTarimaPk + " ]";
    }
    
}
