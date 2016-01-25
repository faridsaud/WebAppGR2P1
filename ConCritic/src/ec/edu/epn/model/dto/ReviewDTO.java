package ec.edu.epn.model.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.JsonObject;

public class ReviewDTO {
	private int id;
	private int calificacion;
	private String comentario;
	private Date fecha;
	private String titulo;
	private ItemDTO item;
	private UsuarioDTO usuario;
	
	public ReviewDTO() {
		this.id = 0;
		this.calificacion = 0;
		this.comentario = "";
		this.fecha = null;
		this.titulo = "";
		this.item = null;
		this.usuario = null;
	}

	public ReviewDTO(JsonObject jsonObject) {
		try{
			this.id=jsonObject.getInt("id");
		}catch(Exception e){
		}
		this.calificacion = Integer.parseInt(jsonObject.getString("calificacion"));
		this.comentario = jsonObject.getString("comentario");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.fecha=new Date();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.titulo = jsonObject.getString("titulo");
		this.item= new ItemDTO();
		this.item.setId(jsonObject.getJsonObject("item").getInt("id"));
		this.usuario = new UsuarioDTO();
		this.usuario.setEmail(jsonObject.getJsonObject("usuario").getString("email"));
				
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public ItemDTO getItem() {
		return item;
	}
	public void setItem(ItemDTO item) {
		this.item = item;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "ReviewDTO [id=" + id + ", calificacion=" + calificacion + ", comentario=" + comentario + ", fecha="
				+ fecha + ", titulo=" + titulo + ", item=" + item + ", usuario=" + usuario + "]";
	}
}