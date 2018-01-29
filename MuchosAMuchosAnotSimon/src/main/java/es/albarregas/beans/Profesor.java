/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Simon
 */
@Entity
@Table(name = "profeorNaMAnot")
public class Profesor implements Serializable{
     @Id
    @GeneratedValue(strategy = IDENTITY)
    
    @Column (name = "profesorId")
    private Long idProfesor;
    private String nombre;
    private String apellidos;
    @ManyToMany(fetch=FetchType.EAGER,cascade={javax.persistence.CascadeType.ALL})
    @JoinTable(name="ProfesorModulo",
            joinColumns = {@JoinColumn(name="profesorId")},
            inverseJoinColumns = {@JoinColumn(name="moduloId")})
    private Set<Modulo> modulos;

    public Profesor() {
    }

    public Profesor(Long idProfesor, String nombre, String apellidos, Set<Modulo> modulos) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.modulos = modulos;
    }

    public Long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Set<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(Set<Modulo> modulos) {
        this.modulos = modulos;
    }

    
    
}
