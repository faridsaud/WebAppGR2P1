app.controller('eliminarReview',['$scope','$rootScope','$location','$http','toastr','$stateParams',function($scope,$rootScope,$location,$http,toastr,$stateParams){
  if(!$rootScope.usuarioLogeado){
    $location.path("/home");
  }else{
    $http({
      method:'DELETE',
      url:"http://localhost:8080/ConCritic/REST/Review/"+$stateParams.idReview,
    }).then(function success(response){
      toastr.success("Review eliminada con exito", "Exito");
      $location.path("/home");

    },
    function error(response){
    });

  }



}]);
