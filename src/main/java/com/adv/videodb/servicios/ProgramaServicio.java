package com.adv.videodb.servicios;

import com.adv.videodb.entidades.Programa;
import com.adv.videodb.entidades.Secretaria;
import com.adv.videodb.repositorios.ProgramaRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ProgramaServicio {

    @Autowired
    private ProgramaRepositorio programaRepo;

    @Autowired
    private SecretariaServicio secreService;

    @Transactional
    public void registrarPrograma(String nombre, List<Secretaria> secretarias) {

        Programa programa = new Programa();

        programa.setNombre(nombre);
        programa.setSecretarias(secretarias);

        programaRepo.save(programa);

        for (Secretaria sec : secretarias) {
            secreService.agregarPrograma(sec.getIdSecretaria(), programa);
        }
    }

//    @Transactional
//    public void modificarPrograma(String idPrograma, String nombre, List<Secretaria> secretarias) {
//
//        Optional<Programa> respuesta = programaRepo.findById(idPrograma);
//
//        Programa programa = new Programa();
//
//        if (respuesta.isPresent()) {
//            
//            programa = respuesta.get();
//            
//            programa.setNombre(nombre);
//            programa.setSecretarias(secretarias);
//
//            programaRepo.save(programa);
//
//            for (Secretaria sec : secretarias) {
//                secreService.agregarPrograma(sec.getIdSecretaria(), programa);
//            }
//        }
//    }

    @Transactional
    public void eliminarPrograma(String idPrograma) {

        Programa programa = programaRepo.getById(idPrograma);
        List<Secretaria> lista = (List<Secretaria>) programa.getSecretarias();

        for (Secretaria sec : lista) {
            secreService.quitarPrograma(sec.getIdSecretaria(), programa);
        }

        programaRepo.delete(programa);
    }
}
