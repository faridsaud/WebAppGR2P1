package ec.edu.epn.model.servicio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ec.edu.epn.model.dto.ItemDTO;
import ec.edu.epn.model.dto.MultimediaDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.jpa.Item;
import ec.edu.epn.model.jpa.Multimedia;

public class ServiceMultimedia {

	public void registrarMultimedia(MultimediaDTO mulDTO) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Multimedia mul= new Multimedia();

		int idItem = mulDTO.getItem().getId();
		Item itm = entitymanager.find(Item.class, idItem);
		mul.setItem(itm);
		mul.setPathmultimedia(mulDTO.getPath());
		entitymanager.persist(mul);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	public MultimediaDTO buscarMultimedia(int idMultimedia) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		MultimediaDTO mulDTO = new MultimediaDTO();
		Multimedia mul= em.find(Multimedia.class, idMultimedia);
		if(mul==null){
			mulDTO=null;
		}else{
			mulDTO.setId(idMultimedia);
			ItemDTO itmDTO = new ItemDTO();
			itmDTO.setId(mul.getItem().getIditem());
			mulDTO.setItem(itmDTO);
			mulDTO.setPath(mul.getPathmultimedia());
		}
		return mulDTO;
	}

	public List<MultimediaDTO> listarMultimediasAll() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = null;
			query = em.createQuery(
					"Select m from Multimedia m");
		@SuppressWarnings("unchecked")
		List<Multimedia> listaMultimedias = query.getResultList();
		List<MultimediaDTO> listaMultimediasDTO = new ArrayList<MultimediaDTO>();
		em.close();
		emf.close();
		if (listaMultimedias.equals(null)) {
			listaMultimedias = new ArrayList<Multimedia>();
		} else {
			for (Multimedia mul : listaMultimedias) {
				MultimediaDTO mulDTO=new MultimediaDTO();
				mulDTO.setId(mul.getIdmultimedia());
				mulDTO.setPath(mul.getPathmultimedia());
				listaMultimediasDTO.add(mulDTO);
			}
		}

		return listaMultimediasDTO;
	}
	
	public List<MultimediaDTO> listarMultimediasAllByUsr(UsuarioDTO usr) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = null;
			query = em.createQuery(
					"Select m from Multimedia m where m.item.usuario.emailusr=:iEmail");
			query.setParameter("iEmail",usr.getEmail());
		@SuppressWarnings("unchecked")
		List<Multimedia> listaMultimedias = query.getResultList();
		List<MultimediaDTO> listaMultimediasDTO = new ArrayList<MultimediaDTO>();
		em.close();
		emf.close();
		if (listaMultimedias.equals(null)) {
			listaMultimedias = new ArrayList<Multimedia>();
		} else {
			for (Multimedia mul : listaMultimedias) {
				MultimediaDTO mulDTO=new MultimediaDTO();
				mulDTO.setId(mul.getIdmultimedia());
				mulDTO.setPath(mul.getPathmultimedia());
				listaMultimediasDTO.add(mulDTO);
			}
		}

		return listaMultimediasDTO;
	}
	
	public List<MultimediaDTO> listarMultimediasLike(int idItem) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = null;
			query = em.createQuery(
					"Select m from Multimedia m where m.item.iditem=:iIdItem");
			query.setParameter("iIdItem",idItem);
		@SuppressWarnings("unchecked")
		List<Multimedia> listaMultimedias = query.getResultList();
		List<MultimediaDTO> listaMultimediasDTO = new ArrayList<MultimediaDTO>();
		em.close();
		emf.close();
		if (listaMultimedias.equals(null)) {
			listaMultimedias = new ArrayList<Multimedia>();
		} else {
			for (Multimedia mul : listaMultimedias) {
				MultimediaDTO mulDTO=new MultimediaDTO();
				mulDTO.setId(mul.getIdmultimedia());
				mulDTO.setPath(mul.getPathmultimedia());
				listaMultimediasDTO.add(mulDTO);
			}
		}

		return listaMultimediasDTO;
	}
	
	public List<MultimediaDTO> listarMultimediasLikeIdByUsr(int idItem, UsuarioDTO usr) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = null;
			query = em.createQuery(
					"Select m from Multimedia m where m.item.iditem=:iIdItem AND m.item.usuario.emailusr=:iEmail");
			query.setParameter("iEmail",usr.getEmail());
			query.setParameter("iIdItem",idItem);
		@SuppressWarnings("unchecked")
		List<Multimedia> listaMultimedias = query.getResultList();
		List<MultimediaDTO> listaMultimediasDTO = new ArrayList<MultimediaDTO>();
		em.close();
		emf.close();
		if (listaMultimedias.equals(null)) {
			listaMultimedias = new ArrayList<Multimedia>();
		} else {
			for (Multimedia mul : listaMultimedias) {
				MultimediaDTO mulDTO=new MultimediaDTO();
				mulDTO.setId(mul.getIdmultimedia());
				mulDTO.setPath(mul.getPathmultimedia());
				listaMultimediasDTO.add(mulDTO);
			}
		}

		return listaMultimediasDTO;
	}
	
	public void actualizarMultimedia(MultimediaDTO mulDTOInicial, MultimediaDTO mulDTOFinal) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery(
				"UPDATE Multimedia m SET m.pathmultimedia=:iMultimediaPath WHERE m.idmultimedia=:iIdMultimedia");
		query.setParameter("iMultimediaPath", mulDTOFinal.getPath());
		query.setParameter("iIdMultimedia", mulDTOInicial.getId());
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	public void eliminarMultimedia(MultimediaDTO mulDTO) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
		Query query = em.createQuery(
				"DELETE FROM Multimedia m  WHERE m.idmultimedia=:iIdMultimedia");
		query.setParameter("iIdMultimedia", mulDTO.getId());
		query.executeUpdate();
		em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		em.close();
		emf.close();
	}

}
