/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.PersonaFacadeLocal;
import EJB.TareaFacadeLocal;
import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Persona;
import modelo.Usuario;

/**
 *
 * @author Ricardo
 */
@Named
@ViewScoped
public class ModificaruController implements Serializable {
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @EJB
    private PersonaFacadeLocal personaEJB;
    
    @EJB
    private TareaFacadeLocal tareaEJB;
    
    private Usuario usuario;
    
    
    @PostConstruct
    public void init(){
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }
    
    public void modificar(){
        
        try{
            String miCorreo = usuario.getCorreo();
            if(!usuarioEJB.mismoCorreoDistintoMio(usuario)){
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Ya hay un usuario con este correo.",""));

              }else{
           
            personaEJB.edit(usuario.getIdPersona());
            usuarioEJB.edit(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario modificado con exito",""));
            }
        }catch(Exception e){
            System.out.println("Modificaucontroller"+e.getStackTrace());
        }
        
    }
    
    public void elimiar(){
        try{  
            tareaEJB.removeTareasUser(usuario);
            Persona per = usuario.getIdPersona();
            usuarioEJB.remove(usuario);
            personaEJB.remove(per);
            FacesMessage msg = new FacesMessage("Usuario "+usuario.getIdPersona().getNombre()+" eliminado con exito.","");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            String str =  FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

            FacesContext.getCurrentInstance().getExternalContext().redirect(str+"/faces/index.xhtml");
        }catch(Exception e){
            System.out.println("Modificaucontroller"+e.getMessage());
        }  
        
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
    
}
