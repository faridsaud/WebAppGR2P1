app.controller('eliminarUsuario',['$scope','$rootScope','$location','$http','toastr','$stateParams',function($scope,$rootScope,$location,$http,toastr,$stateParams){
  console.log($stateParams.email);
  $http({
    method:'DELETE',
    url:"http://localhost:8080/ConCritic/REST/Usuario/"+$stateParams.email,
  }).then(function success(response){
    $scope.usuario=response.data;
    console.log("usuario"+$scope.usuario);
    $location.path("/home");


  },
  function error(response){
  });


}]);
