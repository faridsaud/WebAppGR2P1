<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<%@ page import="java.util.*,ec.edu.epn.model.dto.*,ec.edu.epn.model.servicio.*"%>
<div class="container">
	<%
		Boolean errorCreacion = (Boolean) request.getAttribute("errorCreacionItem");
		if (errorCreacion == null)
			errorCreacion = false;
		if (errorCreacion == true) {
	%>
	<script type="text/javascript">
		alert("error en la creacion de item")
	</script>
	<%
		}
	%>
<%
	ItemDTO itmDTO= new ItemDTO();
	itmDTO=(ItemDTO)request.getSession().getAttribute("itemModificar");
	if(itmDTO==null){
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);
	}
%>
	 <form method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="nombre">Nombre del Item</label> <input name="nombreItm"
				type="text" class="form-control" id="nombreItm" value=<%=itmDTO.getNombre()%>>
		</div>

		<div class="form-group">
			<label for="categoria">Categoria</label> <select name="categoriaItm"
				class="form-control" id="categoriaItm">
				<option value="<%=itmDTO.getCategoria().getNombre()%>" selected><%=itmDTO.getCategoria().getNombre()%></option>
				<%
						ServiceCategoria sc = new ServiceCategoria();
						List<CategoriaDTO> listaCategorias = sc.listarCategoriasAll();
						if(listaCategorias==null){
							listaCategorias= new ArrayList<CategoriaDTO>();
						}
						for (CategoriaDTO cat : listaCategorias) {
					%>
				<option value="<%=cat.getNombre()%>"><%=cat.getNombre()%></option>
				<%}%>
			</select>
		</div>

		<div class="form-group">
			<label for="descripcion">Descripcion</label>
			<textarea name="descripcionItm" class="form-control" rows="5"
				id="descripcionItm"><%=itmDTO.getDescripcion()%></textarea>
		</div>
		
		<!-- 
		<label>A continuacion por favor seleccione las imagenes del
			item</label>
		<div class="form-group">
			<label for="inputFile">Seleccionar archivo 1</label> <input
				type="file" id="inputFile" name="inputFile1">
		</div>
		<div class="form-group">
			<label for="inputFile">Seleccionar archivo 2</label> <input
				type="file" id="inputFile" name="inputFile2">
		</div>
		<div class="form-group">
			<label for="inputFile">Seleccionar archivo 3</label> <input
				type="file" id="inputFile" name="inputFile3">
		</div>
		<div class="form-group">
			<label for="inputFile">Seleccionar archivo 4</label> <input
				type="file" id="inputFile" name="inputFile4">
		</div>
		 -->
		<button type="submit" class="btn btn-default">Modificar</button>
	</form>
</div>

<jsp:include page="/templates/footer.jsp"></jsp:include>