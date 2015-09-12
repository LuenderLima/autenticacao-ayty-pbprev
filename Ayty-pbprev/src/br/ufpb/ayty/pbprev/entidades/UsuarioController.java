package br.ufpb.ayty.pbprev.entidades;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ufpb.ayty.pbprev.dao.UsuarioJpaController;

public class UsuarioController {
	
	private static UsuarioController singleton;
	private String unidadeDeArmazenamento;
	
	private UsuarioController() {
		this.unidadeDeArmazenamento = "aytypbprevbd";
	}
	
	public static UsuarioController getInstance() {
		if(singleton == null) {
			singleton = new UsuarioController();
		}
		return singleton;
	}
	
	public void cadastrarUsuario(Usuario usuario) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.unidadeDeArmazenamento);
        UsuarioJpaController ujc = new UsuarioJpaController(emf);

        try {
            ujc.create(usuario);

        } finally {
            emf.close();
        }

    }
	
	public boolean validarUsuario(String login, String senha) throws UsuarioInexistenteException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(this.unidadeDeArmazenamento);
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        boolean resposta;

        try {
            resposta = ujc.validarUsuario(login, senha);

        } finally {
            emf.close();
        }
        
        return resposta;
    }
}
