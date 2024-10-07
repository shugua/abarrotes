package com.fresh.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author juanc
 */
public class MateriaPrimaProductoD implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idMateriaPrimaProductoPK;
    private Integer idEntradaMateriaPrimaFK;
    private Integer numeroTarima;
    private BigDecimal cantidad;
    private Integer idProductoFK;
    private String nombreProducto;
    private String estatus;
    private BigDecimal pesoBruto;
    private Integer idPresentacion;
    private BigDecimal pesoPresentacion;
    private Integer idTarima;
    private BigDecimal pesoTarima;
    private BigDecimal pesoNeto;
    private String observaciones;
    private String nombreTarima;
    private String nombrePresentacion;
    private Integer idOrdenFk;
    private Date fechaPelado;
    
    private String loteMateriaPrima;
    private String ordenTrabajo;
    private BigDecimal pesoAcomulado;

    public MateriaPrimaProductoD() {

    }

    public MateriaPrimaProductoD(Integer idMateriaPrimaProductoPK, Integer idEntradaMateriaPrimaFK, Integer numeroTarima, Integer idProductoFK, BigDecimal cantidad, BigDecimal pesoBruto, BigDecimal pesoNeto, Integer idTarima, Integer idPresentacion, String estatus, String observaciones, String nombreProducto, String nombreTarima, String nombrePresentacion, BigDecimal pesoPresentacion, BigDecimal pesoTarima) {
        this.idMateriaPrimaProductoPK = idMateriaPrimaProductoPK;
        this.idEntradaMateriaPrimaFK = idEntradaMateriaPrimaFK;
        this.numeroTarima = numeroTarima;
        this.cantidad = cantidad;
        this.idProductoFK = idProductoFK;
        this.nombreProducto = nombreProducto;
        this.estatus = estatus;
        this.pesoBruto = pesoBruto;
        this.idPresentacion = idPresentacion;
        this.pesoPresentacion = pesoPresentacion;
        this.idTarima = idTarima;
        this.pesoTarima = pesoTarima;
        this.pesoNeto = pesoNeto;
        this.observaciones = observaciones;
        this.nombreTarima = nombreTarima;
        this.nombrePresentacion = nombrePresentacion;
    }

    public Integer getIdMateriaPrimaProductoPK() {
        return idMateriaPrimaProductoPK;
    }

    public void setIdMateriaPrimaProductoPK(Integer idMateriaPrimaProductoPK) {
        this.idMateriaPrimaProductoPK = idMateriaPrimaProductoPK;
    }

    public Integer getIdEntradaMateriaPrimaFK() {
        return idEntradaMateriaPrimaFK;
    }

    public void setIdEntradaMateriaPrimaFK(Integer idEntradaMateriaPrimaFK) {
        this.idEntradaMateriaPrimaFK = idEntradaMateriaPrimaFK;
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

    public Integer getIdProductoFK() {
        return idProductoFK;
    }

    public void setIdProductoFK(Integer idProductoFK) {
        this.idProductoFK = idProductoFK;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(BigDecimal pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public Integer getIdPresentacion() {
        return idPresentacion;
    }

    public void setIdPresentacion(Integer idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public BigDecimal getPesoPresentacion() {
        return pesoPresentacion;
    }

    public void setPesoPresentacion(BigDecimal pesoPresentacion) {
        this.pesoPresentacion = pesoPresentacion;
    }

    public Integer getIdTarima() {
        return idTarima;
    }

    public void setIdTarima(Integer idTarima) {
        this.idTarima = idTarima;
    }

    public BigDecimal getPesoTarima() {
        return pesoTarima;
    }

    public void setPesoTarima(BigDecimal pesoTarima) {
        this.pesoTarima = pesoTarima;
    }

    public BigDecimal getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(BigDecimal pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getNombreTarima() {
        return nombreTarima;
    }

    public void setNombreTarima(String nombreTarima) {
        this.nombreTarima = nombreTarima;
    }

    public String getNombrePresentacion() {
        return nombrePresentacion;
    }

    public void setNombrePresentacion(String nombrePresentacion) {
        this.nombrePresentacion = nombrePresentacion;
    }

    public Date getFechaPelado() {
        return fechaPelado;
    }

    public void setFechaPelado(Date fechaPelado) {
        this.fechaPelado = fechaPelado;
    }

    public Integer getIdOrdenFk() {
        return idOrdenFk;
    }

    public void setIdOrdenFk(Integer idOrdenFk) {
        this.idOrdenFk = idOrdenFk;
    }

    public String getLoteMateriaPrima() {
        return loteMateriaPrima;
    }

    public void setLoteMateriaPrima(String loteMateriaPrima) {
        this.loteMateriaPrima = loteMateriaPrima;
    }

    public String getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(String ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public BigDecimal getPesoAcomulado() {
        return pesoAcomulado;
    }

    public void setPesoAcomulado(BigDecimal pesoAcomulado) {
        this.pesoAcomulado = pesoAcomulado;
    }
    
    

    @Override
    public String toString() {
        return "MateriaPrimaProductoD{" + "idMateriaPrimaProductoPK=" + idMateriaPrimaProductoPK + ", idEntradaMateriaPrimaFK=" + idEntradaMateriaPrimaFK + ", numeroTarima=" + numeroTarima + ", cantidad=" + cantidad + ", idProductoFK=" + idProductoFK + ", nombreProducto=" + nombreProducto + ", estatus=" + estatus + ", pesoBruto=" + pesoBruto + ", idPresentacion=" + idPresentacion + ", pesoPresentacion=" + pesoPresentacion + ", idTarima=" + idTarima + ", pesoTarima=" + pesoTarima + ", pesoNeto=" + pesoNeto + ", observaciones=" + observaciones + ", nombreTarima=" + nombreTarima + ", nombrePresentacion=" + nombrePresentacion + '}';
    }

  
}
