/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.PersonaFacadeLocal;
import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.List;
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
public class ModificarUsuarioController implements Serializable {


@EJB
private UsuarioFacadeLocal usuarioEJB;

@EJB
private PersonaFacadeLocal personaEJB;

private Usuario usuario;
private List<Usuario> listaUsuarios;
private String accion;
private Persona persona;

@PostConstruct
    public void init(){
        listaUsuarios = usuarioEJB.findAll();
        usuario = new Usuario();
    }
    
    
    public void modificarUsuario(Usuario sus){
        usuario = sus;
        this.accion = "M";
        
    }
    
    public void eliminarUsuario(Usuario sus){
        usuario = sus;
        this.accion = "E";
    }
    
    public void modificar(){
        try{
            String miCorreo = usuario.getCorreo();
            if(!usuarioEJB.mismoCorreoDistintoMio(usuario)){
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Ya hay un usuario con este correo.",""));
              }else{
            personaEJB.edit(usuario.getIdPersona());
            usuarioEJB.edit(usuario);
            listaUsuarios = usuarioEJB.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario modificado con exito",""));

            }
        }catch(Exception e){
            System.out.println("ModificaUSUAIROcONTROLLER"+e.getStackTrace());
        }
    }
    
    public void eliminar(Usuario sus){
      try{  
                        Persona per = sus.getIdPersona();
            usuarioEJB.remove(sus);
            personaEJB.remove(per);
            listaUsuarios = usuarioEJB.findAll();
             FacesMessage msg = new FacesMessage("Usuario "+sus.getIdPersona().getNombre()+" eliminado con exito.","");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }catch(Exception e){
            System.out.println("ModificaUSUAIROcONTROLLER"+e.getMessage());
        }  
    }
    
    
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
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
