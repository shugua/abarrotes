/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresh.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juanc
 */
@Entity
@Table(name = "tipo_presentacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPresentacion.findAll", query = "SELECT t FROM TipoPresentacion t"),
    @NamedQuery(name = "TipoPresentacion.findByIdPresentacionPk", query = "SELECT t FROM TipoPresentacion t WHERE t.idPresentacionPk = :idPresentacionPk"),
    @NamedQuery(name = "TipoPresentacion.findByNombre", query = "SELECT t FROM TipoPresentacion t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoPresentacion.findByPeso", query = "SELECT t FROM TipoPresentacion t WHERE t.peso = :peso"),
    @NamedQuery(name = "TipoPresentacion.findByEstatus", query = "SELECT t FROM TipoPresentacion t WHERE t.estatus = :estatus")})
public class TipoPresentacion implements Serializable {

    @OneToMany(mappedBy = "tipoPresentacion")
    private List<EntradaMateriaPrimaProducto> entradaMateriaPrimaProductoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PRESENTACION_PK")
    private Integer idPresentacionPk;
    @Column(name = "NOMBRE")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PESO")
    private BigDecimal peso;
    @Column(name = "ESTATUS")
    private Character estatus;

    public TipoPresentacion() {
    }

    public TipoPresentacion(Integer idPresentacionPk) {
        this.idPresentacionPk = idPresentacionPk;
    }

    public Integer getIdPresentacionPk() {
        return idPresentacionPk;
    }

    public void setIdPresentacionPk(Integer idPresentacionPk) {
        this.idPresentacionPk = idPresentacionPk;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPresentacionPk != null ? idPresentacionPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPresentacion)) {
            return false;
        }
        TipoPresentacion other = (TipoPresentacion) object;
        if ((this.idPresentacionPk == null && other.idPresentacionPk != null) || (this.idPresentacionPk != null && !this.idPresentacionPk.equals(other.idPresentacionPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.TipoPresentacion[ idPresentacionPk=" + idPresentacionPk + " ]";
    }

    @XmlTransient
    public List<EntradaMateriaPrimaProducto> getEntradaMateriaPrimaProductoList() {
        return entradaMateriaPrimaProductoList;
    }

    public void setEntradaMateriaPrimaProductoList(List<EntradaMateriaPrimaProducto> entradaMateriaPrimaProductoList) {
        this.entradaMateriaPrimaProductoList = entradaMateriaPrimaProductoList;
    }
    
}
