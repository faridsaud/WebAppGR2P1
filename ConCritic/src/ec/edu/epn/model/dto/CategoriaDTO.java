package ec.edu.epn.model.dto;

import ec.edu.epn.model.jpa.Categoria;

public class CategoriaDTO {
	private String nombre = "";
	private String descripcion = "";

	public CategoriaDTO() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
