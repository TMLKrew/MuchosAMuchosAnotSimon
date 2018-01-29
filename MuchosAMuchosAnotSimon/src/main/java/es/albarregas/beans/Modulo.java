/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Simon
 */
@Entity
@Table(name = "moduloNaMAnot")
public class Modulo {
     @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column (name = "moduloId")
    private Long idModulo;
    private String denominacion;
    @ManyToMany (fetch=FetchType.EAGER,cascade = {CascadeType.ALL}, mappedBy="modulos")
    private Set<Profesor> profesores;

    public Modulo() {
    }

    public Modulo(Long idModulo, String denominacion, Set<Profesor> profesores) {
        this.idModulo = idModulo;
        this.denominacion = denominacion;
        this.profesores = profesores;
    }

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Set<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(Set<Profesor> profesores) {
        this.profesores = profesores;
    }
    
    
}
