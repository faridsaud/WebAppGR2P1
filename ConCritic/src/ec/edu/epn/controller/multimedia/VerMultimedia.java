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
import ec.edu.epn.model.servicio.ServiceMultimedia;

/**
 * Servlet implementation class VerMultimedia
 */
@WebServlet("/Multimedia/Ver")
public class VerMultimedia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerMultimedia() {
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

		} else {
			ServiceMultimedia sm = new ServiceMultimedia();
			String idItem =(String)request.getParameter("IdItem");
			List<MultimediaDTO> listaMultimedia = new ArrayList<MultimediaDTO>();

			if (idItem==null){
				
			}else{
				int id = Integer.parseInt(idItem);
				listaMultimedia=sm.listarMultimediasLike(id);
				request.setAttribute("listaMultimedia", listaMultimedia);
			}
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/multimedia/ver.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
