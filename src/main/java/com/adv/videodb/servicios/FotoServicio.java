
package com.adv.videodb.servicios;

import com.adv.videodb.entidades.Foto;
import com.adv.videodb.excepciones.MiException;
import com.adv.videodb.repositorios.FotoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {
    
    @Autowired
    private FotoRepositorio fotoRepo;

    public Foto guardar(MultipartFile archivo) throws MiException {
        if (archivo.getContentType().contains("image")) {
            try {
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setArchivo(archivo.getBytes());
                return fotoRepo.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    public Foto actualizar(MultipartFile archivo, String idFoto) throws MiException {
        if (archivo.getContentType().contains("image")) {
            try {

                Foto foto = new Foto();

                if (idFoto != null) {
                    Optional<Foto> respuesta = fotoRepo.findById(idFoto);

                    if (respuesta.isPresent()) {
                        foto = respuesta.get();
                    }
                }

                foto.setMime(archivo.getContentType());

                foto.setNombre(archivo.getName());

                foto.setArchivo(archivo.getBytes());

                return fotoRepo.save(foto);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
    
    @Transactional(readOnly = true)
    public Foto getOne(String idFoto){
        return fotoRepo.getOne(idFoto);
    }
    
    @Transactional(readOnly = true)
    public List<Foto> listarTodos() {
        return fotoRepo.findAll();
    }
}
