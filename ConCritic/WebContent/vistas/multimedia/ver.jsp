<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,ec.edu.epn.model.dto.*,ec.edu.epn.model.servicio.*"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<form method="post">
				<div class="form-group">
					<label for="nombreItm">Nombre del Item</label> <select
						name="IdItem" class="form-control" id="IdItem">
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

				<button type="submit" class="btn btn-default">Buscar</button>
			</form>

		</div>
	</div>
	<div class="row">
		<br></br>
		<div class="col-xs-12">

			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
					<li data-target="#myCarousel" data-slide-to="3"></li>
				</ol>

				<!-- Wrapper for slides -->
				<%
					List<MultimediaDTO> listaMultimedia = (List<MultimediaDTO>) request.getAttribute("listaMultimedia");
				%>

				<div class="carousel-inner" role="listbox">
					<%
						int contador = 0;
						if (listaMultimedia != null)
							for (MultimediaDTO mulDTO : listaMultimedia) {
								if (contador == 0) {
					%>
					<div class="item active">
						<img class="img-responsive center-block"
							src="<%=mulDTO.getPath()%>" alt="<%=contador%>" />
					</div>
					<%
						} else {
					%>

					<div class="item">
						<img class="img-responsive center-block"
							src="<%=mulDTO.getPath()%>" alt="<%=contador%>" />
					</div>
					<%
						}
								contador++;
							}
					%>

				</div>

				<!-- Left and right controls -->
				<a class="left carousel-control" href="#myCarousel" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>

		</div>
	</div>
</div>

<jsp:include page="/templates/footer.jsp"></jsp:include>
