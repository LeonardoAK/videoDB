
package com.adv.videodb.repositorios;

import com.adv.videodb.entidades.Carpeta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarpetaRepositorio extends JpaRepository<Carpeta, String>{
    
    @Query("SELECT c FROM carpeta c WHERE c.nombre = :nombre")
    public List<Carpeta> buscarPorNombre(@Param("nombre") String nombre);
    
}
