package ec.edu.epn.controller.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.ReviewDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.servicio.ServiceReview;

/**
 * Servlet implementation class ModificarReview
 */
@WebServlet("/Review/Modificar")
public class ModificarReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
		if (usrLogeado == null) {
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);

		}else{
			String idReviewModificar=(String)request.getParameter("idReviewModificar");
			if(idReviewModificar!=null){
				int idRev=Integer.parseInt(idReviewModificar);
				ServiceReview sr= new ServiceReview();
				ReviewDTO revDTO=sr.buscarReviewById(idRev);
				request.getSession().setAttribute("reviewModificar", revDTO);
				request.setAttribute("idItemReview", revDTO.getItem().getId());
			}
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/review/modificar.jsp").forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
		if (usrLogeado == null) {
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);

		}
		String tituloRev=(String)request.getParameter("tituloRev");
		String calRev=(String)request.getParameter("calificacionRev");
		String comentarioRev=(String)request.getParameter("comentarioRev");
		try{
			int calificacionRev=Integer.parseInt(calRev);
			ReviewDTO revDTOFinal=new ReviewDTO();
			revDTOFinal.setCalificacion(calificacionRev);
			revDTOFinal.setTitulo(tituloRev);
			revDTOFinal.setComentario(comentarioRev);
			ReviewDTO revDTOInicial=(ReviewDTO)request.getSession().getAttribute("reviewModificar");
			ServiceReview sr=new ServiceReview();
			sr.actualizarReview(revDTOInicial, revDTOFinal);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);
	}

}
