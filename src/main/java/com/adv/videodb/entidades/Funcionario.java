
package com.adv.videodb.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Funcionario {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idFuncionario;
    private String nombre;
    private String cargo;
    @OneToOne
    private Secretaria secretaria;
    @OneToOne
    private Foto foto;

    public Funcionario() {
    }

    public Funcionario(String idFuncionario, String nombre, String cargo, Secretaria secretaria, Foto foto) {
        this.idFuncionario = idFuncionario;
        this.nombre = nombre;
        this.cargo = cargo;
        this.secretaria = secretaria;
        this.foto = foto;
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Secretaria getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(Secretaria secretaria) {
        this.secretaria = secretaria;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "idFuncionario=" + idFuncionario + ", nombre=" + nombre + ", cargo=" + cargo + ", secretaria=" + secretaria + ", foto=" + foto + '}';
    }  
    
}
