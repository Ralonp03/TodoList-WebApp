/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Menu;
import modelo.Usuario;

/**
 *
 * @author Ricardo
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> implements MenuFacadeLocal {

    @PersistenceContext(unitName = "TareasTA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }
    
    @Override
    public List<Menu> obtenerMenusUsuario(Usuario us){
        
        int idRol = us.getIdRol().getIdRol();
        List<Menu> listaMenus = this.findAll();
        List<Menu> lista = new ArrayList<Menu>();
        //Arraylist??
        for(Menu m : listaMenus){
            
            if(m.getIdRol().getIdRol() == idRol){
                lista.add(m);
            }
            
        }
        return lista;
    }
    
}
