package com.fresh.repository;

import com.fresh.dominio.AccesMenu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccesMenuRepository extends JpaRepository<AccesMenu, Integer> {

    @Query(value = "SELECT a.*,m.descripcion,m.tipo,m.nivel,m.url_sistema FROM acces_menu a \n"
            + "LEFT JOIN menu m ON m.id_menu = a.id_menu_fk WHERE id_rol_fk =1 :idRol", nativeQuery = true)
    List<AccesMenu> findByIdRolFk(@Param("idRol") Integer idRolFk);

    @Modifying
    @Query(value = "INSERT INTO USUARIO(USERNAME,PASSWORD,ROLE,NOMBRE,TELEFONO,ID_ROL_FK,ESTATUS,ID_USUARIO_ALTA_FK,FECHA_ALTA) VALUES(:username,:password,'ADMIN',:nombre,:telefono,:idRolFk,:estatus,:idUsuarioFk,:now())", nativeQuery = true)
    int insert(@Param("username") String username, @Param("password") String password, @Param("nombre") String nombre, @Param("telefono") String telefono, @Param("idRolFk") Integer idRolFk, @Param("estatus") String estatus, @Param("idUsuarioFk") Integer idUsuarioFk);

    @Modifying
    @Query(value = "INSERT INTO USUARIO(USERNAME,PASSWORD,ROLE,NOMBRE,TELEFONO,ID_ROL_FK,ESTATUS,ID_USUARIO_ALTA_FK,FECHA_ALTA) VALUES(:username,:password,'ADMIN',:nombre,:telefono,:idRolFk,:estatus,:idUsuarioFk,:now())", nativeQuery = true)
    int delete(@Param("username") String username, @Param("password") String password, @Param("nombre") String nombre, @Param("telefono") String telefono, @Param("idRolFk") Integer idRolFk, @Param("estatus") String estatus, @Param("idUsuarioFk") Integer idUsuarioFk);

}
