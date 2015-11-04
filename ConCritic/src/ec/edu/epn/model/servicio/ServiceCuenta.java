package ec.edu.epn.model.servicio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.jpa.Usuario;

public class ServiceCuenta {

	public void registrarUsuario(UsuarioDTO usrDTO) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Usuario usr = new Usuario();
		usr.setAdminusr(usrDTO.isAdmin());
		usr.setApellidousr(usrDTO.getApellido());
		usr.setEmailusr(usrDTO.getEmail());
		usr.setEstadousr(usrDTO.isEstado());
		usr.setFechanacimientousr(usrDTO.getFechaNacimiento());
		usr.setNombreusr(usrDTO.getNombre());
		usr.setPaisusr(usrDTO.getPais());
		usr.setPasswordusr(usrDTO.getPassword());

		entitymanager.persist(usr);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}

	public UsuarioDTO buscarUsuario(String email, String password) {
		UsuarioDTO usrDTO = new UsuarioDTO();
		Usuario usr = new Usuario();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery(
				"Select u from Usuario u where u.estadousr='true' and u.emailusr=:iEmail and u.passwordusr=:iPassword");
		query.setParameter("iEmail", email);
		query.setParameter("iPassword", password);
		@SuppressWarnings("unchecked")
		List<Usuario> listaUsuariosActivos = query.getResultList();
		em.close();
		emf.close();
		if (listaUsuariosActivos.equals(null)) {
			listaUsuariosActivos = new ArrayList<Usuario>();
		}
		if(listaUsuariosActivos.isEmpty()==true){
			usrDTO=null;
			return usrDTO;
		}
		usr = listaUsuariosActivos.get(0);
		usrDTO.setNombre(usr.getNombreusr());
		usrDTO.setApellido(usr.getApellidousr());
		usrDTO.setEmail(usr.getEmailusr());
		usrDTO.setPassword(usr.getPasswordusr());
		usrDTO.setPais(usr.getPaisusr());
		usrDTO.setEstado(usr.isEstadousr());
		usrDTO.setAdmin(usr.isAdminusr());
		usrDTO.setFechaNacimiento(usr.getFechanacimientousr());
		return usrDTO;
	}

}
