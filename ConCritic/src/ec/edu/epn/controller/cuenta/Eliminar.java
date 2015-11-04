package ec.edu.epn.controller.cuenta;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.servicio.ServiceCuenta;

/**
 * Servlet implementation class Eliminar
 */
@WebServlet("/Cuenta/Eliminar")
public class Eliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Eliminar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletConfig().getServletContext().getRequestDispatcher("/Cuenta/Administrar").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			ServiceCuenta sc= new ServiceCuenta();
			String email= (String)request.getParameter("emailEliminar");
			UsuarioDTO usrEliminar=sc.buscarUsuarioByEmail(email);
			sc.eliminarUsuario(usrEliminar);
		}catch(Exception e){
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
