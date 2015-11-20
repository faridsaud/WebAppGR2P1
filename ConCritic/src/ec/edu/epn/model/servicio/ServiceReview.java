package ec.edu.epn.model.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ec.edu.epn.model.dto.ItemDTO;
import ec.edu.epn.model.dto.ReviewDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.jpa.Item;
import ec.edu.epn.model.jpa.Review;
import ec.edu.epn.model.jpa.Usuario;

public class ServiceReview {

	public void registrarReview(ReviewDTO revDTO) {

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
	public void actualizarReview(ReviewDTO revDTOInicial, ReviewDTO revDTOFinal) {

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
	

	public ReviewDTO buscarReviewByUserByItem(UsuarioDTO usrDTO, ItemDTO itmDTO) {

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

	public List<ReviewDTO> buscarReviewsByItem(ItemDTO itmDTO) {

		List<ReviewDTO> listaReviewsDTO = new ArrayList<ReviewDTO>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select r from Review r where r.item.iditem=:iIdItem");
		query.setParameter("iIdItem", itmDTO.getId());
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
			revDTO.setItem(itmDTO);
			revDTO.setTitulo(rev.getTituloreview());
			ServiceCuenta sc = new ServiceCuenta();
			revDTO.setUsuario(sc.buscarUsuarioByEmail(rev.getUsuario().getEmailusr()));
			listaReviewsDTO.add(revDTO);
		}
		return listaReviewsDTO;
	}

	public List<ReviewDTO> buscarReviewsLikeItem(ItemDTO itmDTO) {

		List<ReviewDTO> listaReviewsDTO = new ArrayList<ReviewDTO>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select r from Review r where r.item.nombreitem LIKE :iNombreItem");
		query.setParameter("iNombreItem", "%" + itmDTO.getNombre() + "%");
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

	public List<ReviewDTO> buscarReviewsByUsr(UsuarioDTO usrDTO) {

		List<ReviewDTO> listaReviewsDTO = new ArrayList<ReviewDTO>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select r from Review r where r.usuario.emailusr=:iEmail");
		query.setParameter("iEmail", usrDTO.getEmail());
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

	public ReviewDTO buscarReviewById(int id) {

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
	
	public void eliminarReview(int id) {

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
