package com.fresh.repository;

import com.fresh.dominio.TipoTarima;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvasadoRepository extends JpaRepository<TipoTarima, Long> {

    @Modifying
    @Query(value = "UPDATE ENVASADO SET ESTATUS =3 WHERE ID_ENVASADO_PK =:idEnvasado", nativeQuery = true)
    int delete(@Param("idEnvasado") Integer idEnvasado);

    @Modifying
    @Query(value = "INSERT INTO ENVASADO(ID_ORDEN_FK,NUMERO_TARIMA,ID_ENTRADA_MATERIA_PRIMA_FK,CANTIDAD,KILOS,TAMANO,ESTATUS,TIPO_PRODUCTO_FK,FECHA,ID_USUARIO_FK,CANTIDAD_POR_CAJA,PESO_UNITARIO,TARA_UNITARIO,KILO_BRUTO,LOTE_PRODUCTO_TERMINADO) "
            + "VALUES(:idOrden,:numeroTarima,:idEntrada,:cantidad,:kilos,:tamano,'1',:tipoProducto,:fecha,:idUsuario,:cantidadCaja,:pesoUnitario,:taraUnitario,:kiloBruto,:loteProductoTermiando)", nativeQuery = true)  
    int insert(@Param("idOrden") Integer idOrden, @Param("numeroTarima") Integer numeroTarima, @Param("idEntrada") Integer idEntrada, @Param("cantidad") BigDecimal cantidad, @Param("kilos") BigDecimal kilos, @Param("tamano") String tamano, @Param("tipoProducto") Integer tipoProducto, @Param("fecha") Date fecha, @Param("idUsuario") Integer idUsuario, @Param("cantidadCaja") BigDecimal cantidadCaja, @Param("pesoUnitario") BigDecimal pesoUnitario, @Param("taraUnitario") BigDecimal taraUnitario,@Param("kiloBruto") BigDecimal kiloBruto, @Param("loteProductoTermiando") String loteProductoTermiando);
                                                                                                                                                                                                                                                                                                                                                                                         
    @Query(value = "SELECT E.ID_ENVASADO_PK,E.ID_ORDEN_FK,E.ID_ENTRADA_MATERIA_PRIMA_FK,E.NUMERO_TARIMA,E.CANTIDAD,E.KILOS,E.CANTIDAD_ENTREGADA,E.KILOS_ENTREGADOS, "
            + "E.TAMANO,E.ESTATUS,E.TIPO_PRODUCTO_FK,E.FECHA,E.FECHA_CANCELACION,E.ID_USUARIO_FK,E.ID_USUARIO_CANCELA_FK "
            + ",OT.NUMERO_ORDEN,TP.DESCRIPCION,EM.LOTE "
            + "FROM ENVASADO E "
            + "LEFT JOIN ORDEN_TRABAJO OT ON OT.ID_ORDEN_PK = E.ID_ORDEN_FK "
            + "LEFT JOIN TIPO_PRODUCTO TP ON TP.ID_TIPO_PRODUCTO_PK = E.TIPO_PRODUCTO_FK "
            + "LEFT JOIN ENTRADA_MATERIA_PRIMA EM ON EM.ID_ENTRADA_MATERIA_PRIMA_PK =ID_ENTRADA_MATERIA_PRIMA_FK "
            + "WHERE STR_TO_DATE(DATE_FORMAT(E.FECHA,'%d/%m/%Y'),'%d/%m/%Y') BETWEEN STR_TO_DATE(:fechaInicio, '%d/%m/%Y') AND STR_TO_DATE(:fechaFin, '%d/%m/%Y') "
            + "AND (:idOrdenTrabajo IS NULL OR E.ID_ORDEN_FK = :idOrdenTrabajo) "
            + "AND (:idEntrada IS NULL OR E.ID_ENTRADA_MATERIA_PRIMA_FK = :idEntrada) "
            + "AND (:estatus IS NULL OR E.ESTATUS = :estatus)", nativeQuery = true)
    List<Object[]> getByIdOrdenEntradaAndFechas(@Param("idOrdenTrabajo") Integer idOrdenTrabajo, @Param("idEntrada") Integer idEntrada, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin, String estatus);

    @Query(value = "SELECT E.ID_ENVASADO_PK,E.ID_ORDEN_FK,E.ID_ENTRADA_MATERIA_PRIMA_FK,E.NUMERO_TARIMA,E.CANTIDAD,E.KILOS,E.CANTIDAD_ENTREGADA,E.KILOS_ENTREGADOS, "
            + "E.TAMANO,E.ESTATUS,E.TIPO_PRODUCTO_FK,E.FECHA,E.FECHA_CANCELACION,E.ID_USUARIO_FK,E.ID_USUARIO_CANCELA_FK "
            + ",OT.NUMERO_ORDEN,TP.DESCRIPCION,EM.LOTE "
            + "FROM ENVASADO E "
            + "LEFT JOIN ORDEN_TRABAJO OT ON OT.ID_ORDEN_PK = E.ID_ORDEN_FK "
            + "LEFT JOIN TIPO_PRODUCTO TP ON TP.ID_TIPO_PRODUCTO_PK = E.TIPO_PRODUCTO_FK "
            + "LEFT JOIN ENTRADA_MATERIA_PRIMA EM ON EM.ID_ENTRADA_MATERIA_PRIMA_PK =ID_ENTRADA_MATERIA_PRIMA_FK "
            + "WHERE E.ID_ENVASADO_PK =:idEnvasado", nativeQuery = true)
    List<Object[]> getByIdEnvasado(@Param("idEnvasado") Integer idEnvasado);

    @Modifying
    @Query(value = "UPDATE ENVASADO SET ESTATUS ='2' WHERE ID_ENVASADO_PK =:idEnvasado", nativeQuery = true)
    int cargar(@Param("idEnvasado") Integer idEnvasado);

    @Query(value = "SELECT IFNULL(MAX(NUMERO_TARIMA),0)+1 FROM ENVASADO WHERE ID_ORDEN_FK =:idOrden", nativeQuery = true)
    Integer getNumeroTarimaByIdOrden(@Param("idOrden") Integer idOrden);

    @Query(value = "SELECT E.ID_ENVASADO_PK,E.ID_ORDEN_FK,E.ID_ENTRADA_MATERIA_PRIMA_FK,E.NUMERO_TARIMA,E.CANTIDAD,E.KILOS,E.CANTIDAD_ENTREGADA,E.KILOS_ENTREGADOS, "
            + "E.TAMANO,E.ESTATUS,E.TIPO_PRODUCTO_FK,E.FECHA,E.FECHA_CANCELACION,E.ID_USUARIO_FK,E.ID_USUARIO_CANCELA_FK "
            + ",OT.NUMERO_ORDEN,TP.DESCRIPCION,EM.LOTE "
            + "FROM ENVASADO E "
            + "LEFT JOIN ORDEN_TRABAJO OT ON OT.ID_ORDEN_PK = E.ID_ORDEN_FK "
            + "LEFT JOIN TIPO_PRODUCTO TP ON TP.ID_TIPO_PRODUCTO_PK = E.TIPO_PRODUCTO_FK "
            + "LEFT JOIN ENTRADA_MATERIA_PRIMA EM ON EM.ID_ENTRADA_MATERIA_PRIMA_PK =ID_ENTRADA_MATERIA_PRIMA_FK "
            + "WHERE (:idOrdenTrabajo IS NULL OR E.ID_ORDEN_FK = :idOrdenTrabajo) "
            + "AND (:estatus IS NULL OR E.ESTATUS = :estatus)", nativeQuery = true)
    List<Object[]> getByIdOrdenAndEstatus(@Param("idOrdenTrabajo") Integer idOrdenTrabajo, @Param("estatus") String estatus);

    @Modifying
    @Query(value = "UPDATE ENVASADO SET CANTIDAD_ENTREGADA = :cantidadEntregada , KILOS_ENTREGADOS =:kilosEntregados ,ESTATUS =:estatus WHERE ID_ENVASADO_PK =:idEnvasado ", nativeQuery = true)
    int modificaCantidadEntregada(@Param("cantidadEntregada") BigDecimal cantidadEntregada, @Param("kilosEntregados") BigDecimal kilosEntregados,@Param("estatus") String estatus, @Param("idEnvasado") Integer idEnvasado);

}
