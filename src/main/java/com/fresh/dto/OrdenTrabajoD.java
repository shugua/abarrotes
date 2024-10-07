package com.fresh.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author juanc
 */
public class OrdenTrabajoD implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idOrdenPk;
    private String numeroOrden;
    private String lote;
    private Date fechaEntrega;
    private String lotePT;
    private String observaciones;
    private Integer idUsuarioFk;
    private Date fecha;
    private String estatus;
    private Integer idEntradaMateriaPrimaFk;

    private String nombreCliente;
    private String nombreProveedor;
    private String nombreUsuario;
    private BigDecimal cantidad;

    private ArrayList<OrdenTrabajoProductoD> lstOrdenTrabajoProducto;
    private ArrayList<OrdenTrabajoLote> lstOrdenTrabajoLote;

    public OrdenTrabajoD() {
    }

    @Override
    public String toString() {
        return "OrdenTrabajo{" + "idOrdenPk=" + idOrdenPk + ", numeroOrden=" + numeroOrden + ", lote=" + lote + ", fechaEntrega=" + fechaEntrega + ", lotePT=" + lotePT + ", observaciones=" + observaciones + ", idUsuarioFk=" + idUsuarioFk + ", fecha=" + fecha + ", estatus=" + estatus + '}';
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

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getLotePT() {
        return lotePT;
    }

    public void setLotePT(String lotePT) {
        this.lotePT = lotePT;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdUsuarioFk() {
        return idUsuarioFk;
    }

    public void setIdUsuarioFk(Integer idUsuarioFk) {
        this.idUsuarioFk = idUsuarioFk;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdEntradaMateriaPrimaFk() {
        return idEntradaMateriaPrimaFk;
    }

    public void setIdEntradaMateriaPrimaFk(Integer idEntradaMateriaPrimaFk) {
        this.idEntradaMateriaPrimaFk = idEntradaMateriaPrimaFk;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public ArrayList<OrdenTrabajoProductoD> getLstOrdenTrabajoProducto() {
        if (lstOrdenTrabajoProducto == null) {
            lstOrdenTrabajoProducto = new ArrayList<OrdenTrabajoProductoD>();
        }
        return lstOrdenTrabajoProducto;

    }

    public void setLstOrdenTrabajoProducto(ArrayList<OrdenTrabajoProductoD> lstOrdenTrabajoProducto) {
        this.lstOrdenTrabajoProducto = lstOrdenTrabajoProducto;
    }

    public ArrayList<OrdenTrabajoLote> getLstOrdenTrabajoLote() {
        if (lstOrdenTrabajoLote == null) {
            lstOrdenTrabajoLote = new ArrayList<>();
        }

        return lstOrdenTrabajoLote;
    }

    public void setLstOrdenTrabajoLote(ArrayList<OrdenTrabajoLote> lstOrdenTrabajoLote) {
        this.lstOrdenTrabajoLote = lstOrdenTrabajoLote;
    }

}
