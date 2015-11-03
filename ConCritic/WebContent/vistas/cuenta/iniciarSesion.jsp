<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
    <div class="container">

      <form method="post">
        <div class="form-group">
          <label for="exampleInputEmail1">Email</label>
          <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email" name="email" required="true">
        </div>
        <div class="form-group">
          <label for="exampleInputPassword1">Password</label>
          <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password" required="true">
        </div>
        <button type="submit" class="btn btn-default">Iniciar Sesión</button>
      </form>

      <div class="row">

        <div class="col-xs-12">
          <p><a href="./registrar.html">No registrado aún? Registrate</a></p>
        </div>
      </div>
    </div>
<jsp:include page="/templates/footer.jsp"></jsp:include>