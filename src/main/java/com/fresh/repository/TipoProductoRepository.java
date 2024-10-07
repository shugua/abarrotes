package com.fresh.repository;

import com.fresh.dominio.TipoTarima;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoTarima, Long> {

    
    @Query(value = "SELECT ID_TIPO_PRODUCTO_PK,DESCRIPCION,PESO FROM TIPO_PRODUCTO", nativeQuery = true)
    List<Object[]> findAllD();

}
