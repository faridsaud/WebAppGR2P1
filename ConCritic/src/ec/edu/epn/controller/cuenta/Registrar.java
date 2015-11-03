package ec.edu.epn.controller.cuenta;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.servicio.ServiceCuenta;

/**
 * Servlet implementation class Registrar
 */
@WebServlet("/Cuenta/Registrar")
public class Registrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/cuenta/registrar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UsuarioDTO usrDTO=new UsuarioDTO();
		usrDTO.setAdmin(false);
		usrDTO.setEstado(true);
		try{
			usrDTO.setApellido(request.getParameter("apellido"));
			usrDTO.setNombre(request.getParameter("nombre"));
			usrDTO.setEmail(request.getParameter("email"));
			usrDTO.setPais(request.getParameter("pais"));
			usrDTO.setPassword(request.getParameter("password"));
			String fechaNac=request.getParameter("fechaNac");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {

				Date fechaNacimiento= formatter.parse(fechaNac);
				usrDTO.setFechaNacimiento(fechaNacimiento);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServiceCuenta sc=new ServiceCuenta();
			sc.registrarUsuario(usrDTO);
		}catch(Exception e){
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
