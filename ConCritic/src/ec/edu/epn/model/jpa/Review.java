package ec.edu.epn.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the REVIEW database table.
 * 
 */
@Entity
@NamedQuery(name="Review.findAll", query="SELECT r FROM Review r")
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idreview;

	private int calificacionreview;

	private String comentarioreview;

	@Temporal(TemporalType.DATE)
	private Date fechareview;

	private String tituloreview;

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

	public int getIdreview() {
		return this.idreview;
	}

	public void setIdreview(int idreview) {
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