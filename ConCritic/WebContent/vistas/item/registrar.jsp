<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,ec.edu.epn.model.dto.*,ec.edu.epn.model.servicio.*"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
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
	<div class="row">
		<div class="col-xs-12">
			<form method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="nombre">Nombre del Item</label> <input name="nombre"
						type="text" class="form-control" id="nombre">
				</div>

				<div class="form-group">
					<label for="categoria">Categoria</label> 
					<select name="categoria"
						class="form-control" id="categoria">
						
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
					<textarea name="descripcion" class="form-control" rows="5"
						id="descripcion"></textarea>
				</div>
				<!-- 
				<label>A continuación por favor seleccione las imágenes del
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
				<button type="submit" class="btn btn-default">Registrar</button>
			</form>

		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<br> <br> <br>
		</div>
	</div>
</div>

<jsp:include page="/templates/footer.jsp"></jsp:include>