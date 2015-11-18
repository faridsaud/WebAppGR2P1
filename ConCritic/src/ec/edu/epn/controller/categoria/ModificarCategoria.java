package ec.edu.epn.controller.categoria;

import java.io.IOException;

import javax.mail.internet.NewsAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.CategoriaDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.servicio.ServiceCategoria;

/**
 * Servlet implementation class ModificarCategoria
 */
@WebServlet("/Categoria/Modificar")
public class ModificarCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String catModificar=(String)request.getParameter("nombreCatModificar");
		System.out.println(catModificar);
		if(catModificar!=null){
			ServiceCategoria sc=new ServiceCategoria();
			CategoriaDTO catDTOInicial= sc.buscarCategoria(catModificar);
			request.setAttribute("categoriaModificar",catDTOInicial);
		}
		UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
		if(usrLogeado==null){
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);
		}else{
			if(usrLogeado.isAdmin()==false){
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);
			}else{
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/categoria/modificar.jsp").forward(request, response);
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
		if(usrLogeado.isAdmin()){
			CategoriaDTO catDTOFinal= new CategoriaDTO();
			String nombreCat=(String)request.getParameter("nombreCat");
			String descripcionCat=(String)request.getParameter("descripcionCat");
			if(nombreCat==null){
				nombreCat="";
			}
			if(descripcionCat==null){
				descripcionCat="";
			}
			catDTOFinal.setDescripcion(descripcionCat);
			catDTOFinal.setNombre(nombreCat);
			ServiceCategoria sc= new ServiceCategoria();
			try{
			sc.actualizarCategoria(catDTOFinal, catDTOFinal);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);
		
	}

}
