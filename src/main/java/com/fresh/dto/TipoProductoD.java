package com.fresh.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author juanc
 */
public class TipoProductoD implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idTipoProductoPk;
    private String descripcion;
    private BigDecimal peso;

    @Override
    public String toString() {
        return "TipoProductoD{" + "idTipoProductoPk=" + idTipoProductoPk + ", descripcion=" + descripcion + ", peso=" + peso + '}';
    }

    public TipoProductoD() {
    }

    public Integer getIdTipoProductoPk() {
        return idTipoProductoPk;
    }

    public void setIdTipoProductoPk(Integer idTipoProductoPk) {
        this.idTipoProductoPk = idTipoProductoPk;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

}
