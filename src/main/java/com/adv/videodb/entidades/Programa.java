
package com.adv.videodb.entidades;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Programa {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idPrograma;
    private String nombre;
    @ManyToMany
    private Collection<Secretaria> secretarias;

    public Programa() {
    }

    public Programa(String idPrograma, String nombre, Collection<Secretaria> secretarias) {
        this.idPrograma = idPrograma;
        this.nombre = nombre;
        this.secretarias = secretarias;
    }

    public String getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(String idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Secretaria> getSecretarias() {
        return secretarias;
    }

    public void setSecretarias(Collection<Secretaria> secretarias) {
        this.secretarias = secretarias;
    }

    @Override
    public String toString() {
        return "Programa{" + "idPrograma=" + idPrograma + ", nombre=" + nombre + ", secretarias=" + secretarias + '}';
    }
    
    
}
