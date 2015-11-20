package ec.edu.epn.controller.review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.spi.ServiceRegistry;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.ItemDTO;
import ec.edu.epn.model.dto.MultimediaDTO;
import ec.edu.epn.model.dto.ReviewDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.servicio.ServiceMultimedia;
import ec.edu.epn.model.servicio.ServiceReview;

/**
 * Servlet implementation class RegistrarReview
 */
@WebServlet("/Review/Registrar")
public class RegistrarReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrarReview() {
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
			ServiceReview sr = new ServiceReview();
			String idItem = (String) request.getParameter("idItemReview");
			int id = Integer.parseInt(idItem);
			ItemDTO itemDTO=new ItemDTO();
			itemDTO.setId(id);
			System.out.println(idItem);
			ReviewDTO reviewByUsr = sr.buscarReviewByUserByItem(usrLogeado, itemDTO);
			if (reviewByUsr != null) {
				getServletConfig().getServletContext().getRequestDispatcher("/Item/Buscar").forward(request, response);

			} else {

				ServiceMultimedia sm = new ServiceMultimedia();
				List<MultimediaDTO> listaMultimedia = new ArrayList<MultimediaDTO>();

				if (idItem == null) {

				} else {
					request.getSession().setAttribute("idItemRev", id);
					listaMultimedia = sm.listarMultimediasLike(id);
					request.setAttribute("listaMultimediaInfo", listaMultimedia);
				}
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/review/registrar.jsp")
						.forward(request, response);
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
		UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
		ServiceReview sr = new ServiceReview();

		String titulo = (String) request.getParameter("tituloRev");
		String calificacion = (String) request.getParameter("calificacionRev");
		String comentario = (String) request.getParameter("comentarioRev");
		int calificacionRev = Integer.parseInt(calificacion);
		ReviewDTO revDTO = new ReviewDTO();
		try {
			revDTO.setCalificacion(calificacionRev);
			revDTO.setComentario(comentario);
			revDTO.setFecha(new Date());
			int idItemRev = (int) request.getSession().getAttribute("idItemRev");
			ItemDTO itmDTO = new ItemDTO();
			itmDTO.setId(idItemRev);
			revDTO.setItem(itmDTO);
			revDTO.setTitulo(titulo);
			revDTO.setUsuario(usrLogeado);

			sr.registrarReview(revDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);

	}

}
