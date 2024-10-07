/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresh.dominio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juanc
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
//    @NamedQuery(name = "Producto.findByIdProductoPk", query = "SELECT p FROM Producto p WHERE p.idProductoPk = :idProductoPk"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByCodigo", query = "SELECT p FROM Producto p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Producto.findByTipo", query = "SELECT p FROM Producto p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Producto.findByEstatus", query = "SELECT p FROM Producto p WHERE p.estatus = :estatus"),
    @NamedQuery(name = "Producto.findByMinimo", query = "SELECT p FROM Producto p WHERE p.minimo = :minimo"),
    @NamedQuery(name = "Producto.findByTiempoEntrega", query = "SELECT p FROM Producto p WHERE p.tiempoEntrega = :tiempoEntrega"),
    @NamedQuery(name = "Producto.findByFechaAlta", query = "SELECT p FROM Producto p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Producto.findByIdUsuarioAlta", query = "SELECT p FROM Producto p WHERE p.idUsuarioAlta = :idUsuarioAlta"),
    @NamedQuery(name = "Producto.findByFechaEliminacion", query = "SELECT p FROM Producto p WHERE p.fechaEliminacion = :fechaEliminacion"),
    @NamedQuery(name = "Producto.findByIdUsuarioEliminacion", query = "SELECT p FROM Producto p WHERE p.idUsuarioEliminacion = :idUsuarioEliminacion"),
    @NamedQuery(name = "Producto.findByClaveProductoServicio", query = "SELECT p FROM Producto p WHERE p.claveProductoServicio = :claveProductoServicio"),
    @NamedQuery(name = "Producto.findByClaveUnidad", query = "SELECT p FROM Producto p WHERE p.claveUnidad = :claveUnidad"),
    @NamedQuery(name = "Producto.findByFracionArancelaria", query = "SELECT p FROM Producto p WHERE p.fracionArancelaria = :fracionArancelaria")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto_pk")
    private Integer idProductoPk;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "estatus")
    private String estatus;
    @Column(name = "minimo")
    private Integer minimo;
    @Column(name = "tiempo_entrega")
    private Integer tiempoEntrega;
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(name = "id_usuario_alta")
    private Integer idUsuarioAlta;
    @Column(name = "fecha_eliminacion")
    @Temporal(TemporalType.DATE)
    private Date fechaEliminacion;
    @Column(name = "id_usuario_eliminacion")
    private Integer idUsuarioEliminacion;
    @Column(name = "clave_producto_servicio")
    private String claveProductoServicio;
    @Column(name = "clave_unidad")
    private String claveUnidad;
    @Column(name = "fracion_arancelaria")
    private String fracionArancelaria;
    @Column(name = "descripcion")
    private String descripcion;

    public Producto() {
    }

    public Producto(Integer idProductoPk) {
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
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
