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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdClientePk", query = "SELECT c FROM Cliente c WHERE c.idClientePk = :idClientePk"),
    @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cliente.findByNumeroTelefono", query = "SELECT c FROM Cliente c WHERE c.numeroTelefono = :numeroTelefono"),
    @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cliente.findByRfc", query = "SELECT c FROM Cliente c WHERE c.rfc = :rfc"),
    @NamedQuery(name = "Cliente.findByCodigoPostal", query = "SELECT c FROM Cliente c WHERE c.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "Cliente.findByEstatus", query = "SELECT c FROM Cliente c WHERE c.estatus = :estatus"),
    @NamedQuery(name = "Cliente.findByFechaAlta", query = "SELECT c FROM Cliente c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Cliente.findByFechaEliminacion", query = "SELECT c FROM Cliente c WHERE c.fechaEliminacion = :fechaEliminacion"),
    @NamedQuery(name = "Cliente.findByClave", query = "SELECT c FROM Cliente c WHERE c.clave = :clave"),
    @NamedQuery(name = "Cliente.findByCorreo", query = "SELECT c FROM Cliente c WHERE c.correo = :correo")})
public class Cliente implements Serializable {

    @OneToMany(mappedBy = "idClienteFk")
    private Collection<EntradaMateriaPrima> entradaMateriaPrimaCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente_pk")
    private Integer idClientePk;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "numero_telefono")
    private String numeroTelefono;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "rfc")
    private String rfc;
    @Column(name = "codigo_postal")
    private String codigoPostal;
    @Column(name = "estatus")
    private String estatus;
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(name = "fecha_eliminacion")
    @Temporal(TemporalType.DATE)
    private Date fechaEliminacion;
    @Column(name = "clave")
    private String clave;
    @Column(name = "correo")
    private String correo;
    @JoinColumn(name = "id_usuario_alta", referencedColumnName = "ID")
    @ManyToOne
    private Usuario idUsuarioAlta;
    @JoinColumn(name = "id_usuario_eliminacion", referencedColumnName = "ID")
    @ManyToOne
    private Usuario idUsuarioEliminacion;

    public Cliente() {
    }

    public Cliente(Integer idClientePk) {
        this.idClientePk = idClientePk;
    }

    public Integer getIdClientePk() {
        return idClientePk;
    }

    public void setIdClientePk(Integer idClientePk) {
        this.idClientePk = idClientePk;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(Date fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getIdUsuarioAlta() {
        return idUsuarioAlta;
    }

    public void setIdUsuarioAlta(Usuario idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }

    public Usuario getIdUsuarioEliminacion() {
        return idUsuarioEliminacion;
    }

    public void setIdUsuarioEliminacion(Usuario idUsuarioEliminacion) {
        this.idUsuarioEliminacion = idUsuarioEliminacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClientePk != null ? idClientePk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idClientePk == null && other.idClientePk != null) || (this.idClientePk != null && !this.idClientePk.equals(other.idClientePk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.create.Cliente[ idClientePk=" + idClientePk + " ]";
    }

    @XmlTransient
    public Collection<EntradaMateriaPrima> getEntradaMateriaPrimaCollection() {
        return entradaMateriaPrimaCollection;
    }

    public void setEntradaMateriaPrimaCollection(Collection<EntradaMateriaPrima> entradaMateriaPrimaCollection) {
        this.entradaMateriaPrimaCollection = entradaMateriaPrimaCollection;
    }
    
}
