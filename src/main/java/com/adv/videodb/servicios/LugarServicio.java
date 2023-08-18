
package com.adv.videodb.servicios;

import com.adv.videodb.entidades.Lugar;
import com.adv.videodb.enumeradores.Cpc;
import com.adv.videodb.enumeradores.TipoDeLugar;
import com.adv.videodb.excepciones.MiException;
import com.adv.videodb.repositorios.LugarRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LugarServicio {
    
    @Autowired
    private LugarRepositorio lugarRepo;
       
    @Transactional
    public void registrarLugar(String nombre, String nombreSubEspacio, String barrio, String cpc, String tdl) throws MiException{
        
        validar(nombre, barrio, cpc, tdl);
        
        Lugar lugar = new Lugar();
        
        lugar.setNombre(nombre);
        lugar.setNombreSubEspacios(nombreSubEspacio);
        lugar.setBarrio(barrio);
        lugar.setCpc(Cpc.valueOf(cpc));
        lugar.setTdl(TipoDeLugar.valueOf(tdl));
        
        lugarRepo.save(lugar);
        
    }
    
    @Transactional
    public Lugar modificarLugar(String idLugar, String nombre, String nombreSubEspacio, String barrio, String cpc, String tdl) throws MiException{
        
        validar(nombre, barrio, cpc, tdl);
        
        Optional<Lugar> respuesta = lugarRepo.findById(idLugar);
        Lugar lugar = new Lugar();
        
        if (respuesta.isPresent()){
            
            lugar = respuesta.get();
            
            lugar.setNombre(nombre);
            lugar.setNombreSubEspacios(nombreSubEspacio);
            lugar.setBarrio(barrio);
            lugar.setCpc(Cpc.valueOf(cpc));
            lugar.setTdl(TipoDeLugar.valueOf(tdl));
            
            lugarRepo.save(lugar);          
        }
        return lugar;
    }
    
    @Transactional    
    public void eliminarLugar(String idLugar){
        Lugar lugar = lugarRepo.getById(idLugar);
        lugarRepo.delete(lugar);
    }
    
    @Transactional(readOnly = true)
    public Lugar getOne(String idLugar) {
        return lugarRepo.getOne(idLugar);
    }
    
    @Transactional(readOnly = true)
    public List<Lugar> listarLugares() {
        return lugarRepo.findAll();
    }
    
    @Transactional(readOnly = true)
    public Lugar buscarLugarPorNombre(String nombre) {
        return lugarRepo.buscarPorNombre(nombre);
    }
    
    @Transactional(readOnly = true)
    public Lugar buscarLugarPorSubEspacio(String nombreSubEspacio) {
        return lugarRepo.buscarPorSubEspacio(nombreSubEspacio);
    }
    
    @Transactional(readOnly = true)
    public List<Lugar> buscarLugarPorBarrio(String barrio) {
        return lugarRepo.buscarPorBarrio(barrio);
    }
    
    @Transactional(readOnly = true)
    public List<Lugar> buscarLugarPorCpc(String Cpc) {
        return lugarRepo.buscarPorCpc(Cpc);
    }
    
    @Transactional(readOnly = true)
    public List<Lugar> buscarLugarPorTdl(String tdl) {
        return lugarRepo.buscarPorTdl(tdl);
    }
    
    private void validar(String nombre, String barrio, String cpc, String tdl) throws MiException{
        
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede quedar vacio");
        }
        if (barrio.isEmpty() || barrio == null) {
            throw new MiException("El barrio no puede quedar vacio");
        }
        if (cpc.isEmpty() || !(cpc.equals(Cpc.ARGUELLO.toString()) && cpc.equals(Cpc.CAPDEVILLA.toString()) && cpc.equals(Cpc.CENTRO_AMERICA.toString()) && cpc.equals(Cpc.CHALET_SAN_FELIPE.toString())
                && cpc.equals(Cpc.COLON.toString()) && cpc.equals(Cpc.EMPALME.toString()) && cpc.equals(Cpc.GUINIAZU.toString()) && cpc.equals(Cpc.JARDIN.toString()) 
                && cpc.equals(Cpc.MERCADO_DE_LA_CIUDAD.toString()) || cpc.equals(Cpc.MERCANTIL.toString()) || cpc.equals(Cpc.MONSENIOR_PABLO_CABRERA.toString())
                && cpc.equals(Cpc.PUEYRREDON.toString()) && cpc.equals(Cpc.RANCAGUA.toString()) && cpc.equals(Cpc.RUTA_20.toString()) && cpc.equals(Cpc.SAN_VICENTE.toString()) 
                && cpc.equals(Cpc.ARGUELLO.toString()) && cpc.equals(Cpc.SAN_VICENTE.toString()) && cpc.equals(Cpc.VILLA_LIBERTADOR.toString()))){
            throw new MiException("No se ingreso un CPC valido");
        }
        if (tdl.isEmpty() || !(tdl.equals(TipoDeLugar.BARRIO.toString()) && tdl.equals(TipoDeLugar.CALLE.toString()) && tdl.equals(TipoDeLugar.CENTRO_MEDICO.toString()) 
                && tdl.equals(TipoDeLugar.CPC_CENTRO_OPERATIVO.toString()) && tdl.equals(TipoDeLugar.EDUCATIVO.toString()) && tdl.equals(TipoDeLugar.ESPACIO_MUNICIPAL.toString()) 
                && tdl.equals(TipoDeLugar.ESPACIO_VERDE.toString()) && tdl.equals(TipoDeLugar.ICONICO_HISTORICO.toString()) && tdl.equals(TipoDeLugar.RESIDUOS_RECICLAJE.toString()) 
                && tdl.equals(TipoDeLugar.OTRO.toString()))) {
            throw new MiException("No se ingreso un Tipo De Lugar valido");
        }
    }
}
