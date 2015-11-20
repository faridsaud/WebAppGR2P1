package ec.edu.epn.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CATEGORIA database table.
 * 
 */
@Entity
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nombrecategoria;

	private String descripcioncategoria;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="categoria", cascade={CascadeType.ALL})
	private List<Item> items;

	public Categoria() {
	}

	public String getNombrecategoria() {
		return this.nombrecategoria;
	}

	public void setNombrecategoria(String nombrecategoria) {
		this.nombrecategoria = nombrecategoria;
	}

	public String getDescripcioncategoria() {
		return this.descripcioncategoria;
	}

	public void setDescripcioncategoria(String descripcioncategoria) {
		this.descripcioncategoria = descripcioncategoria;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setCategoria(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setCategoria(null);

		return item;
	}

}