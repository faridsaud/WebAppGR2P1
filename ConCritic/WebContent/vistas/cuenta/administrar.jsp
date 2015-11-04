<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,ec.edu.epn.model.dto.*"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<div class="container">


	<div class="row">
		<div class="col-xs-12">
			<form method="get">

				<div class="form-group">
					<label for="correo">Correo Eletr�nico</label> <input name="email"
						type="text" class="form-control" id="email">
				</div>

				<button type="submit" class="btn btn-default">Buscar</button>
			</form>

		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<br> <br>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<table class="table">
				<thead>
					<tr>
						<th>Correo Eletr�nico</th>
						<th>Acci�n</th>

					</tr>
				</thead>
				<tbody>
					<%
						List<UsuarioDTO> listaUsuarios = (List<UsuarioDTO>) request.getAttribute("listaUsuariosAdministrar");
						if(listaUsuarios==null){
							listaUsuarios= new ArrayList<UsuarioDTO>();
						}
						for (UsuarioDTO usr : listaUsuarios) {
					%>
					<tr>
						<td><%=usr.getEmail()%></td>
						<td>
							<form method="get"
								action="${pageContext.request.contextPath}/Cuenta/Modificar">
								<button type="submit" class="btn btn-default"
									value="<%=usr.getEmail()%>" name="emailModificar">
									<span class="glyphicon glyphicon-pencil"
										title="Modificar cuenta"></span>
								</button>
							</form>
							<form method="post"
								action="${pageContext.request.contextPath}/Cuenta/Eliminar">
								<button type="submit" class="btn btn-default"
									value="<%=usr.getEmail()%>" name="emailEliminar">
									<span class="glyphicon glyphicon-remove"
										title="Eliminar cuenta"></span>
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
