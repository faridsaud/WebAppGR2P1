app.controller('registrarReview',['$scope','$rootScope','$location','$http','toastr','$stateParams',function($scope,$rootScope,$location,$http,toastr,$stateParams){
  if(!$rootScope.usuarioLogeado){
    $location.path("/home");
  }else{
    $http({
      method:'GET',
      url:"http://localhost:8080/ConCritic/REST/Item/"+$stateParams.idItem,

    }).then(function success(response){
      $scope.item=response.data;
      console.log($scope.item);
      $http({
        method:'GET',
        url:"http://localhost:8080/ConCritic/REST/Multimedia/"+$scope.item.id,

      }).then(function success(response){
        $scope.listaMultimedia=response.data;
        console.log($scope.listaMultimedia);
      },
      function error(response){
      });
    },
    function error(response){
    });

    console.log($rootScope.usuarioLogeado);
    $scope.registrar=function(){
      $http({
        method:'POST',
        url:"http://localhost:8080/ConCritic/REST/Review/",
        data:{
          calificacion:$scope.review.calificacion,
          comentario:$scope.review.comentario,
          fecha:$scope.review.fecha,
          titulo:$scope.review.titulo,
          item:{
            id:$scope.item.id
          },
          usuario:{
            email:$rootScope.usuarioLogeado.email
          }
        }
      }).then(function success(response){
        console.log("registro de review");
        toastr.success("Exito al registrar la review","Exito");
      },
      function error(response){
        toastr.error("Error al registrar la review","Error");
      });
    };
  }



}]);
