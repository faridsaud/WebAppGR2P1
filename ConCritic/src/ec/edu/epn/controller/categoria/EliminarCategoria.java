package ec.edu.epn.controller.categoria;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.CategoriaDTO;
import ec.edu.epn.model.servicio.ServiceCategoria;

/**
 * Servlet implementation class EliminarCategoria
 */
@WebServlet("/Categoria/Eliminar")
public class EliminarCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarCategoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletConfig().getServletContext().getRequestDispatcher("/Categoria/Administrar").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			ServiceCategoria sc= new ServiceCategoria();
			String nombreCat= (String)request.getParameter("nombreCatEliminar");
			CategoriaDTO catEliminar=sc.buscarCategoria(nombreCat);
			sc.eliminarCategoria(catEliminar);
		}catch(Exception e){
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
