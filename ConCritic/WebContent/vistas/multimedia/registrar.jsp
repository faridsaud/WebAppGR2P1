<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,ec.edu.epn.model.dto.*,ec.edu.epn.model.servicio.*"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<form method="post" enctype="multipart/form-data">

				<div class="form-group">
					<label for="nombreItm">Nombre del Item</label> <select
						name="IdItem" class="form-control" id="nombreItm">
						<%
							UsuarioDTO usrLogeado = (UsuarioDTO) request.getSession().getAttribute("usuarioLogeado");
							ServiceItem si = new ServiceItem();
							List<ItemDTO> listaItems = new ArrayList<ItemDTO>();
							if (usrLogeado.isAdmin()) {
								listaItems = si.listarItemsAll();
							} else {
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