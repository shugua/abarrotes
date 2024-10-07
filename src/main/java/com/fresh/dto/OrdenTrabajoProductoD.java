package com.fresh.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author juanc
 */
public class OrdenTrabajoProductoD implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idOrdenTrabajoProducto;
    private BigDecimal cantidad;
    private String tipo;
    private Integer idOrdenFk;
    private String estatus;
    private String lotePT;

    @Override
    public String toString() {
        return "OrdenTrabajoProducto{" + "idOrdenTrabajoProducto=" + idOrdenTrabajoProducto + ", cantidad=" + cantidad + ", tipo=" + tipo + ", idOrdenFk=" + idOrdenFk + ", estatus=" + estatus + '}';
    }

    
    public Integer getIdOrdenTrabajoProducto() {
        return idOrdenTrabajoProducto;
    }

    public void setIdOrdenTrabajoProducto(Integer idOrdenTrabajoProducto) {
        this.idOrdenTrabajoProducto = idOrdenTrabajoProducto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdOrdenFk() {
        return idOrdenFk;
    }

    public void setIdOrdenFk(Integer idOrdenFk) {
        this.idOrdenFk = idOrdenFk;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getLotePT() {
        return lotePT;
    }

    public void setLotePT(String lotePT) {
        this.lotePT = lotePT;
    }
    
    
    
}
