app.config(function ($stateProvider, $urlRouterProvider) {
  console.log("entro a router")
  $urlRouterProvider.otherwise("/home");
  $stateProvider
  .state('home', {
    url: "/home",
    templateUrl: "./html/home.html",
    controller: 'home'
  })
  .state('registrarUsuario', {
    url: "/Usuario/registrar",
    templateUrl: "./html/Usuario/registrar.html",
    controller: 'registrarUsuario'
  })
  .state('iniciarSesion', {
    url: "/Usuario/iniciarSesion",
    templateUrl: "./html/Usuario/iniciarSesion.html",
    controller: 'iniciarSesion'
  })
  .state('cerrarSesion', {
    url: "/Usuario/cerrarSesion",
    templateUrl: "./html/home.html",
    controller: 'cerrarSesion'
  })
  .state('administrarUsuario', {
    url: "/Usuario/administrar",
    templateUrl: "./html/Usuario/administrar.html",
    controller: 'administrarUsuario'
  })
  .state('modificarUsuario', {
    url: "/Usuario/modificar/{email}",
    templateUrl: "./html/Usuario/modificar.html",
    controller: 'modificarUsuario'
  })
  .state('eliminarUsuario', {
    url: "/Usuario/eliminar/{email}",
    templateUrl: "./html/Usuario/administrar.html",
    controller: 'eliminarUsuario'
  })
  .state('registrarReview', {
    url: "/Review/registrar/{idItem}",
    templateUrl: "./html/Review/registrar.html",
    controller: 'registrarReview'
  })

});
