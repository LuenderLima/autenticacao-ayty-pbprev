package br.ufpb.ayty.pbprev.servicos;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import br.ufpb.ayty.pbprev.entidades.Usuario;
import br.ufpb.ayty.pbprev.entidades.UsuarioController;


@Path("/valida")
public class ValidaUsuario {
	
	@POST
	@Path("/validar-usuario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String validaUsuario(String dado) {
		try {
			JSONObject json = new JSONObject(dado);
			if(UsuarioController.getInstance().validarUsuario(json.getString("Login"), json.getString("Senha"))) {
				return "Usuário Válido!";
			}
			
			return "Login e senha não conferem!"; 
			
		} catch (Exception e) { 
			return e.getMessage();
		}
	}
	
	@POST
	@Path("/cadastrar-usuario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String cadastraUsuario(String dado) {
		try {
			JSONObject json;

			json = new JSONObject(dado);

			UsuarioController.getInstance().cadastrarUsuario(new Usuario(json.getString("Login"),
					json.getString("Senha")));

			return "Usuário cadastrado com sucesso!";
			
		} catch (JSONException e) {
			return e.getMessage();
		}
	}
	
}
