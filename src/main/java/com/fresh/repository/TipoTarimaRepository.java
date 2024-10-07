package com.fresh.repository;

import com.fresh.dominio.TipoTarima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTarimaRepository extends JpaRepository<TipoTarima, Long> {

    

}
