<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<div class="container">
	<%
		Boolean errorCreacion = (Boolean) request.getAttribute("errorCreacionCategoria");
		if (errorCreacion == null)
			errorCreacion = false;
		if (errorCreacion == true) {
	%>
	<script type="text/javascript">
		alert("error en la creacion de la categoria")
	</script>
	<%
		}
	%>

	  <form method="post">
            <div class="form-group">
              <label for="nombreCat">Nombre de la Categoría</label>
              <input name="nombreCat" type="text" class="form-control" id="nombreCat" >
            </div>
              <div class="form-group">
                <label for="descripcionCat">Descripción</label>
                <textarea name="descripcionCat" class="form-control" rows="5" id="descripcionCat"></textarea>
              </div>
            <button type="submit" class="btn btn-default">Registrar</button>
          </form>
</div>

<jsp:include page="/templates/footer.jsp"></jsp:include>