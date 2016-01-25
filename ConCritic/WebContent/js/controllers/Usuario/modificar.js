app.controller('modificarUsuario',['$scope','$rootScope','$location','$http','toastr','$stateParams',function($scope,$rootScope,$location,$http,toastr,$stateParams){
  console.log($stateParams.email);
  $http({
    method:'GET',
    url:"http://localhost:8080/ConCritic/REST/Usuario/"+$stateParams.email,
  }).then(function success(response){
    $scope.usuarioBase=response.data;
    $scope.usuario=$scope.usuarioBase;
  },
  function error(response){
  });

  $scope.modificar=function(){
    $http({
      method:'PUT',
      url:"http://localhost:8080/ConCritic/REST/Usuario/",
      data:{
        usrDTOInicial:$scope.usuarioBase,
        usrDTOFinal:$scope.usuario
      }
    }).then(function success(response){
      if(response.data.length==0){
        toastr.error("Error al modificar el usuario");
      }else{
        toastr.success("Usuario modificado con exito","Exito");
      }
    },
    function error(response){
    });
  }
}]);
