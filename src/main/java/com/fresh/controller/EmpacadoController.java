package com.fresh.controller;

import com.fresh.dto.ClienteD;
import com.fresh.dto.Lavado;
import com.fresh.dto.OrdenTrabajoD;
import com.fresh.dto.TipoProductoD;
import com.fresh.dto.UsuarioD;
import com.fresh.service.ClienteService;
import com.fresh.service.LavadoService;
import com.fresh.service.OrdenTrabajoService;
import com.fresh.service.TipoProductoService;
import com.fresh.util.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class EmpacadoController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = LoggerFactory.getLogger(EmpacadoController.class);

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private LavadoService lavadoService;
    @Autowired
    private TipoProductoService tipoProductoService;
    @Autowired
    private InfoUsuarioController infoUsuarioController;
    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    private Lavado data;
    private OrdenTrabajoD ordenTrabajo;
    private ClienteD cliente;
    private UsuarioD usuario;

    private String stateView;

    private List<Lavado> model;
    private ArrayList<TipoProductoD> lstTipoProductoD;
    
    private Integer idTipoProductoFk;

    @PostConstruct
    public void init() {
        stateView = "init";

        usuario = infoUsuarioController.getUsuario();

        model = new ArrayList<>();
        idTipoProductoFk = null;
        lstTipoProductoD = tipoProductoService.findAll();

    }

    private String validaInsert() {
        String mensaje = "";

        if (ordenTrabajo == null || ordenTrabajo.getIdOrdenPk() == null) {
            mensaje = "Selecciona una Orden de Trabajo";
        }

        return mensaje;
    }

    private void reset() {
        ordenTrabajo = new OrdenTrabajoD();
        cliente = new ClienteD();
    }

    public void buscaDatos() {

        buscaProducto();

    }

    private void buscaProducto() {
        model = lavadoService.getByIdOrden(ordenTrabajo.getIdOrdenPk(),idTipoProductoFk);
    }

    public void empacar() {
        String mensaje = validaInsert();
        if (mensaje.isEmpty()) {
            lavadoService.empacar(data.getIdLavadoPk());
            buscaProducto();
            JsfUtil.addSuccessMessage("Registro Modificado Correctamente.");
        } else {
            JsfUtil.addErrorMessage(mensaje);
        }

    }

    public void remover() {

        JsfUtil.addSuccessMessage("Registro Removido Correctamente.");

    }

    public List<OrdenTrabajoD> autoCompleteOrdenTrabajo(String ordenTrabajo) {
        System.out.println("ordenTrabajo " + ordenTrabajo);
        return ordenTrabajoService.findByOrdenTrabajo(ordenTrabajo);
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

    public ClienteD getCliente() {
        return cliente;
    }

    public void setCliente(ClienteD cliente) {
        this.cliente = cliente;
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

    public Lavado getData() {
        return data;
    }

    public void setData(Lavado data) {
        this.data = data;
    }

    public Integer getIdTipoProductoFk() {
        return idTipoProductoFk;
    }

    public void setIdTipoProductoFk(Integer idTipoProductoFk) {
        this.idTipoProductoFk = idTipoProductoFk;
    }
    
    

    public ArrayList<TipoProductoD> getLstTipoProductoD() {
        return lstTipoProductoD;
    }

    public void setLstTipoProductoD(ArrayList<TipoProductoD> lstTipoProductoD) {
        this.lstTipoProductoD = lstTipoProductoD;
    }
    

    public List<Lavado> getModel() {
        return model;
    }

    public void setModel(List<Lavado> model) {
        this.model = model;
    }

}
