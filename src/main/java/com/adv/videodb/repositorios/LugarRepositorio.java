
package com.adv.videodb.repositorios;

import com.adv.videodb.entidades.Lugar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LugarRepositorio extends JpaRepository<Lugar, String> {
    
    @Query("SELECT l FROM lugar l WHERE l.nombre = :nombre")
    public Lugar buscarPorNombre(@Param("nombre")String nombre);
    
    @Query("SELECT l FROM lugar l WHERE l.nombresubespacio = :nombresubespacio")
    public Lugar buscarPorSubEspacio(@Param("nombresubespacio")String nombreSubEspacio);
    
    @Query("SELECT l FROM lugar l WHERE l.barrio = :barrio")
    public List<Lugar> buscarPorBarrio(@Param("barrio") String barrio);
    
    @Query("SELECT l FROM lugar l WHERE l.cpc = :cpc")
    public List<Lugar> buscarPorCpc(@Param("cpc")String cpc);
    
    @Query("SELECT l FROM lugar l WHERE L.tdl = :tdl")
    public List<Lugar> buscarPorTdl(@Param("tdl")String tdl);
}
