package ec.edu.epn.model.servicio;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.jpa.Usuario;

@Path("/Usuario/")
@Produces("application/json")
public class ServiceCuenta {

	@POST
	@Path("/")
	@Consumes("application/json")
	public void registrarUsuario(JsonObject JsonRequest) {
		UsuarioDTO usrDTO = new UsuarioDTO(JsonRequest);
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

	@POST
	@Path("/buscar")
	@Consumes("application/json")
	public UsuarioDTO buscarUsuario(JsonObject jsonObject) {
		String password= jsonObject.getString("password");
		String email=jsonObject.getString("email");
		System.out.println("Email+Password" + email + password);
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
		if (listaUsuariosActivos.isEmpty() == true) {
			usrDTO = null;
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

	@POST
	@Path("/listar")
	@Consumes("application/json")
	public List<UsuarioDTO> listarUsuarios(JsonObject jsonObject) {
		UsuarioDTO usrDTO = new UsuarioDTO(jsonObject);
		String email="";
		try{
			email = jsonObject.getString("emailBuscar");
				
		}catch(Exception e){
			email="";
		}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = null;
		System.out.println(usrDTO+email);
		if (usrDTO.isAdmin() == true) {
			if (email.equals("")) {
				query = em.createQuery("Select u from Usuario u");
			} else {
				query = em.createQuery("Select u from Usuario u where u.emailusr LIKE :iEmail");
				query.setParameter("iEmail", "%" + email + "%");
			}
		}
		if ((usrDTO.isAdmin() == false)) {
			query = em.createQuery(
					"Select u from Usuario u where u.estadousr='true' and u.emailusr=:iEmail and u.passwordusr=:iPassword");
			query.setParameter("iEmail", usrDTO.getEmail());
			query.setParameter("iPassword", usrDTO.getPassword());

		}
		@SuppressWarnings("unchecked")
		List<Usuario> listaUsuariosActivos = query.getResultList();
		List<UsuarioDTO> listaUsuariosActivosDTO = new ArrayList<UsuarioDTO>();
		em.close();
		emf.close();
		if (listaUsuariosActivos.equals(null)) {
			listaUsuariosActivos = new ArrayList<Usuario>();
		} else {
			for (Usuario usr : listaUsuariosActivos) {
				UsuarioDTO usrDTOOutput = new UsuarioDTO();
				usrDTOOutput.setNombre(usr.getNombreusr());
				usrDTOOutput.setApellido(usr.getApellidousr());
				usrDTOOutput.setEmail(usr.getEmailusr());
				usrDTOOutput.setPassword(usr.getPasswordusr());
				usrDTOOutput.setPais(usr.getPaisusr());
				usrDTOOutput.setEstado(usr.isEstadousr());
				usrDTOOutput.setAdmin(usr.isAdminusr());
				usrDTOOutput.setFechaNacimiento(usr.getFechanacimientousr());
				listaUsuariosActivosDTO.add(usrDTOOutput);
			}
		}

		return listaUsuariosActivosDTO;
	}

	@PUT
	@Path("/")
	@Consumes("application/json")
	public void actualizarUsuario(JsonObject jsonObject) {
		UsuarioDTO usrDTOInicial = new UsuarioDTO(jsonObject.getJsonObject("usrDTOInicial"));
		UsuarioDTO usrDTOFinal = new UsuarioDTO(jsonObject.getJsonObject("usrDTOFinal"));
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery(
				"UPDATE Usuario u  SET u.nombreusr=:iNombre, u.apellidousr=:iApellido, u.passwordusr=:iPassword, u.fechanacimientousr=:iFechaNac, u.paisusr=:iPais WHERE u.emailusr=:iEmail");
		query.setParameter("iNombre", usrDTOFinal.getNombre());
		query.setParameter("iApellido", usrDTOFinal.getApellido());
		query.setParameter("iPassword", usrDTOFinal.getPassword());
		query.setParameter("iFechaNac", usrDTOFinal.getFechaNacimiento());
		query.setParameter("iPais", usrDTOFinal.getPais());
		query.setParameter("iEmail", usrDTOInicial.getEmail());
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		emf.close();	
	}

	@DELETE
	@Path("/{email}")
	@Consumes("application/json")
	public void eliminarUsuario(@PathParam("email") String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("DELETE FROM Usuario u  WHERE u.emailusr=:iEmail");
			query.setParameter("iEmail", email);
			query.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
	}

	@GET
	@Path("/{email}")
	public UsuarioDTO buscarUsuarioByEmail(@PathParam("email")String email) {
		UsuarioDTO usrDTO = new UsuarioDTO();
		Usuario usr = new Usuario();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("Select u from Usuario u where u.emailusr=:iEmail");
		query.setParameter("iEmail", email);
		@SuppressWarnings("unchecked")
		List<Usuario> listaUsuariosActivos = query.getResultList();
		em.close();
		emf.close();
		if (listaUsuariosActivos.equals(null)) {
			listaUsuariosActivos = new ArrayList<Usuario>();
		}
		if (listaUsuariosActivos.isEmpty() == true) {
			usrDTO = null;
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
