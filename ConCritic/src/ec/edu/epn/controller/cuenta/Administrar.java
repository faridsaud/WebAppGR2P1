package ec.edu.epn.controller.cuenta;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.servicio.ServiceCuenta;

/**
 * Servlet implementation class Administrar
 */
@WebServlet("/Cuenta/Administrar")
public class Administrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Administrar() {
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
		String email = request.getParameter("email");
		boolean error = false;
		UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
		if (email == null) {
			email = "";
		}
		if (usrLogeado == null) {
			error = true;
		}
		try {
			if (error == false) {
				ServiceCuenta sc = new ServiceCuenta();
				List<UsuarioDTO> listaUsuariosDTO = sc.listarUsuarios(usrLogeado, email);
				request.setAttribute("listaUsuariosAdministrar", listaUsuariosDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/cuenta/administrar.jsp").forward(request, response);
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
