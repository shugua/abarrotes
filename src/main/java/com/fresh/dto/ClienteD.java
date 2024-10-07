
package com.fresh.dto;

import com.fresh.dominio.Usuario;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author juanc
 */
public class ClienteD implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idClientePk;
    private String nombre;
    private String numeroTelefono;
    private String direccion;
    private String rfc;
    private String codigoPostal;
    private String estatus;
    private Date fechaAlta;
    private Date fechaEliminacion;
    private String clave;
    private String correo;
    private Integer idUsuarioAltaPk;
    private Integer idUsuarioEliminacionPk;
    private Usuario idUsuarioAlta;
    private Usuario idUsuarioEliminacion;

    public ClienteD(Integer idClientePk, String nombre, String numeroTelefono, String direccion, String rfc, String codigoPostal, String estatus, Date fechaAlta, Date fechaEliminacion, String clave, String correo) {
        this.idClientePk = idClientePk;
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
        this.direccion = direccion;
        this.rfc = rfc;
        this.codigoPostal = codigoPostal;
        this.estatus = estatus;
        this.fechaAlta = fechaAlta;
        this.fechaEliminacion = fechaEliminacion;
        this.clave = clave;
        this.correo = correo;
    }
    
    

    public ClienteD() {
    }

    public ClienteD(Integer idClientePk) {
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

    public Integer getIdUsuarioAltaPk() {
        return idUsuarioAltaPk;
    }

    public void setIdUsuarioAltaPk(Integer idUsuarioAltaPk) {
        this.idUsuarioAltaPk = idUsuarioAltaPk;
    }

    public Integer getIdUsuarioEliminacionPk() {
        return idUsuarioEliminacionPk;
    }

    public void setIdUsuarioEliminacionPk(Integer idUsuarioEliminacionPk) {
        this.idUsuarioEliminacionPk = idUsuarioEliminacionPk;
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
        if (!(object instanceof ClienteD)) {
            return false;
        }
        ClienteD other = (ClienteD) object;
        if ((this.idClientePk == null && other.idClientePk != null) || (this.idClientePk != null && !this.idClientePk.equals(other.idClientePk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.create.Cliente[ idClientePk=" + idClientePk + " ]";
    }
    
}
