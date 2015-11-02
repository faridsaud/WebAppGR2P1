package ec.edu.epn.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TAG database table.
 * 
 */
@Entity
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nombretag;

	//bi-directional many-to-many association to Item
	@ManyToMany
	@JoinTable(
		name="ITEMTAG"
		, joinColumns={
			@JoinColumn(name="NOMBRETAG")
			}
		, inverseJoinColumns={
			@JoinColumn(name="IDITEM")
			}
		)
	private List<Item> items;

	public Tag() {
	}

	public String getNombretag() {
		return this.nombretag;
	}

	public void setNombretag(String nombretag) {
		this.nombretag = nombretag;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}