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
					<label for="nombreItm">Nombre del Item</label> <select
						name="idItem" class="form-control" id="idItem">
						<%
							UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
							ServiceItem si = new ServiceItem();
							List<ItemDTO> listaItems = new ArrayList<ItemDTO>();
							if(usrLogeado.isAdmin()){
								listaItems = si.listarItemsAll();
							}else{
								listaItems = si.listarItemsByUsr(usrLogeado);
							}
							if (listaItems == null) {
								listaItems = new ArrayList<ItemDTO>();
							}
							for (ItemDTO itm : listaItems) {
						%>
						<option value="<%=itm.getId()%>"><%=itm.getNombre()%></option>
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
						<th>Path</th>
						<th>Acción</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<MultimediaDTO> listaMultimedia = (List<MultimediaDTO>) request.getAttribute("listaMultimedia");
						if (listaMultimedia == null) {
							listaMultimedia = new ArrayList<MultimediaDTO>();
						}
						for (MultimediaDTO mul : listaMultimedia) {
					%>
					<tr>
						<td><%=mul.getPath()%></td>
						<td>
							<form method="post"
								action="${pageContext.request.contextPath}/Multimedia/Eliminar">
								<button type="submit" class="btn btn-default"
									value="<%=mul.getId()%>" name="idMultimediaEliminar">
									<span class="glyphicon glyphicon-remove" title="Eliminar item"></span>
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