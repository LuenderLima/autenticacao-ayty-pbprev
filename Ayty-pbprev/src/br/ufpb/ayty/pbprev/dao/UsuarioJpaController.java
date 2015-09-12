package br.ufpb.ayty.pbprev.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufpb.ayty.pbprev.entidades.Usuario;
import br.ufpb.ayty.pbprev.entidades.UsuarioInexistenteException;

public class UsuarioJpaController {
	
	private EntityManagerFactory emf = null;

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {

            em = getEntityManager();
            em.getTransaction().begin();

            em.persist(usuario);
            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public boolean validarUsuario(String login, String senha) throws UsuarioInexistenteException {
        EntityManager em = null;

        try {
            em = getEntityManager();
            
            Usuario result = pesquisarUsuarioPorLogin(login);
            
            System.out.println("(validarUsuario) usuario pesquisado pelo login: "+result.getLogin());
            
            if (result.getLogin().equals(login) && result.getSenha().equals(senha)){
                return true;
            }
            
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return false;
    }
    
    public Usuario pesquisarUsuarioPorLogin(String login) throws UsuarioInexistenteException {
        EntityManager em = null;
        try {
            em = getEntityManager();

            if (!existeUsuario(login)) {
                throw new UsuarioInexistenteException("Usu�rio de login " + login + " n�o est� cadastrado!");
            }

            return em.find(Usuario.class, login);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    }
    
    public boolean existeUsuario(String login) {
        Object result = null;
        EntityManager em = null;

        try {
            em = getEntityManager();
            Query query = em.createQuery("SELECT 1 FROM Usuario WHERE login = ?");
            query.setParameter(1, login);
            query.setMaxResults(1);

            result = query.getSingleResult();
        } catch (NoResultException e) {
            result = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return (result != null);
    }
}
