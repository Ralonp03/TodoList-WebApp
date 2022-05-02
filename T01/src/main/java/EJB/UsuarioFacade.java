/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Usuario;

/**
 *
 * @author Ricardo
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "TareasTA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario verificarUsuario(Usuario us){
    String consulta = "FROM Usuario u WHERE u.correo=:param1 and u.password=:param2";
    Query query = em.createQuery(consulta);
    query.setParameter("param1", us.getCorreo());
    query.setParameter("param2", us.getPassword());
    List<Usuario> resultado = query.getResultList();
    
    if(resultado.isEmpty()){
        return null;
    } 
    Usuario user = resultado.get(0);
    
    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
        
    return user;
    }
    
}
