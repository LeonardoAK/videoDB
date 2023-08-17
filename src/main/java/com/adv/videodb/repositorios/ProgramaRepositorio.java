
package com.adv.videodb.repositorios;

import com.adv.videodb.entidades.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramaRepositorio extends JpaRepository<Programa, String>{
    
        
}
