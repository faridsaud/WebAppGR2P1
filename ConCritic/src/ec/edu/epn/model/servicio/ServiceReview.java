package ec.edu.epn.model.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ec.edu.epn.model.dto.ItemDTO;
import ec.edu.epn.model.dto.ReviewDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.jpa.Item;
import ec.edu.epn.model.jpa.Review;
import ec.edu.epn.model.jpa.Usuario;

@Path("/Review/")
@Produces("application/json")
@Consumes("application/json")
public class ServiceReview {

	@POST
	@Path("/")
	public void registrarReview(JsonObject jsonObject) {
		/*requiere id del usuario e id del item*/
		System.out.println(jsonObject);
		ReviewDTO revDTO= new ReviewDTO(jsonObject);
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Review rev = new Review();
		rev.setCalificacionreview(revDTO.getCalificacion());
		rev.setComentarioreview(revDTO.getComentario());
		rev.setFechareview(revDTO.getFecha());
		Item itm = entitymanager.find(Item.class, revDTO.getItem().getId());
		rev.setItem(itm);
		Usuario usr = entitymanager.find(Usuario.class, revDTO.getUsuario().getEmail());
		rev.setUsuario(usr);
		rev.setTituloreview(revDTO.getTitulo());
		itm.setCalificacionitem(((itm.getCalificacionitem() * itm.getNumvotositem()) + (revDTO.getCalificacion()))
				/ (itm.getNumvotositem() + 1));
		itm.setNumvotositem(itm.getNumvotositem() + 1);
		entitymanager.persist(rev);
		entitymanager.persist(itm);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	@PUT
	@Path("/")
	public void actualizarReview(JsonObject jsonObject) {
		ReviewDTO revDTOInicial= new ReviewDTO(jsonObject.getJsonObject("revDTOInicial"));
		ReviewDTO revDTOFinal= new ReviewDTO(jsonObject.getJsonObject("revDTOFinal"));

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery(
				"UPDATE Review r  SET r.comentarioreview=:iComentario, r.calificacionreview=:iCalificacion, r.tituloreview=:iTitulo, r.fechareview=:iFecha WHERE r.idreview=:iIdReview");
		query.setParameter("iComentario", revDTOFinal.getComentario());
		query.setParameter("iCalificacion", revDTOFinal.getCalificacion());
		query.setParameter("iTitulo", revDTOFinal.getTitulo());
		query.setParameter("iFecha", new Date());
		query.setParameter("iIdReview", revDTOInicial.getId());
		query.executeUpdate();

		Item itm = em.find(Item.class, revDTOInicial.getItem().getId());
		itm.setCalificacionitem((itm.getCalificacionitem()*itm.getNumvotositem()+revDTOFinal.getCalificacion()-revDTOInicial.getCalificacion())/itm.getNumvotositem());
		em.persist(itm);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	@POST
	@Path("/BuscarByUsuarioItem")
	public ReviewDTO buscarReviewByUserByItem(JsonObject jsonObject) {
		UsuarioDTO usrDTO= new UsuarioDTO();
		usrDTO.setEmail(jsonObject.getJsonObject("usuario").getString("email"));
		ItemDTO itmDTO= new ItemDTO();
		itmDTO.setId(jsonObject.getJsonObject("item").getInt("id"));
		ReviewDTO revDTO = new ReviewDTO();
		Review rev = new Review();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createQuery("Select r from Review r where r.usuario.emailusr=:iEmail AND r.item.iditem=:iIdItem");
		query.setParameter("iEmail", usrDTO.getEmail());
		query.setParameter("iIdItem", itmDTO.getId());
		System.out.println(itmDTO.getId() + "" + usrDTO.getEmail());
		@SuppressWarnings("unchecked")
		List<Review> listaReviews = query.getResultList();
		em.close();
		emf.close();
		if (listaReviews.equals(null)) {
			listaReviews = new ArrayList<Review>();
		}
		if (listaReviews.isEmpty() == true) {
			revDTO = null;
			return revDTO;
		}
		rev = listaReviews.get(0);
		revDTO.setCalificacion(rev.getCalificacionreview());
		revDTO.setComentario(rev.getComentarioreview());
		revDTO.setFecha(rev.getFechareview());
		revDTO.setId(rev.getIdreview());
		revDTO.setItem(itmDTO);
		revDTO.setTitulo(rev.getTituloreview());
		revDTO.setUsuario(usrDTO);
		return revDTO;
	}

	@GET
	@Path("/buscar/{idItem}")
	public List<ReviewDTO> buscarReviewsByItem(@PathParam("idItem") int idItem) {
		
		List<ReviewDTO> listaReviewsDTO = new ArrayList<ReviewDTO>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select r from Review r where r.item.iditem=:iIdItem");
		query.setParameter("iIdItem", idItem);
		@SuppressWarnings("unchecked")
		List<Review> listaReviews = query.getResultList();
		em.close();
		emf.close();
		
		if (listaReviews.equals(null)) {
			listaReviews = new ArrayList<Review>();
		}
		if (listaReviews.isEmpty() == true) {
			return listaReviewsDTO;
		}
		for (Review rev : listaReviews) {
			ReviewDTO revDTO = new ReviewDTO();
			revDTO.setCalificacion(rev.getCalificacionreview());
			revDTO.setComentario(rev.getComentarioreview());
			revDTO.setFecha(rev.getFechareview());
			revDTO.setId(rev.getIdreview());
			revDTO.setTitulo(rev.getTituloreview());
			ServiceCuenta sc = new ServiceCuenta();
			revDTO.setUsuario(sc.buscarUsuarioByEmail(rev.getUsuario().getEmailusr()));
			listaReviewsDTO.add(revDTO);
		}
		return listaReviewsDTO;
	}

	@GET
	@Path("/buscarLikeItem/{nombreItem}")
	public List<ReviewDTO> buscarReviewsLikeItem(@PathParam("nombreItem") String nombreItem) {

		List<ReviewDTO> listaReviewsDTO = new ArrayList<ReviewDTO>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select r from Review r where r.item.nombreitem LIKE :iNombreItem");
		query.setParameter("iNombreItem", "%" + nombreItem + "%");
		@SuppressWarnings("unchecked")
		List<Review> listaReviews = query.getResultList();
		em.close();
		emf.close();
		if (listaReviews.equals(null)) {
			listaReviews = new ArrayList<Review>();
		}
		if (listaReviews.isEmpty() == true) {
			return listaReviewsDTO;
		}
		for (Review rev : listaReviews) {
			ReviewDTO revDTO = new ReviewDTO();
			revDTO.setCalificacion(rev.getCalificacionreview());
			revDTO.setComentario(rev.getComentarioreview());
			revDTO.setFecha(rev.getFechareview());
			revDTO.setId(rev.getIdreview());
			ServiceItem si = new ServiceItem();
			revDTO.setItem(si.buscarItem(rev.getItem().getIditem()));
			revDTO.setTitulo(rev.getTituloreview());
			ServiceCuenta sc = new ServiceCuenta();
			revDTO.setUsuario(sc.buscarUsuarioByEmail(rev.getUsuario().getEmailusr()));
			listaReviewsDTO.add(revDTO);
		}
		return listaReviewsDTO;
	}
	@GET
	@Path("/buscarLikeItem/{nombreItem}/{email}")
	public List<ReviewDTO> buscarReviewsLikeItemUsuario(@PathParam("nombreItem") String nombreItem, @PathParam("email") String email) {
		if(nombreItem==null)
			nombreItem="";
		if(email==null)
			email="";
		List<ReviewDTO> listaReviewsDTO = new ArrayList<ReviewDTO>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select r from Review r where r.item.nombreitem LIKE :iNombreItem and r.usuario.emailusr=:iEmail ");
		query.setParameter("iNombreItem", "%" + nombreItem + "%");
		query.setParameter("iEmail", email );
		@SuppressWarnings("unchecked")
		List<Review> listaReviews = query.getResultList();
		em.close();
		emf.close();
		if (listaReviews.equals(null)) {
			listaReviews = new ArrayList<Review>();
		}
		if (listaReviews.isEmpty() == true) {
			return listaReviewsDTO;
		}
		for (Review rev : listaReviews) {
			ReviewDTO revDTO = new ReviewDTO();
			revDTO.setCalificacion(rev.getCalificacionreview());
			revDTO.setComentario(rev.getComentarioreview());
			revDTO.setFecha(rev.getFechareview());
			revDTO.setId(rev.getIdreview());
			ServiceItem si = new ServiceItem();
			revDTO.setItem(si.buscarItem(rev.getItem().getIditem()));
			revDTO.setTitulo(rev.getTituloreview());
			ServiceCuenta sc = new ServiceCuenta();
			revDTO.setUsuario(sc.buscarUsuarioByEmail(rev.getUsuario().getEmailusr()));
			listaReviewsDTO.add(revDTO);
		}
		return listaReviewsDTO;
	}

	@GET
	@Path("/buscarByUser/{emailUsuario}")
	public List<ReviewDTO> buscarReviewsByUsr(@PathParam("emailUsuario") String emailUsuario) {

		List<ReviewDTO> listaReviewsDTO = new ArrayList<ReviewDTO>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select r from Review r where r.usuario.emailusr=:iEmail");
		query.setParameter("iEmail", emailUsuario);
		@SuppressWarnings("unchecked")
		List<Review> listaReviews = query.getResultList();
		em.close();
		emf.close();
		if (listaReviews.equals(null)) {
			listaReviews = new ArrayList<Review>();
		}
		if (listaReviews.isEmpty() == true) {
			return listaReviewsDTO;
		}
		for (Review rev : listaReviews) {
			ReviewDTO revDTO = new ReviewDTO();
			revDTO.setCalificacion(rev.getCalificacionreview());
			revDTO.setComentario(rev.getComentarioreview());
			revDTO.setFecha(rev.getFechareview());
			revDTO.setId(rev.getIdreview());
			ServiceItem si = new ServiceItem();
			revDTO.setItem(si.buscarItem(rev.getItem().getIditem()));
			revDTO.setTitulo(rev.getTituloreview());
			ServiceCuenta sc = new ServiceCuenta();
			revDTO.setUsuario(sc.buscarUsuarioByEmail(rev.getUsuario().getEmailusr()));
			listaReviewsDTO.add(revDTO);
		}

		return listaReviewsDTO;
	}

	@GET
	@Path("/")
	public List<ReviewDTO> buscarAllReviews() {

		List<ReviewDTO> listaReviewsDTO = new ArrayList<ReviewDTO>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select r from Review r");
		@SuppressWarnings("unchecked")
		List<Review> listaReviews = query.getResultList();
		em.close();
		emf.close();
		if (listaReviews.equals(null)) {
			listaReviews = new ArrayList<Review>();
		}
		if (listaReviews.isEmpty() == true) {
			return listaReviewsDTO;
		}
		for (Review rev : listaReviews) {
			ReviewDTO revDTO = new ReviewDTO();
			revDTO.setCalificacion(rev.getCalificacionreview());
			revDTO.setComentario(rev.getComentarioreview());
			revDTO.setFecha(rev.getFechareview());
			revDTO.setId(rev.getIdreview());
			ServiceItem si = new ServiceItem();
			revDTO.setItem(si.buscarItem(rev.getItem().getIditem()));
			System.out.println("Verificando id item" + revDTO.getItem().getId());
			revDTO.setTitulo(rev.getTituloreview());
			ServiceCuenta sc = new ServiceCuenta();
			revDTO.setUsuario(sc.buscarUsuarioByEmail(rev.getUsuario().getEmailusr()));
			System.out.println("Verificando email user" + revDTO.getUsuario().getEmail());
			listaReviewsDTO.add(revDTO);
		}

		return listaReviewsDTO;
	}

	@GET
	@Path("/{id}")
	public ReviewDTO buscarReviewById(@PathParam("id")int id) {

		ReviewDTO revDTO = new ReviewDTO();
		Review rev = new Review();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select r from Review r where r.idreview=:iIdReview");
		query.setParameter("iIdReview", id);
		@SuppressWarnings("unchecked")
		List<Review> listaReviews = query.getResultList();
		em.close();
		emf.close();
		if (listaReviews.equals(null)) {
			listaReviews = new ArrayList<Review>();
		}
		if (listaReviews.isEmpty() == true) {
			revDTO = null;
			return revDTO;
		}
		rev = listaReviews.get(0);
		revDTO.setCalificacion(rev.getCalificacionreview());
		revDTO.setComentario(rev.getComentarioreview());
		revDTO.setFecha(rev.getFechareview());
		revDTO.setId(rev.getIdreview());
		ServiceItem si = new ServiceItem();
		revDTO.setItem(si.buscarItem(rev.getItem().getIditem()));
		revDTO.setTitulo(rev.getTituloreview());
		ServiceCuenta sc = new ServiceCuenta();
		revDTO.setUsuario(sc.buscarUsuarioByEmail(rev.getUsuario().getEmailusr()));
		return revDTO;
	}
	
	@DELETE
	@Path("/{id}")
	public void eliminarReview(@PathParam("id")int id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
		Query query = em.createQuery(
				"DELETE FROM Review r  WHERE r.idreview=:iIdReview");
		query.setParameter("iIdReview", id);

		ServiceReview sr=new ServiceReview();
		ReviewDTO revDTO=sr.buscarReviewById(id);
		Item itm = em.find(Item.class, revDTO.getItem().getId());
		itm.setCalificacionitem((itm.getCalificacionitem()*itm.getNumvotositem()-revDTO.getCalificacion())/(itm.getNumvotositem()-1));
		em.persist(itm);
		query.executeUpdate();
		em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		em.close();
		emf.close();
	}

}
