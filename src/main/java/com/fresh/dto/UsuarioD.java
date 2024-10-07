package com.fresh.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author juanc
 */
public class UsuarioD implements Serializable{

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String password;
    private String role;
    private String nombre;
    private String telefono;
    private String estatus;
    private Integer usuarioAltaFk;
    private Integer usuarioBajaFk;
    private Date fechaAlta;
    private Date fechaBaja;
    private Integer idRolFk;
    private RolD rol;
    
    private ArrayList<MenuD> menu = new ArrayList<>();
    private Set<String> allowedUrl = new HashSet<>();

    public UsuarioD() {
    }

    public UsuarioD(Integer id, String username, String password, String role, String nombre, String telefono, RolD rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.nombre = nombre;
        this.telefono = telefono;
        this.rol = rol;
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

    public RolD getRol() {
        return rol;
    }

    public void setRol(RolD rol) {
        this.rol = rol;
    }

    public ArrayList<MenuD> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<MenuD> menu) {
        this.menu = menu;
    }

    public Set<String> getAllowedUrl() {
        return allowedUrl;
    }

    public void setAllowedUrl(Set<String> allowedUrl) {
        this.allowedUrl = allowedUrl;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getUsuarioAltaFk() {
        return usuarioAltaFk;
    }

    public void setUsuarioAltaFk(Integer usuarioAltaFk) {
        this.usuarioAltaFk = usuarioAltaFk;
    }

    public Integer getUsuarioBajaFk() {
        return usuarioBajaFk;
    }

    public void setUsuarioBajaFk(Integer usuarioBajaFk) {
        this.usuarioBajaFk = usuarioBajaFk;
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

    public Integer getIdRolFk() {
        return idRolFk;
    }

    public void setIdRolFk(Integer idRolFk) {
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
        if (!(object instanceof UsuarioD)) {
            return false;
        }
        UsuarioD other = (UsuarioD) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.Usuario[ id=" + id + " ]";
    }

  
}
