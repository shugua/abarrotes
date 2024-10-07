
package com.fresh.dto;

import java.io.Serializable;

/**
 *
 * @author juanc
 */
public class AccesMenuD implements Serializable {

    private static final long serialVersionUID = 1L;
    private String editar;
    private String eliminar;
    private String insertar;
    private Integer idAccesMenuPk;
    private MenuD idMenuFk;
    private RolD idRolFk;

    public AccesMenuD() {
    }

    public AccesMenuD(Integer idAccesMenuPk) {
        this.idAccesMenuPk = idAccesMenuPk;
    }

    public String getEditar() {
        return editar;
    }

    public void setEditar(String editar) {
        this.editar = editar;
    }

    public String getEliminar() {
        return eliminar;
    }

    public void setEliminar(String eliminar) {
        this.eliminar = eliminar;
    }

    public String getInsertar() {
        return insertar;
    }

    public void setInsertar(String insertar) {
        this.insertar = insertar;
    }

    public Integer getIdAccesMenuPk() {
        return idAccesMenuPk;
    }

    public void setIdAccesMenuPk(Integer idAccesMenuPk) {
        this.idAccesMenuPk = idAccesMenuPk;
    }

    public MenuD getIdMenuFk() {
        return idMenuFk;
    }

    public void setIdMenuFk(MenuD idMenuFk) {
        this.idMenuFk = idMenuFk;
    }

    public RolD getIdRolFk() {
        return idRolFk;
    }

    public void setIdRolFk(RolD idRolFk) {
        this.idRolFk = idRolFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccesMenuPk != null ? idAccesMenuPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccesMenuD)) {
            return false;
        }
        AccesMenuD other = (AccesMenuD) object;
        if ((this.idAccesMenuPk == null && other.idAccesMenuPk != null) || (this.idAccesMenuPk != null && !this.idAccesMenuPk.equals(other.idAccesMenuPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.AccesMenu[ idAccesMenuPk=" + idAccesMenuPk + " ]";
    }

}
