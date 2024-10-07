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
public interface LavadoRepository extends JpaRepository<TipoTarima, Long> {

    @Modifying
    @Query(value = "UPDATE  LAVADO SET ESTATUS ='0' WHERE ID_LAVADO_PK =:idPelado", nativeQuery = true)
    int delete(@Param("idPelado") Integer idEntrada);

    @Modifying
    @Query(value = "INSERT INTO LAVADO(FECHA,ID_ORDEN_FK,ID_ENTRADA_MATERIA_PRIMA_FK,CANTIDAD,PESO_NETO,OBSERVACIONES,ID_TIPO_PRODUCTO_FK,ID_USUARIO_FK) "
            + "VALUES(:fecha,:idOrden,:idEntrada,:cantidad,:pesoNeto,:observaciones,:idTipoProducto,:idUsuario)", nativeQuery = true)
    int insert(@Param("fecha") Date fecha, @Param("idOrden") Integer idOrden, @Param("idEntrada") Integer idEntrada, @Param("cantidad") BigDecimal cantidad, @Param("pesoNeto") BigDecimal pesoNeto, @Param("observaciones") String observaciones, @Param("idTipoProducto") Integer idTipoProducto, @Param("idUsuario") Integer idUsuario);

    @Query(value = "SELECT L.ID_LAVADO_PK,L.FECHA,L.ID_ORDEN_FK,L.ID_ENTRADA_MATERIA_PRIMA_FK,L.CANTIDAD,L.PESO_NETO,L.OBSERVACIONES "
            + ",L.ID_TIPO_PRODUCTO_FK,L.ID_USUARIO_FK,EM.LOTE,O.NUMERO_ORDEN,TP.DESCRIPCION,U.NOMBRE,L.ESTATUS "
            + "FROM LAVADO L "
            + "LEFT JOIN entrada_materia_prima EM ON EM.ID_ENTRADA_MATERIA_PRIMA_PK = L.ID_ENTRADA_MATERIA_PRIMA_FK "
            + "LEFT JOIN orden_trabajo O ON O.ID_ORDEN_PK = L.ID_ORDEN_FK "
            + "LEFT JOIN TIPO_PRODUCTO TP ON TP.ID_TIPO_PRODUCTO_PK = L.ID_TIPO_PRODUCTO_FK "
            + "LEFT JOIN USUARIO U ON U.ID = L.ID_USUARIO_FK "
            + "WHERE STR_TO_DATE(DATE_FORMAT(L.FECHA, '%d/%m/%Y'), '%d/%m/%Y')  BETWEEN STR_TO_DATE(:fechaInicio, '%d/%m/%Y') AND STR_TO_DATE(:fechaFin, '%d/%m/%Y') "
            + "AND (:idOrdenTrabajo IS NULL OR L.ID_ORDEN_FK = :idOrdenTrabajo) "
            + "AND (:idEntrada IS NULL OR L.ID_ENTRADA_MATERIA_PRIMA_FK = :idEntrada) "
            + "AND (:estatus IS NULL OR L.ESTATUS = :estatus)", nativeQuery = true)
    List<Object[]> getByIdOrdenEntradaAndFechas(@Param("idOrdenTrabajo") Integer idOrdenTrabajo, @Param("idEntrada") Integer idEntrada, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin, String estatus);

    @Query(value = "SELECT L.ID_LAVADO_PK,L.FECHA,L.ID_ORDEN_FK,L.ID_ENTRADA_MATERIA_PRIMA_FK,L.CANTIDAD,L.PESO_NETO,L.OBSERVACIONES "
            + ",L.ID_TIPO_PRODUCTO_FK,L.ID_USUARIO_FK,EM.LOTE,O.NUMERO_ORDEN,TP.DESCRIPCION,U.NOMBRE,L.ESTATUS "
            + "FROM LAVADO L "
            + "LEFT JOIN entrada_materia_prima EM ON EM.ID_ENTRADA_MATERIA_PRIMA_PK = L.ID_ENTRADA_MATERIA_PRIMA_FK "
            + "LEFT JOIN orden_trabajo O ON O.ID_ORDEN_PK = L.ID_ORDEN_FK "
            + "LEFT JOIN TIPO_PRODUCTO TP ON TP.ID_TIPO_PRODUCTO_PK = L.ID_TIPO_PRODUCTO_FK "
            + "LEFT JOIN USUARIO U ON U.ID = L.ID_USUARIO_FK "
            + "WHERE L.ID_LAVADO_PK=:idLavado", nativeQuery = true)
    List<Object[]> getByIdLavado(@Param("idLavado") Integer idLavado);

