package ec.edu.epn.controller.multimedia;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.MultimediaDTO;
import ec.edu.epn.model.servicio.ServiceMultimedia;

/**
 * Servlet implementation class EliminarMultimedia
 */
@WebServlet("/Multimedia/Eliminar")
public class EliminarMultimedia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarMultimedia() {
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
		getServletConfig().getServletContext().getRequestDispatcher("/Multimedia/Administrar").forward(request,
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
			ServiceMultimedia sm = new ServiceMultimedia();
			int idMultimediaEliminar = Integer.parseInt(request.getParameter("idMultimediaEliminar"));
			MultimediaDTO mulEliminar = sm.buscarMultimedia(idMultimediaEliminar);
			sm.eliminarMultimedia(mulEliminar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
