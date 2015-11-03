package ec.edu.epn.model.servicio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.jpa.Usuario;

public class ServiceCuenta {
	
	
	
	public void registrarUsuario(UsuarioDTO usrDTO){
		
		EntityManagerFactory emfactory = Persistence.
				createEntityManagerFactory( "ConCritic" );
		EntityManager entitymanager = emfactory.
				createEntityManager( );
		entitymanager.getTransaction( ).begin( );
		
		Usuario usr = new Usuario( ); 
		usr.setAdminusr(usrDTO.isAdmin());
		usr.setApellidousr(usrDTO.getApellido());
		usr.setEmailusr(usrDTO.getEmail());
		usr.setEstadousr(usrDTO.isEstado());
		usr.setFechanacimientousr(usrDTO.getFechaNacimiento());
		usr.setNombreusr(usrDTO.getNombre());
		usr.setPaisusr(usrDTO.getPais());
		usr.setPasswordusr(usrDTO.getPassword());
		
		entitymanager.persist( usr);
		entitymanager.getTransaction( ).commit( );
		
		entitymanager.close( );
		emfactory.close( );
	}

}
