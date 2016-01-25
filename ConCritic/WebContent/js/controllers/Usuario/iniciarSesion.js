app.controller('iniciarSesion',['$scope','$rootScope','$location','$http','toastr',function($scope,$rootScope,$location,$http,toastr){
  if($rootScope.usuarioLogeado){
    $location.path("/home");
  }else{
    $scope.usuario= new Object();
    $scope.iniciarSesion= function(){
      $http({
        method:'POST',
        url:'http://localhost:8080/ConCritic/REST/Usuario/buscar',
        data:{
          email:$scope.usuario.email,
          password:$scope.usuario.password
        }
      }).then(function sucess(response){
        if(response.data.length==0){
          toastr.error("Error al logear usuario","Error");
        }else{
          $rootScope.usuarioLogeado=response.data;
          toastr.success("Usuario logeado con exito", "Exito");
          $location.path("/home");
        }
      }, function error(response){
        toastr.error("Error al logear usuario","Error");
      })
    }
  }
}]);