    @Query(value = "SELECT L.ID_LAVADO_PK,L.FECHA,L.ID_ORDEN_FK,L.ID_ENTRADA_MATERIA_PRIMA_FK,L.CANTIDAD,L.PESO_NETO,L.OBSERVACIONES,L.ID_TIPO_PRODUCTO_FK,L.ESTATUS,EMP.LOTE,TP.DESCRIPCION from lavado L "
            + "LEFT JOIN ENTRADA_MATERIA_PRIMA EMP ON EMP.ID_ENTRADA_MATERIA_PRIMA_PK =L.ID_ENTRADA_MATERIA_PRIMA_FK "
            + "LEFT JOIN TIPO_PRODUCTO TP ON TP.ID_TIPO_PRODUCTO_PK =L.ID_TIPO_PRODUCTO_FK "
            + "WHERE L.ID_ORDEN_FK = :idOrden AND L.ESTATUS != 0 "
            + "AND (:idTipoProducto IS NULL OR L.ID_TIPO_PRODUCTO_FK = :idTipoProducto)", nativeQuery = true)
    List<Object[]> getByIdOrden(@Param("idOrden") Integer idOrden,@Param("idTipoProducto") Integer idTipoProducto);

    @Modifying
    @Query(value = "UPDATE  LAVADO SET ESTATUS ='2', FECHA_EMPACADO=NOW() WHERE ID_LAVADO_PK =:idLavado", nativeQuery = true)
    int empacar(@Param("idLavado") Integer idEntrada);
    
       @Query(value = "SELECT L.ID_LAVADO_PK,L.FECHA,L.ID_ORDEN_FK,L.ID_ENTRADA_MATERIA_PRIMA_FK,L.CANTIDAD,L.PESO_NETO,L.OBSERVACIONES "
            + ",L.ID_TIPO_PRODUCTO_FK,L.ID_USUARIO_FK,EM.LOTE,O.NUMERO_ORDEN,TP.DESCRIPCION,U.NOMBRE,L.ESTATUS,L.FECHA_EMPACADO "
            + "FROM LAVADO L "
            + "LEFT JOIN entrada_materia_prima EM ON EM.ID_ENTRADA_MATERIA_PRIMA_PK = L.ID_ENTRADA_MATERIA_PRIMA_FK "
            + "LEFT JOIN orden_trabajo O ON O.ID_ORDEN_PK = L.ID_ORDEN_FK "
            + "LEFT JOIN TIPO_PRODUCTO TP ON TP.ID_TIPO_PRODUCTO_PK = L.ID_TIPO_PRODUCTO_FK "
            + "LEFT JOIN USUARIO U ON U.ID = L.ID_USUARIO_FK "
            + "WHERE STR_TO_DATE(DATE_FORMAT(L.FECHA_EMPACADO, '%d/%m/%Y'), '%d/%m/%Y')  BETWEEN STR_TO_DATE(:fechaInicio, '%d/%m/%Y') AND STR_TO_DATE(:fechaFin, '%d/%m/%Y') "
            + "AND (:idOrdenTrabajo IS NULL OR L.ID_ORDEN_FK = :idOrdenTrabajo) "
            + "AND (:idEntrada IS NULL OR L.ID_ENTRADA_MATERIA_PRIMA_FK = :idEntrada) "
            + "AND  L.ESTATUS = 2 ", nativeQuery = true)
    List<Object[]> getEmpacadoByIdOrdenEntradaAndFechas(@Param("idOrdenTrabajo") Integer idOrdenTrabajo, @Param("idEntrada") Integer idEntrada, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);


}
