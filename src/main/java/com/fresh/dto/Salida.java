package com.fresh.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author juanc
 */
public class Salida implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idSalidaPk;
    private Date fecha;
    private Integer idCliente;
    private Integer idUsuario;
    private Integer idOrdenFk;
    private String datoCamion;
    private String chofer;
    private String estatus;
    private String observaciones;
    private String nombreCliente;
    private String nombreUsuario;
    private BigDecimal kilos;
    private BigDecimal kilosBruto;
    private BigDecimal cantidad;
    private String numeroOrden;
    private String tract;
    private String plate;
    private String box;
    private String plateBox;
    private String seal;
    private String temperature;
    private String deviceId;
    private BigDecimal cantidadPorCaja;
    private BigDecimal pesoUnitario;
    private BigDecimal taraUnitario;

    private ArrayList<SalidaProducto> lstSalidaProducto;

    public Integer getIdSalidaPk() {
        return idSalidaPk;
    }

    public void setIdSalidaPk(Integer idSalidaPk) {
        this.idSalidaPk = idSalidaPk;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDatoCamion() {
        return datoCamion;
    }

    public void setDatoCamion(String datoCamion) {
        this.datoCamion = datoCamion;
    }

    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public BigDecimal getKilos() {
        return kilos;
    }

    public void setKilos(BigDecimal kilos) {
        this.kilos = kilos;
    }

    public BigDecimal getKilosBruto() {
        return kilosBruto;
    }

    public void setKilosBruto(BigDecimal kilosBruto) {
        this.kilosBruto = kilosBruto;
    }

    
    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTract() {
        return tract;
    }

    public void setTract(String tract) {
        this.tract = tract;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getPlateBox() {
        return plateBox;
    }

    public void setPlateBox(String plateBox) {
        this.plateBox = plateBox;
    }

    public String getSeal() {
        return seal;
    }

    public void setSeal(String seal) {
        this.seal = seal;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public BigDecimal getCantidadPorCaja() {
        return cantidadPorCaja;
    }

    public void setCantidadPorCaja(BigDecimal cantidadPorCaja) {
        this.cantidadPorCaja = cantidadPorCaja;
    }

    public BigDecimal getPesoUnitario() {
        return pesoUnitario;
    }

    public void setPesoUnitario(BigDecimal pesoUnitario) {
        this.pesoUnitario = pesoUnitario;
    }

    public BigDecimal getTaraUnitario() {
        return taraUnitario;
    }

    public void setTaraUnitario(BigDecimal taraUnitario) {
        this.taraUnitario = taraUnitario;
    }

    public Integer getIdOrdenFk() {
        return idOrdenFk;
    }

    public void setIdOrdenFk(Integer idOrdenFk) {
        this.idOrdenFk = idOrdenFk;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }
    

    public ArrayList<SalidaProducto> getLstSalidaProducto() {
        if (lstSalidaProducto == null) {
            lstSalidaProducto = new ArrayList<>();
        }
        return lstSalidaProducto;
    }

    public void setLstSalidaProducto(ArrayList<SalidaProducto> lstSalidaProducto) {
        this.lstSalidaProducto = lstSalidaProducto;
    }

}
