package com.adv.videodb.servicios;

import com.adv.videodb.entidades.Funcionario;
import com.adv.videodb.entidades.Programa;
import com.adv.videodb.entidades.Secretaria;
import com.adv.videodb.excepciones.MiException;
import com.adv.videodb.repositorios.SecretariaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class SecretariaServicio {

    @Autowired
    private SecretariaRepositorio secretariaRepo;

    @Transactional
    public void registrarSecretaria(String nombre, List<Funcionario> funcionarios, List<Programa> programas) {

        Secretaria secretaria = new Secretaria();

        secretaria.setNombre(nombre);
        secretaria.setFuncionarios(funcionarios);
        secretaria.setProgramas(programas);

        secretariaRepo.save(secretaria);

    }

    @Transactional
    public Secretaria modificarSecretaria(String idSecretaria, String nombre, List<Funcionario> funcionarios, List<Programa> programas) {

        Optional<Secretaria> respuesta = secretariaRepo.findById(idSecretaria);
        Secretaria secretaria = new Secretaria();

        if (respuesta.isPresent()) {

            secretaria = respuesta.get();

            secretaria.setNombre(nombre);
            secretaria.setFuncionarios(funcionarios);
            secretaria.setProgramas(programas);

            secretariaRepo.save(secretaria);
        }

        return secretaria;
    }

    @Transactional
    public void agregarPrograma(String idSecretaria, Programa programa) {

        Optional<Secretaria> respuesta = secretariaRepo.findById(idSecretaria);
        Secretaria secretaria = new Secretaria();

        if (respuesta.isPresent()) {

            secretaria = respuesta.get();

            List<Programa> programas = (List<Programa>) secretaria.getProgramas();
            programas.add(programa);
            secretaria.setProgramas(programas);

            secretariaRepo.save(secretaria);
        }

    }

    @Transactional
    public void quitarPrograma(String idSecretaria, Programa programa) {

        Optional<Secretaria> respuesta = secretariaRepo.findById(idSecretaria);
        Secretaria secretaria = new Secretaria();

        if (respuesta.isPresent()) {

            secretaria = respuesta.get();

            List<Programa> programas = (List<Programa>) secretaria.getProgramas();

            if (programas.contains(programa)) {
                programas.remove(programa);
                secretaria.setProgramas(programas);

                secretariaRepo.save(secretaria);
            }
        }
    }

    @Transactional
    public void agregarFuncionario(String idSecretaria, Funcionario funcionario) {

        Optional<Secretaria> respuesta = secretariaRepo.findById(idSecretaria);
        Secretaria secretaria = new Secretaria();

        if (respuesta.isPresent()) {

            secretaria = respuesta.get();

            List<Funcionario> funcionarios = (List<Funcionario>) secretaria.getFuncionarios();

            if (!funcionarios.contains(funcionario)) {

                funcionarios.add(funcionario);
                secretaria.setFuncionarios(funcionarios);

                secretariaRepo.save(secretaria);
            }

        }

    }

    @Transactional
    public void quitarFuncionario(String idSecretaria, Funcionario funcionario) {

        Optional<Secretaria> respuesta = secretariaRepo.findById(idSecretaria);
        Secretaria secretaria = new Secretaria();

        if (respuesta.isPresent()) {

            secretaria = respuesta.get();

            List<Funcionario> funcionarios = (List<Funcionario>) secretaria.getFuncionarios();

            if (funcionarios.contains(funcionario)) {
                funcionarios.remove(funcionario);
                secretaria.setFuncionarios(funcionarios);

                secretariaRepo.save(secretaria);
            }
        }
    }

    @Transactional
    public void eliminarSecretaria(String idSecretaria) {

        Secretaria secretaria = secretariaRepo.getById(idSecretaria);

        secretariaRepo.delete(secretaria);
    }

    @Transactional(readOnly = true)
    public List<Secretaria> listarSecretarias() {

        List<Secretaria> secretarias = new ArrayList();

        secretarias = secretariaRepo.findAll();

        return secretarias;
    }

    @Transactional(readOnly = true)
    public Secretaria getOne(String idSecretaria) {
        return secretariaRepo.getOne(idSecretaria);
    }

    @Transactional(readOnly = true)
    public Secretaria buscarPorNombre(String nombre) {
        return secretariaRepo.buscarPorNombre(nombre);
    }
}
