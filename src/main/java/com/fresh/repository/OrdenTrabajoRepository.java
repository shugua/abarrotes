package com.fresh.repository;

import com.fresh.dominio.OrdenTrabajo;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajo, Integer> {

    @Modifying
    @Query(value = "UPDATE ORDEN_TRABAJO SET ESTATUS = '0' WHERE ID_ORDEN_PK =:idOrden", nativeQuery = true)
    int delete(@Param("idOrden") Integer idOrden);

    @Modifying
    @Query(value = "UPDATE ORDEN_TRABAJO SET NUMERO_ORDEN =:numeroOrden,FECHA_ENTREGA=:fechaEntrega,LOTE_PT=:lotePt,OBSERVACIONES=:observaciones WHERE ID_ORDEN_PK=:idOrden ", nativeQuery = true)
    int update(@Param("numeroOrden") String numeroOrden, @Param("fechaEntrega") Date fechaEntrega, @Param("lotePt") String lotePt, @Param("observaciones") String observaciones, @Param("idOrden") Integer idOrden);

    @Query(value = "SELECT DISTINCT O.ID_ORDEN_PK,O.NUMERO_ORDEN,O.FECHA_ENTREGA,O.LOTE_PT,O.OBSERVACIONES,O.ID_USUARIO_FK,O.FECHA,O.ESTATUS "
            + ",SUM(OP.CANTIDAD) AS CANTIDAD "
            + "FROM ORDEN_TRABAJO O "
            + "LEFT JOIN ORDEN_TRABAJO_PRODUCTO OP ON OP.ID_ORDEN_FK =O.ID_ORDEN_PK AND OP.ESTATUS !='0' "
            + "LEFT JOIN orden_trabajo_lote OTL ON OTL.ID_ORDEN_FK = O.ID_ORDEN_PK "
            + "LEFT JOIN ENTRADA_MATERIA_PRIMA EM ON EM.ID_ENTRADA_MATERIA_PRIMA_PK = OTL.ID_ENTRADA_MATERIA_PRIMA_FK "
            + "LEFT JOIN CLIENTE C ON C.ID_CLIENTE_PK = EM.ID_CLIENTE_FK "
            + "LEFT JOIN PROVEEDOR P ON P.ID_PROVEEDOR_PK =EM.ID_PROVEEDOR_FK "
            + "WHERE STR_TO_DATE(DATE_FORMAT(O.FECHA, '%d/%m/%Y'), '%d/%m/%Y')  BETWEEN STR_TO_DATE(:fechaInicio, '%d/%m/%Y') AND STR_TO_DATE(:fechaFin, '%d/%m/%Y') "
            + "AND (:idProveedor IS NULL OR EM.ID_PROVEEDOR_FK = :idProveedor) "
            + "AND (:idCliente IS NULL OR EM.ID_CLIENTE_FK = :idCliente) "
            + "GROUP BY O.ID_ORDEN_PK,O.NUMERO_ORDEN,O.FECHA_ENTREGA,O.LOTE_PT,O.OBSERVACIONES,O.ID_USUARIO_FK,O.FECHA,O.ESTATUS "
            + ",EM.LOTE,C.NOMBRE,P.NOMBRE", nativeQuery = true)
    List<Object[]> findByFechaAndIdProveedorAndIdCliente(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin, @Param("idProveedor") Integer idProveedor, @Param("idCliente") Integer idCliente);

    @Query(value = "SELECT O.ID_ORDEN_PK,O.NUMERO_ORDEN,O.FECHA_ENTREGA,O.OBSERVACIONES,O.ID_USUARIO_FK,O.FECHA,O.ESTATUS "
            + "FROM ORDEN_TRABAJO O "
            + "WHERE O.ID_ORDEN_PK =:idOrden ", nativeQuery = true)
    List<Object[]> findByIdOrdenPK(@Param("idOrden") Integer idOrden);

    @Query(value = "SELECT O.ID_ORDEN_PK,O.NUMERO_ORDEN,O.FECHA_ENTREGA,O.OBSERVACIONES,O.ID_USUARIO_FK,O.FECHA,O.ESTATUS "
            + "FROM ORDEN_TRABAJO O "
            + "WHERE O.NUMERO_ORDEN LIKE CONCAT('%', :orden, '%')", nativeQuery = true)
    List<Object[]> findByOrdenTrabajo(@Param("orden") String lote);

}
