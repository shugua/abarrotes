package com.fresh.repository;

import com.fresh.dominio.OrdenTrabajoProducto;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenTrabajoProductoRepository extends JpaRepository<OrdenTrabajoProducto, Long> {

    @Modifying
    @Query(value = "UPDATE ORDEN_TRABAJO_PRODUCTO SET ESTATUS ='0' WHERE ID_ORDEN_TRABAJO_PRODUCTO_PK=:idOrdenTP", nativeQuery = true)
    int delete(@Param("idOrdenTP") Integer idOrdenTP);

    @Modifying
    @Query(value = "UPDATE ORDEN_TRABAJO_PRODUCTO SET CANTIDAD =:cantidad , TIPO =:tipo WHERE ID_ORDEN_TRABAJO_PRODUCTO_PK=:idOrdenTP", nativeQuery = true)
    int update(@Param("cantidad") BigDecimal cantidad, @Param("tipo") String tipo, @Param("idOrdenTP") Integer idOrdenTP);

    @Modifying
    @Query(value = "INSERT INTO ORDEN_TRABAJO_PRODUCTO(CANTIDAD,TIPO,ID_ORDEN_FK,ESTATUS,LOTE_PRODUCTO_TERMINADO) VALUES(:cantidad,:tipo,:idOrden,'1',:lotePt)", nativeQuery = true)
    int insert(@Param("cantidad") BigDecimal cantidad, @Param("tipo") String tipo, @Param("idOrden") Integer idOrden, @Param("lotePt") String lotePt);

    @Query(value = "SELECT ID_ORDEN_TRABAJO_PRODUCTO_PK,CANTIDAD,TIPO,ID_ORDEN_FK,ESTATUS,LOTE_PRODUCTO_TERMINADO FROM ORDEN_TRABAJO_PRODUCTO WHERE ID_ORDEN_FK=:idOrden", nativeQuery = true)
    List<Object[]> getByIdOrdenTrabajoFK(@Param("idOrden") Integer idOrden);

}
