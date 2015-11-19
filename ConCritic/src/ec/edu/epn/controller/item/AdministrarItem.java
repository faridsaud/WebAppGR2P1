package ec.edu.epn.controller.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.dto.ItemDTO;
import ec.edu.epn.model.dto.UsuarioDTO;
import ec.edu.epn.model.servicio.ServiceItem;

/**
 * Servlet implementation class AdministrarItem
 */
@WebServlet("/Item/Administrar")
public class AdministrarItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministrarItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
		if (usrLogeado == null) {
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);

		} else {
			List<ItemDTO> listaItems = new ArrayList<ItemDTO>();
			ServiceItem si = new ServiceItem();
			String nombreItem=request.getParameter("nombreItm");
			String nombreCat = request.getParameter("nombreCat");
			
			if (nombreItem == null){
				listaItems = si.listarItemsAll();
			}else{
				if(nombreItem.equals("")){
					if (nombreCat == null){
						listaItems = si.listarItemsAll();
					}else{
						if(nombreCat.equals("")){
							listaItems = si.listarItemsAll();		
						}else{
							listaItems=si.listarItemsLikeCat(nombreCat);
						}
					}
				}else{
					if (nombreCat == null){
						listaItems=si.listarItemsLikeItm(nombreItem);
					}else{
						if(nombreCat.equals("")){
							listaItems=si.listarItemsLikeItm(nombreItem);
						}else{
							listaItems=si.listarItemsLike(nombreItem,nombreCat);
						}
					}	
				}
			}
			request.setAttribute("listaItems", listaItems);
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/item/administrar.jsp")
					.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
