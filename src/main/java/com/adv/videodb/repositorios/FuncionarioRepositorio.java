
package com.adv.videodb.repositorios;

import com.adv.videodb.entidades.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario, String> {
    
    @Query("SELECT f FROM funcionario f WHERE f.nombre = :nombre")
    public Funcionario buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT f FROM funcionario f WHERE f.apellido = :apellido")
    public Funcionario buscarPorApellido(@Param("apellido") String apellido);
    
    @Query ("SELECT f FROM funcionario f WHERE f.cargo = :cargo")
    public Funcionario buscarPorCargo(@Param("cargo") String cargo);
}
