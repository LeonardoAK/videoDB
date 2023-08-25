
package com.adv.videodb.repositorios;

import com.adv.videodb.entidades.Carpeta;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarpetaRepositorio extends JpaRepository<Carpeta, String>{
    
    @Query("SELECT c FROM carpeta c WHERE c.nombre = :nombre")
    public List<Carpeta> buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT c FROM carpeta c WHERE c.fecha = :fecha")
    public List<Carpeta> buscarPorFecha(@Param("fecha") Date fecha);
    
    @Query("SELECT c FROM carpeta c WHERE c.tdc = :tdc")
    public List<Carpeta> buscarPorTdc(@Param("tdc") String tdc);
}
