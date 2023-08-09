
package com.adv.videodb.repositorios;

import com.adv.videodb.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    @Query("SELECT a FROM admin a WHERE a.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);
}
