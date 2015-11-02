package ec.edu.epn.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String iditem;

	private BigDecimal calificacionitem;

	private String descripcionitem;

	@Temporal(TemporalType.DATE)
	private Date fechaimagen;

	private String nombreitem;

	private int numvotositem;

	private String pathimagenitem;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="NOMBRECATEGORIA")
	private Categoria categoria;

	//bi-directional many-to-one association to Review
	@ManyToOne
	@JoinColumn(name="IDREVIEW")
	private Review review;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="EMAILUSR")
	private Usuario usuario;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="item")
	private List<Review> reviews;

	//bi-directional many-to-many association to Tag
	@ManyToMany(mappedBy="items")
	private List<Tag> tags;

	public Item() {
	}

	public String getIditem() {
		return this.iditem;
	}

	public void setIditem(String iditem) {
		this.iditem = iditem;
	}

	public BigDecimal getCalificacionitem() {
		return this.calificacionitem;
	}

	public void setCalificacionitem(BigDecimal calificacionitem) {
		this.calificacionitem = calificacionitem;
	}

	public String getDescripcionitem() {
		return this.descripcionitem;
	}

	public void setDescripcionitem(String descripcionitem) {
		this.descripcionitem = descripcionitem;
	}

	public Date getFechaimagen() {
		return this.fechaimagen;
	}

	public void setFechaimagen(Date fechaimagen) {
		this.fechaimagen = fechaimagen;
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

	public String getPathimagenitem() {
		return this.pathimagenitem;
	}

	public void setPathimagenitem(String pathimagenitem) {
		this.pathimagenitem = pathimagenitem;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Review getReview() {
		return this.review;
	}

	public void setReview(Review review) {
		this.review = review;
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

	public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

}