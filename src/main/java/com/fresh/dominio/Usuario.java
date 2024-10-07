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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u LEFT JOIN FETCH u.idRolFk r WHERE u.username = :username AND u.estatus = 1"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByRole", query = "SELECT u FROM Usuario u WHERE u.role = :role"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono")})
public class Usuario implements Serializable {

    @OneToMany(mappedBy = "idUsuarioFk")
    private Collection<OrdenTrabajo> ordenTrabajoCollection;
    @OneToMany(mappedBy = "idUsuarioFk")
    private Collection<EntradaMateriaPrima> entradaMateriaPrimaCollection;
    @OneToMany(mappedBy = "idUsuarioCancelaFk")
    private Collection<EntradaMateriaPrima> entradaMateriaPrimaCollection1;

    @Column(name = "ESTATUS")
    private String estatus;
    @Column(name = "FECHA_ALTA")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(name = "FECHA_BAJA")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @OneToMany(mappedBy = "idUsuarioAltaFk")
    private Collection<Usuario> usuarioCollection;
    @JoinColumn(name = "ID_USUARIO_ALTA_FK", referencedColumnName = "ID")
    @ManyToOne
    private Usuario idUsuarioAltaFk;
    @OneToMany(mappedBy = "idUsuarioCancelFk")
    private Collection<Usuario> usuarioCollection1;
    @JoinColumn(name = "ID_USUARIO_CANCEL_FK", referencedColumnName = "ID")
    @ManyToOne
    private Usuario idUsuarioCancelFk;

    @OneToMany(mappedBy = "idUsuarioAlta")
    private Collection<Cliente> clienteCollection;
    @OneToMany(mappedBy = "idUsuarioEliminacion")
    private Collection<Cliente> clienteCollection1;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ROLE")
    private String role;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "TELEFONO")
    private String telefono;
    @JoinColumn(name = "ID_ROL_FK", referencedColumnName = "ID_ROL_PK")
    @ManyToOne
    private Rol idRolFk;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Rol getIdRolFk() {
        return idRolFk;
    }

    public void setIdRolFk(Rol idRolFk) {
        this.idRolFk = idRolFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.Usuario[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection1() {
        return clienteCollection1;
    }

    public void setClienteCollection1(Collection<Cliente> clienteCollection1) {
        this.clienteCollection1 = clienteCollection1;
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

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

  

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    public Usuario getIdUsuarioAltaFk() {
        return idUsuarioAltaFk;
    }

    public void setIdUsuarioAltaFk(Usuario idUsuarioAltaFk) {
        this.idUsuarioAltaFk = idUsuarioAltaFk;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection1() {
        return usuarioCollection1;
    }

    public void setUsuarioCollection1(Collection<Usuario> usuarioCollection1) {
        this.usuarioCollection1 = usuarioCollection1;
    }

    public Usuario getIdUsuarioCancelFk() {
        return idUsuarioCancelFk;
    }

    public void setIdUsuarioCancelFk(Usuario idUsuarioCancelFk) {
        this.idUsuarioCancelFk = idUsuarioCancelFk;
    }

    @XmlTransient
    public Collection<OrdenTrabajo> getOrdenTrabajoCollection() {
        return ordenTrabajoCollection;
    }

    public void setOrdenTrabajoCollection(Collection<OrdenTrabajo> ordenTrabajoCollection) {
        this.ordenTrabajoCollection = ordenTrabajoCollection;
    }

    @XmlTransient
    public Collection<EntradaMateriaPrima> getEntradaMateriaPrimaCollection() {
        return entradaMateriaPrimaCollection;
    }

    public void setEntradaMateriaPrimaCollection(Collection<EntradaMateriaPrima> entradaMateriaPrimaCollection) {
        this.entradaMateriaPrimaCollection = entradaMateriaPrimaCollection;
    }

    @XmlTransient
    public Collection<EntradaMateriaPrima> getEntradaMateriaPrimaCollection1() {
        return entradaMateriaPrimaCollection1;
    }

    public void setEntradaMateriaPrimaCollection1(Collection<EntradaMateriaPrima> entradaMateriaPrimaCollection1) {
        this.entradaMateriaPrimaCollection1 = entradaMateriaPrimaCollection1;
    }
    
}
