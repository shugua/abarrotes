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
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
public class EntradaMateriaPrimaController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(EntradaMateriaPrimaController.class);

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

    private ArrayList<TipoTarimaD> lstTipoTarima;
    private ArrayList<TipoPresentacionD> lstTipoPresentacion;
    private ArrayList<MateriaPrimaProductoD> lstProducto;

    private ClienteD cliente;
    private UsuarioD usuario;
    private ProductoD producto;
    private MateriaPrimaD data;
    private MateriaPrimaProductoD dataAdd;
    private MateriaPrimaProductoD dataRemover;
    private ProveedorD proveedor;
    private String stateView;

    private Map paramReport = new HashMap<>();
    private String rutaPDF;

    private String pathFileJasper = "";
    private ByteArrayOutputStream outputStream;

    @PostConstruct
    public void init() {

        usuario = infoUsuarioController.getUsuario();
        data = new MateriaPrimaD();
        dataAdd = new MateriaPrimaProductoD();
        lstProducto = new ArrayList<>();
        lstTipoTarima = tipoTarimaService.findAll();
        lstTipoPresentacion = tipoPresentacionService.findAll();

    }

    public void save() {
        String mensaje = validaInsert();
        if (mensaje.isEmpty()) {
            generaLote();
            data.setIdClienteFK(cliente == null ? null : cliente.getIdClientePk());
            data.setIdProveedorFK(proveedor == null ? null : proveedor.getIdProveedorPk());
            data.setIdUsuarioEntradaFK(usuario.getId());
            data.setFecha(new Date());
            int idEntrada = entradaMateriaPrimaService.insert(data);

            lstProducto.forEach(producto -> {
                producto.setIdEntradaMateriaPrimaFK(idEntrada); // Establece el ID
                entradaMateriaPrimaProductoService.insert(producto); // Llama a inserta

            });
            imprimir();
            reset();

            JsfUtil.addSuccessMessage("Registro Insertado Correctamente.");
        } else {
            JsfUtil.addErrorMessage(mensaje);
        }

    }

    public void calculaPesoNeto() {

        dataAdd.setPesoPresentacion(BigDecimal.ZERO);
        dataAdd.setPesoTarima(BigDecimal.ZERO);
        seteaPesoTarima();
        seteaPesoPresentacion();

        if (dataAdd.getPesoBruto() != null && dataAdd.getCantidad() != null) {
            BigDecimal totalPesoCaja = BigDecimal.ZERO;
            dataAdd.setPesoNeto(BigDecimal.ZERO);
            totalPesoCaja = dataAdd.getPesoPresentacion() == null ? BigDecimal.ZERO : dataAdd.getPesoPresentacion().multiply(dataAdd.getCantidad());
            dataAdd.setPesoNeto(dataAdd.getPesoBruto().subtract(totalPesoCaja).subtract(dataAdd.getPesoTarima() == null ? BigDecimal.ZERO : dataAdd.getPesoTarima()));
        }

    }

    private void calculaTotalesTable() {
        data.setCantidadLlegada(BigDecimal.ZERO);
        data.setPesoNeto(BigDecimal.ZERO);
        data.setPesoBruto(BigDecimal.ZERO);
        data.setCantidadProveedor(BigDecimal.ZERO);
        if (lstProducto != null) {
            lstProducto.forEach(p -> {
                data.setCantidadProveedor(data.getCantidadProveedor().add(p.getCantidad()));
                data.setPesoNeto(data.getPesoNeto().add(p.getPesoNeto()));
                data.setPesoBruto(data.getPesoBruto().add(p.getPesoBruto()));
            });

        }
    }

    public void removerProducto() {
        lstProducto.remove(dataRemover);

        ///se vuelven a calcular los numeros de tarima
        for (int i = 0; i < lstProducto.size(); i++) {
            lstProducto.get(i).setNumeroTarima(i + 1);
        }

        JsfUtil.addSuccessMessage("Producto Eliminado.");
    }

    public void reset() {
        producto = new ProductoD();
        cliente = new ClienteD();
        proveedor = new ProveedorD();
        data = new MateriaPrimaD();
        dataAdd = new MateriaPrimaProductoD();
        lstProducto = new ArrayList<>();
    }

    private String validaInsert() {
        String mensaje = "";
        if (lstProducto == null || lstProducto.isEmpty()) {
            return "Agrega un Producto.";
        }

        if (data.getRemision() == null) {
            return "Ingresa una Remision.";
        }

        if (data.getCantidadProveedor() == null) {
            return "Ingresa Cantidad Proveedor.";
        }

        return mensaje;
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

    private void seteaPesoTarima() {
        if (dataAdd.getIdTarima() != null) {
            lstTipoTarima.forEach(tarima -> {
                if (tarima.getIdTarimaPk() == dataAdd.getIdTarima()) {
                    dataAdd.setPesoTarima(tarima.getPeso());
                }
            });
        } else {
            dataAdd.setPesoTarima(BigDecimal.ZERO);
        }
    }

    private void seteaPesoPresentacion() {
        if (dataAdd.getIdPresentacion() != null) {
            lstTipoPresentacion.forEach(p -> {
                if (p.getIdPresentacionPk() == dataAdd.getIdPresentacion()) {
                    dataAdd.setPesoPresentacion(p.getPeso());
                }
            });
        } else {
            dataAdd.setPesoPresentacion(BigDecimal.ZERO);
        }

    }

    public void generaLote() {
        Integer nmeroEntrada = entradaMateriaPrimaService.getNextNumeroEntrada();
        System.out.println("c nmeroEntrada " + nmeroEntrada);
        data.setLote("LMA"+"-"+nmeroEntrada+"-"+TiempoUtil.getYearTwoDigitos(new Date()) );
        data.setNumeroEntrada(nmeroEntrada);

    }

    public void addProducto() {
        String mensaje = validaAdd();
        if (!mensaje.isBlank()) {
            JsfUtil.addErrorMessage(mensaje);
            return;
        } else {
            dataAdd.setIdProductoFK(producto.getIdProductoPk());
            dataAdd.setNombreProducto(producto.getNombre());
            dataAdd.setNumeroTarima(lstProducto.size() + 1);

            calculaPesoNeto();
            lstProducto.add(dataAdd);
            calculaTotalesTable();
            resetAdd();

            JsfUtil.addSuccessMessage("Producto Agregado.");
        }

    }

    private void resetAdd() {
        dataAdd = new MateriaPrimaProductoD();
    }

    private void resetGuardar() {
        data = new MateriaPrimaD();
        lstProducto = new ArrayList<>();
        proveedor = new ProveedorD();
        cliente = new ClienteD();
        producto = new ProductoD();
    }

    public void imprimir() {
        setParameterTicket(data);
        generateReport((int) System.currentTimeMillis());

        PrimeFaces.current().executeScript("window.frames.miFrame.print();");

    }

    private void setParameterTicket(MateriaPrimaD m) {
        paramReport.put("nombreEmpresa", "Fresh");
        paramReport.put("logo_empresa", "logo.png");

        paramReport.put("cliente", cliente == null ? "":(cliente.getNombre() == null ? "":cliente.getNombre()));
        paramReport.put("fecha", TiempoUtil.getFechaDDMMYYYYHHMM(data.getFecha()));
        paramReport.put("lote", data.getLote());
        paramReport.put("proveedor", proveedor.getNombre());
        paramReport.put("nombreRecibidor", usuario.getNombre());
        paramReport.put("observaciones", data.getObservaciones());
        paramReport.put("lstProductos", lstProducto);
        paramReport.put("remision", data.getRemision());

    }

    public void generateReport(int folio) {

        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String temporal = "";
            if (servletContext.getRealPath("") == null) {
                temporal = Constantes.PATHSERVER;
                System.out.println("path server " + temporal);
            } else {
                temporal = servletContext.getRealPath("");
            }
            pathFileJasper = temporal + "resources" + File.separatorChar + "report" + File.separatorChar + "MateriaPrima" + File.separatorChar + "EntradaMateriaPrima.jasper";
            System.out.println("pathFileJasper " + pathFileJasper);

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

    public String getRutaPDF() {
        return rutaPDF;
    }

    public void setRutaPDF(String rutaPDF) {
        this.rutaPDF = rutaPDF;
    }

}
