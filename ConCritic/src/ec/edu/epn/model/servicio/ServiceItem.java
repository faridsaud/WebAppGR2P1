package ec.edu.epn.model.servicio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ec.edu.epn.model.dto.ItemDTO;
import ec.edu.epn.model.jpa.Categoria;
import ec.edu.epn.model.jpa.Item;
import ec.edu.epn.model.jpa.Usuario;

public class ServiceItem {
	
	public void registrarItem(ItemDTO itemDTO) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Item item = new Item();
		//item.setCalificacionitem(itemDTO.getCalificacion());
		item.setNombreitem(itemDTO.getNombre());
		item.setDescripcionitem(itemDTO.getDescripcion());
		//item.setNumvotositem(itemDTO.getNumeroVotos());
		
		String nombreCategoria = itemDTO.getCategoria().getNombre();
		Categoria cat = entitymanager.find(Categoria.class,nombreCategoria);
		item.setCategoria(cat);
		
		String emailUsr = itemDTO.getUsuario().getEmail();
		Usuario usr = entitymanager.find(Usuario.class,emailUsr);
		item.setUsuario(usr);
		
		entitymanager.persist(item);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}


	public List<ItemDTO> listarItems() {

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
				"UPDATE ITEM i  SET i.NOMBREITEM=:iNombre, i.DESCRIPCIONITEM=:iDescripcion, i.NOMBRECATEGORIA=:iCategoria WHERE i.IDITEM=:iIditem");
		query.setParameter("iNombre", itemDTOFinal.getNombre());
		query.setParameter("iDescripcion", itemDTOFinal.getDescripcion());
		query.setParameter("iCategoria", itemDTOFinal.getCategoria());
		query.setParameter("iIditem", itemDTOInicial.getId());
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	public void eliminarItem(ItemDTO itemDTO) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
		Query query = em.createQuery(
				"DELETE FROM Item i  WHERE i.IDITEM=:iIditem");
		query.setParameter("iIditem", itemDTO.getId());
		query.executeUpdate();
		em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		em.close();
		emf.close();
	}
	
	
	//INCOMPLETA
	public ItemDTO buscarItemById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(
				"Select i from ITEM i where i.IDITEM=:iId");
		query.setParameter("iID", id);
		@SuppressWarnings("unchecked")
		List<Item> listaItems = query.getResultList();
		em.close();
		emf.close();
		
		ItemDTO itemDTO = new ItemDTO();
		Item item = new Item();
		
		if (listaItems.equals(null)) {
			listaItems = new ArrayList<Item>();
		}
		if (listaItems.isEmpty() == true) {
			itemDTO = null;
			return itemDTO;
		}
		
		item = listaItems.get(0);
		//MAGIA
		return itemDTO;
	}

}