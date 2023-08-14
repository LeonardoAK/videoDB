
package com.adv.videodb.repositorios;

import com.adv.videodb.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    @Query("SELECT u FROM usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);
    
    @Query("SELECT u FROM usuario u WHERE u.name = :name")
    public Usuario buscarPorNombre(@Param("nombre") String nombre);
}
