/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.MenuFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Menu;
import modelo.Tipo;
import modelo.Usuario;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Ricardo
 */
@Named
@SessionScoped
public class MenuController implements Serializable {

    
private MenuModel modelo;

private Usuario user;

    
@EJB
private MenuFacadeLocal menuEJB;

    @PostConstruct
    public void init(){
    user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    modelo = new DefaultMenuModel();
    obtenerMenu();
    }
    
    public void destruitSesion(){
        String str =  FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
    try {  
        //FacesContext.getCurrentInstance().getExternalContext().redirect(str+"/faces/index.xhtml");
        FacesContext.getCurrentInstance().getExternalContext().redirect(str+"/faces/index.xhtml");

    } catch (IOException ex) {
       System.out.println("[Menu Controller ] "+ex.getMessage());
    }
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    }
    
    public MenuModel obtenerMenu(){
    
    List<Menu> listaMenus = menuEJB.obtenerMenusUsuario(user);
    List<Integer> usados = new ArrayList<Integer>();
    
    for(Menu m : listaMenus){
        if(m.getTipo() == Tipo.S){
            DefaultSubMenu subMenu = DefaultSubMenu.builder().label(m.getNombre()).build();
            for(Menu mn : listaMenus){
                if((mn.getTipo() == Tipo.I) && (mn.getIdMenu_Menu() != null) && (m.getIdMenu() == mn.getIdMenu_Menu().getIdMenu())){
                    usados.add(mn.getIdMenu());
                    DefaultMenuItem item = DefaultMenuItem.builder().value(mn.getNombre()).url(mn.getUrl()).build();
                    subMenu.getElements().add(item);
                }
            }

            modelo.getElements().add(subMenu);
        }else{
            if(!usados.contains(m.getIdMenu())){
                DefaultMenuItem item = DefaultMenuItem.builder().value(m.getNombre()).url(m.getUrl()).build();
                modelo.getElements().add(item);
            }
           
        }
        
        
    }
            return modelo;
    }
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    public MenuModel getModelo() {
        return modelo;
    }
    
    
    public void setModelo(MenuModel modelo) {
        this.modelo = modelo;
    }

    
}

