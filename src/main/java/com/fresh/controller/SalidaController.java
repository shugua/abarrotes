package com.fresh.controller;

import com.fresh.dto.ClienteD;
import com.fresh.dto.EnvasadoD;
import com.fresh.dto.MateriaPrimaD;
import com.fresh.dto.OrdenTrabajoD;
import com.fresh.dto.Salida;
import com.fresh.dto.SalidaProducto;
import com.fresh.dto.UsuarioD;
import com.fresh.service.ClienteService;
import com.fresh.service.EntradaMateriaPrimaService;
import com.fresh.service.EnvasadoService;
import com.fresh.service.OrdenTrabajoService;
import com.fresh.service.SalidaProductoService;
import com.fresh.service.SalidaService;
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
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SalidaController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(SalidaController.class);

    @Autowired
    private SalidaService salidaService;
    @Autowired
    private EnvasadoService envasadoService;
    @Autowired
    private SalidaProductoService salidaProductoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EntradaMateriaPrimaService entradaMateriaPrimaService;
    @Autowired
    private InfoUsuarioController infoUsuarioController;
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    private OrdenTrabajoD ordenTrabajo;

    private List<EnvasadoD> model;
    private ArrayList<EnvasadoD> lstEnvasado;
    private ArrayList<SalidaProducto> lstSalidaProducto;

    private Salida data;
    private SalidaProducto dataProducto;
    private ClienteD cliente;
    private UsuarioD usuario;

    private String stateView;

    private Map paramReport = new HashMap<>();
    private String rutaPDF;
    private String pathFileJasper = "";
    private ByteArrayOutputStream outputStream;

    @PostConstruct
    public void init() {
        stateView = "init";

        model = new ArrayList<>();
        data = new Salida();
        lstEnvasado = new ArrayList<>();
        usuario = infoUsuarioController.getUsuario();

    }

    public void save() {
        try {
            Integer idSalida = 0;
            String mensaje = validaInsert();
            System.out.println("mensaje " + mensaje);
            if (mensaje.isEmpty()) {
                data.setFecha(new Date());
//                data.setIdCliente(ordenTrabajo.getid);
                data.setIdUsuario(usuario.getId());
                idSalida = salidaService.insert(data);
                if (idSalida != 0) {
                    for (EnvasadoD e : lstEnvasado) {
                        ///modfica la cantidad entregada en la tabla de envasado
                        envasadoService.modificaCantidadEntregada(e.getCantidadEntregada(), BigDecimal.ZERO, e.getEstatus(), e.getIdEnvasadoPk());

                        SalidaProducto sp = new SalidaProducto();
                        sp.setIdSalidaFk(idSalida);
                        sp.setIdEnvasadoFk(e.getIdEnvasadoPk());
                        sp.setCantidad(e.getCantidadSalida());
                        sp.setKilos(e.getKilos());
                        sp.setEstatus("1");
                        salidaProductoService.insert(sp);
                    }
                    imprimir();
                    reset();
                    JsfUtil.addSuccessMessage("Registro Insertado Correctamente.");
                }
            } else {

                JsfUtil.addErrorMessage(mensaje);
            }

        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Error " + ex.getMessage());
        }

    }

    private String validaExistencias() {
        String mensaje = "";
        lstSalidaProducto = new ArrayList<>();
        for (EnvasadoD e : lstEnvasado) {
            ////trae el producto envasado para modificar la cantidad entregada
            EnvasadoD envasado = envasadoService.getByIdEnvasado(e.getIdEnvasadoPk());
            envasado.setCantidadEntregada(envasado.getCantidadEntregada().add(e.getCantidadSalida()));
            e.setCantidadEntregada(envasado.getCantidadEntregada());
            if (envasado.getCantidadEntregada().compareTo(e.getCantidad()) > 0) {///SI SE VAN A ENTREGAR MAS CANTIDAD A LA EXISTENTE REGRESA MENSAJE
                mensaje = "Cantidad Insuficiente. Tarima " + e.getNumeroTarima();
            }

            if (envasado.getCantidad().compareTo(e.getCantidadEntregada()) == 0) {///SI YA NO HAY CANTIDAD A ENTREGAR SE CAMBIA EL ESTATUS A 2 ENTREGADO
                e.setEstatus("2");
            }

        }
        return mensaje;
    }

    private String validaInsert() {
        String mensaje = "";

        if (data.getChofer() == null || data.getChofer().isEmpty()) {
            mensaje = "Ingresa un Chofer.";
        }
//        if (data.getDatoCamion() == null || data.getDatoCamion().isEmpty()) {
//            mensaje = "Ingresa los Datos del Camion.";
//        }

        if (ordenTrabajo == null || ordenTrabajo.getIdOrdenPk() == null) {
            mensaje = "Selecciona una Orden";
        }

        if (lstEnvasado == null || lstEnvasado.isEmpty()) {
            mensaje = "Agrega un Producto";
        }

        if (data.getTract() == null || data.getTract().isEmpty()) {
            mensaje = "Ingresa un Valor para Tract.";
        }
        if (data.getPlate() == null || data.getPlate().isEmpty()) {
            mensaje = "Ingresa un Valor para Plate.";
        }
        if (data.getBox() == null || data.getBox().isEmpty()) {
            mensaje = "Ingresa un Valor para Box.";
        }
        if (data.getPlateBox() == null || data.getPlateBox().isEmpty()) {
            mensaje = "Ingresa un Valor para Plate Box.";
        }
        if (data.getSeal() == null || data.getSeal().isEmpty()) {
            mensaje = "Ingresa un Valor para Seal.";
        }
        if (data.getDeviceId() == null || data.getDeviceId().isEmpty()) {
            mensaje = "Ingresa un Valor para Device Id.";
        }

        if (mensaje.isEmpty()) {///si no hay mensaje validan existencias

            mensaje = validaExistencias();
        }
        return mensaje;
    }

    private void reset() {
        data = new Salida();
        model = new ArrayList<>();
        ordenTrabajo = new OrdenTrabajoD();
        lstEnvasado = new ArrayList<>();
    }

    public void buscaDatos() {
        Integer idOrden = ordenTrabajo == null ? null : ordenTrabajo.getIdOrdenPk();
        model = envasadoService.getByIdOrdenAndEstatus(idOrden, null);

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

    public List<EnvasadoD> getModel() {
        return model;
    }

    public void setModel(ArrayList<EnvasadoD> model) {
        this.model = model;
    }

    public ArrayList<EnvasadoD> getLstEnvasado() {
        return lstEnvasado;
    }

    public void setLstEnvasado(ArrayList<EnvasadoD> lstEnvasado) {
        this.lstEnvasado = lstEnvasado;
    }

    public Salida getData() {
        return data;
    }

    public void setData(Salida data) {
        this.data = data;
    }

    public SalidaProducto getDataProducto() {
        return dataProducto;
    }

    public void setDataProducto(SalidaProducto dataProducto) {
        this.dataProducto = dataProducto;
    }

}
