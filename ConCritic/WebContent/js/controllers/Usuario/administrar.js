app.controller('administrarUsuario',['$scope','$rootScope','$location','$http','toastr',function($scope,$rootScope,$location,$http,toastr){
  if(!$rootScope.usuarioLogeado){
    $location.path("/home");
  }else{
    $scope.emailBuscar="";
    console.log($rootScope.usuarioLogeado);
    $scope.buscar=function(){
      $http({
        method:'POST',
        url:"http://localhost:8080/ConCritic/REST/Usuario/listar",
        data:{
          email:$rootScope.usuarioLogeado.email,
          password:$rootScope.usuarioLogeado.password,
          nombre:$rootScope.usuarioLogeado.nombre,
          apellido:$rootScope.usuarioLogeado.apellido,
          fechaNacimiento:"2014-12-12",
          pais:$rootScope.usuarioLogeado.pais,
          admin:$rootScope.usuarioLogeado.admin,
          estado:$rootScope.usuarioLogeado.estado,
          emailBuscar:$scope.emailBuscar
        }
      }).then(function success(response){
        $scope.listaUsuarios=response.data;
      },
      function error(response){
      });
    };
  }



}]);
