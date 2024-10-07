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
public interface SalidaProductoRepository extends JpaRepository<TipoTarima, Long> {

    @Modifying
    @Query(value = "UPDATE SALIDA_PRODUCTO SET ESTATUS ='2' WHERE ID_SALIDA_PRODUCTO_FK =:idSalidaProducto", nativeQuery = true)
    int delete(@Param("idSalidaProducto") Integer idSalidaProducto);

    @Modifying
    @Query(value = "INSERT INTO SALIDA_PRODUCTO(ID_SALIDA_FK,ID_ENVASADO_FK,CANTIDAD,KILOS,KILOS_BRUTO,TARA,ESTATUS,OBSERVACIONES) "
            + "VALUES(:idSalida,:idEnvasado,:cantidad,:kilos,:kiloBruto,:tara,'1',:observaciones)", nativeQuery = true)
    int insert(@Param("idSalida") Integer idSalida, @Param("idEnvasado") Integer idEnvasado, @Param("cantidad") BigDecimal cantidad, @Param("kilos") BigDecimal kilos, @Param("kiloBruto") BigDecimal kiloBruto, @Param("tara") BigDecimal tara, @Param("observaciones") String observaciones);

    @Query(value = "SELECT SP.ID_SALIDA_PRODUCTO_FK,SP.ID_SALIDA_FK,SP.ID_ENVASADO_FK,SP.CANTIDAD,SP.KILOS,SP.KILOS_BRUTO,SP.TARA, "
            + "SP.ESTATUS,SP.OBSERVACIONES ,SP.ID_USUARIO_CANCELA_FK,SP.FECHA_CANCELACION,E.ID_ORDEN_FK,E.ID_ENTRADA_MATERIA_PRIMA_FK, "
            + "E.NUMERO_TARIMA,E.TAMANO,E.TIPO_PRODUCTO_FK,TP.DESCRIPCION "
            + "FROM SALIDA_PRODUCTO SP "
            + "LEFT JOIN ENVASADO E ON E.ID_ENVASADO_PK = SP.ID_ENVASADO_FK "
            + "LEFT JOIN TIPO_PRODUCTO TP ON TP.ID_TIPO_PRODUCTO_PK = E.TIPO_PRODUCTO_FK "
            + "WHERE SP.ID_SALIDA_FK =:idSalida AND(:estatus IS NULL OR SP.ESTATUS =:estatus) ", nativeQuery = true)
    List<Object[]> getByIdSalidaAndEstatus(@Param("idSalida") Integer idSalida, @Param("estatus") String estatus);

    @Query(value = "SELECT SP.ID_SALIDA_PRODUCTO_FK,SP.ID_SALIDA_FK,SP.ID_ENVASADO_FK,SP.CANTIDAD,SP.KILOS,SP.KILOS_BRUTO,SP.TARA, "
            + "SP.ESTATUS,SP.OBSERVACIONES ,SP.ID_USUARIO_CANCELA_FK,SP.FECHA_CANCELACION,E.ID_ORDEN_FK,E.ID_ENTRADA_MATERIA_PRIMA_FK, "
            + "E.NUMERO_TARIMA,E.TAMANO,E.TIPO_PRODUCTO_FK,TP.DESCRIPCION "
            + "FROM SALIDA_PRODUCTO SP "
            + "LEFT JOIN ENVASADO E ON E.ID_ENVASADO_PK = SP.ID_ENVASADO_FK "
            + "LEFT JOIN TIPO_PRODUCTO TP ON TP.ID_TIPO_PRODUCTO_PK = E.TIPO_PRODUCTO_FK "
            + "WHERE SP.ID_SALIDA_PRODUCTO_FK =:idSalidaProducto ", nativeQuery = true)
    List<Object[]> getByIdSalidaProducto(@Param("idSalidaProducto") Integer idSalidaProducto);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Integer getLastInsertId();

}
