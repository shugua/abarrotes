package com.fresh.repository;

import com.fresh.dominio.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    public Producto findByIdProductoPk(Integer idProductoPk);
//    @Query(value = "SELECT ID_PRODUCTO_PK,NOMBRE,CODIGO,TIPO,ESTATUS,MINIMO,TIEMPO_ENTREGA,FECHA_ALTA,ID_USUARIO_ALTA,clave_producto_servicio,clave_unidad,fracion_arancelaria FROM PRODUCTO WHERE ID_PRODUCTO_PK =:idProductoPk", nativeQuery = true)
//    Producto findByIdProductoPk(@Param("idProductoPk") Integer idProductoPk);

    
    @Query(value = "SELECT p.* FROM Producto p WHERE p.ESTATUS=1 AND p.nombre LIKE CONCAT('%', :nombreProducto, '%') and tipo=:tipo ORDER BY P.NOMBRE ASC", nativeQuery = true)
    public List<Producto>findByIdNombre(@Param("nombreProducto") String nombreProducto,@Param("tipo") String tipo);
    
     @Query(value = "SELECT p.* FROM Producto p WHERE p.TIPO=:tipo ORDER BY P.NOMBRE ASC", nativeQuery = true)
    public List<Producto>findByTipo(@Param("tipo") String tipo);
    
    
    @Modifying
    @Query(value = "UPDATE PRODUCTO SET ESTATUS ='0', FECHA_ELIMINACION = NOW(), id_usuario_eliminacion =:idUsuario WHERE ID_PRODUCTO_PK =:idProductoPk", nativeQuery = true)
    int deleteByProductoPk(@Param("idProductoPk") Integer idProductoPk, @Param("idUsuario") Integer idUsuario);

    @Modifying
    @Query(value = "UPDATE PRODUCTO SET ESTATUS ='1' WHERE ID_PRODUCTO_PK =:idProductoPk", nativeQuery = true)
    int activarByIdProductoPk(@Param("idProductoPk") Integer idProductoPk);

    @Modifying
    @Query(value = "UPDATE PRODUCTO SET NOMBRE= :nombre, CODIGO =:codigo,TIPO =:tipo,ESTATUS =:estatus,MINIMO =:minimo,TIEMPO_ENTREGA =:tiempoEntrega,DESCRIPCION =:descripcion WHERE ID_PRODUCTO_PK=:idProductoPk", nativeQuery = true)
    int update(@Param("nombre") String nombre, @Param("codigo") String codigo, @Param("tipo") String tipo, @Param("estatus") String estatus, @Param("minimo") Integer minimo, @Param("tiempoEntrega") Integer tiempoEntrega, @Param("idProductoPk") Integer idProductoPk, @Param("descripcion") String descripcion);

    @Modifying
    @Query(value = "INSERT INTO PRODUCTO(NOMBRE,CODIGO,TIPO,ESTATUS,MINIMO,TIEMPO_ENTREGA,FECHA_ALTA,ID_USUARIO_ALTA,DESCRIPCION) VALUES(:nombre,:codigo,:tipo,'1',:minimo,:tiempoEntrega,NOW(),:idUsuario,:descripcion)", nativeQuery = true)
    int insert(@Param("nombre") String nombre, @Param("codigo") String codigo, @Param("tipo") String tipo, @Param("minimo") Integer minimo, @Param("tiempoEntrega") Integer tiempoEntrega, @Param("idUsuario") Integer idUsuario, @Param("descripcion") String descripcion);

}
