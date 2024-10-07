package com.fresh.controller;

import com.fresh.dto.ClienteD;
import com.fresh.dto.EnvasadoD;
import com.fresh.dto.Lavado;
import com.fresh.dto.MateriaPrimaD;
import com.fresh.dto.MateriaPrimaProductoD;
import com.fresh.dto.OrdenTrabajoD;
import com.fresh.dto.ProductoD;
import com.fresh.dto.ProveedorD;
import com.fresh.dto.TipoPresentacionD;
import com.fresh.dto.TipoProductoD;
import com.fresh.dto.TipoTarimaD;
import com.fresh.dto.UsuarioD;
import com.fresh.service.ClienteService;
import com.fresh.service.EntradaMateriaPrimaProductoService;
import com.fresh.service.EntradaMateriaPrimaService;
import com.fresh.service.EnvasadoService;
import com.fresh.service.LavadoService;
import com.fresh.service.OrdenTrabajoService;
import com.fresh.service.ProductoService;
import com.fresh.service.ProveedorService;
import com.fresh.service.TipoPresentacionService;
import com.fresh.service.TipoProductoService;
import com.fresh.service.TipoTarimaService;
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
import net.bytebuddy.implementation.bytecode.collection.ArrayLength;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Juan de la Cruz
 */
@Component
@Scope(value = "view", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HistorialEnvasadoController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(HistorialEnvasadoController.class);

    @Autowired
    private EnvasadoService envasadoService;
    @Autowired
    TipoProductoService tipoProductoService;
    @Autowired
    private InfoUsuarioController infoUsuarioController;
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;
    @Autowired
    private EntradaMateriaPrimaService entradaMateriaPrimaService;

    private List<EnvasadoD> model;
    private ArrayList<TipoProductoD> lstTipoProductoD;

    private EnvasadoD data;
    private UsuarioD usuario;
    private OrdenTrabajoD ordenTrabajo;
    private MateriaPrimaD entrada;

    private Date fechaInicio;
    private Date fechaFin;
    private String estatus;
    private String stateView;

    private BigDecimal cantidadTotal;
    private BigDecimal pesoNetoTotal;
    BigDecimal acomulado;

    private Map paramReport = new HashMap<>();
    private String rutaPDF;
    private byte[] bytes;
    private String pathFileJasper = "";
    private ByteArrayOutputStream outputStream;

    @PostConstruct
    public void init() {

        usuario = infoUsuarioController.getUsuario();
        estatus = "1";
        fechaFin = new Date();
        fechaInicio = new Date();
        lstTipoProductoD = tipoProductoService.findAll();

        buscar();

    }

    public void buscar() {

        model = envasadoService.getByIdOrdenEntradaAndFechas(ordenTrabajo == null ? null : ordenTrabajo.getIdOrdenPk(), entrada == null ? null : entrada.getIdEntradaMateriaPrimaPK(), TiempoUtil.getFechaDDMMYYYY(fechaInicio), TiempoUtil.getFechaDDMMYYYY(fechaFin), estatus.isEmpty() ? null : estatus);
        calculaTotales();
    }

    public void calculaTotales() {
        cantidadTotal = BigDecimal.ZERO;
        pesoNetoTotal = BigDecimal.ZERO;

        model.forEach(t -> {

            cantidadTotal = cantidadTotal.add(t.getCantidad());
            pesoNetoTotal = pesoNetoTotal.add(t.getKilos());


        });
    }

    public void remover() {
        envasadoService.delete(data);
        JsfUtil.addSuccessMessage("Registro Eliminado Correctamente.");
        buscar();
    }

    public void imprimir() {
        setParameterTicket();
        generateReport((int) System.currentTimeMillis(), "LavadoHistorial.jasper");

        PrimeFaces.current().executeScript("window.frames.miFrame.print();");

    }


    private void setParameterTicket() {

        int difreneciaDias = TiempoUtil.diferenciasDeFechas(fechaInicio, fechaFin);
        String fecha = "";
        if (difreneciaDias == 0) {
            fecha = TiempoUtil.getFechaDDMMYYYY(fechaInicio);
        } else {
            fecha = TiempoUtil.getFechaDDMMYYYY(fechaInicio) + "-" + TiempoUtil.getFechaDDMMYYYY(fechaFin);
        }
        paramReport.put("nombreEmpresa", "Fresh");
        paramReport.put("logo_empresa", "../logo.png");
        paramReport.put("fecha", fecha);

    }

    public void generateReport(int folio, String reporte) {

        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String temporal = "";
            if (servletContext.getRealPath("") == null) {
                temporal = Constantes.PATHSERVER;
            } else {
                temporal = servletContext.getRealPath("");
            }
            pathFileJasper = temporal + "resources" + File.separatorChar + "report" + File.separatorChar + "Lavado" + File.separatorChar + reporte;

            JasperPrint jp = JasperFillManager.fillReport(pathFileJasper, paramReport);
            outputStream = JasperReportUtil.getOutputStreamFromReport(paramReport, pathFileJasper);

            JRPdfExporter jRPdfExporter = new JRPdfExporter();
            jRPdfExporter.setExporterInput(new SimpleExporterInput(jp));
            jRPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            byte[] bytes = outputStream.toByteArray();
            rutaPDF = UtilUpload.saveFileTemp(bytes, "LavadoHistorial", 1, 2);
        } catch (Exception exception) {
            JsfUtil.addErrorMessage("Error " + exception.getMessage());
            JsfUtil.addErrorMessage("Error " + exception.getStackTrace());
            System.out.println("Error >" + exception.getMessage());
            exception.getStackTrace();
        }
    }

    public List<OrdenTrabajoD> autoCompleteOrdenTrabajo(String ordenTrabajo) {
        return ordenTrabajoService.findByOrdenTrabajo(ordenTrabajo);
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

    public String getStateView() {
        return stateView;
    }

    public void setStateView(String stateView) {
        this.stateView = stateView;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

    public OrdenTrabajoD getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajoD ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public List<EnvasadoD> getModel() {
        return model;
    }

    public void setModel(List<EnvasadoD> model) {
        this.model = model;
    }

    public EnvasadoD getData() {
        return data;
    }

    public void setData(EnvasadoD data) {
        this.data = data;
    }

    public BigDecimal getAcomulado() {
        return acomulado;
    }

    public void setAcomulado(BigDecimal acomulado) {
        this.acomulado = acomulado;
    }

  

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public MateriaPrimaD getEntrada() {
        return entrada;
    }

    public void setEntrada(MateriaPrimaD entrada) {
        this.entrada = entrada;
    }

    public String getRutaPDF() {
        return rutaPDF;
    }

    public void setRutaPDF(String rutaPDF) {
        this.rutaPDF = rutaPDF;
    }

}
