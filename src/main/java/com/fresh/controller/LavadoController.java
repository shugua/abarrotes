package com.fresh.controller;

import com.fresh.dto.ClienteD;
import com.fresh.dto.Lavado;
import com.fresh.dto.MateriaPrimaD;
import com.fresh.dto.OrdenTrabajoD;
import com.fresh.dto.OrdenTrabajoLote;
import com.fresh.dto.OrdenTrabajoProductoD;
import com.fresh.dto.TipoProductoD;
import com.fresh.dto.UsuarioD;
import com.fresh.service.ClienteService;
import com.fresh.service.EntradaMateriaPrimaService;
import com.fresh.service.OrdenTrabajoLoteService;
import com.fresh.service.OrdenTrabajoProductoService;
import com.fresh.service.OrdenTrabajoService;
import com.fresh.service.LavadoService;
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
import java.time.LocalDateTime;
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
public class LavadoController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(LavadoController.class);

    @Autowired
    private LavadoService peladoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EntradaMateriaPrimaService entradaMateriaPrimaService;
    @Autowired
    private InfoUsuarioController infoUsuarioController;
    @Autowired
    TipoProductoService tipoProductoService;
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;
    @Autowired
    private OrdenTrabajoLoteService ordenTrabajoLoteService;
    @Autowired
    private OrdenTrabajoProductoService ordenTrabajoProductoService;

    private OrdenTrabajoD ordenTrabajo;

    private ArrayList<Lavado> model;
    private ArrayList<TipoProductoD> lstTipoProductoD;

    private Lavado data;
    private ClienteD cliente;
    private UsuarioD usuario;

    private MateriaPrimaD entrada;

    private String stateView;

    private Map paramReport = new HashMap<>();
    private String rutaPDF;
    private String pathFileJasper = "";
    private ByteArrayOutputStream outputStream;

    @PostConstruct
    public void init() {
        stateView = "init";

        data = new Lavado();
        usuario = infoUsuarioController.getUsuario();
        lstTipoProductoD = tipoProductoService.findAll();

    }

    public void save() {
        try {
            String mensaje = validaInsert();

            if (mensaje.isEmpty()) {
                data.setFecha(new Date());
                data.setIdEntradaMateriaPrimaFK(entrada.getIdEntradaMateriaPrimaPK());
                data.setIdOrdenFk(ordenTrabajo.getIdOrdenPk());
                data.setIdUsuarioFk(usuario.getId());
                peladoService.insert(data);
//                imprimir();
                reset();
                JsfUtil.addSuccessMessage("Registro Insertado Correctamente.");
            } else {
   
                JsfUtil.addErrorMessage(mensaje);
            }

        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Error " + ex.getMessage());
        }

    }

    private String validaInsert() {
        String mensaje = "";

        if (entrada == null || entrada.getIdEntradaMateriaPrimaPK() == null) {
            mensaje = "Selecciona un Lote";
        }

        if (ordenTrabajo == null || ordenTrabajo.getIdOrdenPk() == null) {
            mensaje = "Selecciona una Orden";
        }

        if (data.getCantidad() == null) {
            mensaje = "Ingresa una Cantidad";
        }

        if (data.getPesoNeto() == null) {
            mensaje = "Ingresa Peso Neto";
        }

        if (data.getIdTipoProductoFk() == null) {
            mensaje = "Selecciona un Tipo de Producto";
        }

        return mensaje;
    }

    private void reset() {
        data = new Lavado();
    }

    public void add() {

    }

    public void imprimir() {
        setParameterTicket();
        generateReport((int) System.currentTimeMillis(), "OrdenTrabajo.jasper");
        PrimeFaces.current().executeScript("window.frames.miFrame.print();");

    }

    private void setParameterTicket() {
        BigDecimal total = BigDecimal.ZERO;

        paramReport.put("nombreEmpresa", "Fresh");
        paramReport.put("logo_empresa", "../logo.png");

//        paramReport.put("fecha", TiempoUtil.getFechaDDMMYYYYHHMM(data.getFecha()));
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
            pathFileJasper = temporal + "resources" + File.separatorChar + "report" + File.separatorChar + "OrdenTrabajo" + File.separatorChar + reporte;
            JasperPrint jp = JasperFillManager.fillReport(pathFileJasper, paramReport);
            outputStream = JasperReportUtil.getOutputStreamFromReport(paramReport, pathFileJasper);
            JRPdfExporter jRPdfExporter = new JRPdfExporter();
            jRPdfExporter.setExporterInput(new SimpleExporterInput(jp));
            jRPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            byte[] bytes = outputStream.toByteArray();
            rutaPDF = UtilUpload.saveFileTemp(bytes, "ordenTrabajo", 1, 2);
        } catch (Exception exception) {
            JsfUtil.addErrorMessage("Error " + exception.getMessage());
            JsfUtil.addErrorMessage("Error " + exception.getStackTrace());
            System.out.println("Error >" + exception.getMessage());
            exception.getStackTrace();
        }
    }

    public List<MateriaPrimaD> autoCompleteLote(String lote) {
        return entradaMateriaPrimaService.findByLote(lote);
    }

    public ArrayList<ClienteD> autoCompleteCliente(String nombreCliente) {
        return clienteService.findByIdNombre(nombreCliente);
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

    public ClienteD getCliente() {
        return cliente;
    }

    public void setCliente(ClienteD cliente) {
        this.cliente = cliente;
    }

    public String getRutaPDF() {
        return rutaPDF;
    }

    public void setRutaPDF(String rutaPDF) {
        this.rutaPDF = rutaPDF;
    }

    public MateriaPrimaD getEntrada() {
        return entrada;
    }

    public void setEntrada(MateriaPrimaD entrada) {
        this.entrada = entrada;
    }

    public OrdenTrabajoD getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajoD ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public String getStateView() {
        return stateView;
    }

    public void setStateView(String stateView) {
        this.stateView = stateView;
    }

    public ArrayList<Lavado> getModel() {
        return model;
    }

    public void setModel(ArrayList<Lavado> model) {
        this.model = model;
    }

    public Lavado getData() {
        return data;
    }

    public void setData(Lavado data) {
        this.data = data;
    }

    public ArrayList<TipoProductoD> getLstTipoProductoD() {
        return lstTipoProductoD;
    }

    public void setLstTipoProductoD(ArrayList<TipoProductoD> lstTipoProductoD) {
        this.lstTipoProductoD = lstTipoProductoD;
    }

}
