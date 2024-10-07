package com.fresh.repository;

import com.fresh.dominio.Menu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query(value = "SELECT men.id_menu, men.descripcion, men.tipo, men.nivel, men.url_sistema "
            + "FROM menu men "
            + "LEFT JOIN acces_menu acces  ON acces.id_menu_fk = men.id_menu "
            + "WHERE acces.ID_ROL_FK =:idRol ORDER BY men.nivel ASC", nativeQuery = true)
    List<Menu> findByIdRolFk(@Param("idRol") Integer idRolFk);

}
