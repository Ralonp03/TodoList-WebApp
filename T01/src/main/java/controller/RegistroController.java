/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.RolFacadeLocal;
import EJB.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Persona;
import modelo.Rol;
import modelo.Usuario;

/**
 *
 * @author Ricardo
 */
@Named
@ViewScoped
public class RegistroController implements Serializable{
    
    private Usuario usuario;
    private Persona persona;
    //private Rol rol;
    //private List<Rol> listaRoles;


   //@EJB
    //private RolFacadeLocal rolEJB;
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
        persona = new Persona();
        //rol = new Rol();
        //listaRoles = rolEJB.findAll();
    }
    public void atras(){
        String str =  FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(str+"/faces/index.xhtml");
        } catch (IOException ex) {
            System.out.println("[RegistroController]: "+ex.getMessage());
        }
    }
    public void create(){
        
        try{
            Rol rol = new Rol();
            rol.setDescripcion("Usuario Gratis");
            rol.setIdRol(2);
            rol.setTipoUsuario('G');
            usuario.setIdRol(rol);
            usuario.setIdPersona(persona);
            //usuario.setEstado(true);
            //usuario.setUltimaConexion(null);
            if(usuarioEJB.mismoCorreo(usuario.getCorreo())){
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Ya hay un usuario con este correo.",""));
  
            }else{
            usuarioEJB.create(usuario);
            String str =  FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario registrado con exito",""));
            FacesContext.getCurrentInstance().getExternalContext().redirect(str+"/faces/index.xhtml");
            }        
            
        }catch(Exception e){
        System.out.println("Error en "+ e.getMessage());
        
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    
}
