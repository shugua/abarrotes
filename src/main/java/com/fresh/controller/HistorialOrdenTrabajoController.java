package com.fresh.controller;

import com.fresh.dto.ClienteD;
import com.fresh.dto.MateriaPrimaD;
import com.fresh.dto.OrdenTrabajoD;
import com.fresh.dto.OrdenTrabajoLote;
import com.fresh.dto.OrdenTrabajoProductoD;
import com.fresh.dto.ProveedorD;
import com.fresh.dto.UsuarioD;
import com.fresh.service.ClienteService;
import com.fresh.service.EntradaMateriaPrimaService;
import com.fresh.service.OrdenTrabajoLoteService;
import com.fresh.service.OrdenTrabajoProductoService;
import com.fresh.service.OrdenTrabajoService;
import com.fresh.service.ProveedorService;
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
public class HistorialOrdenTrabajoController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(HistorialOrdenTrabajoController.class);

    @Autowired
    private InfoUsuarioController infoUsuarioController;
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;
    @Autowired
    private OrdenTrabajoLoteService ordenTrabajoLoteService;
    @Autowired
    private OrdenTrabajoProductoService ordenTrabajoProductoService;
    @Autowired
    private EntradaMateriaPrimaService entradaMateriaPrimaService;

    private List<OrdenTrabajoD> model;

    private ClienteD cliente;
    private UsuarioD usuario;
    private OrdenTrabajoD data;
    private MateriaPrimaD entrada;
    private OrdenTrabajoProductoD dataP;
    private OrdenTrabajoLote OTrabajoLoteR;
    private OrdenTrabajoProductoD oTProductoRemove;
    private OrdenTrabajoProductoD oTProductoEdit;
    private ProveedorD proveedor;

    private Date fechaInicio;
    private Date fechaFin;
    private String stateView;

    private Map paramReport = new HashMap<>();
    private String rutaPDF;
    private byte[] bytes;
    private String pathFileJasper = "";
    private ByteArrayOutputStream outputStream;

    @PostConstruct
    public void init() {

        usuario = infoUsuarioController.getUsuario();

        data = new OrdenTrabajoD();
        cliente = new ClienteD();
        proveedor = new ProveedorD();
        fechaFin = new Date();
        fechaInicio = new Date();
        entrada = new MateriaPrimaD();
        dataP = new OrdenTrabajoProductoD();

        buscar();

    }

    public void buscar() {
        model = ordenTrabajoService.findByFechaAndIdProveedorAndIdCliente(proveedor == null ? null : proveedor.getIdProveedorPk(), cliente == null ? null : cliente.getIdClientePk(), TiempoUtil.getFechaDDMMYYYY(fechaInicio), TiempoUtil.getFechaDDMMYYYY(fechaFin));
    }

    public void removerProducto() {
        ordenTrabajoProductoService.delete(oTProductoRemove.getIdOrdenTrabajoProducto());
        buscar();
        JsfUtil.addSuccessMessage("Producto Cancelado Correctamente.");
    }

    public void oTPLoteRemove() {
        ordenTrabajoLoteService.delete(OTrabajoLoteR);
        buscar();
        JsfUtil.addSuccessMessage("Producto Cancelado Correctamente.");
    }

    private void editarEntrada(MateriaPrimaD m) {

        JsfUtil.addSuccessMessage("Orden de Trabajo Editada Correctamente.");
    }

    private String validaAdd() {
        String mensaje = "";

        return mensaje;
    }

    public void imprimir() {
        setParameterTicket();
        generateReport((int) System.currentTimeMillis(), "OrdenTrabajo.jasper");

        PrimeFaces.current().executeScript("window.frames.miFrame.print();");

    }

    private void setParameterTicket() {
//        BigDecimal total = BigDecimal.ZERO;
//        total = data.getLstOrdenTrabajoProducto().stream().map(OrdenTrabajoProductoD::getCantidad).reduce(BigDecimal.ZERO, BigDecimal::add);
        ArrayList<OrdenTrabajoProductoD> lstTemp = new ArrayList();
        
        ////para imprimir solo se toman los productos activos 
        data.getLstOrdenTrabajoProducto().forEach(d -> {
            if (d.getEstatus().equals("1")) {
                lstTemp.add(d);
            }
        });

        paramReport.put("nombreEmpresa", "Fresh");
        paramReport.put("logo_empresa", "../logo.png");

        paramReport.put("fecha", TiempoUtil.getFechaDDMMYYYYHHMM(data.getFecha()));
        paramReport.put("fechaEntrega", TiempoUtil.getFechaDDMMYYYYHHMM(data.getFechaEntrega()));
        paramReport.put("cliente", data.getNombreCliente());
        paramReport.put("proveedor", data.getNombreProveedor());
        paramReport.put("lote", data.getLote());
        paramReport.put("ordenT", data.getNumeroOrden());
        paramReport.put("loteProductoTerminado", data.getLotePT());

        paramReport.put("nombreSolicita", data.getNombreUsuario());
        paramReport.put("observaciones", data.getObservaciones());
        paramReport.put("lstProductos", lstTemp);
        paramReport.put("total", data.getCantidad());
        paramReport.put("lstLote", data.getLstOrdenTrabajoLote());

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

    private void reset() {
        data = new OrdenTrabajoD();
        dataP = new OrdenTrabajoProductoD();
        cliente = new ClienteD();
        entrada = new MateriaPrimaD();
    }

    public void add() {
        if (dataP.getCantidad() != null && dataP.getTipo() != null && dataP.getLotePT()!= null) {
            data.getLstOrdenTrabajoProducto().add(dataP);
            dataP.setIdOrdenFk(data.getIdOrdenPk());
            dataP.setEstatus("1");

            ordenTrabajoProductoService.insert(dataP);
            dataP = new OrdenTrabajoProductoD();
            JsfUtil.addSuccessMessage("Registro Agregado Correctamente.");
        } else {
            JsfUtil.addErrorMessage("Cantidad o Tipo Vacios.");
        }

    }

    public void addLote() {
        if (entrada != null && entrada.getIdEntradaMateriaPrimaPK() != null) {
            ArrayList<OrdenTrabajoLote> lstT = ordenTrabajoLoteService.getByIdOrdenAndIdEntrada(data.getIdOrdenPk(), entrada.getIdEntradaMateriaPrimaPK());
            if (lstT == null || lstT.isEmpty()) {///se valida que no exista el lote en la orden de trabajo
                OrdenTrabajoLote oT = new OrdenTrabajoLote();
                oT.setIdEntradaMateriaPrimaFk(entrada.getIdEntradaMateriaPrimaPK());
                oT.setLote(entrada.getLote());
                oT.setNombreCliente(entrada.getNombreCliente());
                oT.setNombreProveedor(entrada.getNombreProveedor());
                oT.setIdOrdenTrabajoFk(data.getIdOrdenPk());
                data.getLstOrdenTrabajoLote().add(oT);

                ordenTrabajoLoteService.insert(oT);
                oT = new OrdenTrabajoLote();
                JsfUtil.addSuccessMessage("Registro Agregado Correctamente.");

            } else {
                JsfUtil.addErrorMessage("Lote Repetido.");
            }

        } else {
            JsfUtil.addErrorMessage("Lote Vacios.");
        }

        entrada = new MateriaPrimaD();

    }

    public List<MateriaPrimaD> autoCompleteLote(String lote) {
        return entradaMateriaPrimaService.findByLote(lote);
    }

    public void onRowEdit(RowEditEvent event) {
        data = (OrdenTrabajoD) event.getObject();
        ordenTrabajoService.update(data);
        JsfUtil.addSuccessMessage("Registro Editado.");

    }

    public void onRowCancel(RowEditEvent event) {
        JsfUtil.addSuccessMessage("Se Cancelo la Ediccion.");
    }

    public void onRowEditProducto(RowEditEvent event) {
        oTProductoEdit = (OrdenTrabajoProductoD) event.getObject();

    }

    public ArrayList<ProveedorD> autoCompleteProveedor(String nombreProveedor) {
        return proveedorService.findByIdNombre(nombreProveedor,"2");
    }

    public ArrayList<ClienteD> autoCompleteCliente(String nombreCliente) {
        return clienteService.findByIdNombre(nombreCliente);
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

    public ClienteD getCliente() {
        return cliente;
    }

    public void setCliente(ClienteD cliente) {
        this.cliente = cliente;
    }

    public ProveedorD getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorD proveedor) {
        this.proveedor = proveedor;
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

    public List<OrdenTrabajoD> getModel() {
        return model;
    }

    public void setModel(List<OrdenTrabajoD> model) {
        this.model = model;
    }

    public OrdenTrabajoD getData() {
        return data;
    }

    public void setData(OrdenTrabajoD data) {
        this.data = data;
    }

    public OrdenTrabajoProductoD getoTProductoRemove() {
        return oTProductoRemove;
    }

    public void setoTProductoRemove(OrdenTrabajoProductoD oTProductoRemove) {
        this.oTProductoRemove = oTProductoRemove;
    }

    public OrdenTrabajoProductoD getoTProductoEdit() {
        return oTProductoEdit;
    }

    public void setoTProductoEdit(OrdenTrabajoProductoD oTProductoEdit) {
        this.oTProductoEdit = oTProductoEdit;
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

    public OrdenTrabajoProductoD getDataP() {
        return dataP;
    }

    public void setDataP(OrdenTrabajoProductoD dataP) {
        this.dataP = dataP;
    }

    public OrdenTrabajoLote getOTrabajoLoteR() {
        return OTrabajoLoteR;
    }

    public void setOTrabajoLoteR(OrdenTrabajoLote OTrabajoLoteR) {
        this.OTrabajoLoteR = OTrabajoLoteR;
    }

}
