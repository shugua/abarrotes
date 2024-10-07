package com.fresh.controller;

import com.fresh.dto.ClienteD;
import com.fresh.dto.MateriaPrimaD;
import com.fresh.dto.MateriaPrimaProductoD;
import com.fresh.dto.ProductoD;
import com.fresh.dto.ProveedorD;
import com.fresh.dto.TipoPresentacionD;
import com.fresh.dto.TipoTarimaD;
import com.fresh.dto.UsuarioD;
import com.fresh.service.ClienteService;
import com.fresh.service.EntradaMateriaPrimaProductoService;
import com.fresh.service.EntradaMateriaPrimaService;
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
public class HistorialMateriaPrimaController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(HistorialMateriaPrimaController.class);

    @Autowired
    private TipoTarimaService tipoTarimaService;
    @Autowired
    private TipoPresentacionService tipoPresentacionService;
    @Autowired
    private InfoUsuarioController infoUsuarioController;
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
    private ArrayList<TipoPresentacionD> lstTipoPresentacion;
    private ArrayList<MateriaPrimaProductoD> lstProducto;

    private ClienteD cliente;
    private UsuarioD usuario;
    private ProductoD producto;
    private ProductoD productoEdit;
    private MateriaPrimaD data;
    private MateriaPrimaProductoD dataAdd;
    private MateriaPrimaProductoD dataEdit;
    private MateriaPrimaProductoD dataRemover;
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

        data = new MateriaPrimaD();
        dataAdd = new MateriaPrimaProductoD();
        cliente = new ClienteD();
        proveedor = new ProveedorD();
        lstProducto = new ArrayList<>();
        lstTipoTarima = tipoTarimaService.findAll();
        lstTipoPresentacion = tipoPresentacionService.findAll();
        fechaFin = new Date();
        fechaInicio = new Date();
        buscar();

    }

    public void buscar() {
        model = entradaMateriaPrimaService.findByIdProveedorAndIdClienteAndFecha(proveedor == null ? null : proveedor.getIdProveedorPk(), cliente == null ? null : cliente.getIdClientePk(), TiempoUtil.getFechaDDMMYYYY(fechaInicio), TiempoUtil.getFechaDDMMYYYY(fechaFin));
        
        for(MateriaPrimaD m:model){
            System.out.println("fecha model "+m.getFecha());
        }
        
    }

    public void calculaPesoTotalEntrada(MateriaPrimaD m) {

        m.setPesoNeto(BigDecimal.ZERO);
        m.setPesoBruto(BigDecimal.ZERO);
        m.setTotalTaraCaja(BigDecimal.ZERO);
        m.setTotalTaraTarima(BigDecimal.ZERO);
        m.setCantidadProveedor(BigDecimal.ZERO);

        m.getLstMateriaPrimaProducto().forEach(p -> {
//            @Param("totalTara") BigDecimal totalTara, @Param("totalCaja") BigDecimal totalCaja,@Param("cantidad")
            if (!p.getEstatus().equals("0")) {///SOLO SE SUMARAN PRODUCTOS QUE NO ESTEN CANCELADOS ESTATUS 0
                p.setPesoNeto(p.getPesoBruto().subtract(p.getPesoPresentacion().multiply(p.getCantidad())).subtract(p.getPesoTarima()));
                m.setPesoBruto(m.getPesoBruto().add(p.getPesoBruto()));
                m.setPesoNeto(m.getPesoNeto().add(p.getPesoNeto()));
                m.setTotalTaraCaja(m.getTotalTaraCaja().add(p.getPesoPresentacion().multiply(p.getCantidad())));
                m.setTotalTaraTarima(m.getTotalTaraTarima().add(p.getPesoTarima()));
                m.setCantidadProveedor(m.getCantidadProveedor().add(p.getCantidad()));
            }
        });
    }

    public void removerProducto() {
        dataRemover.setEstatus("0");
        entradaMateriaPrimaProductoService.delete(dataRemover.getIdMateriaPrimaProductoPK());
        data = entradaMateriaPrimaService.findByIdEntradaMateriaPrimaPk(dataRemover.getIdEntradaMateriaPrimaFK());////SE TREAE LA ENTRADA CON SUS PRODUCTOS ACTUALIZADOS
        calculaPesoTotalEntrada(data);
        editarEntrada(data);
        buscar();
        JsfUtil.addSuccessMessage("Producto Cancelado Correctamente.");
    }

    private void editarEntrada(MateriaPrimaD m) {
        entradaMateriaPrimaService.update(m);
        JsfUtil.addSuccessMessage("Entrada Editada Correctamente.");
    }

    public void calculaPesoNeto(MateriaPrimaProductoD mP) {
        mP.setPesoPresentacion(BigDecimal.ZERO);
        mP.setPesoTarima(BigDecimal.ZERO);
        seteaPesoTarima(mP);
        seteaPesoPresentacion(mP);
        if (mP.getPesoBruto() != null && mP.getCantidad() != null) {
            BigDecimal totalPesoCaja = BigDecimal.ZERO;
            mP.setPesoNeto(BigDecimal.ZERO);
            totalPesoCaja = mP.getPesoPresentacion().multiply(mP.getCantidad());
            mP.setPesoNeto(mP.getPesoBruto().subtract(totalPesoCaja).subtract(mP.getPesoTarima()));
        }

    }

    public void reset() {

        dataAdd = new MateriaPrimaProductoD();
    }

    private String validaAdd() {
        String mensaje = "";
        if (producto == null || producto.getIdProductoPk() == null) {
            return "Selecciona Un Producto.";
        }

        if (dataAdd.getCantidad() == null) {
            return "Ingresa una Cantidad.";
        }

        if (dataAdd.getPesoBruto() == null) {
            return "Ingresa Peso Bruto.";
        }

        if (dataAdd.getIdPresentacion() == null) {
            return "Selecciona una Presentacion.";
        }
        if (dataAdd.getIdTarima() == null) {
            return "Selecciona un Tipo de Tarima.";
        }

        return mensaje;
    }

    private void seteaPesoTarima(MateriaPrimaProductoD m) {
        if (m.getIdTarima() != null) {
            lstTipoTarima.forEach(tarima -> {
                if (tarima.getIdTarimaPk() == m.getIdTarima()) {
                    m.setPesoTarima(tarima.getPeso());
                    m.setNombreTarima(tarima.getNombre());
                }
            });
        } else {
            m.setPesoTarima(BigDecimal.ZERO);
        }
    }

    private void seteaPesoPresentacion(MateriaPrimaProductoD m) {
        if (m.getIdPresentacion() != null) {
            lstTipoPresentacion.forEach(p -> {
                if (p.getIdPresentacionPk() == m.getIdPresentacion()) {
                    m.setPesoPresentacion(p.getPeso());
                    m.setNombrePresentacion(p.getNombre());
                }
            });
        } else {
            m.setPesoPresentacion(BigDecimal.ZERO);
        }

    }

    public void addProducto() {
        String mensaje = validaAdd();
        if (!mensaje.isBlank()) {
            JsfUtil.addErrorMessage(mensaje);
            return;
        } else {
            dataAdd.setIdProductoFK(producto.getIdProductoPk());
            dataAdd.setNombreProducto(producto.getNombre());
            dataAdd.setNumeroTarima(data.getLstMateriaPrimaProducto().size() + 1);
            dataAdd.setEstatus("1");
//            seteaPesoTarima(dataAdd);
//            seteaPesoPresentacion(dataAdd);
            dataAdd.setIdEntradaMateriaPrimaFK(data.getIdEntradaMateriaPrimaPK());

            calculaPesoNeto(dataAdd);
            data.getLstMateriaPrimaProducto().add(dataAdd);
            entradaMateriaPrimaProductoService.insert(dataAdd);
            calculaPesoTotalEntrada(data);
            editarEntrada(data);

            resetAdd();

            JsfUtil.addSuccessMessage("Producto Agregado.");
        }

    }

    public void resetAdd() {
        dataAdd = new MateriaPrimaProductoD();
    }

    public void imprimir() {
        setParameterTicket(data);
        generateReport((int) System.currentTimeMillis(), "EntradaMateriaPrima.jasper");

        PrimeFaces.current().executeScript("window.frames.miFrame.print();");

    }

    public void imprimirTarima() {
        setParameterTicket(data);
        generateReport((int) System.currentTimeMillis(), "EntradaTarima.jasper");

        PrimeFaces.current().executeScript("window.frames.miFrame.print();");

    }

    private void setParameterTicket(MateriaPrimaD m) {

        ArrayList<MateriaPrimaProductoD> lstTemp = new ArrayList<>();
        data.getLstMateriaPrimaProducto().forEach(mT -> {
            if (!mT.getEstatus().equals("0")) {
                lstTemp.add(mT);
            }
        });

        paramReport.put("nombreEmpresa", "Fresh");
        paramReport.put("logo_empresa", "logo.png");

        paramReport.put("cliente", data.getNombreCliente());
        paramReport.put("fecha", TiempoUtil.getFechaDDMMYYYYHHMM(data.getFecha()));
        paramReport.put("lote", data.getLote());
        paramReport.put("proveedor", data.getNombreProveedor());
        paramReport.put("nombreRecibidor", data.getNombreUsuarioEntrada());
        paramReport.put("observaciones", data.getObservaciones());
//        paramReport.put("lstProductos", data.getLstMateriaPrimaProducto());
        paramReport.put("lstProductos", lstTemp);
        paramReport.put("remision", data.getRemision());

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
            rutaPDF = UtilUpload.saveFileTemp(bytes, "entradaMateriaPrima", 1, 2);
        } catch (Exception exception) {
            JsfUtil.addErrorMessage("Error " + exception.getMessage());
            JsfUtil.addErrorMessage("Error " + exception.getStackTrace());
            System.out.println("Error >" + exception.getMessage());
            exception.getStackTrace();
        }
    }

    public void onRowEdit(RowEditEvent event) {

        data = (MateriaPrimaD) event.getObject();

        data.setIdClienteFK(data.getCliente() == null ? null : data.getCliente().getIdClientePk());
        data.setIdProveedorFK(data.getProveedor().getIdProveedorPk());
        data.setNombreProveedor(data.getProveedor().getNombre());
        data.setNombreCliente(data.getCliente() == null ? null : data.getCliente().getNombre());
        editarEntrada(data);

    }

    public void onRowCancel(RowEditEvent event) {
        JsfUtil.addSuccessMessage("Se Cancelo la Ediccion.");
    }

    public void onRowEditProducto(RowEditEvent event) {
        dataEdit = (MateriaPrimaProductoD) event.getObject();
        calculaPesoNeto(dataEdit);
        entradaMateriaPrimaProductoService.update(dataEdit);
        JsfUtil.addSuccessMessage("Tarima Editada.");
        data = entradaMateriaPrimaService.findByIdEntradaMateriaPrimaPk(dataEdit.getIdEntradaMateriaPrimaFK());////SE TREAE LA ENTRADA CON SUS PRODUCTOS ACTUALIZADOS
        calculaPesoTotalEntrada(data);
        editarEntrada(data);

    }

    public ArrayList<ProveedorD> autoCompleteProveedor(String nombreProveedor) {
        return proveedorService.findByIdNombre(nombreProveedor,"2");
    }

    public ArrayList<ClienteD> autoCompleteCliente(String nombreCliente) {
        return clienteService.findByIdNombre(nombreCliente);
    }

    public ArrayList<ProductoD> autoCompleteProducto(String nombreProducto) {
        return productoService.findByIdNombre(nombreProducto,"2");
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

    public ClienteD getCliente() {
        return cliente;
    }

    public void setCliente(ClienteD cliente) {
        this.cliente = cliente;
    }

    public ProductoD getProducto() {
        return producto;
    }

    public void setProducto(ProductoD producto) {
        this.producto = producto;
    }

    public MateriaPrimaD getData() {
        return data;
    }

    public void setData(MateriaPrimaD data) {
        this.data = data;
    }

    public ProveedorD getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorD proveedor) {
        this.proveedor = proveedor;
    }

    public MateriaPrimaProductoD getDataAdd() {
        return dataAdd;
    }

    public void setDataAdd(MateriaPrimaProductoD dataAdd) {
        this.dataAdd = dataAdd;
    }

    public MateriaPrimaProductoD getDataRemover() {
        return dataRemover;
    }

    public void setDataRemover(MateriaPrimaProductoD dataRemover) {
        this.dataRemover = dataRemover;
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

    public List<MateriaPrimaD> getModel() {
        return model;
    }

    public void setModel(List<MateriaPrimaD> model) {
        this.model = model;
    }

    public String getRutaPDF() {
        return rutaPDF;
    }

    public void setRutaPDF(String rutaPDF) {
        this.rutaPDF = rutaPDF;
    }

}
