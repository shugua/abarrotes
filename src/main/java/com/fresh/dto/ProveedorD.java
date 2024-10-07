package com.fresh.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author juanc
 */
public class ProveedorD implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idProveedorPk;
    private String nombre;
    private String numeroTelefono;
    private String estatus;
    private String clave;
    private Date fechaAlta;
    private Integer idUsuarioAlta;
    private Date fechaEliminacion;
    private Integer idUsuarioEliminacion;
    private String correo;
    private String domicilio;
    private Integer tipoProveedor;
    private Integer numeroProveedor;
    private String terminoPago;
    private Integer formaPago;
    private String divisa;
    private String rfc;
    private String tipo;

    public ProveedorD(Integer idProveedorPk, String nombre, String numeroTelefono, String estatus, String clave, Date fechaAlta, Integer idUsuarioAlta, Date fechaEliminacion, Integer idUsuarioEliminacion, String correo) {
        this.idProveedorPk = idProveedorPk;
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
        this.estatus = estatus;
        this.clave = clave;
        this.fechaAlta = fechaAlta;
        this.idUsuarioAlta = idUsuarioAlta;
        this.fechaEliminacion = fechaEliminacion;
        this.idUsuarioEliminacion = idUsuarioEliminacion;
        this.correo = correo;
    }

    public ProveedorD() {
    }

    public ProveedorD(Integer idProveedorPk) {
        this.idProveedorPk = idProveedorPk;
    }

    public Integer getIdProveedorPk() {
        return idProveedorPk;
    }

    public void setIdProveedorPk(Integer idProveedorPk) {
        this.idProveedorPk = idProveedorPk;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Integer getIdUsuarioAlta() {
        return idUsuarioAlta;
    }

    public void setIdUsuarioAlta(Integer idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }

    public Date getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(Date fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public Integer getIdUsuarioEliminacion() {
        return idUsuarioEliminacion;
    }

    public void setIdUsuarioEliminacion(Integer idUsuarioEliminacion) {
        this.idUsuarioEliminacion = idUsuarioEliminacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(Integer tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public Integer getNumeroProveedor() {
        return numeroProveedor;
    }

    public void setNumeroProveedor(Integer numeroProveedor) {
        this.numeroProveedor = numeroProveedor;
    }

    public String getTerminoPago() {
        return terminoPago;
    }

    public void setTerminoPago(String terminoPago) {
        this.terminoPago = terminoPago;
    }

    public Integer getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(Integer formaPago) {
        this.formaPago = formaPago;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedorPk != null ? idProveedorPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorD)) {
            return false;
        }
        ProveedorD other = (ProveedorD) object;
        if ((this.idProveedorPk == null && other.idProveedorPk != null) || (this.idProveedorPk != null && !this.idProveedorPk.equals(other.idProveedorPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.create.Proveedor[ idProveedorPk=" + idProveedorPk + " ]";
    }

}
