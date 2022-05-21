/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ricardo
 */

@Entity
@Table(name="categorias")
public class Categoria {
    //para los tipos no primitivos hay que inicializarlos en el conctacto de la base de datos( ej String)
    //para los tipos primitivos no hace falta inicializarlos( int, boolean
    //unicamente para las claves primarias
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idCategoria;
    
    //para el resto de columnas, renombrar nombreCategoria
    @Column(name="Nombre")
    private String nombre;
    
    //estadoCategoria
    @Column(name="Estado")
    private String estado;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
    
    
}
