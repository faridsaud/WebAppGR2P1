package ec.edu.epn.controller.categoria;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.CategoriaDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.servicio.ServiceCategoria;

/**
 * Servlet implementation class RegistrarCategoria
 */
@WebServlet("/Categoria/Registrar")
public class RegistrarCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
		if(usrLogeado==null){
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);
			
		}else{
			if(usrLogeado.isAdmin()==true){
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/categoria/registrar.jsp").forward(request, response);
			}else{
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);
						
			}
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean errorCreacion=false;
		UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
		if(usrLogeado==null){
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);
			
		}else{
			if(usrLogeado.isAdmin()==true){
				String categoria=request.getParameter("nombreCat");
				String descripcion=request.getParameter("descripcionCat");
				if(categoria!=null){
					ServiceCategoria sc=new ServiceCategoria();
					CategoriaDTO catDTO=new CategoriaDTO();
					catDTO.setNombre(categoria);
					if(descripcion==null){
						descripcion="";
					}
					catDTO.setDescripcion(descripcion);
					try{
					sc.registrarCategoria(catDTO);
					}catch(Exception e){
						e.printStackTrace();
						errorCreacion=true;
					}
					request.setAttribute("errorCreacionCategoria", errorCreacion);
				}
				doGet(request, response);
			}else{
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);
						
			}
			
		}
		
	}

}
