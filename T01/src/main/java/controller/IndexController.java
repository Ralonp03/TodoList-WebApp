/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuario;

/**
 *
 * @author Ricardo
 */
@Named
@ViewScoped
public class IndexController implements Serializable{
    
    private Usuario usuario;

    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }
    

    public void verificarUsuario(){
    
    Usuario usr = usuarioEJB.verificarUsuario(usuario);
    Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

    if((usr == null) || user == null ) {
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario o contraseña no valido.",""));
    }else{
    
        String str =  FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
        try {
            if(user.getIdRol().getIdRol() == 1){
            FacesContext.getCurrentInstance().getExternalContext().redirect(str+"/faces/privado/administrador/modificarusuario.xhtml");
            }else if(user.getIdRol().getIdRol() == 2){
            FacesContext.getCurrentInstance().getExternalContext().redirect(str+"/faces/privado/usuario/tareas.xhtml");    
            // FacesContext.getCurrentInstance().getExternalContext().redirect(str+"/faces/privado/usuario/inicio.xhtml");    
            }else if(user.getIdRol().getIdRol() == 3){
                            FacesContext.getCurrentInstance().getExternalContext().redirect(str+"/faces/privado/usuarioPro/tareasP.xhtml");    

            }
        } catch (IOException ex) {
            System.out.println("[IndexController]: "+ex.getMessage());
        }
    
         }
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
