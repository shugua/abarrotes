package com.fresh.repository;

import com.fresh.dominio.Rol;
import com.fresh.dto.MenuD;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    @Query(value = "SELECT men.id_menu, men.descripcion, men.tipo, men.nivel, men.url_sistema FROM menu men, acces_menu acces  WHERE acces.ID_ROL_FK =:idRol", nativeQuery = true)
    List<Rol> findByIdRolFk(@Param("idRol") Integer idRolFk);

    @Modifying
    @Query(value = "update USUARIO set ESTATUS ='0', FECHA_BAJA = NOW(),  ID_USUARIO_CANCEL_FK =:idUsuario where ID =:idUsuarioPk", nativeQuery = true)
    int deleteByIdUsuarioPk(@Param("idUsuario") Integer idUsuarioPk, @Param("idUsuario") Integer idUsuario);

    @Modifying
    @Query(value = "update USUARIO set ESTATUS ='1' where ID =:idUsuarioPk", nativeQuery = true)
    int activarByIdUsuarioPk(@Param("idUsuarioPk") Integer idUsuarioPk);

    @Modifying
    @Query(value = "update USUARIO set USERNAME =:username, PASSWORD =:password,  NOMBRE =:nombre,TELEFONO=:telefono;ID_ROL_FK =:idRolFk where ID =:idUsuarioPk", nativeQuery = true)
    int update(@Param("username") String username, @Param("password") String password, @Param("nombre") String nombre, @Param("telefono") String telefono, @Param("idRolFk") Integer idRolFk, @Param("idUsuarioPk") Integer idUsuarioPk);

    @Modifying
    @Query(value = "INSERT INTO USUARIO(USERNAME,PASSWORD,ROLE,NOMBRE,TELEFONO,ID_ROL_FK,ESTATUS,ID_USUARIO_ALTA_FK,FECHA_ALTA) VALUES(:username,:password,'ADMIN',:nombre,:telefono,:idRolFk,:estatus,:idUsuarioFk,:now())", nativeQuery = true)
    int insert(@Param("username") String username, @Param("password") String password, @Param("nombre") String nombre, @Param("telefono") String telefono, @Param("idRolFk") Integer idRolFk, @Param("estatus") String estatus, @Param("idUsuarioFk") Integer idUsuarioFk);

}
