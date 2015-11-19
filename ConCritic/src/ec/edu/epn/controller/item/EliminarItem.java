package ec.edu.epn.controller.item;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.CategoriaDTO;
import ec.edu.epn.model.dto.ItemDTO;
import ec.edu.epn.model.servicio.ServiceCategoria;
import ec.edu.epn.model.servicio.ServiceItem;

/**
 * Servlet implementation class EliminarItem
 */
@WebServlet("/Item/Eliminar")
public class EliminarItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarItem() {
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
		getServletConfig().getServletContext().getRequestDispatcher("/Item/Administrar").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ServiceItem si = new ServiceItem();
			int idItemEliminar = Integer.parseInt(request.getParameter("idItemEliminar"));
			ItemDTO itmEliminar = si.buscarItem(idItemEliminar);
			si.eliminarItem(itmEliminar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
