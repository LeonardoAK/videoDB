
package com.adv.videodb.entidades;

import com.adv.videodb.enumeradores.Cpc;
import com.adv.videodb.enumeradores.TipoDeLugar;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Lugar {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idLugar;
    private String nombre;
    private String nombreSubEspacios;
    private String barrio;
    @Enumerated(EnumType.STRING)
    private Cpc cpc;
    @Enumerated(EnumType.STRING)
    private TipoDeLugar tdl;

    public Lugar() {
    }

    public Lugar(String idLugar, String nombre, String nombreSubEspacios, String barrio, Cpc cpc, TipoDeLugar tdl) {
        this.idLugar = idLugar;
        this.nombre = nombre;
        this.nombreSubEspacios = nombreSubEspacios;
        this.barrio = barrio;
        this.cpc = cpc;
        this.tdl = tdl;
    }

    public String getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(String idLugar) {
        this.idLugar = idLugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreSubEspacios() {
        return nombreSubEspacios;
    }

    public void setNombreSubEspacios(String nombreSubEspacios) {
        this.nombreSubEspacios = nombreSubEspacios;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public Cpc getCpc() {
        return cpc;
    }

    public void setCpc(Cpc cpc) {
        this.cpc = cpc;
    }

    public TipoDeLugar getTdl() {
        return tdl;
    }

    public void setTdl(TipoDeLugar tdl) {
        this.tdl = tdl;
    }

    @Override
    public String toString() {
        return "Lugar{" + "idLugar=" + idLugar + ", nombre=" + nombre + ", nombreSubEspacios=" + nombreSubEspacios + ", barrio=" + barrio + ", cpc=" + cpc + ", tdl=" + tdl + '}';
    }
    
    
}
