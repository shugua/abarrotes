package com.fresh.service;

import com.fresh.dominio.Cliente;
import com.fresh.dto.ClienteD;
import com.fresh.repository.ClienteRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan de la Cruz
 */
@Service
public class ClienteService implements Serializable {

    @Autowired
    ClienteRepository clienteRepository;

    public ArrayList<ClienteD> findAll() {
        List<Cliente> lstCliente = clienteRepository.findAll();

        if (lstCliente == null || lstCliente.isEmpty()) {
            return new ArrayList<>();
        }

        return (ArrayList<ClienteD>) lstCliente.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());

    }

    public ClienteD findByIdClientePk(Integer idClientePk) {
        return convertirEntidadADTO(clienteRepository.findByIdClientePk(idClientePk));

    }

    @Transactional
    public int delete(Integer idClientePk, Integer idUsuario) {
        return clienteRepository.deleteByIdClientePk(idClientePk, idUsuario);
    }

    @Transactional
    public int activar(Integer idClientePk) {
        return clienteRepository.activarByIdClientePk(idClientePk);
    }

    @Transactional
    public int update(ClienteD c) {

        return clienteRepository.update(c.getNombre(), c.getNumeroTelefono(), c.getDireccion(), c.getRfc(), c.getCodigoPostal(), c.getEstatus(), c.getClave(), c.getCorreo(), c.getIdClientePk());
    }

    @Transactional
    public int insert(ClienteD c) {
        return clienteRepository.insert(c.getNombre(), c.getNumeroTelefono(), c.getDireccion(), c.getRfc(), c.getCodigoPostal(), c.getEstatus(), c.getIdUsuarioAltaPk(), c.getClave(), c.getCorreo());
    }

    public ArrayList<ClienteD> findByIdNombre(String nombreCliente) {

        List<Cliente> lstCliente = clienteRepository.findByIdNombre(nombreCliente);

        if (lstCliente == null || lstCliente.isEmpty()) {
            return new ArrayList<>();
        }

        return (ArrayList<ClienteD>) lstCliente.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    /**
     * Convierte una entidad Cliente a un DTO ClienteD.
     *
     * @param cliente La entidad Cliente
     * @return El DTO ClienteD
     */
    private ClienteD convertirEntidadADTO(Cliente c) {
        ClienteD dto = new ClienteD(c.getIdClientePk(), c.getNombre(), c.getNumeroTelefono(), c.getDireccion(), c.getRfc(), c.getCodigoPostal(), c.getEstatus(), c.getFechaAlta(), c.getFechaEliminacion(), c.getClave(), c.getCorreo());

        return dto;
    }

}
