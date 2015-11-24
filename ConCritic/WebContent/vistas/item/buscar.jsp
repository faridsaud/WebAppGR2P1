<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,ec.edu.epn.model.dto.*,ec.edu.epn.model.servicio.*"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<form method="get">
				<div class="form-group">
					<label for="nombre">Nombre del Item</label> <input name="nombreItm"
						type="text" class="form-control" id="nombreItm">
				</div>
				<div class="form-group">
					<label for="categoria">Categoria</label> <select name="nombreCat"
						class="form-control" id="nombreCat">
						<option value="" selected></option>

						<%
							ServiceCategoria sc = new ServiceCategoria();
							List<CategoriaDTO> listaCategorias = sc.listarCategoriasAll();
							if (listaCategorias == null) {
								listaCategorias = new ArrayList<CategoriaDTO>();
							}
							for (CategoriaDTO cat : listaCategorias) {
						%>
						<option value="<%=cat.getNombre()%>"><%=cat.getNombre()%></option>
						<%
							}
						%>
					</select>
				</div>

				<button type="submit" class="btn btn-default">Buscar</button>
			</form>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<br> <br> <br>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<table class="table">
				<thead>
					<tr>
						<th>Nombre del item</th>
						<th>Calificación</th>
						<th>Acción</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<ItemDTO> listaItems = (List<ItemDTO>) request.getAttribute("listaItems");
						if (listaItems == null) {
							listaItems = new ArrayList<ItemDTO>();
						}
						for (ItemDTO itm : listaItems) {
					%>
					<tr>
						<td>
							<form method="get"
								action="${pageContext.request.contextPath}/Item/Info">
								<button type="submit" class="btn btn-default"
									value="<%=itm.getId()%>" name="idItem"><%=itm.getNombre()%><%=itm.getId()%></button>
							</form>
						</td>
						<td><%=itm.getCalificacion()%></td>
						<td>
							<form method="get"
								action="${pageContext.request.contextPath}/Review/Registrar">
								<button type="submit" class="btn btn-default"
									value="<%=itm.getId()%>" name="idItemReview">
									<span class="glyphicon glyphicon-plus-sign"
										title="Agregar Review"></span>
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