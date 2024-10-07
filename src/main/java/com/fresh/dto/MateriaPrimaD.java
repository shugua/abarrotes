package com.fresh.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author juanc
 */
public class MateriaPrimaD implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idEntradaMateriaPrimaPK;
    private Date fecha;
    private String remision;
    private Integer idProveedorFK;
    private String nombreProveedor;
    private Integer idClienteFK;
    private String nombreCliente;
    private String lote;
    private BigDecimal cantidadProveedor;
    private BigDecimal cantidadLlegada;
    private String observaciones;
    private BigDecimal pesoBruto;
    private BigDecimal pesoNeto;
    private BigDecimal totalTaraTarima;
    private BigDecimal totalTaraCaja;
    private String estatus;
    private String nombreEstatus;
    private Integer idUsuarioEntradaFK;
    private String nombreUsuarioEntrada;
    private Integer idUsuarioCancelaFK;
    private Integer numeroEntrada;
    private String nombreUsuarioCancela;
    private Date fechaCancelacion;
    private List<MateriaPrimaProductoD> lstMateriaPrimaProducto;

    private ProveedorD proveedor;
    private ClienteD cliente;

    public MateriaPrimaD() {
    }

    public MateriaPrimaD(Integer idEntradaMateriaPrimaPK, String lote, Date fecha, Integer idProveedorFK, String nombreProveedor, Integer idClienteFK, String nombreCliente, BigDecimal pesoBruto, BigDecimal pesoNeto, String observaciones, Integer idUsuarioEntradaFK, String nombreUsuarioEntrada, String estatus) {
        this.idEntradaMateriaPrimaPK = idEntradaMateriaPrimaPK;
        this.fecha = fecha;
        this.idProveedorFK = idProveedorFK;
        this.nombreProveedor = nombreProveedor;
        this.idClienteFK = idClienteFK;
        this.nombreCliente = nombreCliente;
        this.lote = lote;
        this.observaciones = observaciones;
        this.pesoBruto = pesoBruto;
        this.pesoNeto = pesoNeto;
        this.estatus = estatus;
        this.idUsuarioEntradaFK = idUsuarioEntradaFK;
        this.nombreUsuarioEntrada = nombreUsuarioEntrada;
    }

    public Integer getIdEntradaMateriaPrimaPK() {
        return idEntradaMateriaPrimaPK;
    }

    public void setIdEntradaMateriaPrimaPK(Integer idEntradaMateriaPrimaPK) {
        this.idEntradaMateriaPrimaPK = idEntradaMateriaPrimaPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRemision() {
        return remision;
    }

    public void setRemision(String remision) {
        this.remision = remision;
    }

    public Integer getIdProveedorFK() {
        return idProveedorFK;
    }

    public void setIdProveedorFK(Integer idProveedorFK) {
        this.idProveedorFK = idProveedorFK;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Integer getIdClienteFK() {
        return idClienteFK;
    }

    public void setIdClienteFK(Integer idClienteFK) {
        this.idClienteFK = idClienteFK;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public BigDecimal getCantidadProveedor() {
        return cantidadProveedor;
    }

    public void setCantidadProveedor(BigDecimal cantidadProveedor) {
        this.cantidadProveedor = cantidadProveedor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public BigDecimal getTotalTaraTarima() {
        return totalTaraTarima;
    }

    public void setTotalTaraTarima(BigDecimal totalTaraTarima) {
        this.totalTaraTarima = totalTaraTarima;
    }

    public BigDecimal getTotalTaraCaja() {
        return totalTaraCaja;
    }

    public void setTotalTaraCaja(BigDecimal totalTaraCaja) {
        this.totalTaraCaja = totalTaraCaja;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getNombreEstatus() {
        return nombreEstatus;
    }

    public void setNombreEstatus(String nombreEstatus) {
        this.nombreEstatus = nombreEstatus;
    }

    public Integer getIdUsuarioEntradaFK() {
        return idUsuarioEntradaFK;
    }

    public void setIdUsuarioEntradaFK(Integer idUsuarioEntradaFK) {
        this.idUsuarioEntradaFK = idUsuarioEntradaFK;
    }

    public String getNombreUsuarioEntrada() {
        return nombreUsuarioEntrada;
    }

    public void setNombreUsuarioEntrada(String nombreUsuarioEntrada) {
        this.nombreUsuarioEntrada = nombreUsuarioEntrada;
    }

    public Integer getIdUsuarioCancelaFK() {
        return idUsuarioCancelaFK;
    }

    public void setIdUsuarioCancelaFK(Integer idUsuarioCancelaFK) {
        this.idUsuarioCancelaFK = idUsuarioCancelaFK;
    }

    public String getNombreUsuarioCancela() {
        return nombreUsuarioCancela;
    }

    public void setNombreUsuarioCancela(String nombreUsuarioCancela) {
        this.nombreUsuarioCancela = nombreUsuarioCancela;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public List<MateriaPrimaProductoD> getLstMateriaPrimaProducto() {
        return lstMateriaPrimaProducto;
    }

    public void setLstMateriaPrimaProducto(List<MateriaPrimaProductoD> lstMateriaPrimaProducto) {
        this.lstMateriaPrimaProducto = lstMateriaPrimaProducto;
    }

    public Integer getNumeroEntrada() {
        return numeroEntrada;
    }

    public void setNumeroEntrada(Integer numeroEntrada) {
        this.numeroEntrada = numeroEntrada;
    }

    public BigDecimal getCantidadLlegada() {
        return cantidadLlegada;
    }

    public void setCantidadLlegada(BigDecimal cantidadLlegada) {
        this.cantidadLlegada = cantidadLlegada;
    }

    public ProveedorD getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorD proveedor) {
        this.proveedor = proveedor;
    }

    public ClienteD getCliente() {
        return cliente;
    }

    public void setCliente(ClienteD cliente) {
        this.cliente = cliente;
    }
    
    

    @Override
    public String toString() {
        return "MateriaPrima{" + "idEntradaMateriaPrimaPK=" + idEntradaMateriaPrimaPK + ", fecha=" + fecha + ", remision=" + remision + ", idProveedorFK=" + idProveedorFK + ", nombreProveedor=" + nombreProveedor + ", idClienteFK=" + idClienteFK + ", nombreCliente=" + nombreCliente + ", lote=" + lote + ", cantidadProveedor=" + cantidadProveedor + ", observaciones=" + observaciones + ", pesoBruto=" + pesoBruto + ", pesoNeto=" + pesoNeto + ", totalTaraTarima=" + totalTaraTarima + ", totalTaraCaja=" + totalTaraCaja + ", estatus=" + estatus + ", nombreEstatus=" + nombreEstatus + ", idUsuarioEntradaFK=" + idUsuarioEntradaFK + ", nombreUsuarioEntrada=" + nombreUsuarioEntrada + ", idUsuarioCancelaFK=" + idUsuarioCancelaFK + ", nombreUsuarioCancela=" + nombreUsuarioCancela + ", fechaCancelacion=" + fechaCancelacion + ", lstMateriaPrimaProducto=" + lstMateriaPrimaProducto + '}';
    }

}
