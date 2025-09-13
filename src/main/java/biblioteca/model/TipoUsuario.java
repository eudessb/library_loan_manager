package biblioteca.model;

/**
 * Enum que define os tipos de usuários do sistema para controle
 * de permissões e regras de negócio.
 */
public enum TipoUsuario {
	
	/** Usuário com privilégios de administrador. */
	ADMIN, 
	
	/** Usuário comum, como Aluno ou Professor. */
	COMUM
}