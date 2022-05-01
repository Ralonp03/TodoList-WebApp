/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name="Tareas")
public class Tarea {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idTarea;
    
    @Column(name="Contenido")
    private String contenido;
    
    @Temporal(TemporalType.DATE)
    @Column(name="FechaVencimiento")
    private Date fechaVencimiento;
    
    @Column(name="Importancia")
    private boolean importancia;
    
    @JoinColumn(name="idPersona")
    @ManyToOne
    private Persona idPersona;
    
    @JoinColumn(name="idCategoria")
    @ManyToOne
    private Categoria idCategoria;
    
    @JoinColumn(name="idPrioridad")
    @OneToOne(cascade=CascadeType.PERSIST)
    private Prioridad idPrioridad;
    
    
}
