package com.fresh.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author juanc
 */
public class Lavado implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idLavadoPk;
    private Date fecha;
    private Integer idOrdenFk;
    private Integer idEntradaMateriaPrimaFK;
    private BigDecimal cantidad;
    private BigDecimal pesoNeto;
    private Integer idTipoProductoFk;
    private Integer idUsuarioFk;
    private String observaciones;

    private String numeroOrdenTrabajo;
    private String loteMateriaPrima;
    private String descripcionTipoProducto;
    private String nombreUsuario;
    private BigDecimal pesoAcomulado;
    private BigDecimal cantidadAcomulado;
    private String estatus;
    private Date fechaEmpacado;

    private ArrayList<Lavado> lstLavado;

    private JRBeanCollectionDataSource collection;

    public Lavado() {
    }

    @Override
    public String toString() {
        return "Lavado{" + "idLavadoPk=" + idLavadoPk + ", fecha=" + fecha + ", idOrdenFk=" + idOrdenFk + ", idEntradaMateriaPrimaFK=" + idEntradaMateriaPrimaFK + ", cantidad=" + cantidad + ", pesoNeto=" + pesoNeto + ", idTipoProductoFk=" + idTipoProductoFk + ", idUsuarioFk=" + idUsuarioFk + ", numeroOrdenTrabajo=" + numeroOrdenTrabajo + ", loteMateriaPrima=" + loteMateriaPrima + ", descripcionTipoProducto=" + descripcionTipoProducto + ", nombreUsuario=" + nombreUsuario + '}';
    }

    public Integer getIdLavadoPk() {
        return idLavadoPk;
    }

    public void setIdLavadoPk(Integer idLavadoPk) {
        this.idLavadoPk = idLavadoPk;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(BigDecimal pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public Integer getIdTipoProductoFk() {
        return idTipoProductoFk;
    }

    public void setIdTipoProductoFk(Integer idTipoProductoFk) {
        this.idTipoProductoFk = idTipoProductoFk;
    }

    public Integer getIdUsuarioFk() {
        return idUsuarioFk;
    }

    public void setIdUsuarioFk(Integer idUsuarioFk) {
        this.idUsuarioFk = idUsuarioFk;
    }

    public String getNumeroOrdenTrabajo() {
        return numeroOrdenTrabajo;
    }

    public void setNumeroOrdenTrabajo(String numeroOrdenTrabajo) {
        this.numeroOrdenTrabajo = numeroOrdenTrabajo;
    }

    public String getLoteMateriaPrima() {
        return loteMateriaPrima;
    }

    public void setLoteMateriaPrima(String loteMateriaPrima) {
        this.loteMateriaPrima = loteMateriaPrima;
    }

    public String getDescripcionTipoProducto() {
        return descripcionTipoProducto;
    }

    public void setDescripcionTipoProducto(String descripcionTipoProducto) {
        this.descripcionTipoProducto = descripcionTipoProducto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getPesoAcomulado() {
        return pesoAcomulado;
    }

    public void setPesoAcomulado(BigDecimal pesoAcomulado) {
        this.pesoAcomulado = pesoAcomulado;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public BigDecimal getCantidadAcomulado() {
        return cantidadAcomulado;
    }

    public void setCantidadAcomulado(BigDecimal cantidadAcomulado) {
        this.cantidadAcomulado = cantidadAcomulado;
    }

    public ArrayList<Lavado> getLstLavado() {
        if (lstLavado == null) {
            lstLavado = new ArrayList<>();
        }
        return lstLavado;
    }

    public void setLstLavado(ArrayList<Lavado> lstLavado) {
        this.lstLavado = lstLavado;
    }

    public Date getFechaEmpacado() {
        return fechaEmpacado;
    }

    public void setFechaEmpacado(Date fechaEmpacado) {
        this.fechaEmpacado = fechaEmpacado;
    }
    
    public JRBeanCollectionDataSource getCollection() {
        collection = new JRBeanCollectionDataSource(lstLavado);
        return collection;
    }

    public void setCollection(JRBeanCollectionDataSource collection) {
        this.collection = collection;
    }

}
