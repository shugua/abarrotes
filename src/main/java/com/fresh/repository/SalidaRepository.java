package com.fresh.repository;

import com.fresh.dominio.TipoTarima;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalidaRepository extends JpaRepository<TipoTarima, Long> {

    @Modifying
    @Query(value = "UPDATE SALIDA SET ESTATUS ='2' WHERE ID_SALIDA_PK =:idSalida", nativeQuery = true)
    int delete(@Param("idSalida") Integer idSalida);

    @Modifying
    @Query(value = "INSERT INTO SALIDA(FECHA,ID_CLIENTE_FK,DATOS_CAMION,CHOFER,OBSERVACIONES,ID_USUARIO_FK,TRACT,PLATE,BOX,PLATE_BOX,SEAL,TEMPERATURE,DEVICE_ID) VALUES(:fecha,:idCliente,:datoCamion,:chofer,:observaciones,:idUsuario,:tract,:plate,:box,:plateBox,:seal,:temperature,:deviceId)", nativeQuery = true)
    int insert(@Param("fecha") Date fecha, @Param("idCliente") Integer idCliente, @Param("datoCamion") String datoCamion, @Param("chofer") String chofer, @Param("observaciones") String observaciones, @Param("idUsuario") Integer idUsuario, @Param("tract") String tract, @Param("plate") String plate, @Param("box") String box, @Param("plateBox") String plateBox, @Param("seal") String seal, @Param("temperature") String temperature, @Param("deviceId") String deviceId);

    @Query(value = "SELECT S.ID_SALIDA_PK,S.FECHA,S.CHOFER,S.ID_USUARIO_FK,S.ESTATUS "
            + ",C.NOMBRE AS C_NOMBRE,U.NOMBRE,SUM(SP.CANTIDAD) AS CANTIDAD, "
            + "SUM(SP.CANTIDAD*E.CANTIDAD_POR_CAJA*E.PESO_UNITARIO) AS KILOS_NETO "
            + ",SUM(SP.CANTIDAD*E.CANTIDAD_POR_CAJA*(E.PESO_UNITARIO+E.TARA_UNITARIO)) AS KILOS_BRUTO "
            + ",E.ID_ORDEN_FK,EMP.ID_CLIENTE_FK,OT.NUMERO_ORDEN "
            + "FROM SALIDA S "
            + "LEFT JOIN SALIDA_PRODUCTO SP ON SP.ID_SALIDA_FK = S.ID_SALIDA_PK AND SP.ESTATUS ='1' "
            + "LEFT JOIN ENVASADO E ON E.ID_ENVASADO_PK = SP.ID_ENVASADO_FK "
            + "LEFT JOIN USUARIO U ON U.ID = S.ID_USUARIO_FK "
            + "LEFT JOIN orden_trabajo_lote OTL ON OTL.ID_ORDEN_FK =E.ID_ORDEN_FK "
            + "LEFT JOIN entrada_materia_prima EMP ON EMP.ID_ENTRADA_MATERIA_PRIMA_PK = OTL.ID_ENTRADA_MATERIA_PRIMA_FK "
            + "LEFT JOIN CLIENTE C ON C.ID_CLIENTE_PK = EMP.ID_CLIENTE_FK "
            + "LEFT JOIN ORDEN_TRABAJO OT ON OT.ID_ORDEN_PK = E.ID_ORDEN_FK "
            
            + "WHERE STR_TO_DATE(DATE_FORMAT(S.FECHA,'%d/%m/%Y'),'%d/%m/%Y') BETWEEN STR_TO_DATE(:fechaInicio, '%d/%m/%Y') AND STR_TO_DATE(:fechaFin, '%d/%m/%Y') "
            + "AND (:idOrdenTrabajo IS NULL OR E.ID_ORDEN_FK = :idOrdenTrabajo) "
            + "AND (:idEntrada IS NULL OR E.ID_ENTRADA_MATERIA_PRIMA_FK = :idEntrada) "
            + "AND (:estatus IS NULL OR S.ESTATUS = :estatus) "
            + "GROUP BY S.ID_SALIDA_PK,S.FECHA,S.ID_CLIENTE_FK,S.DATOS_CAMION,S.CHOFER,S.ID_USUARIO_FK,S.ESTATUS "
            + ",C.NOMBRE,U.NOMBRE,OT.NUMERO_ORDEN ", nativeQuery = true
    )
    List<Object[]> getByIdOrdenEntradaAndFechas(@Param("idOrdenTrabajo") Integer idOrdenTrabajo, @Param("idEntrada") Integer idEntrada, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin, @Param("estatus") String estatus);

    @Query(value = "SELECT S.ID_SALIDA_PK,S.FECHA,S.ID_CLIENTE_FK,S.DATOS_CAMION,S.CHOFER,S.ID_USUARIO_FK,S.ESTATUS "
            + ",C.NOMBRE AS C_NOMBRE,U.NOMBRE,SUM(SP.KILOS) AS KILOS,SUM(SP.CANTIDAD) AS CANTIDAD FROM SALIDA S "
            + "LEFT JOIN SALIDA_PRODUCTO SP ON SP.ID_SALIDA_FK = S.ID_SALIDA_PK AND SP.ESTATUS ='1' "
            + "LEFT JOIN ENVASADO E ON E.ID_ENVASADO_PK = SP.ID_ENVASADO_FK "
            + "LEFT JOIN CLIENTE C ON C.ID_CLIENTE_PK =S.ID_CLIENTE_FK "
            + "LEFT JOIN USUARIO U ON U.ID = S.ID_USUARIO_FK "
            + "WHERE  S.ID_SALIDA_PK =:idSalida"
            + "GROUP BY S.ID_SALIDA_PK,S.FECHA,S.ID_CLIENTE_FK,S.DATOS_CAMION,S.CHOFER,S.ID_USUARIO_FK,S.ESTATUS "
            + ",C.NOMBRE,U.NOMBRE ", nativeQuery = true)
    List<Object[]> getByIdSalida(@Param("idSalida") Integer idSalida);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Integer getLastInsertId();

}
