package ec.edu.epn.controller.cuenta;

import java.io.IOException;

import javax.print.attribute.standard.PrinterLocation;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.servicio.ServiceCuenta;

/**
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/Cuenta/IniciarSesion")
public class IniciarSesionCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarSesionCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UsuarioDTO usrLogeado=(UsuarioDTO)request.getSession().getAttribute("usuarioLogeado");
		if(usrLogeado==null){
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/cuenta/iniciarSesion.jsp").forward(request, response);
		}else{
			getServletConfig().getServletContext().getRequestDispatcher("/Home").forward(request, response);
				
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email="";
		String password="";
		ServiceCuenta sc=new ServiceCuenta();
		try{
			email=(String)request.getParameter("email");
			password=(String)request.getParameter("password");
		}catch(Exception e){
			email="";
			password="";
		}
		
		UsuarioDTO usrDTO= sc.buscarUsuario(email, password);
		if(usrDTO!=null){
			request.getSession().setAttribute("usuarioLogeado", usrDTO);
			System.out.println(usrDTO.getNombre());
		}else{
			request.setAttribute("errorLogin", true);
		}
		doGet(request, response);
	}

}
