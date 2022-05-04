/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Tarea;

/**
 *
 * @author Ricardo
 */
@Local
public interface TareaFacadeLocal {

    void create(Tarea tarea);

    void edit(Tarea tarea);

    void remove(Tarea tarea);

    Tarea find(Object id);

    List<Tarea> findAll();
    
    List<Tarea> findAllImportancia();
    
    List<Tarea> findAllToday();
    
    List<Tarea> findAllThisWeek();

    List<Tarea> findRange(int[] range);

    int count();
    
}
