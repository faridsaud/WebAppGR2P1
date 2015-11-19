<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<%@ page import="ec.edu.epn.model.dto.*" %>
<div class="container">
	<%
		Boolean errorCreacion = (Boolean) request.getAttribute("errorCreacionCategoria");
		if (errorCreacion == null)
			errorCreacion = false;
		if (errorCreacion == true) {
	%>
	<script type="text/javascript">
		alert("error en la creacion de categoria")
	</script>
	<%
		}
	%>
	<%
	CategoriaDTO catDTO= new CategoriaDTO();
	catDTO=(CategoriaDTO)request.getAttribute("categoriaModificar");
	if(catDTO==null){
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request, response);
	}
%>

	<form method="post">
		<div class="form-group">
			<label for="nombreCat">Nombre de la Categoría</label> <input
				name="nombreCat" type="text" class="form-control" id="nombreCat"
				value=<%=catDTO.getNombre()%> readonly="readonly">
		</div>
		<div class="form-group">
			<label for="descripcionCat">Descripción</label>
			<textarea name="descripcionCat" class="form-control" rows="5"
				id="descripcionCat"><%=catDTO.getDescripcion()%></textarea>
		</div>
		<button type="submit" class="btn btn-default">Actualizar</button>
	</form>
</div>

<jsp:include page="/templates/footer.jsp"></jsp:include>