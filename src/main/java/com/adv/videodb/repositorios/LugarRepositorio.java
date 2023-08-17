
package com.adv.videodb.repositorios;

import com.adv.videodb.entidades.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LugarRepositorio extends JpaRepository<Lugar, String> {
    
}
