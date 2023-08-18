package com.adv.videodb.servicios;

import com.adv.videodb.entidades.Foto;
import com.adv.videodb.entidades.Funcionario;
import com.adv.videodb.entidades.Secretaria;
import com.adv.videodb.excepciones.MiException;
import com.adv.videodb.repositorios.FuncionarioRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FuncionarioServicio {

    @Autowired
    private FuncionarioRepositorio funcionarioRepo;

    @Autowired
    private FotoServicio fotoServicio;

    @Autowired
    private SecretariaServicio secreServicio;

    @Transactional
    public void registrarFuncionario(String nombre, String apellido, String cargo, Secretaria secretaria, MultipartFile imagen) throws MiException {

        Funcionario funcionario = new Funcionario();
        validar(nombre, apellido, cargo);

        Foto foto = fotoServicio.guardar(imagen);

        funcionario.setNombre(nombre);
        funcionario.setApellido(apellido);
        funcionario.setCargo(cargo);
        funcionario.setApellido(apellido);
        funcionario.setFoto(foto);

        secreServicio.agregarFuncionario(secretaria.getIdSecretaria(), funcionario);

        funcionario.setSecretaria(secretaria);

        funcionarioRepo.save(funcionario);

    }

    @Transactional
    public Funcionario modificarFuncionario(String idFuncionario, String nombre, String apellido, String cargo, Secretaria secretaria, MultipartFile imagen) throws MiException {

        Optional<Funcionario> respuesta = funcionarioRepo.findById(idFuncionario);
        Funcionario funcionario = new Funcionario();

        if (respuesta.isPresent()) {

            validar(nombre, apellido, cargo);

            funcionario = respuesta.get();

            funcionario.setNombre(nombre);
            funcionario.setApellido(apellido);
            funcionario.setCargo(cargo);
            funcionario.setApellido(apellido);

            if (imagen.getContentType().contains("image")) {
                String idFoto = null;
                if (funcionario.getFoto() != null) {
                    idFoto = funcionario.getFoto().getIdImagen();
                }
                Foto foto = fotoServicio.actualizar(imagen, idFoto);
                funcionario.setFoto(foto);
            } else {
                funcionario.setFoto(respuesta.get().getFoto());
            }

            secreServicio.agregarFuncionario(secretaria.getIdSecretaria(), funcionario);

            funcionario.setSecretaria(secretaria);

            funcionarioRepo.save(funcionario);
        }

        return funcionario;

    }

    @Transactional
    public void eliminarFuncionario(String idFuncionario) {

        Funcionario funcionario = funcionarioRepo.getById(idFuncionario);

        secreServicio.quitarFuncionario(funcionario.getSecretaria().getIdSecretaria(), funcionario);
        funcionarioRepo.delete(funcionario);

    }

    private void validar(String nombre, String apellido, String cargo) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede quedar vacio");
        }
        if (apellido.isEmpty() || apellido == null) {
            throw new MiException("El apellido no puede quedar vacio");
        }
        if (cargo.isEmpty() || cargo == null) {
            throw new MiException("El cargo no puede quedar vacio");
        }
    }

    @Transactional(readOnly = true)
    public Funcionario getOne(String idFuncionario) {
        return funcionarioRepo.getOne(idFuncionario);
    }

    @Transactional(readOnly = true)
    public Funcionario buscarFuncionarioPorNombre(String nombre) {
        return funcionarioRepo.buscarPorNombre(nombre);
    }

    @Transactional(readOnly = true)
    public Funcionario buscarFuncionarioPorApellido(String apellido) {
        return funcionarioRepo.buscarPorNombre(apellido);
    }

    @Transactional(readOnly = true)
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepo.findAll();
    }

}
