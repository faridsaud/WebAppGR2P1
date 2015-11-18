package ec.edu.epn.controller.item;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ec.edu.epn.model.dto.CategoriaDTO;
import ec.edu.epn.model.dto.ItemDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.servicio.ServiceItem;

/**
 * Servlet implementation class RegistrarItem
 */
@WebServlet("/Item/Registrar")
@MultipartConfig
public class RegistrarItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrarItem() {
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
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/item/registrar.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ItemDTO itemDTO = new ItemDTO();

		try {
			itemDTO.setNombre(request.getParameter("nombre"));
			itemDTO.setDescripcion(request.getParameter("descripcion"));

			CategoriaDTO catDTO = new CategoriaDTO();
			catDTO.setNombre(request.getParameter("categoria"));
			itemDTO.setCategoria(catDTO);
			itemDTO.setUsuario((UsuarioDTO) request.getSession().getAttribute("usuarioLogeado"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		ServiceItem si = new ServiceItem();
		si.registrarItem(itemDTO);

		for (int i = 1; i <= 4; i++) {
			Part filePart = request.getPart("inputFile" + i);
			System.out.println(filePart.toString());
			final String fileName = getFileName(filePart);
			if (fileName != null) {
				System.out.println("imprimiendo archivo");
				System.out.println(fileName);
			}

			OutputStream out = null;
			InputStream filecontent = null;
			try {
				out = new FileOutputStream(new File(
						"/Users/davidromero/Desktop/" + itemDTO.getNombre() + "/" + File.separator + fileName));
				filecontent = filePart.getInputStream();

				int read = 0;
				final byte[] bytes = new byte[1024];

				while ((read = filecontent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
				if (filecontent != null) {
					filecontent.close();
				}

			}
		}
		doGet(request, response);
	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}