/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Tarea;
import modelo.Usuario;

/**
 *
 * @author Ricardo
 */
@Local
public interface TareaFacadeLocal {

    void create(Tarea tarea);

    void edit(Tarea tarea);

    void remove(Tarea tarea);

    void removeTareasUser(Usuario usuario);
    
    Tarea find(Object id);

    List<Tarea> findAll();
    
    List<Tarea> findAllFiltrado();
    
    List<Tarea> findAllImportancia();
    
    List<Tarea> findAllToday();
    
    List<Tarea> findAllThisWeek();

    List<Tarea> findRange(int[] range);

    int count();
    
}
