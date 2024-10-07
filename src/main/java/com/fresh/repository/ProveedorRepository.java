package com.fresh.repository;

import com.fresh.dominio.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    @Query(value = "SELECT p.* FROM PROVEEDOR p WHERE p.ESTATUS=1 AND p.nombre LIKE CONCAT('%', :nombreProveedor, '%') AND tipo_proveedor =:tipo ORDER BY p.NOMBRE  ASC", nativeQuery = true)
    public List<Proveedor> findByIdNombre(@Param("nombreProveedor") String nombreProveedor,@Param("tipo") String tipo);

    @Query(value = "SELECT ID_PROVEEDOR_PK,NOMBRE,NUMERO_TELEFONO,ESTATUS,CLAVE,FECHA_ALTA,ID_USUARIO_ALTA,FECHA_ELIMINACION,ID_USUARIO_ELIMINACION,CORREO,divisa,termino_pago,forma_pago,tipo_proveedor,rfc,domicilio FROM PROVEEDOR WHERE ID_PROVEEDOR_PK =:idProveedorPk", nativeQuery = true)
    Proveedor findByIdProveedorPk(@Param("idProveedorPk") Integer idProveedorPk);

    @Query(value = "SELECT ID_PROVEEDOR_PK,NOMBRE,NUMERO_TELEFONO,ESTATUS,CLAVE,FECHA_ALTA,ID_USUARIO_ALTA,FECHA_ELIMINACION,ID_USUARIO_ELIMINACION,CORREO,divisa,termino_pago,forma_pago,tipo_proveedor,rfc,domicilio FROM PROVEEDOR WHERE tipo_proveedor =:tipo", nativeQuery = true)
     List<Proveedor> findByTipo(@Param("tipo") String tipo);

    @Modifying
    @Query(value = "UPDATE PROVEEDOR SET ESTATUS ='0',FECHA_ELIMINACION = NOW(),ID_USUARIO_ELIMINACION =:idUsuario WHERE ID_PROVEEDOR_PK =:idProveedorPk", nativeQuery = true)
    int deleteByIdProveedorPk(@Param("idProveedorPk") Integer idProveedorPk, @Param("idUsuario") Integer idUsuario);

    @Modifying
    @Query(value = "UPDATE PROVEEDOR SET ESTATUS ='1'WHERE ID_PROVEEDOR_PK =:idProveedorPk", nativeQuery = true)
    int activarByIdProveedorPk(@Param("idProveedorPk") Integer idProveedorPk);

    @Modifying
    @Query(value = "update PROVEEDOR set NOMBRE =:nombre, NUMERO_TELEFONO = :telefono,ESTATUS =:estatus,CLAVE =:clave,CORREO =:correo ,divisa =:divisa,termino_pago=:termino,forma_pago=:pago,tipo_proveedor=:tipo,rfc=:rfc,domicilio=:domicilio where ID_PROVEEDOR_PK =:idProveedorPk", nativeQuery = true)
    int update(@Param("nombre") String nombre, @Param("telefono") String telefono, @Param("estatus") String estatus, @Param("clave") String clave, @Param("correo") String correo, @Param("tipo") Integer tipo, @Param("divisa") String divisa, @Param("pago") Integer pago, @Param("termino") String termino, @Param("rfc") String rfc, @Param("domicilio") String domicilio, @Param("idProveedorPk") Integer idProveedorPk);

    @Modifying
    @Query(value = "INSERT INTO PROVEEDOR(NOMBRE,NUMERO_TELEFONO,ESTATUS,CLAVE,FECHA_ALTA,ID_USUARIO_ALTA,CORREO,divisa,termino_pago,forma_pago,tipo_proveedor,rfc,domicilio) VALUES(:nombre,:telefono,:estatus,:clave,NOW(),:idUsuario,:correo,:divisa,:termino,:pago,:tipo,:rfc,:domicilio)", nativeQuery = true)
    int insert(@Param("nombre") String nombre, @Param("telefono") String telefono, @Param("estatus") String estatus, @Param("clave") String clave, @Param("idUsuario") Integer idUsuario, @Param("correo") String correo, @Param("tipo") Integer tipo, @Param("divisa") String divisa, @Param("pago") Integer pago, @Param("termino") String termino, @Param("rfc") String rfc, @Param("domicilio") String domicilio);

    @Query(value = "select IFNULL(max(numero_proveedor),0)+1 from proveedor where tipo_proveedor = :tipoProveedor", nativeQuery = true)
    Integer getNextValTipoProveedor(@Param("tipoProveedor") Integer tipoProveedor);

}
