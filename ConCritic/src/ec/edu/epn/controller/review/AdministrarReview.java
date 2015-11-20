package ec.edu.epn.controller.review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.ItemDTO;
import ec.edu.epn.model.dto.ReviewDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.servicio.ServiceReview;

/**
 * Servlet implementation class AdministrarReview
 */
@WebServlet("/Review/Administrar")
public class AdministrarReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministrarReview() {
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
			String nombreItem = (String) request.getParameter("nombreItm");
			List<ReviewDTO> listaReviews = new ArrayList<ReviewDTO>();
			ServiceReview sr = new ServiceReview();
			if (nombreItem == null || nombreItem.equals("")) {
				if (usrLogeado.isAdmin()) {
					listaReviews = sr.buscarAllReviews();
				} else {
					listaReviews = sr.buscarReviewsByUsr(usrLogeado);
				}
			} else {
				ItemDTO itmDTO = new ItemDTO();
				itmDTO.setNombre(nombreItem);
				listaReviews = sr.buscarReviewsLikeItem(itmDTO);
			}
			for(ReviewDTO rev:listaReviews){
			}
			request.setAttribute("listaReviews", listaReviews);
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/review/administrar.jsp")
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
