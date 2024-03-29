/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Menu;
import modelo.Usuario;

/**
 *
 * @author Ricardo
 */
@Local
public interface MenuFacadeLocal {

    void create(Menu menu);

    void edit(Menu menu);

    void remove(Menu menu);
    
    List<Menu> obtenerMenusUsuario(Usuario us);

    Menu find(Object id);

    List<Menu> findAll();

    List<Menu> findRange(int[] range);

    int count();
    
}
