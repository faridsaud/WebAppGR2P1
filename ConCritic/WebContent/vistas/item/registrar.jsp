<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,ec.edu.epn.model.dto.*"%>
<jsp:include page="/templates/header.jsp"></jsp:include>

<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<!-- 
			<form method="post">

				<div class="form-group">
					<label for="nombre">Nombre del Item</label> 
					<input name="nombre"
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
					<label for="descripcion">Descripcion</label>
					<textarea name="descripcion" class="form-control" rows="5"
						id="descripcion"></textarea>
				</div>
				
				<button type="submit" class="btn btn-default">Registrar</button>
			</form>
 -->
			<form method="post" enctype="multipart/form-data">
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
					<label for="descripcion">Descripcion</label>
					<textarea name="descripcion" class="form-control" rows="5"
						id="descripcion"></textarea>
				</div>
				<label>A continuacion por favor seleccione las imagenes del item</label>
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

				<button type="submit" class="btn btn-default">Submit</button>
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