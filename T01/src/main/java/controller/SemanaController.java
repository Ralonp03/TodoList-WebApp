/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.CategoriaFacadeLocal;
import EJB.PrioridadFacadeLocal;
import EJB.TareaFacadeLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Categoria;
import modelo.Prioridad;
import modelo.Tarea;
import modelo.Usuario;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Ricardo
 */
@Named
@RequestScoped
public class SemanaController implements Serializable{


    
@EJB
private TareaFacadeLocal tareaEJB;    

@EJB
private PrioridadFacadeLocal prioridadEJB;

@EJB
private CategoriaFacadeLocal categoriaEJB;
//
@Inject
private Tarea tar;

private Prioridad prioridad;
private List<Prioridad> listaPrioridades;

private List<Tarea> listaTareas;
private List<Tarea> tareas;
private List<Categoria> listaCategorias;

@PostConstruct
public void init(){
listaTareas = this.tareaEJB.findAllThisWeek();
listaPrioridades = this.prioridadEJB.findAll();
prioridad = new Prioridad();
listaCategorias = categoriaEJB.findAll();

 }

    public void onRowEdit(RowEditEvent<Tarea> event) {
        Tarea tt = event.getObject();
        tareaEJB.edit(tt);
        this.listaTareas = tareaEJB.findAllThisWeek();
        FacesMessage msg = new FacesMessage("Tarea actualizada","");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        }

        public void onRowCancel(RowEditEvent<Tarea> event) {
            FacesMessage msg = new FacesMessage("Tarea no actualizada", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    public void eliminarTarea(Tarea tat){
        try{
            
           this.tareaEJB.remove(tat);
           this.listaTareas = tareaEJB.findAllThisWeek();
            
        }catch(Exception e){
            System.out.println("tareasController: "+e.getMessage());
        }   

    }
    public void crearTarea(){
        
        try{
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();  

        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
        if(tar.getFechaVencimiento() == null){
            tar.setFechaVencimiento(date);
        }
        if(prioridad.getIdPrioridad() != 0){
        for(Prioridad p : listaPrioridades){
            if(p.getIdPrioridad() == prioridad.getIdPrioridad()){
                prioridad.setColor(p.getColor());
                prioridad.setNombre(p.getNombre());
            }
        }
        }else{
          for(Prioridad p : listaPrioridades){
            if(p.getIdPrioridad() == 1){
                prioridad.setColor(p.getColor());
                prioridad.setNombre(p.getNombre());
                prioridad.setIdPrioridad(1);
            }
        }    
        }
       
        tar.setIdPrioridad(prioridad);
        
        
        //TODO
       Categoria cat = new Categoria();
        for(Categoria c : listaCategorias){
            if(c.getIdCategoria() == 1){
                    cat.setEstado(c.isEstado());
                    cat.setIdCategoria(c.getIdCategoria());
                    cat.setNombre(c.getNombre());
            }
        }
        tar.setIdCategoria(cat);
        tar.setIdPersona(us.getIdPersona());
        tareaEJB.create(tar);
        //TODO
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarea registrada", us.getIdPersona().getNombre()));
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", true);
            
        }catch(Exception e){
            System.out.println("[TareasController]: "+e.getMessage());
        }
        
        
        
    }
    

    public TareaFacadeLocal getTareaEJB() {
        return tareaEJB;
    }

    public void setTareaEJB(TareaFacadeLocal tareaEJB) {
        this.tareaEJB = tareaEJB;
    }

    public Tarea getTar() {
        return tar;
    }

    public void setTar(Tarea tar) {
        this.tar = tar;
    }

    public List<Tarea> getListaTareas() {
        return listaTareas;
    }

    public void setListaTareas(List<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public List<Prioridad> getListaPrioridades() {
        return listaPrioridades;
    }

    public void setListaPrioridades(List<Prioridad> listaPrioridades) {
        this.listaPrioridades = listaPrioridades;
    }


    
    
}