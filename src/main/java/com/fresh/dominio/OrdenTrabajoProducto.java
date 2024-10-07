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
@Table(name = "orden_trabajo_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenTrabajoProducto.findAll", query = "SELECT o FROM OrdenTrabajoProducto o"),
    @NamedQuery(name = "OrdenTrabajoProducto.findByIdOrdenTrabajoProductoPk", query = "SELECT o FROM OrdenTrabajoProducto o WHERE o.idOrdenTrabajoProductoPk = :idOrdenTrabajoProductoPk"),
    @NamedQuery(name = "OrdenTrabajoProducto.findByCantidad", query = "SELECT o FROM OrdenTrabajoProducto o WHERE o.cantidad = :cantidad"),
    @NamedQuery(name = "OrdenTrabajoProducto.findByTipo", query = "SELECT o FROM OrdenTrabajoProducto o WHERE o.tipo = :tipo"),
    @NamedQuery(name = "OrdenTrabajoProducto.findByEstatus", query = "SELECT o FROM OrdenTrabajoProducto o WHERE o.estatus = :estatus")})
public class OrdenTrabajoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ORDEN_TRABAJO_PRODUCTO_PK")
    private Integer idOrdenTrabajoProductoPk;
    @Column(name = "CANTIDAD")
    private Long cantidad;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "ESTATUS")
    private Character estatus;
    @JoinColumn(name = "ID_ORDEN_FK", referencedColumnName = "ID_ORDEN_PK")
    @ManyToOne
    private OrdenTrabajo idOrdenFk;

    public OrdenTrabajoProducto() {
    }

    public OrdenTrabajoProducto(Integer idOrdenTrabajoProductoPk) {
        this.idOrdenTrabajoProductoPk = idOrdenTrabajoProductoPk;
    }

    public Integer getIdOrdenTrabajoProductoPk() {
        return idOrdenTrabajoProductoPk;
    }

    public void setIdOrdenTrabajoProductoPk(Integer idOrdenTrabajoProductoPk) {
        this.idOrdenTrabajoProductoPk = idOrdenTrabajoProductoPk;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

    public OrdenTrabajo getIdOrdenFk() {
        return idOrdenFk;
    }

    public void setIdOrdenFk(OrdenTrabajo idOrdenFk) {
        this.idOrdenFk = idOrdenFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdenTrabajoProductoPk != null ? idOrdenTrabajoProductoPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenTrabajoProducto)) {
            return false;
        }
        OrdenTrabajoProducto other = (OrdenTrabajoProducto) object;
        if ((this.idOrdenTrabajoProductoPk == null && other.idOrdenTrabajoProductoPk != null) || (this.idOrdenTrabajoProductoPk != null && !this.idOrdenTrabajoProductoPk.equals(other.idOrdenTrabajoProductoPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.OrdenTrabajoProducto[ idOrdenTrabajoProductoPk=" + idOrdenTrabajoProductoPk + " ]";
    }
    
}
