package ec.edu.epn.model.servicio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ec.edu.epn.model.dto.CategoriaDTO;
import ec.edu.epn.model.dto.ItemDTO;
import ec.edu.epn.model.dto.ReviewDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.jpa.Categoria;
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


	public ReviewDTO buscarReviewByUser(UsuarioDTO usrDTO) {

		ReviewDTO revDTO = new ReviewDTO();
		Review rev= new Review();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(
				"Select r from Review r where r.usuario.emailusr=:iEmail");
		query.setParameter("iEmail", usrDTO.getEmail());
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
		rev=listaReviews.get(0);
		revDTO.setCalificacion(rev.getCalificacionreview());
		revDTO.setComentario(rev.getComentarioreview());
		revDTO.setFecha(rev.getFechareview());
		revDTO.setId(rev.getIdreview());
		ServiceItem si= new ServiceItem();
		ItemDTO itmDTO= si.buscarItem(rev.getIdreview());
		revDTO.setItem(itmDTO);
		revDTO.setTitulo(rev.getTituloreview());
		revDTO.setUsuario(usrDTO);
		return revDTO;
	}
	public List<ReviewDTO> buscarReviewsByItem(ItemDTO itmDTO) {

		List<ReviewDTO> listaReviewsDTO= new ArrayList<ReviewDTO>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(
				"Select r from Review r where r.item.iditem=:iIdItem");
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
		for(Review rev:listaReviews){
			ReviewDTO revDTO = new ReviewDTO();
			revDTO.setCalificacion(rev.getCalificacionreview());
			revDTO.setComentario(rev.getComentarioreview());
			revDTO.setFecha(rev.getFechareview());
			revDTO.setId(rev.getIdreview());
			revDTO.setItem(itmDTO);
			revDTO.setTitulo(rev.getTituloreview());
			ServiceCuenta sc= new ServiceCuenta();
			revDTO.setUsuario(sc.buscarUsuarioByEmail(rev.getUsuario().getEmailusr()));
			listaReviewsDTO.add(revDTO);
		}
		return listaReviewsDTO;
	}



}
