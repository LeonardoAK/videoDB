/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adv.videodb.entidades;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Secretaria {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idSecretaria;
    private String nombre;
    @OneToOne
    private Collection<Funcionario> funcionarios;
    @ManyToMany
    private Collection<Programa> programas;

    public Secretaria() {
    }

    public Secretaria(String idSecretaria, String nombre, Collection<Funcionario> funcionarios, Collection<Programa> programas) {
        this.idSecretaria = idSecretaria;
        this.nombre = nombre;
        this.funcionarios = funcionarios;
        this.programas = programas;
    }

    public String getIdSecretaria() {
        return idSecretaria;
    }

    public void setIdSecretaria(String idSecretaria) {
        this.idSecretaria = idSecretaria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Collection<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Collection<Programa> getProgramas() {
        return programas;
    }

    public void setProgramas(Collection<Programa> programas) {
        this.programas = programas;
    }

    @Override
    public String toString() {
        return "Secretaria{" + "idSecretaria=" + idSecretaria + ", nombre=" + nombre + ", funcionarios=" + funcionarios + ", programas=" + programas + '}';
    }
    
    
    
}
