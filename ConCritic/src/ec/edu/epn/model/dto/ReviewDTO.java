package ec.edu.epn.model.dto;

import java.util.Date;

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
}