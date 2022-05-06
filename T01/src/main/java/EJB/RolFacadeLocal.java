/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Menu;
import modelo.Rol;
import modelo.Usuario;

/**
 *
 * @author Ricardo
 */
@Local
public interface RolFacadeLocal {

    void create(Rol rol);

    void edit(Rol rol);

    void remove(Rol rol);

    Rol find(Object id);
    
    List<Rol> findAllNotMine(Rol rol);
    
    List<Rol> findAll();

    List<Rol> findRange(int[] range);

    int count();
    
}
