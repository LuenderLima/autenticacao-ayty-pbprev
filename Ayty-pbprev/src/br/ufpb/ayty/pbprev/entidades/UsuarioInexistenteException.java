package br.ufpb.ayty.pbprev.entidades;

public class UsuarioInexistenteException extends Exception{
	private static final long serialVersionUID = 1L;

	public UsuarioInexistenteException(String mensagem) {
		super(mensagem);
	}
}
