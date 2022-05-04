/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Tarea;
import modelo.Usuario;

/**
 *
 * @author Ricardo
 */
@Stateless
public class TareaFacade extends AbstractFacade<Tarea> implements TareaFacadeLocal {

    @PersistenceContext(unitName = "TareasTA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TareaFacade() {
        super(Tarea.class);
    }
    
    public List<Tarea> findAllFiltrado(){
    List<Tarea> lista = this.findAll();
    List<Tarea> listaR = new ArrayList<Tarea>();   
    Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

     for(Tarea l:lista){
            if(l.getIdPersona().getIdPersona() == user.getIdPersona().getIdPersona()){
                listaR.add(l);
            }
        }
        return listaR;
    
    }
    
    public List<Tarea> findAllImportancia(){
        List<Tarea> lista = this.findAllFiltrado();
        List<Tarea> listaR = new ArrayList<Tarea>();
        for(Tarea l:lista){
            if(l.isImportancia() == true){
                listaR.add(l);
            }
        }
        
        return listaR;
    }
    
    public  List<Tarea> findAllToday(){
     List<Tarea> lista = this.findAllFiltrado();
     List<Tarea> listaR = new ArrayList<Tarea>();
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
      Date date = new Date();  
      for(Tarea l:lista){
          Instant instant1 = l.getFechaVencimiento().toInstant()
          .truncatedTo(ChronoUnit.DAYS);
           Instant instant2 = date.toInstant()
         .truncatedTo(ChronoUnit.DAYS);

            if(instant1.equals(instant2)){
                listaR.add(l);
            }
        }
        
     return listaR;
 
    }
    
    public List<Tarea> findAllThisWeek(){
     List<Tarea> lista = this.findAllFiltrado();
     List<Tarea> listaR = new ArrayList<Tarea>();
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
      Date date = new Date();  
      for(Tarea l:lista){
          Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(l.getFechaVencimiento());
             Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date);
   
            if(calendar1.get(Calendar.WEEK_OF_MONTH) == calendar2.get(Calendar.WEEK_OF_MONTH)){
                listaR.add(l);
            }
        }
     return listaR;  
    }
    
    
    
}
