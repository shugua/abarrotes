package com.fresh.repository;

import com.fresh.dominio.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = "SELECT id_cliente_pk,nombre,numero_telefono,direccion,rfc,codigo_postal,estatus,fecha_alta,id_usuario_alta,fecha_eliminacion,id_usuario_eliminacion,clave,correo FROM cliente WHERE ESTATUS=1 AND nombre LIKE CONCAT('%', :nombreCliente, '%') ORDER BY nombre ASC", nativeQuery = true)
    public List<Cliente> findByIdNombre(@Param("nombreCliente") String nombreCliente);

    @Query(value = "SELECT id_cliente_pk,nombre,numero_telefono,direccion,rfc,codigo_postal,estatus,fecha_alta,id_usuario_alta,fecha_eliminacion,id_usuario_eliminacion,clave,correo FROM cliente WHERE id_cliente_pk =:idClientePk", nativeQuery = true)
    Cliente findByIdClientePk(@Param("idClientePk") Integer idClientePk);

    @Modifying
    @Query(value = "update cliente set estatus ='0', fecha_eliminacion = NOW(), id_usuario_eliminacion =:idUsuario where id_cliente_pk =:idClientePk", nativeQuery = true)
    int deleteByIdClientePk(@Param("idClientePk") Integer idClientePk, @Param("idUsuario") Integer idUsuario);

    @Modifying
    @Query(value = "update cliente set estatus ='1' where id_cliente_pk =:idClientePk", nativeQuery = true)
    int activarByIdClientePk(@Param("idClientePk") Integer idClientePk);

    @Modifying
    @Query(value = "update cliente set nombre=:nombre ,numero_telefono =:telefono,direccion =:direccion,rfc = :rfc,codigo_postal =:cp,estatus=:estatus,clave=:clave,correo=:correo where id_cliente_pk =:idClientePk", nativeQuery = true)
    int update(@Param("nombre") String nombre, @Param("telefono") String telefono, @Param("direccion") String direccion, @Param("rfc") String rfc, @Param("cp") String cp, @Param("estatus") String estatus, @Param("clave") String clave, @Param("correo") String correo, @Param("idClientePk") Integer idClientePk);

    @Modifying
    @Query(value = "insert into cliente(nombre,numero_telefono,direccion,rfc,codigo_postal,estatus,fecha_alta,id_usuario_alta,clave,correo) values(:nombre,:telefono,:direccion,:rfc,:cp,:estatus,NOW(),:idAlta,:clave,:correo)", nativeQuery = true)
    int insert(@Param("nombre") String nombre, @Param("telefono") String telefono, @Param("direccion") String direccion, @Param("rfc") String rfc, @Param("cp") String cp, @Param("estatus") String estatus, @Param("idAlta") Integer idAlta, @Param("clave") String clave, @Param("correo") String correo);

}
