
package com.adv.videodb.repositorios;

import com.adv.videodb.entidades.Secretaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SecretariaRepositorio extends JpaRepository<Secretaria, String> {
    
    @Query("SELECT s FROM secretaria s WHERE s.nombre = :nombre")
    public Secretaria buscarPorNombre(@Param("nombre") String nombre);
    
}
