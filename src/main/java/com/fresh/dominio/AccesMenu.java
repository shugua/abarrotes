/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresh.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanc
 */
@Entity
@Table(name = "acces_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccesMenu.findAll", query = "SELECT a FROM AccesMenu a"),
    @NamedQuery(name = "AccesMenu.findByEditar", query = "SELECT a FROM AccesMenu a WHERE a.editar = :editar"),
    @NamedQuery(name = "AccesMenu.findByEliminar", query = "SELECT a FROM AccesMenu a WHERE a.eliminar = :eliminar"),
    @NamedQuery(name = "AccesMenu.findByInsertar", query = "SELECT a FROM AccesMenu a WHERE a.insertar = :insertar"),
    @NamedQuery(name = "AccesMenu.findByIdAccesMenuPk", query = "SELECT a FROM AccesMenu a WHERE a.idAccesMenuPk = :idAccesMenuPk"),
    @NamedQuery(name = "AccesMenu.findByIdRol", query = "SELECT a FROM AccesMenu a WHERE a.idRolFk = :idRolFk")})
public class AccesMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "EDITAR")
    private String editar;
    @Column(name = "ELIMINAR")
    private String eliminar;
    @Column(name = "INSERTAR")
    private String insertar;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ACCES_MENU_PK")
    private Integer idAccesMenuPk;
    @JoinColumn(name = "ID_MENU_FK", referencedColumnName = "ID_MENU")
    @ManyToOne(optional = false)
    private Menu idMenuFk;
    @JoinColumn(name = "ID_ROL_FK", referencedColumnName = "ID_ROL_PK")
    @ManyToOne
    private Rol idRolFk;

    public AccesMenu() {
    }

    public AccesMenu(Integer idAccesMenuPk) {
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

    public Menu getIdMenuFk() {
        return idMenuFk;
    }

    public void setIdMenuFk(Menu idMenuFk) {
        this.idMenuFk = idMenuFk;
    }

    public Rol getIdRolFk() {
        return idRolFk;
    }

    public void setIdRolFk(Rol idRolFk) {
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
        if (!(object instanceof AccesMenu)) {
            return false;
        }
        AccesMenu other = (AccesMenu) object;
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
