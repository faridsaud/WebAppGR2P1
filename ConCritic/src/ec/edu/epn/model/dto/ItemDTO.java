package ec.edu.epn.model.dto;

import java.util.List;

public class ItemDTO {
	private int id;
	private float calificacion;
	private String descripcion;
	private String nombre;
	private int numeroVotos;
	private CategoriaDTO categoria;
	private UsuarioDTO usuario;
	private List<MultimediaDTO> multimedias;
	private List<ReviewDTO> reviews;
	
	public ItemDTO(){
		this.id = 0;
		this.calificacion = 0;
		this.descripcion = "";
		this.nombre = "";
		this.numeroVotos = 0;
		this.categoria = null;
		this.usuario = null;
		this.multimedias = null;
		this.reviews = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroVotos() {
		return numeroVotos;
	}

	public void setNumeroVotos(int numeroVotos) {
		this.numeroVotos = numeroVotos;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public List<MultimediaDTO> getMultimedias() {
		return multimedias;
	}

	public void setMultimedias(List<MultimediaDTO> multimedias) {
		this.multimedias = multimedias;
	}

	public List<ReviewDTO> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewDTO> reviews) {
		this.reviews = reviews;
	}
}