/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresh.dominio;

import java.io.Serializable;
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
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByIdProveedorPk", query = "SELECT p FROM Proveedor p WHERE p.idProveedorPk = :idProveedorPk"),
    @NamedQuery(name = "Proveedor.findByNombre", query = "SELECT p FROM Proveedor p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Proveedor.findByNumeroTelefono", query = "SELECT p FROM Proveedor p WHERE p.numeroTelefono = :numeroTelefono"),
    @NamedQuery(name = "Proveedor.findByEstatus", query = "SELECT p FROM Proveedor p WHERE p.estatus = :estatus"),
    @NamedQuery(name = "Proveedor.findByClave", query = "SELECT p FROM Proveedor p WHERE p.clave = :clave"),
    @NamedQuery(name = "Proveedor.findByFechaAlta", query = "SELECT p FROM Proveedor p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Proveedor.findByIdUsuarioAlta", query = "SELECT p FROM Proveedor p WHERE p.idUsuarioAlta = :idUsuarioAlta"),
    @NamedQuery(name = "Proveedor.findByFechaEliminacion", query = "SELECT p FROM Proveedor p WHERE p.fechaEliminacion = :fechaEliminacion"),
    @NamedQuery(name = "Proveedor.findByIdUsuarioEliminacion", query = "SELECT p FROM Proveedor p WHERE p.idUsuarioEliminacion = :idUsuarioEliminacion"),
    @NamedQuery(name = "Proveedor.findByCorreo", query = "SELECT p FROM Proveedor p WHERE p.correo = :correo")})
public class Proveedor implements Serializable {

    @OneToMany(mappedBy = "idProveedorFk")
    private Collection<EntradaMateriaPrima> entradaMateriaPrimaCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proveedor_pk")
    private Integer idProveedorPk;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "numero_telefono")
    private String numeroTelefono;
    @Column(name = "estatus")
    private String estatus;
    @Column(name = "clave")
    private String clave;
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
    @Column(name = "correo")
    private String correo;
    @Column(name = "divisa")
    private String divisa;
    @Column(name = "termino_pago")
    private String terminoPago;
    @Column(name = "forma_pago")
    private Integer formaPago;
    @Column(name = "tipo_proveedor")
    private Integer tipoProveedor;
    @Column(name = "rfc")
    private String rfc;
    @Column(name = "domicilio")
    private String domicilio;

    public Proveedor() {
    }

    public Proveedor(Integer idProveedorPk) {
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

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
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

    public Integer getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(Integer tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
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
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedorPk == null && other.idProveedorPk != null) || (this.idProveedorPk != null && !this.idProveedorPk.equals(other.idProveedorPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.create.Proveedor[ idProveedorPk=" + idProveedorPk + " ]";
    }

    @XmlTransient
    public Collection<EntradaMateriaPrima> getEntradaMateriaPrimaCollection() {
        return entradaMateriaPrimaCollection;
    }

    public void setEntradaMateriaPrimaCollection(Collection<EntradaMateriaPrima> entradaMateriaPrimaCollection) {
        this.entradaMateriaPrimaCollection = entradaMateriaPrimaCollection;
    }
    
}
