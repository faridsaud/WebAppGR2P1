package ec.edu.epn.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the REVIEW database table.
 * 
 */
@Entity
@NamedQuery(name="Review.findAll", query="SELECT r FROM Review r")
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String idreview;

	private int calificacionreview;

	private String comentarioreview;

	@Temporal(TemporalType.DATE)
	private Date fechareview;

	private String tituloreview;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="review")
	private List<Item> items;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="IDITEM")
	private Item item;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="EMAILUSR")
	private Usuario usuario;

	public Review() {
	}

	public String getIdreview() {
		return this.idreview;
	}

	public void setIdreview(String idreview) {
		this.idreview = idreview;
	}

	public int getCalificacionreview() {
		return this.calificacionreview;
	}

	public void setCalificacionreview(int calificacionreview) {
		this.calificacionreview = calificacionreview;
	}

	public String getComentarioreview() {
		return this.comentarioreview;
	}

	public void setComentarioreview(String comentarioreview) {
		this.comentarioreview = comentarioreview;
	}

	public Date getFechareview() {
		return this.fechareview;
	}

	public void setFechareview(Date fechareview) {
		this.fechareview = fechareview;
	}

	public String getTituloreview() {
		return this.tituloreview;
	}

	public void setTituloreview(String tituloreview) {
		this.tituloreview = tituloreview;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setReview(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setReview(null);

		return item;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}