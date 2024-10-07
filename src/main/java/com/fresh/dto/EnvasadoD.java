package com.fresh.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author juanc
 */
public class EnvasadoD implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idEnvasadoPk;
    private Integer idOrdenFk;
    private Integer idEntradaMateriaPrimaFK;
    private Integer numeroTarima;
    private BigDecimal cantidad;
    private BigDecimal kilosBruto;
    private BigDecimal kilos;
    private BigDecimal cantidadEntregada;
    private BigDecimal cantidadSalida;
    private BigDecimal kilosEntregados;
    private String tamano;
    private String estatus;
    private Integer idTipoProductoFk;
    private Date fecha;
    private Date fechaCancelacion;
    private Integer idUsuarioFk;
    private Integer idUsuarioCancelaFk;
    private String nombreUsuario;
    private String numeroOrden;
    private String nombreTipoProducto;
    private String lote;
    private BigDecimal cantidadCaja;
    private BigDecimal pesoUnitario;
    private BigDecimal taraUnitario;
    private String loteProductoTermiando;


    public Integer getIdEnvasadoPk() {
        return idEnvasadoPk;
    }

    public void setIdEnvasadoPk(Integer idEnvasadoPk) {
        this.idEnvasadoPk = idEnvasadoPk;
    }

    public Integer getIdOrdenFk() {
        return idOrdenFk;
    }

    public void setIdOrdenFk(Integer idOrdenFk) {
        this.idOrdenFk = idOrdenFk;
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

    public BigDecimal getKilos() {
        return kilos;
    }

    public void setKilos(BigDecimal kilos) {
        this.kilos = kilos;
    }

    public BigDecimal getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(BigDecimal cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public BigDecimal getKilosEntregados() {
        return kilosEntregados;
    }

    public void setKilosEntregados(BigDecimal kilosEntregados) {
        this.kilosEntregados = kilosEntregados;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdTipoProductoFk() {
        return idTipoProductoFk;
    }

    public void setIdTipoProductoFk(Integer idTipoProductoFk) {
        this.idTipoProductoFk = idTipoProductoFk;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getNombreTipoProducto() {
        return nombreTipoProducto;
    }

    public void setNombreTipoProducto(String nombreTipoProducto) {
        this.nombreTipoProducto = nombreTipoProducto;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public BigDecimal getKilosBruto() {
        return kilosBruto;
    }

    public void setKilosBruto(BigDecimal kilosBruto) {
        this.kilosBruto = kilosBruto;
    }

    public BigDecimal getCantidadSalida() {
        return cantidadSalida;
    }

    public void setCantidadSalida(BigDecimal cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }

    public BigDecimal getCantidadCaja() {
        return cantidadCaja;
    }

    public void setCantidadCaja(BigDecimal cantidadCaja) {
        this.cantidadCaja = cantidadCaja;
    }

    public BigDecimal getPesoUnitario() {
        return pesoUnitario;
    }

    public void setPesoUnitario(BigDecimal pesoUnitario) {
        this.pesoUnitario = pesoUnitario;
    }

    public BigDecimal getTaraUnitario() {
        return taraUnitario;
    }

    public void setTaraUnitario(BigDecimal taraUnitario) {
        this.taraUnitario = taraUnitario;
    }

    public String getLoteProductoTermiando() {
        return loteProductoTermiando;
    }

    public void setLoteProductoTermiando(String loteProductoTermiando) {
        this.loteProductoTermiando = loteProductoTermiando;
    }
    
    
    

}
