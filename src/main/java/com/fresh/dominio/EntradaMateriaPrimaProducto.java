/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresh.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "entrada_materia_prima_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntradaMateriaPrimaProducto.findAll", query = "SELECT e FROM EntradaMateriaPrimaProducto e"),
    @NamedQuery(name = "EntradaMateriaPrimaProducto.findByIdEntradaProductoPk", query = "SELECT e FROM EntradaMateriaPrimaProducto e WHERE e.idEntradaProductoPk = :idEntradaProductoPk"),
    @NamedQuery(name = "EntradaMateriaPrimaProducto.findByNumeroTarima", query = "SELECT e FROM EntradaMateriaPrimaProducto e WHERE e.numeroTarima = :numeroTarima"),
    @NamedQuery(name = "EntradaMateriaPrimaProducto.findByCantidad", query = "SELECT e FROM EntradaMateriaPrimaProducto e WHERE e.cantidad = :cantidad"),
    @NamedQuery(name = "EntradaMateriaPrimaProducto.findByPesoBruto", query = "SELECT e FROM EntradaMateriaPrimaProducto e WHERE e.pesoBruto = :pesoBruto"),
    @NamedQuery(name = "EntradaMateriaPrimaProducto.findByPesoNeto", query = "SELECT e FROM EntradaMateriaPrimaProducto e WHERE e.pesoNeto = :pesoNeto"),
    @NamedQuery(name = "EntradaMateriaPrimaProducto.findByPesoTarima", query = "SELECT e FROM EntradaMateriaPrimaProducto e WHERE e.pesoTarima = :pesoTarima"),
    @NamedQuery(name = "EntradaMateriaPrimaProducto.findByPesoPresentacion", query = "SELECT e FROM EntradaMateriaPrimaProducto e WHERE e.pesoPresentacion = :pesoPresentacion"),
    @NamedQuery(name = "EntradaMateriaPrimaProducto.findByTipoTarima", query = "SELECT e FROM EntradaMateriaPrimaProducto e WHERE e.tipoTarima = :tipoTarima"),
    @NamedQuery(name = "EntradaMateriaPrimaProducto.findByTipoPresentacion", query = "SELECT e FROM EntradaMateriaPrimaProducto e WHERE e.tipoPresentacion = :tipoPresentacion"),
    @NamedQuery(name = "EntradaMateriaPrimaProducto.findByEstatus", query = "SELECT e FROM EntradaMateriaPrimaProducto e WHERE e.estatus = :estatus")})
public class EntradaMateriaPrimaProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ENTRADA_PRODUCTO_PK")
    private Integer idEntradaProductoPk;
    @Column(name = "NUMERO_TARIMA")
    private Integer numeroTarima;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @Column(name = "PESO_BRUTO")
    private BigDecimal pesoBruto;
    @Column(name = "PESO_NETO")
    private BigDecimal pesoNeto;
    @Column(name = "PESO_TARIMA")
    private BigDecimal pesoTarima;
    @Column(name = "PESO_PRESENTACION")
    private BigDecimal pesoPresentacion;
    @Column(name = "TIPO_TARIMA")
    private Integer tipoTarima;
    @Column(name = "TIPO_PRESENTACION")
    private Integer tipoPresentacion;
    @Column(name = "ESTATUS")
    private String estatus;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "ID_ENTRADA_MATERIA_PRIMA_FK", referencedColumnName = "ID_ENTRADA_MATERIA_PRIMA_PK")
    @ManyToOne
    private EntradaMateriaPrima idEntradaMateriaPrimaFk;

    public EntradaMateriaPrimaProducto() {
    }

    public EntradaMateriaPrimaProducto(Integer idEntradaProductoPk) {
        this.idEntradaProductoPk = idEntradaProductoPk;
    }

    public Integer getIdEntradaProductoPk() {
        return idEntradaProductoPk;
    }

    public void setIdEntradaProductoPk(Integer idEntradaProductoPk) {
        this.idEntradaProductoPk = idEntradaProductoPk;
    }

    public Integer getNumeroTarima() {
        return numeroTarima;
    }

    public void setNumeroTarima(Integer numeroTarima) {
        this.numeroTarima = numeroTarima;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(BigDecimal pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public BigDecimal getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(BigDecimal pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public BigDecimal getPesoTarima() {
        return pesoTarima;
    }

    public void setPesoTarima(BigDecimal pesoTarima) {
        this.pesoTarima = pesoTarima;
    }

    public BigDecimal getPesoPresentacion() {
        return pesoPresentacion;
    }

    public void setPesoPresentacion(BigDecimal pesoPresentacion) {
        this.pesoPresentacion = pesoPresentacion;
    }

    public Integer getTipoTarima() {
        return tipoTarima;
    }

    public void setTipoTarima(Integer tipoTarima) {
        this.tipoTarima = tipoTarima;
    }

    public Integer getTipoPresentacion() {
        return tipoPresentacion;
    }

    public void setTipoPresentacion(Integer tipoPresentacion) {
        this.tipoPresentacion = tipoPresentacion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public EntradaMateriaPrima getIdEntradaMateriaPrimaFk() {
        return idEntradaMateriaPrimaFk;
    }

    public void setIdEntradaMateriaPrimaFk(EntradaMateriaPrima idEntradaMateriaPrimaFk) {
        this.idEntradaMateriaPrimaFk = idEntradaMateriaPrimaFk;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntradaProductoPk != null ? idEntradaProductoPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntradaMateriaPrimaProducto)) {
            return false;
        }
        EntradaMateriaPrimaProducto other = (EntradaMateriaPrimaProducto) object;
        if ((this.idEntradaProductoPk == null && other.idEntradaProductoPk != null) || (this.idEntradaProductoPk != null && !this.idEntradaProductoPk.equals(other.idEntradaProductoPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.create.EntradaMateriaPrimaProducto[ idEntradaProductoPk=" + idEntradaProductoPk + " ]";
    }
    
}
