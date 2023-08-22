package com.adv.videodb.servicios;

import com.adv.videodb.entidades.Carpeta;
import com.adv.videodb.entidades.Funcionario;
import com.adv.videodb.entidades.Lugar;
import com.adv.videodb.entidades.Programa;
import com.adv.videodb.entidades.Secretaria;
import com.adv.videodb.enumeradores.Horario;
import com.adv.videodb.enumeradores.TipoDeCobertura;
import com.adv.videodb.excepciones.MiException;
import com.adv.videodb.repositorios.CarpetaRepositorio;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CarpetaServicio {

    @Autowired
    private CarpetaRepositorio carpetaRepo;

    @Transactional
    public void crearCarpeta(String nombre, String link, Date fecha, int cantCam, int cantDron,
            Collection<Secretaria> secretarias, Programa programa, Collection<Funcionario> funcionarios,
            Lugar lugar, String tdc, String horario, Collection<String> palabrasClave, String ubicacion) throws MiException {

        validar(nombre, link, fecha, cantCam, cantDron, lugar, tdc, horario, ubicacion);

        Carpeta carpeta = new Carpeta();

        carpeta.setNombre(nombre);
        carpeta.setLink(link);
        carpeta.setFecha(fecha);
        carpeta.setCantCam(cantCam);
        carpeta.setCantDron(cantDron);
        carpeta.setSecretarias(secretarias);
        carpeta.setPrograma(programa);
        carpeta.setFuncionarios(funcionarios);
        carpeta.setLugar(lugar);
        carpeta.setTdc(TipoDeCobertura.valueOf(tdc));
        carpeta.setHorario(Horario.valueOf(nombre));
        carpeta.setPalabrasClave(palabrasClave);
        carpeta.setUbicacion(ubicacion);

        carpetaRepo.save(carpeta);

    }

    @Transactional
    public Carpeta modificarCarpeta(String idCarpeta, String nombre, String link, Date fecha, int cantCam,
            int cantDron, Collection<Secretaria> secretarias, Programa programa, Collection<Funcionario> funcionarios,
            Lugar lugar, String tdc, String horario, Collection<String> palabrasClave, String ubicacion) throws MiException {

        Carpeta carpeta = new Carpeta();

        validar(nombre, link, fecha, cantCam, cantDron, lugar, tdc, horario, ubicacion);

        Optional<Carpeta> respuesta = carpetaRepo.findById(idCarpeta);

        if (respuesta.isPresent()) {
            carpeta = respuesta.get();

            carpeta.setNombre(nombre);
            carpeta.setLink(link);
            carpeta.setFecha(fecha);
            carpeta.setCantCam(cantCam);
            carpeta.setCantDron(cantDron);
            carpeta.setSecretarias(secretarias);
            carpeta.setPrograma(programa);
            carpeta.setFuncionarios(funcionarios);
            carpeta.setLugar(lugar);
            carpeta.setTdc(TipoDeCobertura.valueOf(tdc));
            carpeta.setHorario(Horario.valueOf(nombre));
            carpeta.setPalabrasClave(palabrasClave);
            carpeta.setUbicacion(ubicacion);

            carpetaRepo.save(carpeta);

        }

        return carpeta;
    }

    @Transactional
    public void eliminarCarpeta(String idCarpeta) {
        Carpeta carpeta = carpetaRepo.getById(idCarpeta);
        carpetaRepo.delete(carpeta);
    }

    @Transactional(readOnly=true)
    public Carpeta getOne(String idCarpeta) {
        return carpetaRepo.getOne(idCarpeta);
    }
    
    public List<Carpeta> listarCarpetas() {
        return carpetaRepo.findAll();
    }
    
    
    
    private void validar(String nombre, String link, Date fecha, int cantCam, int cantDron,
            Lugar lugar, String tdc, String horario, String ubicacion) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede quedar vacio");
        }
        if (link.isEmpty() || link == null) {
            throw new MiException("El link no puede quedar vacio");
        }
        if (fecha == null) {
            throw new MiException("La fecha no puede quedar vacia");
        }
        if (lugar == null) {
            throw new MiException("El lugar no puede quedar vacio");
        }

        if (cantCam == 0 && cantDron == 0) {
            throw new MiException("Debe haber al menos una camara o un dron");
        }

        if (tdc.isEmpty() || !(tdc.equals(TipoDeCobertura.ACTO_ENCUENTRO.toString()) && tdc.equals(TipoDeCobertura.AVANCE_DE_OBRA.toString()) && tdc.equals(TipoDeCobertura.ENTREGA.toString())
                && tdc.equals(TipoDeCobertura.EVENTO_ESPECIAL.toString()) && tdc.equals(TipoDeCobertura.INAUGURACION.toString()) && tdc.equals(TipoDeCobertura.RECREATIVO.toString())
                && tdc.equals(TipoDeCobertura.SERVICIO.toString()) && tdc.equals(TipoDeCobertura.OTRO.toString()))) {
            throw new MiException("El tipo de cobertura no puede quedar vacio, seleccione una opcion valida");
        }
        if (horario.isEmpty() || !(horario.equals(Horario.ATARDECER.toString()) && horario.equals(Horario.MANANA.toString()) && horario.equals(Horario.MEDIODIA.toString())
                && horario.equals(Horario.NOCHE.toString()))) {
            throw new MiException("El horario no puede quedar vacio, seleccione una opcion valida");
        }
        if (ubicacion.isEmpty() || ubicacion == null) {
            throw new MiException("Ingrese en que mega esta la cobertura");
        }

    }
}
