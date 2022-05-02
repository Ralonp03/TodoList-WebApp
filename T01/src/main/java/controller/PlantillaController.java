/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
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
public class PlantillaController implements Serializable {

    
    public void verificarYmostrar(){
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        try{

        if(user == null){
            String str =  FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(str+"/faces/publico/accesonoautorizado.xhtml");
        }

        }catch(Exception e){
            System.out.println("[Plantilla Controller] : "+e.getMessage());
        }

    }

}