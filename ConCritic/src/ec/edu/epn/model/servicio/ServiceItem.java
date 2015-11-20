package ec.edu.epn.model.servicio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ec.edu.epn.model.dto.CategoriaDTO;
import ec.edu.epn.model.dto.ItemDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.jpa.Categoria;
import ec.edu.epn.model.jpa.Item;
import ec.edu.epn.model.jpa.Usuario;

public class ServiceItem {

	public void registrarItem(ItemDTO itemDTO) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Item item = new Item();
		// item.setCalificacionitem(itemDTO.getCalificacion());
		item.setNombreitem(itemDTO.getNombre());
		item.setDescripcionitem(itemDTO.getDescripcion());
		// item.setNumvotositem(itemDTO.getNumeroVotos());

		String nombreCategoria = itemDTO.getCategoria().getNombre();
		Categoria cat = entitymanager.find(Categoria.class, nombreCategoria);
		item.setCategoria(cat);

		String emailUsr = itemDTO.getUsuario().getEmail();
		Usuario usr = entitymanager.find(Usuario.class, emailUsr);
		item.setUsuario(usr);

		entitymanager.persist(item);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public List<ItemDTO> listarItemsAll() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select i from Item i");

		@SuppressWarnings("unchecked")
		List<Item> listaItems = query.getResultList();
		List<ItemDTO> listaItemsDTO = new ArrayList<ItemDTO>();
		em.close();
		emf.close();
		if (listaItems.equals(null)) {
			listaItems = new ArrayList<Item>();
		} else {
			for (Item itm : listaItems) {
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setId(itm.getIditem());
				itemDTO.setNombre(itm.getNombreitem());
				itemDTO.setDescripcion(itm.getDescripcionitem());
				itemDTO.setCalificacion(itm.getCalificacionitem());
				listaItemsDTO.add(itemDTO);
			}
		}
		return listaItemsDTO;
	}

	public List<ItemDTO> listarItemsLikeCat(String nombreCat) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select i from Item i where i.categoria.nombrecategoria LIKE :iNombreCategoria");
		query.setParameter("iNombreCategoria", "%" + nombreCat + "%");
		@SuppressWarnings("unchecked")
		List<Item> listaItems = query.getResultList();
		List<ItemDTO> listaItemsDTO = new ArrayList<ItemDTO>();
		em.close();
		emf.close();
		if (listaItems.equals(null)) {
			listaItems = new ArrayList<Item>();
		} else {
			for (Item itm : listaItems) {
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setId(itm.getIditem());
				itemDTO.setNombre(itm.getNombreitem());
				itemDTO.setDescripcion(itm.getDescripcionitem());
				itemDTO.setCalificacion(itm.getCalificacionitem());
				listaItemsDTO.add(itemDTO);
			}
		}
		return listaItemsDTO;
	}

	public List<ItemDTO> listarItemsLikeCatByUsr(String nombreCat,UsuarioDTO usr) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select i from Item i where i.categoria.nombrecategoria LIKE :iNombreCategoria AND i.usuario.emailusr=:iEmail");
		query.setParameter("iNombreCategoria", "%" + nombreCat + "%");
		query.setParameter("iEmail", usr.getEmail());
		@SuppressWarnings("unchecked")
		List<Item> listaItems = query.getResultList();
		List<ItemDTO> listaItemsDTO = new ArrayList<ItemDTO>();
		em.close();
		emf.close();
		if (listaItems.equals(null)) {
			listaItems = new ArrayList<Item>();
		} else {
			for (Item itm : listaItems) {
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setId(itm.getIditem());
				itemDTO.setNombre(itm.getNombreitem());
				itemDTO.setDescripcion(itm.getDescripcionitem());
				itemDTO.setCalificacion(itm.getCalificacionitem());
				listaItemsDTO.add(itemDTO);
			}
		}
		return listaItemsDTO;
	}

	public List<ItemDTO> listarItemsLikeItm(String nombreItm) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select i from Item i where i.nombreitem LIKE :iNombreItem");
		query.setParameter("iNombreItem", "%" + nombreItm + "%");

		@SuppressWarnings("unchecked")
		List<Item> listaItems = query.getResultList();
		List<ItemDTO> listaItemsDTO = new ArrayList<ItemDTO>();
		em.close();
		emf.close();
		if (listaItems.equals(null)) {
			listaItems = new ArrayList<Item>();
		} else {
			for (Item itm : listaItems) {
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setId(itm.getIditem());
				itemDTO.setNombre(itm.getNombreitem());
				itemDTO.setDescripcion(itm.getDescripcionitem());
				itemDTO.setCalificacion(itm.getCalificacionitem());
				listaItemsDTO.add(itemDTO);
			}
		}
		return listaItemsDTO;
	}
	
	public List<ItemDTO> listarItemsLikeItmByUsr(String nombreItm, UsuarioDTO usr) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select i from Item i where i.nombreitem LIKE :iNombreItem AND i.usuario.emailusr=:iEmail");
		query.setParameter("iEmail", usr.getEmail());
		query.setParameter("iNombreItem", "%" + nombreItm + "%");

		@SuppressWarnings("unchecked")
		List<Item> listaItems = query.getResultList();
		List<ItemDTO> listaItemsDTO = new ArrayList<ItemDTO>();
		em.close();
		emf.close();
		if (listaItems.equals(null)) {
			listaItems = new ArrayList<Item>();
		} else {
			for (Item itm : listaItems) {
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setId(itm.getIditem());
				itemDTO.setNombre(itm.getNombreitem());
				itemDTO.setDescripcion(itm.getDescripcionitem());
				itemDTO.setCalificacion(itm.getCalificacionitem());
				listaItemsDTO.add(itemDTO);
			}
		}
		return listaItemsDTO;
	}
	
	public List<ItemDTO> listarItemsByUsr(UsuarioDTO usr) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("Select i from Item i where i.usuario.emailusr=:iEmail");
		query.setParameter("iEmail", usr.getEmail());

		@SuppressWarnings("unchecked")
		List<Item> listaItems = query.getResultList();
		List<ItemDTO> listaItemsDTO = new ArrayList<ItemDTO>();
		em.close();
		emf.close();
		if (listaItems.equals(null)) {
			listaItems = new ArrayList<Item>();
		} else {
			for (Item itm : listaItems) {
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setId(itm.getIditem());
				itemDTO.setNombre(itm.getNombreitem());
				itemDTO.setDescripcion(itm.getDescripcionitem());
				itemDTO.setCalificacion(itm.getCalificacionitem());
				listaItemsDTO.add(itemDTO);
			}
		}
		return listaItemsDTO;
	}

	public List<ItemDTO> listarItemsLike(String nombreItm, String nombreCat) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = null;
		query = em.createQuery(
				"Select i from Item i where i.nombreitem LIKE :iNombreItem AND i.categoria.nombrecategoria LIKE :iNombreCategoria ");
		query.setParameter("iNombreItem", "%" + nombreItm + "%");
		query.setParameter("iNombreCategoria", "%" + nombreCat + "%");
		@SuppressWarnings("unchecked")
		List<Item> listaItems = query.getResultList();
		List<ItemDTO> listaItemsDTO = new ArrayList<ItemDTO>();
		em.close();
		emf.close();
		if (listaItems.equals(null)) {
			listaItems = new ArrayList<Item>();
		} else {
			for (Item itm : listaItems) {
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setId(itm.getIditem());
				itemDTO.setNombre(itm.getNombreitem());
				itemDTO.setDescripcion(itm.getDescripcionitem());
				itemDTO.setCalificacion(itm.getCalificacionitem());
				listaItemsDTO.add(itemDTO);
			}
		}
		return listaItemsDTO;
	}
	
	public List<ItemDTO> listarItemsLikeCatItmByUsr(String nombreItm, String nombreCat,UsuarioDTO usr) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = null;
		query = em.createQuery(
				"Select i from Item i where i.nombreitem LIKE :iNombreItem AND i.categoria.nombrecategoria LIKE :iNombreCategoria AND i.usuario.emailusr=:iEmail");
		query.setParameter("iEmail", usr.getEmail());
		query.setParameter("iNombreItem", "%" + nombreItm + "%");
		query.setParameter("iNombreCategoria", "%" + nombreCat + "%");
		@SuppressWarnings("unchecked")
		List<Item> listaItems = query.getResultList();
		List<ItemDTO> listaItemsDTO = new ArrayList<ItemDTO>();
		em.close();
		emf.close();
		if (listaItems.equals(null)) {
			listaItems = new ArrayList<Item>();
		} else {
			for (Item itm : listaItems) {
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setId(itm.getIditem());
				itemDTO.setNombre(itm.getNombreitem());
				itemDTO.setDescripcion(itm.getDescripcionitem());
				itemDTO.setCalificacion(itm.getCalificacionitem());
				listaItemsDTO.add(itemDTO);
			}
		}
		return listaItemsDTO;
	}

	public void actualizarItem(ItemDTO itemDTOInicial, ItemDTO itemDTOFinal) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery(
				"Update Item i Set i.nombreitem=:iNombre, i.descripcionitem=:iDescripcion WHERE i.iditem=:iIditem");
		query.setParameter("iNombre", itemDTOFinal.getNombre());
		// ,i.categoria.nombrecategoria=:iCategoria
		query.setParameter("iDescripcion", itemDTOFinal.getDescripcion());
		//query.setParameter("iCategoria", itemDTOFinal.getCategoria().getNombre());
		query.setParameter("iIditem", itemDTOInicial.getId());
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	public void eliminarItem(ItemDTO itemDTO) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Query query = em.createQuery("Delete from Item i  where i.iditem=:iIditem");
			query.setParameter("iIditem", itemDTO.getId());
			query.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		emf.close();
	}

	public ItemDTO buscarItem(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		ItemDTO itmDTO = new ItemDTO();
		Item itm= em.find(Item.class, id);
		if(itm==null){
			itmDTO=null;
		}else{
			itmDTO.setId(id);
			itmDTO.setNombre(itm.getNombreitem());
			itmDTO.setDescripcion(itm.getDescripcionitem());
			CategoriaDTO catDTO = new CategoriaDTO();
			catDTO.setNombre(itm.getCategoria().getNombrecategoria());
			itmDTO.setCategoria(catDTO);
		}
		return itmDTO;
	}

}