package ec.edu.epn.controller.categoria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.CategoriaDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.servicio.ServiceCategoria;

/**
 * Servlet implementation class AdministrarCategoria
 */
@WebServlet("/Categoria/Administrar")
public class AdministrarCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministrarCategoria() {
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
		UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
		if (usrLogeado == null) {
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);

		} else {
			if (usrLogeado.isAdmin() == true) {
				List<CategoriaDTO> listaCategorias = new ArrayList<CategoriaDTO>();
				ServiceCategoria sc = new ServiceCategoria();
				String nombreCat = request.getParameter("nombreCat");
				String nombreItm = request.getParameter("nombreItm");
				if (nombreCat == null){
					listaCategorias = sc.listarCategoriasAll();
				}else{
					if(nombreCat.equals("")){
						listaCategorias = sc.listarCategoriasAll();		
					}else{
						listaCategorias=sc.listarCategoriasLike(nombreCat);
					}
				}
				request.setAttribute("listaCategorias", listaCategorias);
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/categoria/administrar.jsp")
						.forward(request, response);
			} else {
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request,
						response);

			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
