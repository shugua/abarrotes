
package com.fresh.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author juanc
 */
public class ProductoD implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer idProductoPk;
    private String nombre;
    private String descripcion;
    private String codigo;
    private String tipo;
    private String estatus;
    private Integer minimo;
    private Integer tiempoEntrega;
    private Date fechaAlta;
    private Integer idUsuarioAlta;
    private Date fechaEliminacion;
    private Integer idUsuarioEliminacion;
    private String claveProductoServicio;
    private String claveUnidad;
    private String fracionArancelaria;

    public ProductoD(Integer idProductoPk, String nombre, String codigo, String tipo, String estatus, Integer minimo, Integer tiempoEntrega, Date fechaAlta, Integer idUsuarioAlta, Date fechaEliminacion, Integer idUsuarioEliminacion, String claveProductoServicio, String claveUnidad, String fracionArancelaria) {
        this.idProductoPk = idProductoPk;
        this.nombre = nombre;
        this.codigo = codigo;
        this.tipo = tipo;
        this.estatus = estatus;
        this.minimo = minimo;
        this.tiempoEntrega = tiempoEntrega;
        this.fechaAlta = fechaAlta;
        this.idUsuarioAlta = idUsuarioAlta;
        this.fechaEliminacion = fechaEliminacion;
        this.idUsuarioEliminacion = idUsuarioEliminacion;
        this.claveProductoServicio = claveProductoServicio;
        this.claveUnidad = claveUnidad;
        this.fracionArancelaria = fracionArancelaria;
    }
    
    

    public ProductoD() {
    }

    public ProductoD(Integer idProductoPk) {
        this.idProductoPk = idProductoPk;
    }

    public Integer getIdProductoPk() {
        return idProductoPk;
    }

    public void setIdProductoPk(Integer idProductoPk) {
        this.idProductoPk = idProductoPk;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getMinimo() {
        return minimo;
    }

    public void setMinimo(Integer minimo) {
        this.minimo = minimo;
    }

    public Integer getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(Integer tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
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

    public String getClaveProductoServicio() {
        return claveProductoServicio;
    }

    public void setClaveProductoServicio(String claveProductoServicio) {
        this.claveProductoServicio = claveProductoServicio;
    }

    public String getClaveUnidad() {
        return claveUnidad;
    }

    public void setClaveUnidad(String claveUnidad) {
        this.claveUnidad = claveUnidad;
    }

    public String getFracionArancelaria() {
        return fracionArancelaria;
    }

    public void setFracionArancelaria(String fracionArancelaria) {
        this.fracionArancelaria = fracionArancelaria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductoPk != null ? idProductoPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoD)) {
            return false;
        }
        ProductoD other = (ProductoD) object;
        if ((this.idProductoPk == null && other.idProductoPk != null) || (this.idProductoPk != null && !this.idProductoPk.equals(other.idProductoPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.create.Producto[ idProductoPk=" + idProductoPk + " ]";
    }
    
}
