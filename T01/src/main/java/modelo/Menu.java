/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name="menus")
public class Menu {
    
     //unicamente para las claves primarias
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idMenu;
    
    //para el resto de columnas, renombrar nombreCategoria
    @Column(name="Nombre")
    private String nombre;
    
    @Column(name="Tipo")
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    
    @Column(name="Estado")
    private boolean estado;
    
    @JoinColumn(name="idRol")
    @ManyToOne
    private Rol idRol;
        
    @JoinColumn(name="idMenu_Menu")
    @ManyToOne
    private Menu idMenu_Menu;
    
    @Column(name="url")
    private String url;

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public Menu getIdMenu_Menu() {
        return idMenu_Menu;
    }

    public void setIdMenu_Menu(Menu idMenu_Menu) {
        this.idMenu_Menu = idMenu_Menu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    
}