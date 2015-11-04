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
 * Servlet implementation class Modificar
 */
@WebServlet("/Cuenta/Modificar")
public class ModificarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarCuenta() {
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
		String email = (String) request.getParameter("emailModificar");
		if (email == null) {
			email = "";
		}
		ServiceCuenta sc = new ServiceCuenta();
		UsuarioDTO usr = sc.buscarUsuarioByEmail(email);
		if (usr == null) {
			request.setAttribute("errorActualizacionUsuario", true);
		}
		request.setAttribute("usuarioModificar", usr);
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/cuenta/modificar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UsuarioDTO usuarioLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
		boolean error = false;
		boolean isMail = true;
		UsuarioDTO usrModificar = new UsuarioDTO();
		UsuarioDTO usrModificador = new UsuarioDTO();
		usrModificar.setEmail((String) request.getParameter("email"));
		if (usrModificador.getEmail() == null) {
			isMail = false;
		}
		if (isMail == true) {
			usrModificador.setApellido((String) request.getParameter("apellido"));
			usrModificador.setNombre((String) request.getParameter("nombre"));
			usrModificador.setPassword((String) request.getParameter("password"));
			usrModificador.setPais((String) request.getParameter("pais"));
			boolean permisos = true;
			if ((usuarioLogeado.isAdmin() == false)
					&& (usuarioLogeado.getEmail().equals(usrModificar.getEmail()) == false))
				permisos = false;

			String fechaNac = request.getParameter("fechaNac");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {

				Date fechaNacimiento = formatter.parse(fechaNac);
				usrModificador.setFechaNacimiento(fechaNacimiento);
				ServiceCuenta sc = new ServiceCuenta();
				if (permisos == true)
					sc.actualizarUsuario(usrModificar, usrModificador);
			} catch (Exception e) {
				e.printStackTrace();
				error = true;
				request.setAttribute("errorActualizacionUsuario", error);
			}
		} else {
			request.setAttribute("errorActualizacionUsuario", error);
		}

		doGet(request, response);
	}

}
