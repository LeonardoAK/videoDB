
package com.adv.videodb.entidades;

import com.adv.videodb.enumeradores.Horario;
import com.adv.videodb.enumeradores.TipoDeCobertura;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Carpeta {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idCarpeta;
    private String nombre;
    private String link;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private int cantCam;
    private int cantDron;
    @OneToMany
    private Collection<Secretaria> secretarias;
    @OneToOne
    private Programa programa;
    @OneToMany
    private Collection<Funcionario> funcionarios;
    @OneToOne
    private Lugar lugar;
    @Enumerated(EnumType.STRING)
    private TipoDeCobertura tdc;
    @Enumerated(EnumType.STRING)
    private Horario horario;
    private Collection<String> palabrasClave;
    private String ubicacion;

    public Carpeta() {
    }

    public Carpeta(String idCarpeta, String nombre, String link, Date fecha, int cantCam, int cantDron, Collection<Secretaria> secretarias, Programa programa, Collection<Funcionario> funcionarios, Lugar lugar, TipoDeCobertura tdc, Horario horario, Collection<String> palabrasClave, String ubicacion) {
        this.idCarpeta = idCarpeta;
        this.nombre = nombre;
        this.link = link;
        this.fecha = fecha;
        this.cantCam = cantCam;
        this.cantDron = cantDron;
        this.secretarias = secretarias;
        this.programa = programa;
        this.funcionarios = funcionarios;
        this.lugar = lugar;
        this.tdc = tdc;
        this.horario = horario;
        this.palabrasClave = palabrasClave;
        this.ubicacion = ubicacion;
    }

    public String getIdCarpeta() {
        return idCarpeta;
    }

    public void setIdCarpeta(String idCarpeta) {
        this.idCarpeta = idCarpeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantCam() {
        return cantCam;
    }

    public void setCantCam(int cantCam) {
        this.cantCam = cantCam;
    }

    public int getCantDron() {
        return cantDron;
    }

    public void setCantDron(int cantDron) {
        this.cantDron = cantDron;
    }

    public Collection<Secretaria> getSecretarias() {
        return secretarias;
    }

    public void setSecretarias(Collection<Secretaria> secretarias) {
        this.secretarias = secretarias;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Collection<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Collection<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public TipoDeCobertura getTdc() {
        return tdc;
    }

    public void setTdc(TipoDeCobertura tdc) {
        this.tdc = tdc;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Collection<String> getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(Collection<String> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Carpeta{" + "idCarpeta=" + idCarpeta + ", nombre=" + nombre + ", link=" + link + ", fecha=" + fecha + ", cantCam=" + cantCam + ", cantDron=" + cantDron + ", secretarias=" + secretarias + ", programa=" + programa + ", funcionarios=" + funcionarios + ", lugar=" + lugar + ", tdc=" + tdc + ", horario=" + horario + ", palabrasClave=" + palabrasClave + ", ubicacion=" + ubicacion + '}';
    }

    
}
