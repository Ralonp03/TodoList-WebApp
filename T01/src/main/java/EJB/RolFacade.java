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
import modelo.Rol;

/**
 *
 * @author Ricardo
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> implements RolFacadeLocal {

    @PersistenceContext(unitName = "TareasTA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }
    
    public List<Rol> findAllNotMine(Rol rol){
        List<Rol> lista = this.findAll();
        List<Rol> l = new ArrayList<Rol>();
        for(Rol r : lista){
            if(r.getIdRol() != rol.getIdRol()){
                l.add(r);
            }
        }
        return l;
        
        
        
    }
    
}
