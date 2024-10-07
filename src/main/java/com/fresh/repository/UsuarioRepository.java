package com.fresh.repository;

import com.fresh.dominio.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Optional<Usuario> findByUsername(String username);

    @Query(value = "SELECT u.ID,u.USERNAME,u.PASSWORD,u.ROLE,u.NOMBRE,u.TELEFONO,u.ID_ROL_FK,u.ESTATUS,u.ID_USUARIO_ALTA_FK,u.FECHA_ALTA,u.ID_USUARIO_CANCEL_FK,u.FECHA_BAJA,R.ID_ROL_PK FROM Usuario u "
            + " LEFT JOIN ROL R ON R.ID_ROL_PK = u.ID_ROL_FK "
            + " WHERE ID=:idUsuarioPk", nativeQuery = true)
    Usuario findByIdUsuarioPk(@Param("idUsuarioPk") Integer idUsuarioPk);

    @Modifying
    @Query(value = "update Usuario set ESTATUS ='0', FECHA_BAJA = NOW(),  ID_USUARIO_CANCEL_FK =:idUsuario where ID =:idUsuarioPk", nativeQuery = true)
    int deleteByIdUsuarioPk(@Param("idUsuarioPk") Integer idUsuarioPk, @Param("idUsuario") Integer idUsuario);

    @Modifying
    @Query(value = "update Usuario set ESTATUS ='1' where ID =:idUsuarioPk", nativeQuery = true)
    int activarByIdUsuarioPk(@Param("idUsuarioPk") Integer idUsuarioPk);

    @Modifying
    @Query(value = "update Usuario set USERNAME =:username, PASSWORD =:password,  NOMBRE =:nombre,TELEFONO=:telefono,ID_ROL_FK =:idRolFk where ID =:idUsuarioPk", nativeQuery = true)
    int update(@Param("username") String username, @Param("password") String password, @Param("nombre") String nombre, @Param("telefono") String telefono, @Param("idRolFk") Integer idRolFk, @Param("idUsuarioPk") Integer idUsuarioPk);

    @Modifying
    @Query(value = "INSERT INTO Usuario(USERNAME,PASSWORD,ROLE,NOMBRE,TELEFONO,ID_ROL_FK,ESTATUS,ID_USUARIO_ALTA_FK,FECHA_ALTA) VALUES(:username,:password,'ADMIN',:nombre,:telefono,:idRolFk,:estatus,:idUsuarioFk,NOW())", nativeQuery = true)
    int insert(@Param("username") String username, @Param("password") String password, @Param("nombre") String nombre, @Param("telefono") String telefono, @Param("idRolFk") Integer idRolFk, @Param("estatus") String estatus, @Param("idUsuarioFk") Integer idUsuarioFk);

}
