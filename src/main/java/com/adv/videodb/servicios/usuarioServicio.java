package com.adv.videodb.servicios;

import com.adv.videodb.entidades.Usuario;
import com.adv.videodb.enumeradores.Rol;
import com.adv.videodb.excepciones.MiException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.adv.videodb.repositorios.UsuarioRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class usuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Transactional
    public void registrarUsuario(String nombre, String email, String password, String passwordCheck, String rol) throws MiException {
        
        validar(nombre, email, password, passwordCheck, rol);
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setRol(Rol.valueOf(rol));
        
        usuarioRepo.save(usuario);
    }
    
    @Transactional
    public Usuario modificarUsuario (String idUsuario, String nombre, String email, String password, String passwordCheck, String rol) throws MiException{
        
        validar(nombre, email, password, passwordCheck, rol);
        
        Optional<Usuario> respuesta = usuarioRepo.findById(idUsuario);
        Usuario usuario = new Usuario();
        
        if (respuesta.isPresent()){
            
            usuario = respuesta.get();
            
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setRol(Rol.valueOf(rol));
            
            usuarioRepo.save(usuario);
        }       
        return usuario;
    }
    
    
    private void validar(String nombre, String email, String password, String passwordCheck, String rol) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede quedar vacio");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("El email no puede quedar vacio");
        }
        if (password.isEmpty() || password ==null){
            throw new MiException("La contraseña no puede quedar vacia");
        } else if (password.length() < 6){
            throw new MiException("La contraseña debe tener al menos 6 caracteres");
        }
        
        if (!password.equals(passwordCheck)){
            throw new MiException("Las contraseñas ingresadas no son iguales");
        }
        
        if (rol == null){
            throw new MiException("No se ha asignado un rol");
        } else if ((!rol.equals("ADMIN")) || (!rol.equals("USER"))){
            throw new MiException("No se ingreso un rol valido");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepo.buscarPorEmail(email);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {
            return null;
        }
    }
}
