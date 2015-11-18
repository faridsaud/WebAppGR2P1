package ec.edu.epn.model.servicio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ec.edu.epn.model.dto.CategoriaDTO;
import ec.edu.epn.model.jpa.Categoria;

public class ServiceCategoria {
	
	public void registrarCategoria(CategoriaDTO catDTO) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Categoria cat= new Categoria();
		cat.setNombrecategoria(catDTO.getNombre());
		cat.setDescripcioncategoria(catDTO.getDescripcion());
		entitymanager.persist(cat);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	public CategoriaDTO buscarCategoria(String nombreCat) {
		
		CategoriaDTO catDTO=new CategoriaDTO();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Categoria cat= em.find(Categoria.class, nombreCat);
		if(cat==null){
			catDTO=null;
		}else{
			catDTO.setDescripcion(cat.getDescripcioncategoria());
			catDTO.setNombre(cat.getNombrecategoria());
		}
		return catDTO;
	}

	public List<CategoriaDTO> listarCategoriasAll() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = null;
			query = em.createQuery(
					"Select c from Categoria c");
		@SuppressWarnings("unchecked")
		List<Categoria> listaCategorias = query.getResultList();
		List<CategoriaDTO> listaCategoriasDTO = new ArrayList<CategoriaDTO>();
		em.close();
		emf.close();
		if (listaCategorias.equals(null)) {
			listaCategorias = new ArrayList<Categoria>();
		} else {
			for (Categoria cat : listaCategorias) {
				CategoriaDTO catDTOOutput=new CategoriaDTO();
				catDTOOutput.setDescripcion(cat.getDescripcioncategoria());
				catDTOOutput.setNombre(cat.getNombrecategoria());
				listaCategoriasDTO.add(catDTOOutput);
			}
		}

		return listaCategoriasDTO;
	}
	public List<CategoriaDTO> listarCategoriasLike(String nombreCat) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		Query query = null;
			query = em.createQuery(
					"Select c from Categoria c where c.nombrecategoria LIKE :iNombreCategoria");
			query.setParameter("iNombreCategoria", "%"+nombreCat+"%");
		@SuppressWarnings("unchecked")
		List<Categoria> listaCategorias = query.getResultList();
		List<CategoriaDTO> listaCategoriasDTO = new ArrayList<CategoriaDTO>();
		em.close();
		emf.close();
		if (listaCategorias.equals(null)) {
			listaCategorias = new ArrayList<Categoria>();
		} else {
			for (Categoria cat : listaCategorias) {
				CategoriaDTO catDTOOutput=new CategoriaDTO();
				catDTOOutput.setDescripcion(cat.getDescripcioncategoria());
				catDTOOutput.setNombre(cat.getNombrecategoria());
				listaCategoriasDTO.add(catDTOOutput);
			}
		}

		return listaCategoriasDTO;
	}
	public void actualizarCategoria(CategoriaDTO catDTOInicial, CategoriaDTO catDTOFinal) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery(
				"UPDATE Categoria c SET c.descripcioncategoria=:iDescripcionCategoria WHERE c.nombrecategoria=:iNombreCategoriaKey");
		query.setParameter("iDescripcionCategoria", catDTOFinal.getDescripcion());
		query.setParameter("iNombreCategoriaKey", catDTOInicial.getNombre());
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	public void eliminarCategoria(CategoriaDTO catDTO) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConCritic");
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
		Query query = em.createQuery(
				"DELETE FROM Categoria c  WHERE c.nombrecategoria=:iNombreCategoria");
		query.setParameter("iNombreCategoria", catDTO.getNombre());
		query.executeUpdate();
		em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		em.close();
		emf.close();
	}


}
