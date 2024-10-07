package com.fresh.controller;

import com.fresh.dto.ClienteD;
import com.fresh.dto.EnvasadoD;
import com.fresh.dto.MateriaPrimaD;
import com.fresh.dto.OrdenTrabajoD;
import com.fresh.dto.TipoProductoD;
import com.fresh.dto.UsuarioD;
import com.fresh.service.EntradaMateriaPrimaService;
import com.fresh.service.EnvasadoService;
import com.fresh.service.OrdenTrabajoService;
import com.fresh.service.TipoProductoService;
import com.fresh.util.Constantes;
import com.fresh.util.JasperReportUtil;
import com.fresh.util.JsfUtil;
import com.fresh.util.TiempoUtil;
import com.fresh.util.UtilUpload;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Juan de la Cruz
 */
@Component
@Scope(value = "view", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EnvasadoController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(EnvasadoController.class);

    @Autowired
    private EnvasadoService envasadoService;
    @Autowired
    private TipoProductoService tipoProductoService;
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;
    @Autowired
    private InfoUsuarioController infoUsuarioController;

    @Autowired
    private EntradaMateriaPrimaService entradaMateriaPrimaService;

    private List<EnvasadoD> model;
    private ArrayList<TipoProductoD> lstTipoProductoD;

    private EnvasadoD envasadoD;
    private OrdenTrabajoD ordenTrabajo;
    private MateriaPrimaD entrada;
    private UsuarioD usuario;

    private String rutaPDF;

    private BigDecimal cantidadTotal;
    private BigDecimal pesoNetoTotal;
    Date fecha;

    @PostConstruct
    public void init() {
        envasadoD = new EnvasadoD();
        envasadoD.setCantidadCaja(new BigDecimal(4));
        usuario = infoUsuarioController.getUsuario();
        lstTipoProductoD = tipoProductoService.findAll();
        cantidadTotal = BigDecimal.ZERO;
        pesoNetoTotal = BigDecimal.ZERO;
        fecha = new Date();

    }

    public void buscaDatos() {

        model = envasadoService.getByIdOrdenEntradaAndFechas(ordenTrabajo == null ? null : ordenTrabajo.getIdOrdenPk(), null, TiempoUtil.getFechaDDMMYYYY(fecha), TiempoUtil.getFechaDDMMYYYY(fecha), "1");
        calculaTotales();
    }

    public void calculaTotales() {
        cantidadTotal = BigDecimal.ZERO;
        pesoNetoTotal = BigDecimal.ZERO;

        model.forEach(t -> {

            cantidadTotal = cantidadTotal.add(t.getCantidad());
            pesoNetoTotal = pesoNetoTotal.add(t.getKilos());

        });
        
        System.out.println("totales "+cantidadTotal);
        System.out.println("pesoNetoTotal "+pesoNetoTotal);
    }

    public void save() {
        try {
            String mensaje = validaInsert();

            if (mensaje.isEmpty()) {
                calculaPeso();
                envasadoD.setEstatus("1");
                envasadoD.setFecha(new Date());
                envasadoD.setIdEntradaMateriaPrimaFK(entrada.getIdEntradaMateriaPrimaPK());
                envasadoD.setIdOrdenFk(ordenTrabajo.getIdOrdenPk());
                envasadoD.setIdUsuarioFk(infoUsuarioController.getUsuario().getId());
                envasadoD.setNumeroTarima(envasadoService.getNumeroTarimaByIdOrden(ordenTrabajo.getIdOrdenPk()));
                envasadoService.insert(envasadoD);
//                imprimir();
                reset();
                buscaDatos();
                JsfUtil.addSuccessMessage("Registro Insertado Correctamente.");
            } else {
                System.out.println("error");
                JsfUtil.addErrorMessage(mensaje);
            }

        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Error " + ex.getMessage());
        }

    }

    private String validaInsert() {
        String mensaje = "";

        if (ordenTrabajo == null || ordenTrabajo.getIdOrdenPk() == null) {
            mensaje = "Selecciona una Orden de Trabajo.";
        }
        if (entrada == null || entrada.getIdEntradaMateriaPrimaPK() == null) {
            mensaje = "Selecciona un Lote de Entrada.";
        }

        if (envasadoD.getCantidad() == null) {
            mensaje = "Ingresa una Cantidad.";
        }
        if (envasadoD.getKilosBruto() == null) {
            mensaje = "Ingresa Kilos Bruto.";
        }
        return mensaje;
    }

    public void calculaPeso() {
        envasadoD.setKilosBruto(BigDecimal.ZERO);
        envasadoD.setKilos(BigDecimal.ZERO);
        BigDecimal cantidadTotal = BigDecimal.ZERO;

        if (envasadoD.getCantidad() != null && envasadoD.getCantidadCaja() != null && envasadoD.getPesoUnitario() != null && envasadoD.getTaraUnitario() != null) {
            cantidadTotal = envasadoD.getCantidad().multiply(envasadoD.getCantidadCaja());
            envasadoD.setKilos(cantidadTotal.multiply(envasadoD.getPesoUnitario()));
            envasadoD.setKilosBruto(envasadoD.getKilos().add(cantidadTotal.multiply(envasadoD.getTaraUnitario())));

        }

    }

    private void reset() {
        envasadoD = new EnvasadoD();
        envasadoD.setCantidadCaja(new BigDecimal(4));
    }

    public void imprimir() {

        PrimeFaces.current().executeScript("window.frames.miFrame.print();");

    }

    public List<OrdenTrabajoD> autoCompleteOrden(String orden) {
        return ordenTrabajoService.findByOrdenTrabajo(orden);
    }

    public List<MateriaPrimaD> autoCompleteLote(String lote) {
        return entradaMateriaPrimaService.findByLote(lote);
    }

    public UsuarioD getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioD usuario) {
        this.usuario = usuario;
    }

    public MateriaPrimaD getEntrada() {
        return entrada;
    }

    public void setEntrada(MateriaPrimaD entrada) {
        this.entrada = entrada;
    }

    public List<EnvasadoD> getModel() {
        return model;
    }

    public void setModel(ArrayList<EnvasadoD> model) {
        this.model = model;
    }

    public EnvasadoD getEnvasadoD() {
        return envasadoD;
    }

    public void setEnvasadoD(EnvasadoD envasadoD) {
        this.envasadoD = envasadoD;
    }

    public OrdenTrabajoD getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajoD ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public BigDecimal getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(BigDecimal cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public BigDecimal getPesoNetoTotal() {
        return pesoNetoTotal;
    }

    public void setPesoNetoTotal(BigDecimal pesoNetoTotal) {
        this.pesoNetoTotal = pesoNetoTotal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<TipoProductoD> getLstTipoProductoD() {
        return lstTipoProductoD;
    }

    public void setLstTipoProductoD(ArrayList<TipoProductoD> lstTipoProductoD) {
        this.lstTipoProductoD = lstTipoProductoD;
    }

    public String getRutaPDF() {
        return rutaPDF;
    }

    public void setRutaPDF(String rutaPDF) {
        this.rutaPDF = rutaPDF;
    }

}
