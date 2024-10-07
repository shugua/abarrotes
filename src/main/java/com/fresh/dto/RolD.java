
package com.fresh.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juanc
 */

public class RolD implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idRolPk;
    private String nombreRol;
    private String descripcionRol;
    private List<AccesMenuD> accesMenuCollection;

    public RolD() {
    }

    public RolD(Integer idRolPk, String nombreRol, String descripcionRol) {
        this.idRolPk = idRolPk;
        this.nombreRol = nombreRol;
        this.descripcionRol = descripcionRol;
    }

  

    public Integer getIdRolPk() {
        return idRolPk;
    }

    public void setIdRolPk(Integer idRolPk) {
        this.idRolPk = idRolPk;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    @XmlTransient
    public Collection<AccesMenuD> getAccesMenuCollection() {
        return accesMenuCollection;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolPk != null ? idRolPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolD)) {
            return false;
        }
        RolD other = (RolD) object;
        if ((this.idRolPk == null && other.idRolPk != null) || (this.idRolPk != null && !this.idRolPk.equals(other.idRolPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.Rol[ idRolPk=" + idRolPk + " ]";
    }
    
}
