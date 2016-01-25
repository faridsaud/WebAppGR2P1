app.controller('cerrarSesion',['$scope','$rootScope','$location','$http','toastr',function($scope,$rootScope,$location,$http,toastr){
  $rootScope.usuarioLogeado=undefined;
  $location.path("/home");


}]);
