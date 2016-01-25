app.controller('administrarReview',['$scope','$rootScope','$location','$http','toastr','$stateParams',function($scope,$rootScope,$location,$http,toastr,$stateParams){
  if(!$rootScope.usuarioLogeado){
    $location.path("/home");
  }else{

    if($rootScope.usuarioLogeado.admin){
      $http({
        method:'GET',
        url:"http://localhost:8080/ConCritic/REST/Review/",

      }).then(function success(response){
        $scope.listaReview=response.data
      },
      function error(response){
      });
    }else{

      $http({
        method:'GET',
        url:"http://localhost:8080/ConCritic/REST/Review/buscarByUser/"+$rootScope.usuarioLogeado.email,

      }).then(function success(response){
        $scope.listaReview=response.data
      },
      function error(response){
      });

    }

    $scope.buscar=function(){

      $http({
        method:'GET',
        url:"http://localhost:8080/ConCritic/REST/Review/buscarLikeItem/"+$scope.item.nombre+"/"+$rootScope.usuarioLogeado.email,

      }).then(function success(response){
        $scope.listaReview=response.data
      },
      function error(response){
      });
    }
  }




}]);
