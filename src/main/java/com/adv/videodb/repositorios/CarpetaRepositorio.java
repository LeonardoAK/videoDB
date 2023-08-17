
package com.adv.videodb.repositorios;

import com.adv.videodb.entidades.Carpeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarpetaRepositorio extends JpaRepository<Carpeta, String>{
    
}
