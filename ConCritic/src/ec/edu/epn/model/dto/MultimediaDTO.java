package ec.edu.epn.model.dto;

public class MultimediaDTO {
	private int id;
	private String path;
	private ItemDTO item;
	
	public MultimediaDTO() {
		this.id = 0;
		this.path = "";
		this.item = null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public ItemDTO getItem() {
		return item;
	}
	public void setItem(ItemDTO item) {
		this.item = item;
	}
}