package ec.edu.epn.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the USUARIO database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String emailusr;

	private boolean adminusr;

	private String apellidousr;

	private boolean estadousr;

	@Temporal(TemporalType.DATE)
	private Date fechanacimientousr;

	private String nombreusr;

	private String paisusr;

	private String passwordusr;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="usuario")
	private List<Item> items;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="usuario")
	private List<Review> reviews;

	public Usuario() {
	}

	public String getEmailusr() {
		return this.emailusr;
	}

	public void setEmailusr(String emailusr) {
		this.emailusr = emailusr;
	}

	public boolean isAdminusr() {
		return this.adminusr;
	}

	public void setAdminusr(boolean adminusr) {
		this.adminusr = adminusr;
	}

	public String getApellidousr() {
		return this.apellidousr;
	}

	public void setApellidousr(String apellidousr) {
		this.apellidousr = apellidousr;
	}

	public boolean isEstadousr() {
		return this.estadousr;
	}

	public void setEstadousr(boolean estadousr) {
		this.estadousr = estadousr;
	}

	public Date getFechanacimientousr() {
		return this.fechanacimientousr;
	}

	public void setFechanacimientousr(Date fechanacimientousr) {
		this.fechanacimientousr = fechanacimientousr;
	}

	public String getNombreusr() {
		return this.nombreusr;
	}

	public void setNombreusr(String nombreusr) {
		this.nombreusr = nombreusr;
	}

	public String getPaisusr() {
		return this.paisusr;
	}

	public void setPaisusr(String paisusr) {
		this.paisusr = paisusr;
	}

	public String getPasswordusr() {
		return this.passwordusr;
	}

	public void setPasswordusr(String passwordusr) {
		this.passwordusr = passwordusr;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setUsuario(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setUsuario(null);

		return item;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setUsuario(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setUsuario(null);

		return review;
	}

}