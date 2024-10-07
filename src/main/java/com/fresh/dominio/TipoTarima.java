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
@Table(name = "tipo_tarima")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTarima.findAll", query = "SELECT t FROM TipoTarima t"),
    @NamedQuery(name = "TipoTarima.findByIdTarimaPk", query = "SELECT t FROM TipoTarima t WHERE t.idTarimaPk = :idTarimaPk"),
    @NamedQuery(name = "TipoTarima.findByNombre", query = "SELECT t FROM TipoTarima t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoTarima.findByPeso", query = "SELECT t FROM TipoTarima t WHERE t.peso = :peso"),
    @NamedQuery(name = "TipoTarima.findByEstatus", query = "SELECT t FROM TipoTarima t WHERE t.estatus = :estatus")})
public class TipoTarima implements Serializable {

    @OneToMany(mappedBy = "tipoTarima")
    private List<EntradaMateriaPrimaProducto> entradaMateriaPrimaProductoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TARIMA_PK")
    private Integer idTarimaPk;
    @Column(name = "NOMBRE")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PESO")
    private BigDecimal peso;
    @Column(name = "ESTATUS")
    private Character estatus;

    public TipoTarima() {
    }

    public TipoTarima(Integer idTarimaPk) {
        this.idTarimaPk = idTarimaPk;
    }

    public Integer getIdTarimaPk() {
        return idTarimaPk;
    }

    public void setIdTarimaPk(Integer idTarimaPk) {
        this.idTarimaPk = idTarimaPk;
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
        hash += (idTarimaPk != null ? idTarimaPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTarima)) {
            return false;
        }
        TipoTarima other = (TipoTarima) object;
        if ((this.idTarimaPk == null && other.idTarimaPk != null) || (this.idTarimaPk != null && !this.idTarimaPk.equals(other.idTarimaPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fresh.dominio.TipoTarima[ idTarimaPk=" + idTarimaPk + " ]";
    }

    @XmlTransient
    public List<EntradaMateriaPrimaProducto> getEntradaMateriaPrimaProductoList() {
        return entradaMateriaPrimaProductoList;
    }

    public void setEntradaMateriaPrimaProductoList(List<EntradaMateriaPrimaProducto> entradaMateriaPrimaProductoList) {
        this.entradaMateriaPrimaProductoList = entradaMateriaPrimaProductoList;
    }
    
}
