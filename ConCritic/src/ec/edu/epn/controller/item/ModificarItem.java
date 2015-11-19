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
 * Servlet implementation class ModificarItem
 */
@WebServlet("/Item/Modificar")
@MultipartConfig
public class ModificarItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarItem() {
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
		int idItemModificar = Integer.parseInt(request.getParameter("idItemModificar"));
		ServiceItem si = new ServiceItem();
		ItemDTO itmDTOInicial = si.buscarItem(idItemModificar);
		request.getSession().setAttribute("itemModificar", itmDTOInicial);

		UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
		if (usrLogeado == null) {
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);

		} else {
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/item/modificar.jsp").forward(request,
					response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean errorCreacion = false;
		UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
		ItemDTO itemDTOInicial = (ItemDTO) request.getSession().getAttribute("itemModificar");
		String nombre = request.getParameter("nombreItm");
		if (usrLogeado == null) {
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);
		} else {
			if (nombre != null) {
				ItemDTO itemDTOFinal = new ItemDTO();
				itemDTOFinal.setNombre(nombre);
				itemDTOFinal.setDescripcion(request.getParameter("descripcionItm"));
				CategoriaDTO catDTO = new CategoriaDTO();
				catDTO.setNombre(request.getParameter("categoriaItm"));
				itemDTOFinal.setCategoria(catDTO);
				itemDTOFinal.setUsuario(usrLogeado);
				try {
					ServiceItem si = new ServiceItem();
					si.actualizarItem(itemDTOInicial, itemDTOFinal);
				} catch (Exception e) {
					e.printStackTrace();
					errorCreacion = true;
				}
				request.setAttribute("errorCreacionItem", errorCreacion);
			}
			/*
			 * for (int i = 1; i <= 4; i++) { Part filePart =
			 * request.getPart("inputFile" + i);
			 * System.out.println(filePart.toString()); final String fileName =
			 * getFileName(filePart); if (fileName != null) {
			 * System.out.println("imprimiendo archivo");
			 * System.out.println(fileName); }
			 * 
			 * OutputStream out = null; InputStream filecontent = null; try {
			 * out = new FileOutputStream( new
			 * File("/Users/davidromero/Desktop/" + nombre + "/" +
			 * File.separator + fileName)); filecontent =
			 * filePart.getInputStream();
			 * 
			 * int read = 0; final byte[] bytes = new byte[1024];
			 * 
			 * while ((read = filecontent.read(bytes)) != -1) { out.write(bytes,
			 * 0, read); } } catch (Exception e) { e.printStackTrace(); }
			 * finally { if (out != null) { out.close(); } if (filecontent !=
			 * null) { filecontent.close(); }
			 * 
			 * } }
			 */
			doGet(request, response);
		}
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
