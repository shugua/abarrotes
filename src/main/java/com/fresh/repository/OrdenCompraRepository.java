package com.fresh.repository;

import com.fresh.dominio.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenCompraRepository extends JpaRepository<Producto, Long> {
    

    @Modifying
    @Query(value = "UPDATE PRODUCTO SET NOMBRE= :nombre, CODIGO =:codigo,TIPO =:tipo,ESTATUS =:estatus,MINIMO =:minimo,TIEMPO_ENTREGA =:tiempoEntrega WHERE ID_PRODUCTO_PK=:idProductoPk", nativeQuery = true)
    int update(@Param("nombre") String nombre, @Param("codigo") String codigo, @Param("tipo") String tipo, @Param("estatus") String estatus, @Param("minimo") Integer minimo, @Param("tiempoEntrega") Integer tiempoEntrega, @Param("idProductoPk") Integer idProductoPk);

    @Modifying
    @Query(value = "INSERT INTO PRODUCTO(NOMBRE,CODIGO,TIPO,ESTATUS,MINIMO,TIEMPO_ENTREGA,FECHA_ALTA,ID_USUARIO_ALTA) VALUES(:nombre,:codigo,:tipo,'1',:minimo,:tiempoEntrega,NOW(),:idUsuario)", nativeQuery = true)
    int insert(@Param("nombre") String nombre, @Param("codigo") String codigo, @Param("tipo") String tipo, @Param("minimo") Integer minimo, @Param("tiempoEntrega") Integer tiempoEntrega, @Param("idUsuario") Integer idUsuario);

}
