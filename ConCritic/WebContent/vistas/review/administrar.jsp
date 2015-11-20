<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<%@ page
	import="java.util.*,ec.edu.epn.model.dto.*,ec.edu.epn.model.servicio.*"%>

<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<form method="get">
				<div class="form-group">
					<label for="nombre">Nombre del Item</label> <input name="nombreItm"
						type="text" class="form-control" id="nombreItm">
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
						<th>Título de la Review</th>
						<th>Acción</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<ReviewDTO> listaReviews = (List<ReviewDTO>) request.getAttribute("listaReviews");
						if (listaReviews == null) {
							listaReviews = new ArrayList<ReviewDTO>();
						}
						for (ReviewDTO revDTO : listaReviews) {
					%>
					<tr>
						<td><%=revDTO.getItem().getNombre()%></td>
						<td><%=revDTO.getCalificacion()%></td>
						<td><%=revDTO.getTitulo()%></td>
						<td>
							<form method="get"
								action="${pageContext.request.contextPath}/Review/Modificar">
								<button type="submit" class="btn btn-default"
									value="<%=revDTO.getId()%>" name="idReviewModificar">
									<span class="glyphicon glyphicon-pencil" title="Modificar review"></span>
								</button>
							</form>
							<form method="post"
								action="${pageContext.request.contextPath}/Review/Eliminar">
								<button type="submit" class="btn btn-default"
									value="<%=revDTO.getId()%>" name="idReviewEliminar">
									<span class="glyphicon glyphicon-remove" title="Eliminar review"></span>
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