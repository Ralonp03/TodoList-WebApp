/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Prioridad;

/**
 *
 * @author Ricardo
 */
@Stateless
public class PrioridadFacade extends AbstractFacade<Prioridad> implements PrioridadFacadeLocal {

    @PersistenceContext(unitName = "TareasTA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrioridadFacade() {
        super(Prioridad.class);
    }
    
}
