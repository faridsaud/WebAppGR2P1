package ec.edu.epn.model.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.json.JsonObject;

public class UsuarioDTO {
	private String email="";
	private String password="";
	private String nombre="";
	private String apellido="";
	private Date fechaNacimiento=null;
	private String pais="";
	private boolean admin=false;
	private boolean estado=true;
	private List<ItemDTO> listaItems=null;
	private List<ReviewDTO> listaReviews=null;
	
	public UsuarioDTO(){
	}
	public UsuarioDTO(JsonObject jsonObject){
		this.email=jsonObject.getString("email");
		this.password=jsonObject.getString("password");
		this.nombre=jsonObject.getString("nombre");
		this.apellido=jsonObject.getString("apellido");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.fechaNacimiento=formatter.parse(jsonObject.getString("fechaNacimiento"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pais=jsonObject.getString("pais");
		this.admin=jsonObject.getBoolean("admin");
		this.estado=jsonObject.getBoolean("estado");
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<ItemDTO> getListaItems() {
		return listaItems;
	}

	public void setListaItems(List<ItemDTO> listaItems) {
		this.listaItems = listaItems;
	}

	public List<ReviewDTO> getListaReviews() {
		return listaReviews;
	}

	public void setListaReviews(List<ReviewDTO> listaReviews) {
		this.listaReviews = listaReviews;
	}
	
	

}
