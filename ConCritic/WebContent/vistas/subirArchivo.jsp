<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<div class="container">

	<form method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="inputFile">Seleccionar archivo</label> <input type="file"
				id="inputFile" name="inputFile">
			<p class="help-block">Example block-level help text here.</p>
		</div>

		<button type="submit" class="btn btn-default">Submit</button>
	</form>
</div>
<jsp:include page="/templates/footer.jsp"></jsp:include>
