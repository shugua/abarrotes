package com.fresh.controller;

import com.fresh.dto.ClienteD;
import com.fresh.dto.MateriaPrimaD;
import com.fresh.dto.MateriaPrimaProductoD;
import com.fresh.dto.OrdenTrabajoD;
import com.fresh.dto.ProductoD;
import com.fresh.dto.ProveedorD;
import com.fresh.dto.TipoPresentacionD;
import com.fresh.dto.TipoTarimaD;
import com.fresh.dto.UsuarioD;
import com.fresh.service.ClienteService;
import com.fresh.service.EntradaMateriaPrimaProductoService;
import com.fresh.service.EntradaMateriaPrimaService;
import com.fresh.service.OrdenTrabajoService;
import com.fresh.service.ProductoService;
import com.fresh.service.ProveedorService;
import com.fresh.service.TipoPresentacionService;
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
public class HistorialCuartoSucioController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(HistorialCuartoSucioController.class);

    @Autowired
    private TipoTarimaService tipoTarimaService;
    @Autowired
    private TipoPresentacionService tipoPresentacionService;
    @Autowired
    private InfoUsuarioController infoUsuarioController;
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EntradaMateriaPrimaService entradaMateriaPrimaService;
    @Autowired
    private EntradaMateriaPrimaProductoService entradaMateriaPrimaProductoService;

    private List<MateriaPrimaD> model;
    private ArrayList<TipoTarimaD> lstTipoTarima;
    private List<MateriaPrimaProductoD> lstMateriaPrimaProducto;
    private ArrayList<TipoPresentacionD> lstTipoPresentacion;
    private ArrayList<MateriaPrimaProductoD> lstProducto;

    private UsuarioD usuario;
    private OrdenTrabajoD ordenTrabajo;
    private MateriaPrimaProductoD data;

    private Date fechaInicio;
    private Date fechaFin;
    private String stateView;

    private BigDecimal cantidadTotal;
    private BigDecimal pesoBrutoTotal;
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
        lstProducto = new ArrayList<>();
        lstTipoTarima = tipoTarimaService.findAll();
        lstTipoPresentacion = tipoPresentacionService.findAll();
        fechaFin = new Date();
        fechaInicio = new Date();

        buscar();

    }

    public void buscar() {
        lstMateriaPrimaProducto = entradaMateriaPrimaProductoService.getByIdOrdenEntradaAndFechas(ordenTrabajo == null ? null : ordenTrabajo.getIdOrdenPk(), TiempoUtil.getFechaDDMMYYYY(fechaInicio), TiempoUtil.getFechaDDMMYYYY(fechaFin));
        calculaTotales();
    }

    public void calculaTotales() {
        cantidadTotal = BigDecimal.ZERO;
        pesoBrutoTotal = BigDecimal.ZERO;
        pesoNetoTotal = BigDecimal.ZERO;
        acomulado = BigDecimal.ZERO;

        lstMateriaPrimaProducto.forEach(t -> {
            
            cantidadTotal = cantidadTotal.add(t.getCantidad());
            pesoBrutoTotal = pesoBrutoTotal.add(t.getPesoBruto());
            pesoNetoTotal = pesoNetoTotal.add(t.getPesoNeto());
            acomulado = acomulado.add(t.getPesoNeto());
            t.setPesoAcomulado(acomulado);

        });
    }

    public void imprimir() {
        setParameterTicket(data);
        generateReport((int) System.currentTimeMillis(), "CuartoSucioHistorial.jasper");

        PrimeFaces.current().executeScript("window.frames.miFrame.print();");

    }

    private void setParameterTicket(MateriaPrimaProductoD m) {

        paramReport.put("nombreEmpresa", "Fresh");
        paramReport.put("logo_empresa", "logo.png");
        paramReport.put("fecha", TiempoUtil.getFechaDDMMYYYYHHMM(fechaInicio));
        paramReport.put("lstProductos", lstMateriaPrimaProducto);

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
            pathFileJasper = temporal + "resources" + File.separatorChar + "report" + File.separatorChar + "MateriaPrima" + File.separatorChar + reporte;

            JasperPrint jp = JasperFillManager.fillReport(pathFileJasper, paramReport);
            outputStream = JasperReportUtil.getOutputStreamFromReport(paramReport, pathFileJasper);

            JRPdfExporter jRPdfExporter = new JRPdfExporter();
            jRPdfExporter.setExporterInput(new SimpleExporterInput(jp));
            jRPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            byte[] bytes = outputStream.toByteArray();
            rutaPDF = UtilUpload.saveFileTemp(bytes, "CuartoSucioHistorial", 1, 2);
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

    public ArrayList<MateriaPrimaProductoD> getLstProducto() {
        return lstProducto;
    }

    public void setLstProducto(ArrayList<MateriaPrimaProductoD> lstProducto) {
        this.lstProducto = lstProducto;
    }

    public ArrayList<TipoTarimaD> getLstTipoTarima() {
        return lstTipoTarima;
    }

    public void setLstTipoTarima(ArrayList<TipoTarimaD> lstTipoTarima) {
        this.lstTipoTarima = lstTipoTarima;
    }

    public ArrayList<TipoPresentacionD> getLstTipoPresentacion() {
        return lstTipoPresentacion;
    }

    public void setLstTipoPresentacion(ArrayList<TipoPresentacionD> lstTipoPresentacion) {
        this.lstTipoPresentacion = lstTipoPresentacion;
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

    public BigDecimal getPesoBrutoTotal() {
        return pesoBrutoTotal;
    }

    public void setPesoBrutoTotal(BigDecimal pesoBrutoTotal) {
        this.pesoBrutoTotal = pesoBrutoTotal;
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

    public MateriaPrimaProductoD getData() {
        return data;
    }

    public void setData(MateriaPrimaProductoD data) {
        this.data = data;
    }

    public List<MateriaPrimaProductoD> getLstMateriaPrimaProducto() {
        return lstMateriaPrimaProducto;
    }

    public void setLstMateriaPrimaProducto(List<MateriaPrimaProductoD> lstMateriaPrimaProducto) {
        this.lstMateriaPrimaProducto = lstMateriaPrimaProducto;
    }

    public String getRutaPDF() {
        return rutaPDF;
    }

    public void setRutaPDF(String rutaPDF) {
        this.rutaPDF = rutaPDF;
    }

}
