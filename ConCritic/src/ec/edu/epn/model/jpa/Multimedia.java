package ec.edu.epn.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MULTIMEDIA database table.
 * 
 */
@Entity
@NamedQuery(name="Multimedia.findAll", query="SELECT m FROM Multimedia m")
public class Multimedia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmultimedia;

	private String pathmultimedia;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="IDITEM")
	private Item item;

	public Multimedia() {
	}

	public int getIdmultimedia() {
		return this.idmultimedia;
	}

	public void setIdmultimedia(int idmultimedia) {
		this.idmultimedia = idmultimedia;
	}

	public String getPathmultimedia() {
		return this.pathmultimedia;
	}

	public void setPathmultimedia(String pathmultimedia) {
		this.pathmultimedia = pathmultimedia;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}