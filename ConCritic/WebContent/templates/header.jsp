<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrapT1.css">
<title>Consumers Critics</title>

</head>
<body>
	<div class="row">

		<div class="col-xs-12">
			<nav class="navbar navbar-inverse"> <!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/Home">Home</a>

			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<!-- -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Items<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/Item/Buscar">Buscar</a></li>
							<li><a
								href="${pageContext.request.contextPath}/Item/Registrar">Registrar</a></li>
							<li><a
								href="${pageContext.request.contextPath}/Item/Administrar">Administrar</a></li>
						</ul></li>
					<!-- -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Reviews<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="../Item/buscar.html">Registrar</a></li>
							<li><a href="../Review/administrar.html">Administrar</a></li>
						</ul></li>

					<!-- -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Multimedia<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/Multimedia/Ver">Ver</a></li>
							<li><a
								href="${pageContext.request.contextPath}/Multimedia/Registrar">Registrar</a></li>
							<li><a
								href="${pageContext.request.contextPath}/Multimedia/Administrar">Administrar</a></li>
						</ul></li>

					<!-- -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Categorias<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/Categoria/Registrar">Registrar</a></li>
							<li><a
								href="${pageContext.request.contextPath}/Categoria/Administrar">Administrar</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Cuenta<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/Cuenta/IniciarSesion">Iniciar
									Sesión</a></li>
							<li><a
								href="${pageContext.request.contextPath}/Cuenta/CerrarSesion">Cerrar
									Sesión</a></li>
							<li><a
								href="${pageContext.request.contextPath}/Cuenta/Administrar">Administrar</a></li>
						</ul></li>

				</ul>
			</div>
			<!-- /.navbar-collapse --> </nav>
		</div>

	</div>