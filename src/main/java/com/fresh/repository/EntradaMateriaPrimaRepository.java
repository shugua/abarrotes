package com.fresh.repository;

import com.fresh.dominio.EntradaMateriaPrima;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaMateriaPrimaRepository extends JpaRepository<EntradaMateriaPrima, Integer> {

    @Query(value = "SELECT MAX(NUMERO_ENTRADA) AS max_numero_entrada FROM ENTRADA_MATERIA_PRIMA WHERE YEAR(FECHA) = YEAR(NOW())", nativeQuery = true)
    Integer getNumeroEntrada();

    @Modifying
    @Query(value = "UPDATE ENTRADA_MATERIA_PRIMA SET ESTATUS ='0', FECHA_CANCELACION = NOW(), ID_USUARIO_CANCELA_FK =:idUsuario WHERE ID_ENTRADA_MATERIA_PRIMA_PK =:idEntradaPk", nativeQuery = true)
    int delete(@Param("idEntradaPk") Integer idEntradaPk, @Param("idUsuario") Integer idUsuario);

    @Modifying
    @Query(value = "UPDATE ENTRADA_MATERIA_PRIMA SET PESO_BRUTO=:pesoBruto,PESO_NETO=:pesoNeto,TOTAL_TARA=:totalTara,TOTAL_CAJA=:totalCaja,REMISION=:remision,CANTIDAD=:cantidad,OBSERVACIONES=:observaciones,ID_PROVEEDOR_FK=:idProveedor,ID_CLIENTE_FK =:idCliente WHERE ID_ENTRADA_MATERIA_PRIMA_PK=:idEntradaPk", nativeQuery = true)
    int update(@Param("pesoBruto") BigDecimal pesoBruto, @Param("pesoNeto") BigDecimal pesoNeto, @Param("totalTara") BigDecimal totalTara, @Param("totalCaja") BigDecimal totalCaja, @Param("remision") String remision, @Param("cantidad") BigDecimal cantidad, @Param("observaciones") String observaciones, @Param("idProveedor") Integer idProveedor, @Param("idCliente") Integer idCliente, @Param("idEntradaPk") Integer idEntradaPk);

    @Modifying
    @Query(value = "INSERT INTO ENTRADA_MATERIA_PRIMA(FECHA,ESTATUS,NUMERO_ENTRADA,PESO_BRUTO,PESO_NETO,TOTAL_TARA,TOTAL_CAJA,LOTE,REMISION,CANTIDAD,OBSERVACIONES,ID_USUARIO_FK,ID_PROVEEDOR_FK,ID_CLIENTE_FK) VALUES(NOW(),'1',:numeroEntrada,:pesoBruto,:pesoNeto,:totalTara,:totalCaja,:lote,:remision,:cantidad,:observaciones,:idUsuario,:idProveedor,:idCliente)", nativeQuery = true)
    int insert(@Param("numeroEntrada") Integer numeroEntrada, @Param("pesoBruto") BigDecimal pesoBruto, @Param("pesoNeto") BigDecimal pesoNeto, @Param("totalTara") BigDecimal totalTara, @Param("totalCaja") BigDecimal totalCaja, @Param("lote") String lote, @Param("remision") String remision, @Param("cantidad") BigDecimal cantidad, @Param("observaciones") String observaciones, @Param("idUsuario") Integer idUsuario, @Param("idProveedor") Integer idProveedor, @Param("idCliente") Integer idCliente);

    @Query(value = "SELECT EM.ID_ENTRADA_MATERIA_PRIMA_PK,EM.LOTE,EM.FECHA,EM.ID_PROVEEDOR_FK,P.NOMBRE AS NOMBRE_P,EM.ID_CLIENTE_FK,C.NOMBRE AS NOMBRE_C,EM.PESO_BRUTO,EM.PESO_NETO,EM.OBSERVACIONES,EM.ID_USUARIO_FK,U.NOMBRE AS NOMBRE_U,EM.ESTATUS,EM.CANTIDAD,EM.REMISION FROM ENTRADA_MATERIA_PRIMA EM "
            + "LEFT JOIN PROVEEDOR P ON P.ID_PROVEEDOR_PK =EM.ID_PROVEEDOR_FK "
            + "LEFT JOIN CLIENTE C ON C.ID_CLIENTE_PK = EM.ID_CLIENTE_FK "
            + "LEFT JOIN USUARIO U ON U.ID = EM.ID_USUARIO_FK "
            + "WHERE STR_TO_DATE(DATE_FORMAT(EM.FECHA, '%d/%m/%Y'), '%d/%m/%Y')  BETWEEN STR_TO_DATE(:fechaInicio, '%d/%m/%Y') AND STR_TO_DATE(:fechaFin, '%d/%m/%Y') "
            + "AND (:idProveedor IS NULL OR EM.ID_PROVEEDOR_FK = :idProveedor) "
            + "AND (:idCliente IS NULL OR EM.ID_CLIENTE_FK = :idCliente)", nativeQuery = true)
    List<Object[]> findByIdProveedorAndIdClienteAndFecha(@Param("idProveedor") Integer idProveedor, @Param("idCliente") Integer idCliente, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
    
      @Query(value = "SELECT EM.ID_ENTRADA_MATERIA_PRIMA_PK,EM.LOTE,EM.FECHA,EM.ID_PROVEEDOR_FK,P.NOMBRE AS NOMBRE_P,EM.ID_CLIENTE_FK,C.NOMBRE AS NOMBRE_C,EM.PESO_BRUTO,EM.PESO_NETO,EM.OBSERVACIONES,EM.ID_USUARIO_FK,U.NOMBRE AS NOMBRE_U,EM.ESTATUS FROM ENTRADA_MATERIA_PRIMA EM "
            + "LEFT JOIN PROVEEDOR P ON P.ID_PROVEEDOR_PK =EM.ID_PROVEEDOR_FK "
            + "LEFT JOIN CLIENTE C ON C.ID_CLIENTE_PK = EM.ID_CLIENTE_FK "
            + "LEFT JOIN USUARIO U ON U.ID = EM.ID_USUARIO_FK "
            + "WHERE EM.LOTE LIKE CONCAT('%', :lote, '%')", nativeQuery = true)
    List<Object[]> findByLote(@Param("lote") String lote);
    
    
       @Query(value = "SELECT EM.ID_ENTRADA_MATERIA_PRIMA_PK,EM.LOTE,EM.FECHA,EM.ID_PROVEEDOR_FK,P.NOMBRE AS NOMBRE_P,EM.ID_CLIENTE_FK,C.NOMBRE AS NOMBRE_C,EM.PESO_BRUTO,EM.PESO_NETO,EM.OBSERVACIONES,EM.ID_USUARIO_FK,U.NOMBRE AS NOMBRE_U,EM.ESTATUS,EM.CANTIDAD,EM.REMISION FROM ENTRADA_MATERIA_PRIMA EM "
            + "LEFT JOIN PROVEEDOR P ON P.ID_PROVEEDOR_PK =EM.ID_PROVEEDOR_FK "
            + "LEFT JOIN CLIENTE C ON C.ID_CLIENTE_PK = EM.ID_CLIENTE_FK "
            + "LEFT JOIN USUARIO U ON U.ID = EM.ID_USUARIO_FK "
            + "WHERE ID_ENTRADA_MATERIA_PRIMA_PK = :idEntradaPk", nativeQuery = true)
    List<Object[]> findByIdEntradaPk(@Param("idEntradaPk") Integer idEntradaPk);
    
    

}
