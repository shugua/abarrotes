package com.fresh.dto;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author juanc
 */

public class OrdenTrabajoLote implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idEntradaMateriaPrimaFk;
    private Integer idOrdenTrabajoFk;
    private String lote;
    private String nombreCliente;
    private String nombreProveedor;

    public Integer getIdEntradaMateriaPrimaFk() {
        return idEntradaMateriaPrimaFk;
    }

    public void setIdEntradaMateriaPrimaFk(Integer idEntradaMateriaPrimaFk) {
        this.idEntradaMateriaPrimaFk = idEntradaMateriaPrimaFk;
    }

    public Integer getIdOrdenTrabajoFk() {
        return idOrdenTrabajoFk;
    }

    public void setIdOrdenTrabajoFk(Integer idOrdenTrabajoFk) {
        this.idOrdenTrabajoFk = idOrdenTrabajoFk;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    
    
}
