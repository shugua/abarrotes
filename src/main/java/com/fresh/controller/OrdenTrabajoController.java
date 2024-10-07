package com.fresh.controller;

import com.fresh.dto.ClienteD;
import com.fresh.dto.MateriaPrimaD;
import com.fresh.dto.OrdenTrabajoD;
import com.fresh.dto.OrdenTrabajoLote;
import com.fresh.dto.OrdenTrabajoProductoD;
import com.fresh.dto.UsuarioD;
import com.fresh.repository.OrdenTrabajoProductoRepository;
import com.fresh.service.ClienteService;
import com.fresh.service.EntradaMateriaPrimaService;
import com.fresh.service.OrdenTrabajoLoteService;
import com.fresh.service.OrdenTrabajoProductoService;
import com.fresh.service.OrdenTrabajoService;
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
public class OrdenTrabajoController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(OrdenTrabajoController.class);

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EntradaMateriaPrimaService entradaMateriaPrimaService;
    @Autowired
    private InfoUsuarioController infoUsuarioController;
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;
    @Autowired
    private OrdenTrabajoLoteService ordenTrabajoLoteService;
    @Autowired
    private OrdenTrabajoProductoService ordenTrabajoProductoService;

    private OrdenTrabajoD data;
    private OrdenTrabajoProductoD dataP;
    private OrdenTrabajoProductoD dataPR;
    private ClienteD cliente;
    private UsuarioD usuario;
    private MateriaPrimaD entrada;
    private OrdenTrabajoLote dataLR;

    private String stateView;

    private Map paramReport = new HashMap<>();
    private String rutaPDF;
    private String pathFileJasper = "";
    private ByteArrayOutputStream outputStream;

    @PostConstruct
    public void init() {
        stateView = "init";
        data = new OrdenTrabajoD();
        dataP = new OrdenTrabajoProductoD();
        usuario = infoUsuarioController.getUsuario();

    }

    public void save() {
        try{
        String mensaje = validaInsert();

        if (mensaje.isEmpty()) {
            data.setIdUsuarioFk(usuario.getId());
            data.setFecha(new Date());
            int id = ordenTrabajoService.insert(data);

            ////insertamos tipo ajo 
            data.getLstOrdenTrabajoProducto().forEach(o -> {
                o.setIdOrdenFk(id);
                ordenTrabajoProductoService.insert(o);

            });

            ///insertamos lotes
            data.getLstOrdenTrabajoLote().forEach(o -> {
                o.setIdOrdenTrabajoFk(id);
                ordenTrabajoLoteService.insert(o);
            });

            imprimir();
            reset();
            JsfUtil.addSuccessMessage("Registro Insertado Correctamente.");
        }else{
            System.out.println("error");
            JsfUtil.addErrorMessage(mensaje);
        }
        
       }catch(Exception ex){
            JsfUtil.addErrorMessage("Error "+ex.getMessage());
        }

    }

    private String validaInsert() {
        String mensaje = "";
        if (data.getLstOrdenTrabajoLote() == null || data.getLstOrdenTrabajoLote().isEmpty()) {
            mensaje = "Agregue un Lote.";
        }
        if (data.getLstOrdenTrabajoProducto() == null || data.getLstOrdenTrabajoProducto().isEmpty()) {
            mensaje = "Agregue un Tipo de Producto.";
        }
        
         if (data.getFechaEntrega()== null) {
            mensaje = "Agregue una Fecha de Entrega.";
        }
        
        return mensaje;
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
            dataP = new OrdenTrabajoProductoD();
            totateles();
            JsfUtil.addSuccessMessage("Registro Agregado Correctamente.");
        } else {
            JsfUtil.addErrorMessage("Cantidad o Tipo Vacios.");
        }

    }

    public void addLote() {
        if (entrada != null && entrada.getIdEntradaMateriaPrimaPK() != null) {
            OrdenTrabajoLote oT = new OrdenTrabajoLote();
            oT.setIdEntradaMateriaPrimaFk(entrada.getIdEntradaMateriaPrimaPK());
            oT.setLote(entrada.getLote());
            oT.setNombreCliente(entrada.getNombreCliente());
            oT.setNombreProveedor(entrada.getNombreProveedor());
            data.getLstOrdenTrabajoLote().add(oT);
            entrada = new MateriaPrimaD();

            JsfUtil.addSuccessMessage("Registro Agregado Correctamente.");
        } else {
            JsfUtil.addErrorMessage("Lote Vacios.");
        }

    }

    private void totateles() {

        data.setCantidad(data.getLstOrdenTrabajoProducto().stream().map(OrdenTrabajoProductoD::getCantidad).reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public void remover() {
        data.getLstOrdenTrabajoProducto().remove(dataPR);
        totateles();
        JsfUtil.addSuccessMessage("Registro Removido Correctamente.");

    }

    public void removerLote() {
        data.getLstOrdenTrabajoLote().remove(dataLR);
        JsfUtil.addSuccessMessage("Registro Removido Correctamente.");

    }

    public void imprimir() {
        setParameterTicket();
        generateReport((int) System.currentTimeMillis(), "OrdenTrabajo.jasper");
        PrimeFaces.current().executeScript("window.frames.miFrame.print();");

    }

    private void setParameterTicket() {
        BigDecimal total = BigDecimal.ZERO;
        total = data.getLstOrdenTrabajoProducto().stream().map(OrdenTrabajoProductoD::getCantidad).reduce(BigDecimal.ZERO, BigDecimal::add);
        paramReport.put("nombreEmpresa", "Fresh");
        paramReport.put("logo_empresa", "../logo.png");

        paramReport.put("fecha", TiempoUtil.getFechaDDMMYYYYHHMM(data.getFecha()));
        paramReport.put("fechaEntrega", TiempoUtil.getFechaDDMMYYYYHHMM(data.getFechaEntrega()));
        paramReport.put("cliente", cliente.getNombre());
        paramReport.put("proveedor", data.getLstOrdenTrabajoLote().get(0).getNombreProveedor());
        System.out.println("orden");
//        paramReport.put("lote", entrada.getLote());
        paramReport.put("ordenT", data.getNumeroOrden());
        paramReport.put("loteProductoTerminado", data.getLotePT());

        paramReport.put("nombreSolicita", data.getNombreUsuario());
        paramReport.put("observaciones", data.getObservaciones());
        paramReport.put("lstProductos", data.getLstOrdenTrabajoProducto());
        paramReport.put("total", total);
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

    public void buscaCliente() {
        if (entrada.getIdClienteFK() != null) {
            cliente = clienteService.findByIdClientePk(entrada.getIdClienteFK());
        }
    }

    public List<MateriaPrimaD> autoCompleteLote(String lote) {
        return entradaMateriaPrimaService.findByLote(lote);
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

    public OrdenTrabajoD getData() {
        return data;
    }

    public void setData(OrdenTrabajoD data) {
        this.data = data;
    }

    public OrdenTrabajoProductoD getDataP() {
        return dataP;
    }

    public void setDataP(OrdenTrabajoProductoD dataP) {
        this.dataP = dataP;
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

    public OrdenTrabajoProductoD getDataPR() {
        return dataPR;
    }

    public void setDataPR(OrdenTrabajoProductoD dataPR) {
        this.dataPR = dataPR;
    }

    public OrdenTrabajoLote getDataLR() {
        return dataLR;
    }

    public void setDataLR(OrdenTrabajoLote dataLR) {
        this.dataLR = dataLR;
    }

   

}
