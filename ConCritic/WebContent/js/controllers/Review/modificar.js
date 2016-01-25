app.controller('modificarReview',['$scope','$rootScope','$location','$http','toastr','$stateParams',function($scope,$rootScope,$location,$http,toastr,$stateParams){
  if(!$rootScope.usuarioLogeado){
    $location.path("/home");
  }else{
    $http({
      method:'GET',
      url:"http://localhost:8080/ConCritic/REST/Review/"+$stateParams.idReview,

    }).then(function success(response){
      $scope.reviewBase=response.data;
      $scope.reviewBase.calificacion=$scope.reviewBase.calificacion.toString();
      $scope.review=JSON.parse(JSON.stringify($scope.reviewBase));
      $http({
        method:'GET',
        url:"http://localhost:8080/ConCritic/REST/Multimedia/"+$scope.review.item.id,

      }).then(function success(response){
        $scope.listaMultimedia=response.data;
        console.log($scope.listaMultimedia);
      },
      function error(response){
      });

    },
    function error(response){
    });

    $scope.modificar=function(){
      console.log($scope.review);
      console.log($scope.reviewBase);

      $http({
        method:'PUT',
        url:"http://localhost:8080/ConCritic/REST/Review/",
        data:{
          revDTOInicial:$scope.reviewBase,
          revDTOFinal:$scope.review
        }
      }).then(function success(response){

        console.log("registro de review");
        toastr.success("Exito al modificar la review","Exito");
      },
      function error(response){
        toastr.error("Error al modificar la review","Error");
      });
    };
  }



}]);
