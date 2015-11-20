package ec.edu.epn.controller.multimedia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.MultimediaDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.servicio.ServiceItem;
import ec.edu.epn.model.servicio.ServiceMultimedia;

/**
 * Servlet implementation class AdministrarMultimedia
 */
@WebServlet("/Multimedia/Administrar")
public class AdministrarMultimedia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministrarMultimedia() {
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
			List<MultimediaDTO> listaMultimedias = new ArrayList<MultimediaDTO>();
			ServiceMultimedia sm = new ServiceMultimedia();
			String idItem = request.getParameter("idItem");
			if (usrLogeado.isAdmin()) {
				if (idItem == null) {
					listaMultimedias = sm.listarMultimediasAll();
				} else {
					int id = Integer.parseInt(idItem);
					listaMultimedias = sm.listarMultimediasLike(id);
				}
			} else {
				if (idItem == null) {
					listaMultimedias = sm.listarMultimediasAllByUsr(usrLogeado);
				} else {
					int id = Integer.parseInt(idItem);
					listaMultimedias = sm.listarMultimediasLikeIdByUsr(id,usrLogeado);
				}

			}
			request.setAttribute("listaMultimedia", listaMultimedias);
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/multimedia/administrar.jsp")
					.forward(request, response);
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
