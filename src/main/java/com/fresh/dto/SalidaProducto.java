package com.fresh.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author juanc
 */
public class SalidaProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idSalidaProductoPk;
    private Integer idSalidaFk;
    private Integer idEnvasadoFk;
    private Integer idOrdenFk;
    private Integer idEntradaMateriaPrimaFk;
    private BigDecimal kilos;
    private BigDecimal kilosBruto;
    private BigDecimal cantidad;
    private BigDecimal tara;
    private String estatus;
    private String observaciones;
    private Integer idUsuarioCancelaFk;
    private Integer numeroTarima;
    private Date fechaCancelacion;
    private String tipoProductoDescripcion;
    private String tamano;
    private Integer tarima;

    @Override
    public String toString() {
        return "SalidaProducto{" + "idSalidaProductoPk=" + idSalidaProductoPk + ", idSalidaFk=" + idSalidaFk + ", idEnvasadoFk=" + idEnvasadoFk + ", kilos=" + kilos + ", kilosBruto=" + kilosBruto + ", cantidad=" + cantidad + ", tara=" + tara + ", estatus=" + estatus + ", observaciones=" + observaciones + ", idUsuarioCancelaFk=" + idUsuarioCancelaFk + ", numeroTarima=" + numeroTarima + ", fechaCancelacion=" + fechaCancelacion + ", tipoProductoDescripcion=" + tipoProductoDescripcion + ", tamano=" + tamano + '}';
    }

    public Integer getIdSalidaProductoPk() {
        return idSalidaProductoPk;
    }

    public void setIdSalidaProductoPk(Integer idSalidaProductoPk) {
        this.idSalidaProductoPk = idSalidaProductoPk;
    }

    public Integer getIdSalidaFk() {
        return idSalidaFk;
    }

    public void setIdSalidaFk(Integer idSalidaFk) {
        this.idSalidaFk = idSalidaFk;
    }

    public Integer getIdEnvasadoFk() {
        return idEnvasadoFk;
    }

    public void setIdEnvasadoFk(Integer idEnvasadoFk) {
        this.idEnvasadoFk = idEnvasadoFk;
    }

    public Integer getIdOrdenFk() {
        return idOrdenFk;
    }

    public void setIdOrdenFk(Integer idOrdenFk) {
        this.idOrdenFk = idOrdenFk;
    }

    public Integer getIdEntradaMateriaPrimaFk() {
        return idEntradaMateriaPrimaFk;
    }

    public void setIdEntradaMateriaPrimaFk(Integer idEntradaMateriaPrimaFk) {
        this.idEntradaMateriaPrimaFk = idEntradaMateriaPrimaFk;
    }
    
    public BigDecimal getKilos() {
        return kilos;
    }

    public void setKilos(BigDecimal kilos) {
        this.kilos = kilos;
    }

    public BigDecimal getKilosBruto() {
        return kilosBruto;
    }

    public void setKilosBruto(BigDecimal kilosBruto) {
        this.kilosBruto = kilosBruto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTara() {
        return tara;
    }

    public void setTara(BigDecimal tara) {
        this.tara = tara;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdUsuarioCancelaFk() {
        return idUsuarioCancelaFk;
    }

    public void setIdUsuarioCancelaFk(Integer idUsuarioCancelaFk) {
        this.idUsuarioCancelaFk = idUsuarioCancelaFk;
    }

    public Integer getNumeroTarima() {
        return numeroTarima;
    }

    public void setNumeroTarima(Integer numeroTarima) {
        this.numeroTarima = numeroTarima;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getTipoProductoDescripcion() {
        return tipoProductoDescripcion;
    }

    public void setTipoProductoDescripcion(String tipoProductoDescripcion) {
        this.tipoProductoDescripcion = tipoProductoDescripcion;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public Integer getTarima() {
        return tarima;
    }

    public void setTarima(Integer tarima) {
        this.tarima = tarima;
    }

}
