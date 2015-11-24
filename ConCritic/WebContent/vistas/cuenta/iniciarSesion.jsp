<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
    <div class="container">
<%Boolean errorLogin=(Boolean)request.getAttribute("errorLogin");
if(errorLogin==null)
	errorLogin=false;
if(errorLogin==true){
 %>
 <script type="text/javascript"> alert("credenciales inválidas")</script>
 <%
}
 %>
      <form method="post">
        <div class="form-group">
          <label for="exampleInputEmail1">Email</label>
          <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email" name="email" required title="lo que tu quieres">
        </div>
        <div class="form-group">
          <label for="exampleInputPassword1">Password</label>
          <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password" title="lo que tu quieres">
        </div>
        <button type="submit" class="btn btn-default">Iniciar Sesión</button>
      </form>
	  <hr>
	  <a href="${pageContext.request.contextPath}/Cuenta/Registrar">No registrado aún? Registrate</a>
      </div>
<jsp:include page="/templates/footer.jsp"></jsp:include>