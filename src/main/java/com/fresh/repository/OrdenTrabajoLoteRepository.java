package com.fresh.repository;

import com.fresh.dominio.TipoTarima;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenTrabajoLoteRepository extends JpaRepository<TipoTarima, Long> {

    @Modifying
    @Query(value = "DELETE FROM ORDEN_TRABAJO_LOTE WHERE ID_ENTRADA_MATERIA_PRIMA_FK =:idEntrada AND ID_ORDEN_FK =:idOrden", nativeQuery = true)
    int delete(@Param("idEntrada") Integer idEntrada, @Param("idOrden") Integer idOrden);

    @Modifying
    @Query(value = "INSERT INTO ORDEN_TRABAJO_LOTE(ID_ENTRADA_MATERIA_PRIMA_FK,ID_ORDEN_FK) VALUES(:idEntrada,:idOrden)", nativeQuery = true)
    int insert(@Param("idEntrada") Integer idEntrada, @Param("idOrden") Integer idOrden);

    @Query(value = "select ORT.ID_ENTRADA_MATERIA_PRIMA_FK,ORT.ID_ORDEN_FK,EM.LOTE,C.nombre as NOMBRE_C,P.nombre AS NOMBRE_P from ORDEN_TRABAJO_LOTE ORT "
            + "LEFT JOIN entrada_materia_prima EM ON EM.ID_ENTRADA_MATERIA_PRIMA_PK =ID_ENTRADA_MATERIA_PRIMA_FK "
            + "LEFT JOIN CLIENTE C ON C.ID_CLIENTE_PK =EM.ID_CLIENTE_FK "
            + "LEFT JOIN PROVEEDOR P ON P.id_proveedor_pk =ID_PROVEEDOR_FK "
            + "WHERE ID_ORDEN_FK =:idOrden", nativeQuery = true)
    List<Object[]> getByIdOrden(@Param("idOrden") Integer idOrden);

    @Query(value = "select ID_ENTRADA_MATERIA_PRIMA_FK,ID_ORDEN_FK from ORDEN_TRABAJO_LOTE  "
            + "WHERE ID_ORDEN_FK =:idOrden AND ID_ENTRADA_MATERIA_PRIMA_FK = :idEntrada", nativeQuery = true)
    List<Object[]> getByIdOrdenAndIdEntrada(@Param("idOrden") Integer idOrden, @Param("idEntrada") Integer idEntrada);

}
