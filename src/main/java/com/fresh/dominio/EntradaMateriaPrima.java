/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresh.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juanc
 */
@Entity
@Table(name = "entrada_materia_prima")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntradaMateriaPrima.findAll", query = "SELECT e FROM EntradaMateriaPrima e"),
    @NamedQuery(name = "EntradaMateriaPrima.findByIdEntradaMateriaPrimaPk", query = "SELECT e FROM EntradaMateriaPrima e WHERE e.idEntradaMateriaPrimaPk = :idEntradaMateriaPrimaPk"),
    @NamedQuery(name = "EntradaMateriaPrima.findByFecha", query = "SELECT e FROM EntradaMateriaPrima e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EntradaMateriaPrima.findByFechaCancelacion", query = "SELECT e FROM EntradaMateriaPrima e WHERE e.fechaCancelacion = :fechaCancelacion"),
    @NamedQuery(name = "EntradaMateriaPrima.findByEstatus", query = "SELECT e FROM EntradaMateriaPrima e WHERE e.estatus = :estatus"),
    @NamedQuery(name = "EntradaMateriaPrima.findByNumeroEntrada", query = "SELECT e FROM EntradaMateriaPrima e WHERE e.numeroEntrada = :numeroEntrada"),
    @NamedQuery(name = "EntradaMateriaPrima.findByPesoBruto", query = "SELECT e FROM EntradaMateriaPrima e WHERE e.pesoBruto = :pesoBruto"),
    @NamedQuery(name = "EntradaMateriaPrima.findByPesoNeto", query = "SELECT e FROM EntradaMateriaPrima e WHERE e.pesoNeto = :pesoNeto"),
    @NamedQuery(name = "EntradaMateriaPrima.findByTotalTara", query = "SELECT e FROM EntradaMateriaPrima e WHERE e.totalTara = :totalTara"),
    @NamedQuery(name = "EntradaMateriaPrima.findByTotalCaja", query = "SELECT e FROM EntradaMateriaPrima e WHERE e.totalCaja = :totalCaja"),
    @NamedQuery(name = "EntradaMateriaPrima.findByLote", query = "SELECT e FROM EntradaMateriaPrima e WHERE e.lote = :lote"),
    @NamedQuery(name = "EntradaMateriaPrima.findByRemision", query = "SELECT e FROM EntradaMateriaPrima e WHERE e.remision = :remision"),
    @NamedQuery(name = "EntradaMateriaPrima.findByCantidad", query = "SELECT e FROM EntradaMateriaPrima e WHERE e.cantidad = :cantidad"),
    @NamedQuery(name = "EntradaMateriaPrima.findByObservaciones", query = "SELECT e FROM EntradaMateriaPrima e WHERE e.observaciones = :observaciones")})
public class EntradaMateriaPrima implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ENTRADA_MATERIA_PRIMA_PK")
    private Integer idEntradaMateriaPrimaPk;
    @Column(name = "FECHA", nullable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "FECHA_CANCELACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCancelacion;
    @Column(name = "ESTATUS")
    private String estatus;
    @Column(name = "NUMERO_ENTRADA")
    private Integer numeroEntrada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PESO_BRUTO")
    private BigDecimal pesoBruto;
    @Column(name = "PESO_NETO")
    private BigDecimal pesoNeto;
    @Column(name = "TOTAL_TARA")
    private BigDecimal totalTara;
    @Column(name = "TOTAL_CAJA")
    private BigDecimal totalCaja;
    @Column(name = "LOTE")
    private String lote;
    @Column(name = "REMISION")
    private String remision;
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @Column(name = "OBSERVACIONES")
    private String observaciones;

    @Column(name = "ID_USUARIO_FK")
    private Integer idUsuarioFk;
    @Column(name = "ID_USUARIO_CANCELA_FK")
    private Integer idUsuarioCancelaFk;
    @Column(name = "ID_PROVEEDOR_FK")
    private Integer idProveedorFk;
    @Column(name = "ID_CLIENTE_FK")
    private Integer idClienteFk;

    @OneToMany(mappedBy = "idEntradaMateriaPrimaFk")
    private Collection<EntradaMateriaPrimaProducto> entradaMateriaPrimaProductoCollection;

    public EntradaMateriaPrima() {
    }

    public EntradaMateriaPrima(Integer idEntradaMateriaPrimaPk) {
        this.idEntradaMateriaPrimaPk = idEntradaMateriaPrimaPk;
    }

    public Integer getIdEntradaMateriaPrimaPk() {
        return idEntradaMateriaPrimaPk;
    }

    public void setIdEntradaMateriaPrimaPk(Integer idEntradaMateriaPrimaPk) {
        this.idEntradaMateriaPrimaPk = idEntradaMateriaPrimaPk;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getNumeroEntrada() {
        return numeroEntrada;
    }

    public void setNumeroEntrada(Integer numeroEntrada) {
        this.numeroEntrada = numeroEntrada;
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

    public BigDecimal getTotalTara() {
        return totalTara;
    }

    public void setTotalTara(BigDecimal totalTara) {
        this.totalTara = totalTara;
    }

    public BigDecimal getTotalCaja() {
        return totalCaja;
    }

    public void setTotalCaja(BigDecimal totalCaja) {
        this.totalCaja = totalCaja;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getRemision() {
        return remision;
    }

    public void setRemision(String remision) {
        this.remision = remision;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public Collection<EntradaMateriaPrimaProducto> getEntradaMateriaPrimaProductoCollection() {
        return entradaMateriaPrimaProductoCollection;
    }

    public void setEntradaMateriaPrimaProductoCollection(Collection<EntradaMateriaPrimaProducto> entradaMateriaPrimaProductoCollection) {
        this.entradaMateriaPrimaProductoCollection = entradaMateriaPrimaProductoCollection;
    }

    public Integer getIdUsuarioFk() {
        return idUsuarioFk;
    }

    public void setIdUsuarioFk(Integer idUsuarioFk) {
        this.idUsuarioFk = idUsuarioFk;
    }

    public Integer getIdUsuarioCancelaFk() {
        return idUsuarioCancelaFk;
    }

    public void setIdUsuarioCancelaFk(Integer idUsuarioCancelaFk) {
        this.idUsuarioCancelaFk = idUsuarioCancelaFk;
    }

    public Integer getIdProveedorFk() {
        return idProveedorFk;
    }

    public void setIdProveedorFk(Integer idProveedorFk) {
        this.idProveedorFk = idProveedorFk;
    }

    public Integer getIdClienteFk() {
        return idClienteFk;
    }

    public void setIdClienteFk(Integer idClienteFk) {
        this.idClienteFk = idClienteFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntradaMateriaPrimaPk != null ? idEntradaMateriaPrimaPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntradaMateriaPrima)) {
            return false;
        }
        EntradaMateriaPrima other = (EntradaMateriaPrima) object;
        if ((this.idEntradaMateriaPrimaPk == null && other.idEntradaMateriaPrimaPk != null) || (this.idEntradaMateriaPrimaPk != null && !this.idEntradaMateriaPrimaPk.equals(other.idEntradaMateriaPrimaPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.create.EntradaMateriaPrima[ idEntradaMateriaPrimaPk=" + idEntradaMateriaPrimaPk + " ]";
    }

}
