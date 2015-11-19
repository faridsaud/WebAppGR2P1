<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,ec.edu.epn.model.dto.*"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<div class="container">


	<div class="row">
		<div class="col-xs-12">
			<form method="get">
				<div class="form-group">
					<label for="nombreCat">Nombre de la Categoría</label> 
					<input name="nombreCat"
						type="text" class="form-control" id="nombreCat">
				</div>

				<button type="submit" class="btn btn-default">Buscar</button>
			</form>

		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<br> <br>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<table class="table">
				<thead>
					<tr>
						<th>Nombre de la Categoría</th>
						<th>Acción</th>

					</tr>
				</thead>
				<tbody>
					<%
						List<CategoriaDTO> listaCategorias = (List<CategoriaDTO>) request.getAttribute("listaCategorias");
						if(listaCategorias==null){
							listaCategorias= new ArrayList<CategoriaDTO>();
						}
						for (CategoriaDTO cat : listaCategorias) {
					%>
					<tr>
						<td><%=cat.getNombre()%></td>
						<td>
							<form method="get"
								action="${pageContext.request.contextPath}/Categoria/Modificar">
								<button type="submit" class="btn btn-default"
									value="<%=cat.getNombre()%>" name="nombreCatModificar">
									<span class="glyphicon glyphicon-pencil"
										title="Modificar categoria"></span>
								</button>
							</form>
							<form method="post"
								action="${pageContext.request.contextPath}/Categoria/Eliminar">
								<button type="submit" class="btn btn-default"
									value="<%=cat.getNombre()%>" name="nombreCatEliminar">
									<span class="glyphicon glyphicon-remove"
										title="Eliminar categoria"></span>
								</button>
							</form>
						</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>

		</div>
	</div>


</div>

<jsp:include page="/templates/footer.jsp"></jsp:include>
