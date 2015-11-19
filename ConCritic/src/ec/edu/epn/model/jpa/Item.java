package ec.edu.epn.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ITEM database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int iditem;

	private float calificacionitem;

	private String descripcionitem;

	private String nombreitem;

	private int numvotositem;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="NOMBRECATEGORIA")
	private Categoria categoria;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="EMAILUSR")
	private Usuario usuario;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="item")
	private List<Review> reviews;

	//bi-directional many-to-one association to Multimedia
	@OneToMany(mappedBy="item")
	private List<Multimedia> multimedias;

	public Item() {
	}

	public int getIditem() {
		return this.iditem;
	}

	public void setIditem(int iditem) {
		this.iditem = iditem;
	}

	public float getCalificacionitem() {
		return this.calificacionitem;
	}

	public void setCalificacionitem(float calificacionitem) {
		this.calificacionitem = calificacionitem;
	}

	public String getDescripcionitem() {
		return this.descripcionitem;
	}

	public void setDescripcionitem(String descripcionitem) {
		this.descripcionitem = descripcionitem;
	}

	public String getNombreitem() {
		return this.nombreitem;
	}

	public void setNombreitem(String nombreitem) {
		this.nombreitem = nombreitem;
	}

	public int getNumvotositem() {
		return this.numvotositem;
	}

	public void setNumvotositem(int numvotositem) {
		this.numvotositem = numvotositem;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setItem(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setItem(null);

		return review;
	}

	public List<Multimedia> getMultimedias() {
		return this.multimedias;
	}

	public void setMultimedias(List<Multimedia> multimedias) {
		this.multimedias = multimedias;
	}

	public Multimedia addMultimedia(Multimedia multimedia) {
		getMultimedias().add(multimedia);
		multimedia.setItem(this);

		return multimedia;
	}

	public Multimedia removeMultimedia(Multimedia multimedia) {
		getMultimedias().remove(multimedia);
		multimedia.setItem(null);

		return multimedia;
	}

}