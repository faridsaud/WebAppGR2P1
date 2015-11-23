<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<%@ page
	import="java.util.*,ec.edu.epn.model.dto.*,ec.edu.epn.model.servicio.*"%>
<div class="container">

	<%
		ItemDTO itmDTO = new ItemDTO();
		int idItem = Integer.parseInt(request.getParameter("idItem"));
		ServiceItem si = new ServiceItem();
		itmDTO = si.buscarItem(idItem);
		if (itmDTO == null) {
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/home.jsp").forward(request,
					response);
		}
	%>
	<form>
		<div class="form-group">
			<label for="nombre">Nombre del Item</label> <input name="nombreItm"
				type="text" class="form-control" id="nombreItm"
				value="<%=itmDTO.getNombre()%>" readonly="readonly">
		</div>

		<div class="form-group">
			<label for="categoria">Categoria</label> <select name="categoriaItm"
				class="form-control" id="categoriaItm" disabled="disabled">
				<option value="<%=itmDTO.getCategoria().getNombre()%>" selected><%=itmDTO.getCategoria().getNombre()%></option>
			</select>
		</div>

		<div class="form-group">
			<label for="descripcion">Descripcion</label>
			<textarea name="descripcionItm" class="form-control" rows="5"
				id="descripcionItm" readonly="readonly"><%=itmDTO.getDescripcion()%></textarea>
		</div>
	</form>

	<div class="row">
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
					List<MultimediaDTO> listaMultimedia = (List<MultimediaDTO>) request.getAttribute("listaMultimediaInfo");
				%>

				<div class="carousel-inner" role="listbox">
					<%
						int contador = 0;
						if (listaMultimedia != null)
							for (MultimediaDTO mulDTO : listaMultimedia) {
								if (contador == 0) {
					%>
					<div class="item active">
						<img class="img-responsive center-block" src="<%=mulDTO.getPath()%>" alt="<%=contador%>"
							 />
					</div>
					<%
						} else {
					%>

					<div class="item">
						<img class="img-responsive center-block" src="<%=mulDTO.getPath()%>" alt="<%=contador%>"
							 />
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