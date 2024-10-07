package com.fresh.repository;

import com.fresh.dominio.EntradaMateriaPrimaProducto;
import com.fresh.dominio.Producto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaMateriaPrimaProductoRepository extends JpaRepository<EntradaMateriaPrimaProducto, Long> {

    public Producto findByIdEntradaProductoPk(Integer idEntradaProductoPk);

    @Modifying
    @Query(value = "UPDATE ENTRADA_MATERIA_PRIMA_PRODUCTO SET ESTATUS ='0' WHERE ID_ENTRADA_PRODUCTO_PK =:idEntradaProducto", nativeQuery = true)
    int delete(@Param("idEntradaProducto") Integer idEntradaProducto);

    @Modifying
    @Query(value = "UPDATE ENTRADA_MATERIA_PRIMA_PRODUCTO SET CANTIDAD =:cantidad ,PESO_BRUTO =:pesoB,PESO_NETO =:pesoN ,PESO_TARIMA =:pesoTa,PESO_PRESENTACION=:pesoPr,TIPO_TARIMA=:tipoTa,TIPO_PRESENTACION=:tipoPr,OBSERVACIONES=:Observacion WHERE ID_ENTRADA_PRODUCTO_PK =:idEntradaProducto", nativeQuery = true)
    int update(@Param("cantidad") BigDecimal cantidad,@Param("pesoB") BigDecimal pesoB,@Param("pesoN") BigDecimal pesoN,@Param("pesoTa") BigDecimal pesoTa,@Param("pesoPr") BigDecimal pesoPr,@Param("tipoTa") Integer tipoTa,@Param("tipoPr") Integer tipoPr,@Param("Observacion") String Observacion,@Param("idEntradaProducto") Integer idEntradaProducto);

    @Modifying
    @Query(value = "INSERT INTO ENTRADA_MATERIA_PRIMA_PRODUCTO(ID_ENTRADA_MATERIA_PRIMA_FK ,NUMERO_TARIMA ,ID_PRODUCTO_FK ,CANTIDAD,PESO_BRUTO,PESO_NETO ,PESO_TARIMA,PESO_PRESENTACION,TIPO_TARIMA,TIPO_PRESENTACION,ESTATUS,OBSERVACIONES) "
            + "VALUES(:idEntradaFk,:numeroTarima,:idProductoFk,:cantidad,:pesoBruto,:pesoNeto,:pesoTarima,:pesoPresentacion,:tipoTarima,:tipoPresentacion,'1',:observaciones)", nativeQuery = true)
    int insert(@Param("idEntradaFk") Integer idEntradaFk, @Param("numeroTarima") Integer numeroTarima, @Param("idProductoFk") Integer idProductoFk, @Param("cantidad") BigDecimal cantidad, @Param("pesoBruto") BigDecimal pesoBruto, @Param("pesoNeto") BigDecimal pesoNeto, @Param("pesoTarima") BigDecimal pesoTarima, @Param("pesoPresentacion") BigDecimal pesoPresentacion, @Param("tipoTarima") Integer tipoTarima, @Param("tipoPresentacion") Integer tipoPresentacion, @Param("observaciones") String observaciones);

    @Query(value = "SELECT EMP.ID_eNTRADA_PRODUCTO_PK,EMP.ID_ENTRADA_MATERIA_PRIMA_FK,EMP.NUMERO_TARIMA,EMP.ID_PRODUCTO_FK,EMP.CANTIDAD,EMP.PESO_BRUTO,EMP.PESO_NETO, "
            + "EMP.TIPO_TARIMA,EMP.TIPO_PRESENTACION,EMP.ESTATUS,EMP.OBSERVACIONES,P.NOMBRE AS NOMBRE_P,TT.NOMBRE AS NOMBRE_TT,TP.NOMBRE AS NOMBRE_TP,TP.PESO AS PESO_TP,TT.PESO AS PESO_TT "
            + "FROM entrada_materia_prima_producto EMP "
            + "LEFT JOIN PRODUCTO P ON EMP.ID_PRODUCTO_FK = P.ID_PRODUCTO_PK "
            + "LEFT JOIN TIPO_TARIMA TT ON TT.ID_TARIMA_PK = EMP.TIPO_TARIMA "
            + "LEFT JOIN TIPO_PRESENTACION TP ON TP.ID_PRESENTACION_PK = EMP.TIPO_PRESENTACION "
            + "WHERE EMP.ID_ENTRADA_MATERIA_PRIMA_FK =:idEntradaPk", nativeQuery = true)
    List<Object[]> getByIdEntradaPk(@Param("idEntradaPk") Integer idEntradaPk);

    @Modifying
    @Query(value = "UPDATE ENTRADA_MATERIA_PRIMA_PRODUCTO SET FECHA_PELADO =:fecha ,ESTATUS ='2',ID_ORDEN_FK =:idOrden WHERE ID_ENTRADA_PRODUCTO_PK =:idEntradaProducto", nativeQuery = true)
    int pelar(@Param("fecha") Date fecha, @Param("idOrden") Integer idOrden, @Param("idEntradaProducto") Integer idEntradaProducto);
    
      @Query(value = "SELECT EMP.ID_eNTRADA_PRODUCTO_PK,EMP.ID_ENTRADA_MATERIA_PRIMA_FK,EMP.NUMERO_TARIMA,EMP.ID_PRODUCTO_FK,EMP.CANTIDAD,EMP.PESO_BRUTO,EMP.PESO_NETO, "
            + "EMP.TIPO_TARIMA,EMP.TIPO_PRESENTACION,EMP.ESTATUS,EMP.OBSERVACIONES,P.NOMBRE AS NOMBRE_P,TT.NOMBRE AS NOMBRE_TT,TP.NOMBRE AS NOMBRE_TP,TP.PESO AS PESO_TP,TT.PESO AS PESO_TT "
            + ",EM.LOTE,OT.NUMERO_ORDEN,EMP.FECHA_PELADO "
            + "FROM entrada_materia_prima_producto EMP "
            + "LEFT JOIN PRODUCTO P ON EMP.ID_PRODUCTO_FK = P.ID_PRODUCTO_PK "
            + "LEFT JOIN TIPO_TARIMA TT ON TT.ID_TARIMA_PK = EMP.TIPO_TARIMA "
            + "LEFT JOIN TIPO_PRESENTACION TP ON TP.ID_PRESENTACION_PK = EMP.TIPO_PRESENTACION "
            + "LEFT JOIN entrada_materia_prima EM ON EM.ID_ENTRADA_MATERIA_PRIMA_PK = EMP.ID_ENTRADA_MATERIA_PRIMA_FK "
            + "LEFT JOIN orden_trabajo OT ON OT.ID_ORDEN_PK = EMP.ID_ORDEN_FK "
            + "WHERE STR_TO_DATE(DATE_FORMAT(EMP.FECHA_PELADO, '%d/%m/%Y'), '%d/%m/%Y')  BETWEEN STR_TO_DATE(:fechaInicio, '%d/%m/%Y') AND STR_TO_DATE(:fechaFin, '%d/%m/%Y') "
            + "AND (:idOrdenTrabajo IS NULL OR EMP.ID_ORDEN_FK = :idOrdenTrabajo)", nativeQuery = true)
    List<Object[]> getByIdOrdenEntradaAndFechas(@Param("idOrdenTrabajo") Integer idOrdenTrabajo, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);

}
