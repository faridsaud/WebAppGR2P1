package ec.edu.epn.controller.review;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.ItemDTO;
import ec.edu.epn.model.dto.ReviewDTO;
import ec.edu.epn.model.servicio.ServiceReview;

/**
 * Servlet implementation class PruebaReview
 */
@WebServlet("/Review/Prueba")
public class PruebaReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PruebaReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServiceReview sr= new ServiceReview();
		ItemDTO itmDTO=new ItemDTO();
		itmDTO.setId(2);
		List<ReviewDTO> lista=sr.buscarReviewsByItem(itmDTO);
		for(ReviewDTO rev:lista){
			System.out.println(rev.getTitulo());
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
