
package com.fresh.dto;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 *
 * @author juanc
 */

public class TipoPresentacionD implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer idPresentacionPk;
    private String nombre;
    private BigDecimal peso;
    private Character estatus;

    public TipoPresentacionD() {
    }

    public TipoPresentacionD(Integer idPresentacionPk) {
        this.idPresentacionPk = idPresentacionPk;
    }

    public Integer getIdPresentacionPk() {
        return idPresentacionPk;
    }

    public void setIdPresentacionPk(Integer idPresentacionPk) {
        this.idPresentacionPk = idPresentacionPk;
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
        hash += (idPresentacionPk != null ? idPresentacionPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPresentacionD)) {
            return false;
        }
        TipoPresentacionD other = (TipoPresentacionD) object;
        if ((this.idPresentacionPk == null && other.idPresentacionPk != null) || (this.idPresentacionPk != null && !this.idPresentacionPk.equals(other.idPresentacionPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.TipoPresentacion[ idPresentacionPk=" + idPresentacionPk + " ]";
    }
    
}
