<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,ec.edu.epn.model.dto.*"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<form method="get">
				<div class="form-group">
					<label for="nombre">Nombre del Item</label> <input name="nombre"
						type="text" class="form-control" id="nombre">
				</div>
				<div class="form-group">
					<label for="categoria">Categoria</label> <select name="categoria"
						class="form-control" id="categoria">
						<option value="cat1">categoria 1</option>
						<option value="cat2">categoria 2</option>
						<option value="cat3">categoria 3</option>
					</select>
				</div>

				<div class="form-group">
					<label for="tag">Tags</label> <input name="tag" type="text"
						class="form-control" id="tag">
				</div>

				<button type="submit" class="btn btn-default">Buscar</button>
			</form>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<br>
			<br>
			<br>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<table class="table">
				<thead>
					<tr>
						<th>Nombre del item</th>
						<th>Calificacion</th>
						<th>Accion</th>
					</tr>
				</thead>
				<tbody>
				<%	List<ItemDTO> listaItems = (List<ItemDTO>)request.getAttribute("listadoItems");
					if(listaItems==null){
						listaItems= new ArrayList<ItemDTO>();
					}
					for (ItemDTO itm : listaItems) {
				%>
					<tr>
						<td><%=itm.getNombre()%></td>
						<td><%=itm.getCalificacion()%></td>
						<td>
							<form method="get"
								action="${pageContext.request.contextPath}/Item/Modificar">
								<button type="submit" class="btn btn-default"
									value="<%=itm.getId()%>" name="itemIDModificar">
									<span class="glyphicon glyphicon-pencil"
										title="Modificar item"></span>
								</button>
							</form>
							<form method="post"
								action="${pageContext.request.contextPath}/Item/Eliminar">
								<button type="submit" class="btn btn-default"
									value="<%=itm.getId()%>" name="itemIDEliminar">
									<span class="glyphicon glyphicon-remove"
										title="Eliminar item"></span>
								</button>
							</form>
						</td>
					</tr>
					<%}%>
				</tbody>
			</table>

		</div>
	</div>


</div>
<jsp:include page="/templates/footer.jsp"></jsp:include>