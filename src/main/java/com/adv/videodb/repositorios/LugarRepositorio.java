
package com.adv.videodb.repositorios;

import com.adv.videodb.entidades.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LugarRepositorio extends JpaRepository<Lugar, String> {
    
}
