package ec.edu.epn.controller.multimedia;

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

import ec.edu.epn.model.dto.ItemDTO;
import ec.edu.epn.model.dto.MultimediaDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.servicio.ServiceMultimedia;

/**
 * Servlet implementation class RegistrarMultimedia
 */
@WebServlet("Multimedia/Registrar")
@MultipartConfig
public class RegistrarMultimedia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrarMultimedia() {
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
		UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
		if (usrLogeado == null) {
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);

		} else {
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/multimedia/registrar.jsp")
					.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ItemDTO itm = new ItemDTO();
		int id = Integer.parseInt(request.getParameter("IdItem"));
		itm.setId(id);
		MultimediaDTO mulDTO = new MultimediaDTO();
		mulDTO.setItem(itm);
		System.out.println(request.getContextPath());
		String path = "/Users/davidromero/Documents/workspace/WebAppGR2P1/ConCritic/WebContent/multimedia/";
		File directorio = new File(path + id);
		directorio.mkdir();

		for (int i = 1; i <= 4; i++) {

			Part filePart = request.getPart("inputFile" + i);
			if (filePart == null) {
				i++;
			}
			final String fileName = getFileName(filePart);
			if (fileName != null && fileName.equals("")==false) {
				System.out.println("imprimiendo archivo");
				System.out.println(fileName);
				mulDTO.setPath("/ConCritic/multimedia/" + id + "/" + fileName);
				try {
					ServiceMultimedia sm = new ServiceMultimedia();
					sm.registrarMultimedia(mulDTO);
				} catch (Exception e) {
					e.printStackTrace();
				}

				OutputStream out = null;
				InputStream filecontent = null;
				try {
					out = new FileOutputStream(new File(path + id + "/" + File.separator + fileName));
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
