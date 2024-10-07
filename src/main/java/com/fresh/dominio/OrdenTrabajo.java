/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresh.dominio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanc
 */
@Entity
@Table(name = "orden_trabajo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenTrabajo.findAll", query = "SELECT o FROM OrdenTrabajo o"),
    @NamedQuery(name = "OrdenTrabajo.findByNumeroOrden", query = "SELECT o FROM OrdenTrabajo o WHERE o.numeroOrden = :numeroOrden"),
    @NamedQuery(name = "OrdenTrabajo.findByFechaEntrega", query = "SELECT o FROM OrdenTrabajo o WHERE o.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "OrdenTrabajo.findByLotePt", query = "SELECT o FROM OrdenTrabajo o WHERE o.lotePt = :lotePt"),
    @NamedQuery(name = "OrdenTrabajo.findByObservaciones", query = "SELECT o FROM OrdenTrabajo o WHERE o.observaciones = :observaciones"),
    @NamedQuery(name = "OrdenTrabajo.findByFecha", query = "SELECT o FROM OrdenTrabajo o WHERE o.fecha = :fecha"),
    @NamedQuery(name = "OrdenTrabajo.findByEstatus", query = "SELECT o FROM OrdenTrabajo o WHERE o.estatus = :estatus")})
public class OrdenTrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ORDEN_PK")
    private Integer idOrdenPk;
    @Column(name = "NUMERO_ORDEN")
    private String numeroOrden;
    @Column(name = "FECHA_ENTREGA")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Column(name = "LOTE_PT")
    private String lotePt;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "ESTATUS")
    private Character estatus;
//    @Column(name = "ID_ENTRADA_MATERIA_PRIMA_FK")
//    private Integer idEntradaMateriaPrimaFk;
    @Column(name = "ID_USUARIO_FK")
    private Integer idUsuarioFk;

    public OrdenTrabajo() {
    }

    public OrdenTrabajo(Integer idOrdenPk) {
        this.idOrdenPk = idOrdenPk;
    }

    public Integer getIdOrdenPk() {
        return idOrdenPk;
    }

    public void setIdOrdenPk(Integer idOrdenPk) {
        this.idOrdenPk = idOrdenPk;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getLotePt() {
        return lotePt;
    }

    public void setLotePt(String lotePt) {
        this.lotePt = lotePt;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

//    public Integer getIdEntradaMateriaPrimaFk() {
//        return idEntradaMateriaPrimaFk;
//    }
//
//    public void setIdEntradaMateriaPrimaFk(Integer idEntradaMateriaPrimaFk) {
//        this.idEntradaMateriaPrimaFk = idEntradaMateriaPrimaFk;
//    }

    public Integer getIdUsuarioFk() {
        return idUsuarioFk;
    }

    public void setIdUsuarioFk(Integer idUsuarioFk) {
        this.idUsuarioFk = idUsuarioFk;
    }

    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdenPk != null ? idOrdenPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenTrabajo)) {
            return false;
        }
        OrdenTrabajo other = (OrdenTrabajo) object;
        if ((this.idOrdenPk == null && other.idOrdenPk != null) || (this.idOrdenPk != null && !this.idOrdenPk.equals(other.idOrdenPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.OrdenTrabajo[ idOrdenPk=" + idOrdenPk + " ]";
    }

    
}
